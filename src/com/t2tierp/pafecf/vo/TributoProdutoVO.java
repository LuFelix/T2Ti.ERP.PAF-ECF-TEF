package com.t2tierp.pafecf.vo;

import java.util.Collection;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Tributo do Produto.</p>
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
public class TributoProdutoVO {
    private Integer id;
    private String descricao;
    private Double taxaIpi;
    private Double taxaIssqn;
    private Double taxaPis;
    private Double taxaCofins;
    private Double taxaIcms;
    private String ecfIcms;
    private Collection<ProdutoVO> produtoVOCollection;
    private Collection<Nf2DetalheVO> nf2DetalheVOCollection;
    private Collection<VendaDetalheVO> vendaDetalheVOCollection;

    public TributoProdutoVO() {
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the taxaIpi
     */
    public Double getTaxaIpi() {
        return taxaIpi;
    }

    /**
     * @param taxaIpi the taxaIpi to set
     */
    public void setTaxaIpi(Double taxaIpi) {
        this.taxaIpi = taxaIpi;
    }

    /**
     * @return the taxaIssqn
     */
    public Double getTaxaIssqn() {
        return taxaIssqn;
    }

    /**
     * @param taxaIssqn the taxaIssqn to set
     */
    public void setTaxaIssqn(Double taxaIssqn) {
        this.taxaIssqn = taxaIssqn;
    }

    /**
     * @return the taxaPis
     */
    public Double getTaxaPis() {
        return taxaPis;
    }

    /**
     * @param taxaPis the taxaPis to set
     */
    public void setTaxaPis(Double taxaPis) {
        this.taxaPis = taxaPis;
    }

    /**
     * @return the taxaCofins
     */
    public Double getTaxaCofins() {
        return taxaCofins;
    }

    /**
     * @param taxaCofins the taxaCofins to set
     */
    public void setTaxaCofins(Double taxaCofins) {
        this.taxaCofins = taxaCofins;
    }

    /**
     * @return the taxaIcms
     */
    public Double getTaxaIcms() {
        return taxaIcms;
    }

    /**
     * @param taxaIcms the taxaIcms to set
     */
    public void setTaxaIcms(Double taxaIcms) {
        this.taxaIcms = taxaIcms;
    }

    /**
     * @return the ecfIcms
     */
    public String getEcfIcms() {
        return ecfIcms;
    }

    /**
     * @param ecfIcms the ecfIcms to set
     */
    public void setEcfIcms(String ecfIcms) {
        this.ecfIcms = ecfIcms;
    }

    /**
     * @return the produtoVOCollection
     */
    public Collection<ProdutoVO> getProdutoVOCollection() {
        return produtoVOCollection;
    }

    /**
     * @param produtoVOCollection the produtoVOCollection to set
     */
    public void setProdutoVOCollection(Collection<ProdutoVO> produtoVOCollection) {
        this.produtoVOCollection = produtoVOCollection;
    }

    /**
     * @return the nf2DetalheVOCollection
     */
    public Collection<Nf2DetalheVO> getNf2DetalheVOCollection() {
        return nf2DetalheVOCollection;
    }

    /**
     * @param nf2DetalheVOCollection the nf2DetalheVOCollection to set
     */
    public void setNf2DetalheVOCollection(Collection<Nf2DetalheVO> nf2DetalheVOCollection) {
        this.nf2DetalheVOCollection = nf2DetalheVOCollection;
    }

    /**
     * @return the vendaDetalheVOCollection
     */
    public Collection<VendaDetalheVO> getVendaDetalheVOCollection() {
        return vendaDetalheVOCollection;
    }

    /**
     * @param vendaDetalheVOCollection the vendaDetalheVOCollection to set
     */
    public void setVendaDetalheVOCollection(Collection<VendaDetalheVO> vendaDetalheVOCollection) {
        this.vendaDetalheVOCollection = vendaDetalheVOCollection;
    }

}