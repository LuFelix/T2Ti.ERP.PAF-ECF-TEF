package com.t2tierp.pafecf.vo;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela NF2 - Detalhe.</p>
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
public class Nf2DetalheVO {
    private Integer id;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private Double baseIcms;
    private Double taxaIcms;
    private Double icms;
    private Double icmsOutras;
    private Double icmsIsento;
    private Double taxaDesconto;
    private Double desconto;
    private Double taxaIssqn;
    private Double issqn;
    private Double taxaPis;
    private Double pis;
    private Double taxaCofins;
    private Double cofins;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private Double taxaIpi;
    private Double ipi;
    private String cancelado;
    private String movimentaEstoque;
    private Nf2CabecalhoVO nf2CabecalhoVO;
    private ProdutoVO produtoVO;
    private TributoProdutoVO tributoProdutoVO;
    private CfopVO cfopVO;

    public Nf2DetalheVO() {
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
     * @return the quantidade
     */
    public Double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorUnitario
     */
    public Double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the baseIcms
     */
    public Double getBaseIcms() {
        return baseIcms;
    }

    /**
     * @param baseIcms the baseIcms to set
     */
    public void setBaseIcms(Double baseIcms) {
        this.baseIcms = baseIcms;
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
     * @return the icms
     */
    public Double getIcms() {
        return icms;
    }

    /**
     * @param icms the icms to set
     */
    public void setIcms(Double icms) {
        this.icms = icms;
    }

    /**
     * @return the icmsOutras
     */
    public Double getIcmsOutras() {
        return icmsOutras;
    }

    /**
     * @param icmsOutras the icmsOutras to set
     */
    public void setIcmsOutras(Double icmsOutras) {
        this.icmsOutras = icmsOutras;
    }

    /**
     * @return the icmsIsento
     */
    public Double getIcmsIsento() {
        return icmsIsento;
    }

    /**
     * @param icmsIsento the icmsIsento to set
     */
    public void setIcmsIsento(Double icmsIsento) {
        this.icmsIsento = icmsIsento;
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
     * @return the desconto
     */
    public Double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
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
     * @return the issqn
     */
    public Double getIssqn() {
        return issqn;
    }

    /**
     * @param issqn the issqn to set
     */
    public void setIssqn(Double issqn) {
        this.issqn = issqn;
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
     * @return the pis
     */
    public Double getPis() {
        return pis;
    }

    /**
     * @param pis the pis to set
     */
    public void setPis(Double pis) {
        this.pis = pis;
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
     * @return the cofins
     */
    public Double getCofins() {
        return cofins;
    }

    /**
     * @param cofins the cofins to set
     */
    public void setCofins(Double cofins) {
        this.cofins = cofins;
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
     * @return the acrescimo
     */
    public Double getAcrescimo() {
        return acrescimo;
    }

    /**
     * @param acrescimo the acrescimo to set
     */
    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
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
     * @return the ipi
     */
    public Double getIpi() {
        return ipi;
    }

    /**
     * @param ipi the ipi to set
     */
    public void setIpi(Double ipi) {
        this.ipi = ipi;
    }

    /**
     * @return the cancelado
     */
    public String getCancelado() {
        return cancelado;
    }

    /**
     * @param cancelado the cancelado to set
     */
    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    /**
     * @return the movimentaEstoque
     */
    public String getMovimentaEstoque() {
        return movimentaEstoque;
    }

    /**
     * @param movimentaEstoque the movimentaEstoque to set
     */
    public void setMovimentaEstoque(String movimentaEstoque) {
        this.movimentaEstoque = movimentaEstoque;
    }

    /**
     * @return the nf2CabecalhoVO
     */
    public Nf2CabecalhoVO getNf2CabecalhoVO() {
        return nf2CabecalhoVO;
    }

    /**
     * @param nf2CabecalhoVO the nf2CabecalhoVO to set
     */
    public void setNf2CabecalhoVO(Nf2CabecalhoVO nf2CabecalhoVO) {
        this.nf2CabecalhoVO = nf2CabecalhoVO;
    }

    /**
     * @return the produtoVO
     */
    public ProdutoVO getProdutoVO() {
        return produtoVO;
    }

    /**
     * @param produtoVO the produtoVO to set
     */
    public void setProdutoVO(ProdutoVO produtoVO) {
        this.produtoVO = produtoVO;
    }

    /**
     * @return the tributoProdutoVO
     */
    public TributoProdutoVO getTributoProdutoVO() {
        return tributoProdutoVO;
    }

    /**
     * @param tributoProdutoVO the tributoProdutoVO to set
     */
    public void setTributoProdutoVO(TributoProdutoVO tributoProdutoVO) {
        this.tributoProdutoVO = tributoProdutoVO;
    }

    /**
     * @return the cfopVO
     */
    public CfopVO getCfopVO() {
        return cfopVO;
    }

    /**
     * @param cfopVO the cfopVO to set
     */
    public void setCfopVO(CfopVO cfopVO) {
        this.cfopVO = cfopVO;
    }

}
