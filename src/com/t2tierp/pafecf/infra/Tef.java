/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Classe de controle do TEF.</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM</p>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 *       The author may be contacted at:
 *           t2ti.com@gmail.com</p>
 *
 * @author Albert Eije (T2Ti.COM)
 * @version 1.0
 */
package com.t2tierp.pafecf.infra;

import com.t2tierp.pafecf.view.Caixa;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tef {

    //Variáveis de controle
    private static String nomeRede, NSU, identificacao, data, hora, valor;
    public static boolean relatorioGerencial;
    private static boolean transacaoAprovada;
    private static Integer contaLinhaEOF;
    private static String conteudoArquivo, linhaArquivo, campoArquivo, linha;
    //Arquivos de controle
    public static File arquivoIntPos001 = new File(System.getProperty("user.dir") + "\\intpos.001");
    public static File arquivoImprimeTxt = new File(System.getProperty("user.dir") + "\\imprime.txt");
    public static File arquivoTefTxt = new File(System.getProperty("user.dir") + "\\tef.txt");
    public static File arquivoPendenteTxt = new File(System.getProperty("user.dir") + "\\pendente.txt");
    //Arquivos para comunicação com o GP
    public static File reqIntPos001 = new File("c:\\tef_dial\\req\\intpos.001");
    public static File respAtivo001 = new File("c:\\tef_dial\\resp\\ativo.001");
    public static File respIntPosSts = new File("c:\\tef_dial\\resp\\intpos.sts");
    public static File respIntPos001 = new File("c:\\tef_dial\\resp\\intpos.001");

    /**
     * Realiza a transação TEF.
     * @param pIdentificacao identificação da transação
     * @param pNumeroCupom número do cupom fiscal - COO
     * @param pValorPago valor da transação
     * @param pNumeroTransacao número da transação
     * @return 0 = GP INATIVO / 1 = OK / -1 = FAIL
     */
    public static Integer realizaTransacao(String pIdentificacao, String pNumeroCupom, String pValorPago, Integer pNumeroTransacao) {
        Integer result, numeroLinhas;
        pValorPago = ajustaValorCartao(pValorPago);

        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists()) {
            respIntPos001.delete();
        }

        try {
            //Formata arquivo INTPOS.001 para solicitar a transação TEF
            conteudoArquivo = "";
            conteudoArquivo = "000-000 = CRT" + (char) 13 + (char) 10
                    + "001-000 = " + pIdentificacao + (char) 13 + (char) 10
                    + "002-000 = " + pNumeroCupom + (char) 13 + (char) 10
                    + "003-000 = " + pValorPago + (char) 13 + (char) 10
                    + "999-999 = 0";

            FileWriter gravar = new FileWriter(arquivoIntPos001);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(conteudoArquivo);
            saida.close();
            gravar.close();

            copyFile(arquivoIntPos001, reqIntPos001);
            arquivoIntPos001.delete();
        } catch (IOException e) {
            e.getMessage();
        }
        if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {
            new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").delete();
        }

        result = -2;

        for (int tentativa = 1; tentativa <= 7; tentativa++) {
            try {
                // Verifica se o Gerenciador Padrão recebeu o arquivo INTPOS.001
                if (respIntPosSts.exists()) {
                    linhaArquivo = "";
                    linha = "";

                    while (result == -2) {
                        if (respIntPos001.exists()) {
                            try {
                                FileReader leitura = new FileReader(respIntPos001);
                                BufferedReader entrada = new BufferedReader(leitura);

                                while ((linhaArquivo = entrada.readLine()) != null) {
                                    campoArquivo = linhaArquivo.substring(0, 3);
                                    switch (Integer.parseInt(campoArquivo)) {
                                        case 1: //verifica se o campo de identificação é o mesmo do solicitado
                                            if (!linhaArquivo.substring(10, linhaArquivo.length()).equals(pIdentificacao)) {
                                                entrada.close();
                                                leitura.close();
                                                break;
                                            }
                                            break;
                                        case 9: //verifica se a transação foi aprovada
                                            if (linhaArquivo.substring(10, linhaArquivo.length()).equals("0")) {
                                                transacaoAprovada = true;
                                                result = 1;
                                            }
                                            if (!linhaArquivo.substring(10, linhaArquivo.length()).equals("0")) {
                                                transacaoAprovada = false;
                                                result = -1;
                                            }
                                            break;
                                        case 28: //verifica se existem linhas para serem impressas
                                            if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                                                /*
                                                O arquivo INTPOS.001 é copiado temporariamente. Isso ocorre para cadas transação.
                                                Caso a transação necessite ser cancelada, as informações estarão neste arquivo.
                                                 */
                                                copyFile(respIntPos001, new File("c:\\tef_dial\\resp\\intpos" + pNumeroTransacao + ".001"));
                                                result = 1;

                                                //Armazena o número de linhas para a impressão da via única
                                                numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                                                //Formata o arquivo para impressão da via única
                                                linha = linha + (char) 13 + (char) 10;

                                                for (int i = 1; i <= numeroLinhas; i++) {
                                                    linhaArquivo = entrada.readLine();
                                                    if (linhaArquivo.substring(0, 3).equals("029")) {
                                                        linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                                                    }
                                                }
                                                linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                                                        + " . . . . . . . . . . . . . . . . . . . . . . . . "
                                                        + (char) 13 + (char) 10 + (char) 13 + (char) 10;
                                            }
                                            break;
                                        case 30: //Se o campo for 030 exibe uma mensagem para o operador
                                            if (!linha.equals("")) {
                                                JOptionPane.showMessageDialog(null, linhaArquivo.substring(10, linhaArquivo.length()), "Mensagem para o operador", 2);
                                            } else {
                                                while (!respIntPosSts.exists()) {
                                                    Thread.sleep(1000);
                                                }
                                                if (reqIntPos001.exists()) {
                                                    reqIntPos001.delete();
                                                }
                                                JOptionPane.showMessageDialog(null, linhaArquivo.substring(10, linhaArquivo.length()), "Mensagem para o operador", 2);
                                                result = -1;
                                            }
                                            break;
                                        case 712: //verifica se existem linhas para serem impressas na via do cliente
                                            if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                                                //Armazena o número de linhas para a impressão da via do cliente
                                                numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                                                //Formata o arquivo para impressão da via do cliente
                                                linha = linha + (char) 13 + (char) 10;

                                                for (int i = 1; i <= numeroLinhas; i++) {
                                                    linhaArquivo = entrada.readLine();
                                                    if (linhaArquivo.substring(0, 3).equals("713")) {
                                                        linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                                                    }
                                                }
                                                linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                                                        + " . . . . . . . . . . . . . . . . . . . . . . . . "
                                                        + (char) 13 + (char) 10 + (char) 13 + (char) 10;
                                            }
                                            break;
                                        case 714: //Verifica se existem linhas para serem impressas na via do estabelecimento
                                            if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                                                //Armazena o número de linhas para a impressão da via do estabelecimento
                                                numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                                                //Formata o arquivo para impressão da via do estabelecimento
                                                linha = linha + (char) 13 + (char) 10;

                                                for (int i = 1; i <= numeroLinhas; i++) {
                                                    linhaArquivo = entrada.readLine();
                                                    if (linhaArquivo.substring(0, 3).equals("715")) {
                                                        linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                                                    }
                                                }
                                                linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                                                        + " . . . . . . . . . . . . . . . . . . . . . . . . "
                                                        + (char) 13 + (char) 10 + (char) 13 + (char) 10;
                                            }
                                            break;
                                    }
                                    if (linhaArquivo == null) { //Chegou no fim do arquivo
                                        break;
                                    }
                                } //Fim while not EOF
                                entrada.close();
                                leitura.close();
                            } catch (IOException e) {
                                e.getMessage();
                            }
                        } //Fim File exists
                    } //Fim while result = -2

                    //Cria o arquivo temporário IMPRIME[transacao].txt com a imagem do comprovante.
                    if (!linha.equals("")) {
                        try {
                            FileWriter gravar = new FileWriter(new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt"));
                            PrintWriter saida = new PrintWriter(gravar);
                            saida.println(linha);
                            saida.close();
                            gravar.close();
                            break;
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    }
                } //Fim do If RespIntPOS.sts
                Thread.sleep(1000);

                //Caso o arquivo INTPOS.sts não retorne em 7 segundos exibe mensagem para o operador
                if (tentativa == 7) {
                    if (reqIntPos001.exists()) {
                        reqIntPos001.delete();
                        JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                        result = 0;
                        break;
                    }
                }
                if ((result == 0) || (result == -1)) {
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Tef.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //Fim do for tentativa

        //Se tudo ocorrer bem cria o arquivo PENDENTE.txt
        if (result == 1) {
            result = 1;
            try {
                FileWriter gravar = new FileWriter(arquivoPendenteTxt);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(pNumeroTransacao);
                saida.close();
                gravar.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }

        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists()) {
            respIntPos001.delete();
        }

        return result;
    }

    /**
     * Ajusta o valor para enviar para o GP.
     * @param pValor valor do pagamento
     * @return valor formatado (Exemplo: 10000 para 100,00
     */
    public static String ajustaValorCartao(String pValor) {
        String cValorCartao = pValor.substring(0, pValor.indexOf(","))
                + pValor.substring(pValor.indexOf(",") + 1, pValor.length());
        return cValorCartao;
    }

    /**
     * Cópia de arquivos por Stream.
     * @param pOrigem arquivo de origem.
     * @param pDestino arquivo de destino.
     * @return nenhum.
     */
    public static void copyFile(File pOrigem, File pDestino) throws IOException {
        InputStream entradaCopy = new FileInputStream(pOrigem);
        OutputStream saidaCopy = new FileOutputStream(pDestino);

        // Transfere bytes da entrada para saida
        byte[] buf = new byte[1024];
        int len;
        while ((len = entradaCopy.read(buf)) > 0) {
            saidaCopy.write(buf, 0, len);
        }
        entradaCopy.close();
        saidaCopy.close();
    }

    /**
     * Cria uma identificação inicial para a transação.
     * @return String com o numero da identificação no formato (HHMMSS)
     */
    public static String novaIdentificacao() {
        SimpleDateFormat formHora = new SimpleDateFormat("HHmmss");
        String identificacao = formHora.format(new Date());
        return identificacao;
    }

    /**
     * Verifica se o GP está ativo.
     * @return true = Ativo | false = Inativo.
     */
    public static boolean verificaGerenciadorPadrao() {
        boolean result = false;

        if (arquivoIntPos001.exists()) {
            arquivoIntPos001.delete();
        }

        try {
            conteudoArquivo = "000-000 = ATV" + (char) 13 + (char) 10
                    + "001-000 = " + novaIdentificacao() + (char) 13 + (char) 10
                    + "999-999 = 0";

            FileWriter gravar = new FileWriter(arquivoIntPos001);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(conteudoArquivo);
            saida.close();
            gravar.close();

            copyFile(arquivoIntPos001, reqIntPos001);
            arquivoIntPos001.delete();
        } catch (IOException e) {
            e.getMessage();
        }

        //tenta sete vezes
        for (int tentativa = 1; tentativa <= 7; tentativa++) {
            try {
                if ((respAtivo001.exists()) || (respIntPosSts.exists())) {
                    //GP ativo
                    result = true;
                    break;
                }
                Thread.sleep(1000);
                if (tentativa == 7) {
                    result = false;
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Tef.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    /**
     * Realiza a impressão da Transação TEF.
     * @param pFormaPagamento forma de pagamento
     * @param pValorPago valor da forma de pagamento
     * @param pNumeroCupom número do cupom fiscal - COO
     * @param pIdentificacao identificação da transação
     * @param pNumeroTransacao número da transação
     * @return true ou false
     */
    public static boolean imprimeTransacao(String pFormaPagamento, String pValorPago, String pNumeroCupom, String pIdentificacao, Integer pNumeroTransacao) {
        boolean result = false;


        /*
         * Criação do arquivo TEF.TXT. Vamos utilizar esse arquivo caso ocorra
         * queda de energia para cancelar a transação.
         */
        try {
            FileWriter gravar = new FileWriter(arquivoTefTxt);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(pNumeroTransacao);
            saida.close();
            gravar.close();
            result = true;

            if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {

                //TODO : qual a melhor estratégia para travar o teclado e mouse neste momento?
                if (!relatorioGerencial) {
                    Caixa.ACBrECF.abreCupomVinculado(pNumeroCupom, pFormaPagamento, Double.valueOf(pValorPago));
                }

                FileReader ler = new FileReader(new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt"));
                BufferedReader entrada = new BufferedReader(ler);

                linha = "";

                while ((linha = entrada.readLine()) != null) {
                    if (!relatorioGerencial) {
                        Caixa.ACBrECF.linhaCupomVinculado(linha + (char) 13 + (char) 10);
                    } else {
                        Caixa.ACBrECF.linhaRelatorioGerencial(linha + (char) 13 + (char) 10, 0);
                    }
                    //TODO : Devemos controlar se há erro de comunicação com a impressora? Como?
                }
                entrada.close();
                ler.close();
                //TODO : qual a melhor estratégia para destravar o teclado e mouse neste momento?
                Caixa.ACBrECF.fechaRelatorio();
            }
        } catch (IOException e) {
            e.getMessage();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Confirma a Transação TEF.
     * @param pNumeroTransacao número da transação
     * @return NSU
     */
    public static String confirmaTransacao(Integer pNumeroTransacao) {
        linhaArquivo = "";
        conteudoArquivo = "";
        File arquivo;

        if (new File("c:\\tef_dial\\resp\\intpos" + pNumeroTransacao + ".001").exists()) {

            if (pNumeroTransacao != 0) {
                arquivo = new File("c:\\tef_dial\\resp\\intpos" + pNumeroTransacao + ".001");
            } else {
                arquivo = respIntPos001;
            }

            try {
                FileReader ler = new FileReader(arquivo);
                BufferedReader entrada = new BufferedReader(ler);

                while ((linhaArquivo = entrada.readLine()) != null) {
                    if ((linhaArquivo.substring(0, 3).equals("001"))
                            || (linhaArquivo.substring(0, 3).equals("002"))
                            || (linhaArquivo.substring(0, 3).equals("010"))
                            || (linhaArquivo.substring(0, 3).equals("012"))
                            || (linhaArquivo.substring(0, 3).equals("027"))) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo + (char) 13 + (char) 10;
                    }
                    if (linhaArquivo.substring(0, 3).equals("999")) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo;
                    }
                    //TODO : Está correta essa forma de pegar o NSU?
                    if (linhaArquivo.substring(0, 3).equals("012")) {
                        NSU = linhaArquivo.substring(10, linhaArquivo.length());
                    }

                } //Fim do while not EOF

                entrada.close();
                ler.close();

                // Cria o novo INTPOS.001 da Confirmacão
                conteudoArquivo = "000-000 = CNF" + (char) 13 + (char) 10 + conteudoArquivo;
                FileWriter gravar = new FileWriter(arquivoIntPos001);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(conteudoArquivo);
                saida.close();
                gravar.close();
                copyFile(arquivoIntPos001, reqIntPos001);
                arquivoIntPos001.delete();

                while (!respIntPosSts.exists()) {
                    Thread.sleep(1000);
                }
                respIntPosSts.delete();
                return NSU;
            } catch (InterruptedException ex) {
                Logger.getLogger(Tef.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e) {
                e.getMessage();
            }
        }

        //Exclui o arquivo TEF.txt
        if (arquivoTefTxt.exists()) {
            arquivoTefTxt.delete();
        }
        return null;
    }
}
