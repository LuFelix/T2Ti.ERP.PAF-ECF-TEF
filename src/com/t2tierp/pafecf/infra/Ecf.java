/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Classe de controle do ECF.</p>
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

import com.t2tierp.pafecf.controller.PreVendaController;
import com.t2tierp.pafecf.view.Caixa;
import com.t2tierp.pafecf.vo.TotalTipoPagamentoVO;
import com.t2tierp.pafecf.vo.VendaDetalheVO;
import javax.swing.JOptionPane;

public class Ecf {

    public static void suprimento(Double valor, String descricao) {
        try {
            Caixa.ACBrECF.suprimento(valor, descricao);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void sangria(Double valor, String descricao) {
        try {
            Caixa.ACBrECF.sangria(valor, descricao);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void cancelaCupom() {
        try {
            Caixa.ACBrECF.cancelaCupom();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void reducaoZ() {
        try {
            PreVendaController preVendaControl = new PreVendaController();
            //TODO : Devo cancelar as pre-vendas antes da redução Z do dia?
            preVendaControl.cancelaPreVendasPendentes();
            Caixa.ACBrECF.reducaoZ();
            Paf.gravaR02R03();
            //TODO : Devemos gravar o 60M (sintegra) neste momento?
            Paf.grava60M60A();
            Paf.geraArquivoEstoque();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void leituraX() {
        try {
            Caixa.ACBrECF.leituraX();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void abreCupom(String CPFouCNPJ) {
        try {
            Caixa.ACBrECF.abreCupom(CPFouCNPJ, "", "");
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void vendeItem(VendaDetalheVO vendaDetalhe) {
        try {
            Caixa.ACBrECF.vendeItem(vendaDetalhe.getGTIN(), vendaDetalhe.getDescricaoPDV(), vendaDetalhe.getECFICMS(), vendaDetalhe.getQuantidade(), vendaDetalhe.getValorUnitario(), 0.0, vendaDetalhe.getUnidadeProduto(), "", "");
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void efetuaFormaPagamento(TotalTipoPagamentoVO totalTipoPagamento) {
        try {
            Caixa.ACBrECF.efetuaPagamento(totalTipoPagamento.getTipoPagamentoVO().getCodigo(), totalTipoPagamento.getValor(), "", false);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void subTotalizaCupom(Double AscDesc) {
        try {
            Caixa.ACBrECF.subtotalizaCupom(AscDesc, "");
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void fechaCupom(String observacao) {
        try {
            Caixa.ACBrECF.fechaCupom(observacao);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void cancelaItem(Integer item) {
        try {
            Caixa.ACBrECF.cancelaItemVendido(item);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
