/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Classe de controle do PAF.</p>
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

import com.t2tierp.pafecf.controller.RegistroRController;
import com.t2tierp.pafecf.controller.SintegraController;
import com.t2tierp.pafecf.controller.TotalTipoPagamentoController;
import com.t2tierp.pafecf.view.Caixa;
import com.t2tierp.pafecf.vo.MeiosPagamentoVO;
import com.t2tierp.pafecf.vo.R02VO;
import com.t2tierp.pafecf.vo.R03VO;
import com.t2tierp.pafecf.vo.Sintegra60AVO;
import com.t2tierp.pafecf.vo.Sintegra60MVO;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Paf {

    public static void identificacaoPafEcf() {
        //TODO : Relatorio dentro das especificações?
        try {
            Caixa.ACBrECF.abreRelatorioGerencial(1);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("IDENTIFICACAO DO PAF-ECF", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("NU. LAUDO.........: 123", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("C.N.P.J. .........: 10793118000178", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("EMPRESA...........: T2TI", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("ENDERECO..........: BRASILIA", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("NUMERO............: 123", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("COMPLEMENTO.......: 456", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("BAIRRO............: AGUAS CLARAS", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("CIDADE............: BRASILIA", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("CEP...............: 71000000", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("UF................: DF", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("FONE..............: (61)3042.5277", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("RESPONSAVEL.......: ALBERT EIJE", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("NOME COMERCIAL....: T2TI PDV", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("VERSAO............: 1.00", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("PRINCIPAL ARQUIVO.: PAFECF.JAR", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("MD5...............: 1231213212313213213213", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.fechaRelatorio();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    //TODO : Está correto esse procedimento para gerar verificar se o ECF é autorizado?
    public static boolean ECFAutorizado() {
        String linhaArquivo = "";
        File ecfAutorizados = new File(System.getProperty("user.dir") + "\\ECFsAutorizados.txt");
        try {
            String MD5Serie = Biblioteca.MD5String(Caixa.ACBrECF.getNumSerie());
            FileReader leitura = new FileReader(ecfAutorizados);
            BufferedReader entrada = new BufferedReader(leitura);
            while ((linhaArquivo = entrada.readLine()) != null) {
                if (linhaArquivo.equals(MD5Serie)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //TODO : Está correto esse procedimento para gerar o MD5?
    public static String geraMD5() {
        try {
            //apaga arquivo anterior
            if (new File(System.getProperty("user.dir") + "\\ArqMD5.txt").exists()) {
                new File(System.getProperty("user.dir") + "\\ArqMD5.txt").delete();
            }
            //cria o arquivo TXT na mesma pasta do sistema
            File arquivo = new File(System.getProperty("user.dir") + "\\ArqMD5.txt");

            //grava o nome dos arquivos executáveis da aplicação dentro do arquivo criado acima
            //TODO : quais serão os nossos arquivos executáveis?
            FileWriter gravar = new FileWriter(arquivo);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(System.getProperty("user.dir") + "\\build.xml");
            saida.close();
            gravar.close();

            return Biblioteca.MD5File(System.getProperty("user.dir") + "\\ArqMD5.txt");
        } catch (Exception e) {
            return null;
        }
    }

    public static void meiosPagamento(String dataIni, String dataFim) {
        TotalTipoPagamentoController totalTipoPagamentoControl = new TotalTipoPagamentoController();
        List<MeiosPagamentoVO> listaMeiosPagamento = new ArrayList<MeiosPagamentoVO>();
        listaMeiosPagamento = totalTipoPagamentoControl.meiosPagamento(dataIni, dataFim, Caixa.configuracao.getImpressoraVO().getId());
        String meio, tipoDoc, valor, data = "";

        //TODO : Esse relatório está de acordo com as especificações do PAF-ECF?
        try {
            Caixa.ACBrECF.abreRelatorioGerencial(1);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("MEIOS DE PAGAMENTO", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("PERIODO: " + dataIni + " A " + dataFim, 0);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.linhaRelatorioGerencial("MEIO DE PGTO.  TIPO DOC. VLR.ACUMUL.  DT.ACUMUL.", 0);
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            for (int i = 0; i < listaMeiosPagamento.size(); i++) {
                meio = listaMeiosPagamento.get(i).getDescricao();
                meio = meio + Biblioteca.repete(" ", 15 - meio.length());
                tipoDoc = "FISCAL ";
                NumberFormat formatter = new DecimalFormat("0.00");
                valor = formatter.format(listaMeiosPagamento.get(i).getTotal());
                valor = Biblioteca.repete(" ", 13 - valor.length()) + valor;
                data = " " + listaMeiosPagamento.get(i).getDataHora().toString().substring(0, 10);
                Caixa.ACBrECF.linhaRelatorioGerencial(meio + tipoDoc + valor + data, 0);
            }
            Caixa.ACBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.ACBrECF.fechaRelatorio();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void geraMovimentoECF() {
        String comando =
                System.getProperty("user.dir") + "\\binario\\binario.exe "
                + "geraMovimentoECF "
                + Caixa.configuracao.getIdEmpresa() + " "
                + Caixa.movimento.getIdCaixa() + " "
                + Caixa.movimento.getIdOperador() + " "
                + Caixa.movimento.getIdImpressora() + " "
                + Caixa.configuracao.getPortaECF() + " "
                + Caixa.configuracao.getTimeOutECF() + " "
                + Caixa.configuracao.getIntervaloECF() + " "
                + Caixa.configuracao.getImpressoraVO().getModeloACBr();
        try {
            Caixa.ACBrECF.desativar();
            Process p = Runtime.getRuntime().exec(comando);
            p.waitFor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                Caixa.ACBrECF.ativar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void geraTabelaProdutos() {
        String comando =
                System.getProperty("user.dir") + "\\binario\\binario.exe "
                + "geraTabelaProdutos "
                + Caixa.configuracao.getIdEmpresa() + " "
                + Caixa.movimento.getIdCaixa() + " "
                + Caixa.movimento.getIdOperador() + " "
                + Caixa.movimento.getIdImpressora() + " "
                + Caixa.configuracao.getPortaECF() + " "
                + Caixa.configuracao.getTimeOutECF() + " "
                + Caixa.configuracao.getIntervaloECF() + " "
                + Caixa.configuracao.getImpressoraVO().getModeloACBr();
        try {
            Caixa.ACBrECF.desativar();
            Process p = Runtime.getRuntime().exec(comando);
            p.waitFor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                Caixa.ACBrECF.ativar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void geraArquivoEstoque() {
        String comando =
                System.getProperty("user.dir") + "\\binario\\binario.exe "
                + "geraArquivoEstoque "
                + Caixa.configuracao.getIdEmpresa() + " "
                + Caixa.movimento.getIdCaixa() + " "
                + Caixa.movimento.getIdOperador() + " "
                + Caixa.movimento.getIdImpressora() + " "
                + Caixa.configuracao.getPortaECF() + " "
                + Caixa.configuracao.getTimeOutECF() + " "
                + Caixa.configuracao.getIntervaloECF() + " "
                + Caixa.configuracao.getImpressoraVO().getModeloACBr();
        try {
            Caixa.ACBrECF.desativar();
            Process p = Runtime.getRuntime().exec(comando);
            p.waitFor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                Caixa.ACBrECF.ativar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void gravaR02R03() {
        try {
            List<R03VO> listaR03 = new ArrayList<R03VO>();
            RegistroRController registroRControl = new RegistroRController();

            //Dados para o registro R02
            R02VO R02 = new R02VO();
            R02.setIdCaixa(Caixa.movimento.getIdCaixa());
            R02.setIdOperador(Caixa.movimento.getIdOperador());
            R02.setIdImpressora(Caixa.movimento.getIdImpressora());
            R02.setCRZ(Integer.valueOf(Caixa.ACBrECF.getNumCRZ()));
            R02.setCOO(Integer.valueOf(Caixa.ACBrECF.getNumCOO()));
            R02.setCRO(Integer.valueOf(Caixa.ACBrECF.getNumCRO()));

            java.util.Date data = Caixa.ACBrECF.getDataMovimento();
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());
            java.sql.Time horaSQL = new java.sql.Time(data.getTime());

            R02.setDataMovimento(dataSQL);
            R02.setDataEmissao(dataSQL);
            R02.setHoraEmissao(horaSQL.toString());
            R02.setValorVendaBruta(Caixa.ACBrECF.getVendaBruta());
            R02.setValorGrandeTotal(Caixa.ACBrECF.getGrandeTotal());
            R02 = registroRControl.gravaR02(R02);

            //Dados para o registro R03
            //TODO : Qual o procedimento para gerar o R03?
            //TODO : O exemplo abaixo para geração do R03 está dentro da especificação?
            R03VO R03 = new R03VO();

            //Isento - ICMS
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("I1");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Não-incidência - ICMS
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("N1");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Substituição Tributária - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("FS1");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Isento - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("IS1");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Não-incidência - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("NS1");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Operações Não Fiscais
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("OPNF");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Desconto - ICMS
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("DT");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Desconto - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("DS");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Acréscimo - ICMS
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("AT");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Acréscimo - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("AS");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Cancelamento - ICMS
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("Can-T");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            //Cancelamento - ISSQN
            R03 = new R03VO();
            R03.setIdR02(R02.getId());
            R03.setTotalizadorParcial("Can-S");
            R03.setValorAcumulado(1.0);
            listaR03.add(R03);

            registroRControl.gravaR03(listaR03);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void grava60M60A() {
        try {
            //TODO : Está correto o procedimento para gravação do 60M?
            SintegraController sintegraControl = new SintegraController();
            Sintegra60MVO sintegra60M = new Sintegra60MVO();
            List<Sintegra60AVO> lista60A = new ArrayList<Sintegra60AVO>();

            java.util.Date data = Caixa.ACBrECF.getDataMovimento();
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());

            sintegra60M.setDataEmissao(dataSQL);
            sintegra60M.setNumeroSerieECF(Caixa.ACBrECF.getNumSerie());
            sintegra60M.setNumeroEquipamento(Integer.valueOf(Caixa.ACBrECF.getNumECF()));
            //TODO : O que informar no modelo do documento fiscal?
            sintegra60M.setModeloDocumentoFiscal("2D");
            sintegra60M.setCOOInicial(Integer.valueOf(Caixa.ACBrECF.getNumCOOInicial()));
            sintegra60M.setCOOFinal(Integer.valueOf(Caixa.ACBrECF.getNumCOO()));
            sintegra60M.setCRZ(Integer.valueOf(Caixa.ACBrECF.getNumCRZ()));
            sintegra60M.setCRO(Integer.valueOf(Caixa.ACBrECF.getNumCRO()));
            sintegra60M.setValorVendaBruta(Caixa.ACBrECF.getVendaBruta());
            sintegra60M.setValorGrandeTotal(Caixa.ACBrECF.getGrandeTotal());

            sintegraControl.Grava60M(sintegra60M);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

        //TODO : Qual o procedimento para a gravação do 60A?
    }
}
