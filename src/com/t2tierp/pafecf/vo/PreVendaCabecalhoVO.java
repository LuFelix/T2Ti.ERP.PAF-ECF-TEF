package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.util.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Pre-Venda - Cabeçalho.</p>
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
public class PreVendaCabecalhoVO {
    private Integer id;
    private Date dataHoraPv;
    private Double valorPv;
    private Double taxaDesconto;
    private Double valorDesconto;
    private Double taxaAcrescimo;
    private Double valorAcrescimo;
    private Double valorFinal;
    private String situacao;
    private Integer ccf;
    private Collection<PreVendaDetalheVO> preVendaDetalheVOCollection;
    private Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection;

    public PreVendaCabecalhoVO() {
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
     * @return the dataHoraPv
     */
    public Date getDataHoraPv() {
        return dataHoraPv;
    }

    /**
     * @param dataHoraPv the dataHoraPv to set
     */
    public void setDataHoraPv(Date dataHoraPv) {
        this.dataHoraPv = dataHoraPv;
    }

    /**
     * @return the valorPv
     */
    public Double getValorPv() {
        return valorPv;
    }

    /**
     * @param valorPv the valorPv to set
     */
    public void setValorPv(Double valorPv) {
        this.valorPv = valorPv;
    }

    /**
     * @return the taxaDesconto
     */
    public Double getTaxaDesconto() {
        return taxaDesconto;
    }

    /**
     * @param taxaDesconto the taxaDesconto to set
     */
    public void setTaxaDesconto(Double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    /**
     * @return the valorDesconto
     */
    public Double getValorDesconto() {
        return valorDesconto;
    }

    /**
     * @param valorDesconto the valorDesconto to set
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return the taxaAcrescimo
     */
    public Double getTaxaAcrescimo() {
        return taxaAcrescimo;
    }

    /**
     * @param taxaAcrescimo the taxaAcrescimo to set
     */
    public void setTaxaAcrescimo(Double taxaAcrescimo) {
        this.taxaAcrescimo = taxaAcrescimo;
    }

    /**
     * @return the valorAcrescimo
     */
    public Double getValorAcrescimo() {
        return valorAcrescimo;
    }

    /**
     * @param valorAcrescimo the valorAcrescimo to set
     */
    public void setValorAcrescimo(Double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    /**
     * @return the valorFinal
     */
    public Double getValorFinal() {
        return valorFinal;
    }

    /**
     * @param valorFinal the valorFinal to set
     */
    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the ccf
     */
    public Integer getCcf() {
        return ccf;
    }

    /**
     * @param ccf the ccf to set
     */
    public void setCcf(Integer ccf) {
        this.ccf = ccf;
    }

    /**
     * @return the preVendaDetalheVOCollection
     */
    public Collection<PreVendaDetalheVO> getPreVendaDetalheVOCollection() {
        return preVendaDetalheVOCollection;
    }

    /**
     * @param preVendaDetalheVOCollection the preVendaDetalheVOCollection to set
     */
    public void setPreVendaDetalheVOCollection(Collection<PreVendaDetalheVO> preVendaDetalheVOCollection) {
        this.preVendaDetalheVOCollection = preVendaDetalheVOCollection;
    }

    /**
     * @return the vendaCabecalhoVOCollection
     */
    public Collection<VendaCabecalhoVO> getVendaCabecalhoVOCollection() {
        return vendaCabecalhoVOCollection;
    }

    /**
     * @param vendaCabecalhoVOCollection the vendaCabecalhoVOCollection to set
     */
    public void setVendaCabecalhoVOCollection(Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection) {
        this.vendaCabecalhoVOCollection = vendaCabecalhoVOCollection;
    }

}