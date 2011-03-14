package com.t2tierp.pafecf.vo;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela dos Totais de Tipo de Pagamento.</p>
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
public class TotalTipoPagamentoVO {
    private Integer id;
    private Integer idVendaCabecalho;
    private Double valor;
    private String NSU;
    private TipoPagamentoVO tipoPagamentoVO;

    public TotalTipoPagamentoVO() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idVendaCabecalho
     */
    public Integer getIdVendaCabecalho() {
        return idVendaCabecalho;
    }

    /**
     * @param idVendaCabecalho the idVendaCabecalho to set
     */
    public void setIdVendaCabecalho(Integer idVendaCabecalho) {
        this.idVendaCabecalho = idVendaCabecalho;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the NSU
     */
    public String getNSU() {
        return NSU;
    }

    /**
     * @param NSU the NSU to set
     */
    public void setNSU(String NSU) {
        this.NSU = NSU;
    }

    /**
     * @return the tipoPagamentoVO
     */
    public TipoPagamentoVO getTipoPagamentoVO() {
        return tipoPagamentoVO;
    }

    /**
     * @param tipoPagamentoVO the tipoPagamentoVO to set
     */
    public void setTipoPagamentoVO(TipoPagamentoVO tipoPagamentoVO) {
        this.tipoPagamentoVO = tipoPagamentoVO;
    }
 
}