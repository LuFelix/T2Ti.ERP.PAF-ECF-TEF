package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.sql.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela de Venda - Cabeçalho.</p>
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
public class VendaCabecalhoVO {
    private Integer id;
    private Integer coo;
    private Integer ccf;
    private Integer idCaixa;
    private Integer idOperador;
    private Integer idImpressora;
    private Integer idMovimento;
    private Date dataHoraVenda;
    private Double valorVenda;
    private Double taxaDesconto;
    private Double desconto;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private Double valorFinal;
    private Double valorRecebido;
    private Double troco;
    private Double valorCancelado;
    private String sincronizado;
    private Double totalProdutos;
    private Double totalDocumento;
    private Double baseIcms;
    private Double icms;
    private Double icmsOutras;
    private Double issqn;
    private Double pis;
    private Double cofins;
    private Double acrescimoItens;
    private Double descontoItens;
    private String cancelada;
    private Collection<TotalTipoPgtoVO> totalTipoPgtoVOCollection;
    private Collection<VendaDetalheVO> vendaDetalheVOCollection;
    private FuncionarioVO funcionarioVO;
    private ClienteVO clienteVO;
    private PreVendaCabecalhoVO preVendaCabecalhoVO;
    private DavVO davVO;
    private CfopVO cfopVO;

    public VendaCabecalhoVO() {
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
     * @return the coo
     */
    public Integer getCoo() {
        return coo;
    }

    /**
     * @param coo the coo to set
     */
    public void setCoo(Integer coo) {
        this.coo = coo;
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
     * @return the idCaixa
     */
    public Integer getIdCaixa() {
        return idCaixa;
    }

    /**
     * @param idCaixa the idCaixa to set
     */
    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    /**
     * @return the idOperador
     */
    public Integer getIdOperador() {
        return idOperador;
    }

    /**
     * @param idOperador the idOperador to set
     */
    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * @return the idImpressora
     */
    public Integer getIdImpressora() {
        return idImpressora;
    }

    /**
     * @param idImpressora the idImpressora to set
     */
    public void setIdImpressora(Integer idImpressora) {
        this.idImpressora = idImpressora;
    }

    /**
     * @return the idMovimento
     */
    public Integer getIdMovimento() {
        return idMovimento;
    }

    /**
     * @param idMovimento the idMovimento to set
     */
    public void setIdMovimento(Integer idMovimento) {
        this.idMovimento = idMovimento;
    }

    /**
     * @return the dataHoraVenda
     */
    public Date getDataHoraVenda() {
        return dataHoraVenda;
    }

    /**
     * @param dataHoraVenda the dataHoraVenda to set
     */
    public void setDataHoraVenda(Date dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    /**
     * @return the valorVenda
     */
    public Double getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
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
     * @return the valorRecebido
     */
    public Double getValorRecebido() {
        return valorRecebido;
    }

    /**
     * @param valorRecebido the valorRecebido to set
     */
    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    /**
     * @return the troco
     */
    public Double getTroco() {
        return troco;
    }

    /**
     * @param troco the troco to set
     */
    public void setTroco(Double troco) {
        this.troco = troco;
    }

    /**
     * @return the valorCancelado
     */
    public Double getValorCancelado() {
        return valorCancelado;
    }

    /**
     * @param valorCancelado the valorCancelado to set
     */
    public void setValorCancelado(Double valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    /**
     * @return the sincronizado
     */
    public String getSincronizado() {
        return sincronizado;
    }

    /**
     * @param sincronizado the sincronizado to set
     */
    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    /**
     * @return the totalProdutos
     */
    public Double getTotalProdutos() {
        return totalProdutos;
    }

    /**
     * @param totalProdutos the totalProdutos to set
     */
    public void setTotalProdutos(Double totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    /**
     * @return the totalDocumento
     */
    public Double getTotalDocumento() {
        return totalDocumento;
    }

    /**
     * @param totalDocumento the totalDocumento to set
     */
    public void setTotalDocumento(Double totalDocumento) {
        this.totalDocumento = totalDocumento;
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
     * @return the acrescimoItens
     */
    public Double getAcrescimoItens() {
        return acrescimoItens;
    }

    /**
     * @param acrescimoItens the acrescimoItens to set
     */
    public void setAcrescimoItens(Double acrescimoItens) {
        this.acrescimoItens = acrescimoItens;
    }

    /**
     * @return the descontoItens
     */
    public Double getDescontoItens() {
        return descontoItens;
    }

    /**
     * @param descontoItens the descontoItens to set
     */
    public void setDescontoItens(Double descontoItens) {
        this.descontoItens = descontoItens;
    }

    /**
     * @return the cancelada
     */
    public String getCancelada() {
        return cancelada;
    }

    /**
     * @param cancelada the cancelada to set
     */
    public void setCancelada(String cancelada) {
        this.cancelada = cancelada;
    }

    /**
     * @return the totalTipoPgtoVOCollection
     */
    public Collection<TotalTipoPgtoVO> getTotalTipoPgtoVOCollection() {
        return totalTipoPgtoVOCollection;
    }

    /**
     * @param totalTipoPgtoVOCollection the totalTipoPgtoVOCollection to set
     */
    public void setTotalTipoPgtoVOCollection(Collection<TotalTipoPgtoVO> totalTipoPgtoVOCollection) {
        this.totalTipoPgtoVOCollection = totalTipoPgtoVOCollection;
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

    /**
     * @return the funcionarioVO
     */
    public FuncionarioVO getFuncionarioVO() {
        return funcionarioVO;
    }

    /**
     * @param funcionarioVO the funcionarioVO to set
     */
    public void setFuncionarioVO(FuncionarioVO funcionarioVO) {
        this.funcionarioVO = funcionarioVO;
    }

    /**
     * @return the clienteVO
     */
    public ClienteVO getClienteVO() {
        return clienteVO;
    }

    /**
     * @param clienteVO the clienteVO to set
     */
    public void setClienteVO(ClienteVO clienteVO) {
        this.clienteVO = clienteVO;
    }

    /**
     * @return the preVendaCabecalhoVO
     */
    public PreVendaCabecalhoVO getPreVendaCabecalhoVO() {
        return preVendaCabecalhoVO;
    }

    /**
     * @param preVendaCabecalhoVO the preVendaCabecalhoVO to set
     */
    public void setPreVendaCabecalhoVO(PreVendaCabecalhoVO preVendaCabecalhoVO) {
        this.preVendaCabecalhoVO = preVendaCabecalhoVO;
    }

    /**
     * @return the davVO
     */
    public DavVO getDavVO() {
        return davVO;
    }

    /**
     * @param davVO the davVO to set
     */
    public void setDavVO(DavVO davVO) {
        this.davVO = davVO;
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