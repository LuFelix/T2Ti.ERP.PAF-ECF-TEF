package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.util.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela NF2 - Cabeçalho.</p>
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
public class Nf2CabecalhoVO  {
    private Integer id;
    private String numero;
    private Date dataHoraEmissao;
    private String serie;
    private String subserie;
    private Double totalProdutos;
    private Double totalNf;
    private Double baseIcms;
    private Double icms;
    private Double icmsOutras;
    private Double issqn;
    private Double pis;
    private Double cofins;
    private Double ipi;
    private Double taxaAcrescimo;
    private Double acrescimo;
    private Double acrescimoItens;
    private Double taxaDesconto;
    private Double desconto;
    private Double descontoItens;
    private String cancelada;
    private String sincronizado;
    private ClienteVO clienteVO;
    private FuncionarioVO funcionarioVO;
    private CfopVO cfopVO;
    private Collection<Nf2DetalheVO> nf2DetalheVOCollection;

    public Nf2CabecalhoVO() {
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
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the dataHoraEmissao
     */
    public Date getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    /**
     * @param dataHoraEmissao the dataHoraEmissao to set
     */
    public void setDataHoraEmissao(Date dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the subserie
     */
    public String getSubserie() {
        return subserie;
    }

    /**
     * @param subserie the subserie to set
     */
    public void setSubserie(String subserie) {
        this.subserie = subserie;
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
     * @return the totalNf
     */
    public Double getTotalNf() {
        return totalNf;
    }

    /**
     * @param totalNf the totalNf to set
     */
    public void setTotalNf(Double totalNf) {
        this.totalNf = totalNf;
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

}
