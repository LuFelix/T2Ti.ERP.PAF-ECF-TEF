package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.util.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Cliente.</p>
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
public class ClienteVO  {
    private Integer id;
    private String nome;
    private String cpfCnpj;
    private String rg;
    private String orgaoRg;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Date desde;
    private String tipoPessoa;
    private String excluido;
    private Date dataCadastro;
    private SituacaoCliVO situacaoCliVO;
    private Collection<ContatoVO> contatoVOCollection;
    private Collection<Nf2CabecalhoVO> nf2CabecalhoVOCollection;
    private Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection;
    private Collection<ChequeClienteVO> chequeClienteVOCollection;
    private Collection<EnderecoVO> enderecoVOCollection;

    public ClienteVO() {
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj the cpfCnpj to set
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the orgaoRg
     */
    public String getOrgaoRg() {
        return orgaoRg;
    }

    /**
     * @param orgaoRg the orgaoRg to set
     */
    public void setOrgaoRg(String orgaoRg) {
        this.orgaoRg = orgaoRg;
    }

    /**
     * @return the inscricaoEstadual
     */
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    /**
     * @param inscricaoEstadual the inscricaoEstadual to set
     */
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     * @return the inscricaoMunicipal
     */
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    /**
     * @param inscricaoMunicipal the inscricaoMunicipal to set
     */
    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    /**
     * @return the desde
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
     * @return the tipoPessoa
     */
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa the tipoPessoa to set
     */
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    /**
     * @return the excluido
     */
    public String getExcluido() {
        return excluido;
    }

    /**
     * @param excluido the excluido to set
     */
    public void setExcluido(String excluido) {
        this.excluido = excluido;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the situacaoCliVO
     */
    public SituacaoCliVO getSituacaoCliVO() {
        return situacaoCliVO;
    }

    /**
     * @param situacaoCliVO the situacaoCliVO to set
     */
    public void setSituacaoCliVO(SituacaoCliVO situacaoCliVO) {
        this.situacaoCliVO = situacaoCliVO;
    }

    /**
     * @return the contatoVOCollection
     */
    public Collection<ContatoVO> getContatoVOCollection() {
        return contatoVOCollection;
    }

    /**
     * @param contatoVOCollection the contatoVOCollection to set
     */
    public void setContatoVOCollection(Collection<ContatoVO> contatoVOCollection) {
        this.contatoVOCollection = contatoVOCollection;
    }

    /**
     * @return the nf2CabecalhoVOCollection
     */
    public Collection<Nf2CabecalhoVO> getNf2CabecalhoVOCollection() {
        return nf2CabecalhoVOCollection;
    }

    /**
     * @param nf2CabecalhoVOCollection the nf2CabecalhoVOCollection to set
     */
    public void setNf2CabecalhoVOCollection(Collection<Nf2CabecalhoVO> nf2CabecalhoVOCollection) {
        this.nf2CabecalhoVOCollection = nf2CabecalhoVOCollection;
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

    /**
     * @return the chequeClienteVOCollection
     */
    public Collection<ChequeClienteVO> getChequeClienteVOCollection() {
        return chequeClienteVOCollection;
    }

    /**
     * @param chequeClienteVOCollection the chequeClienteVOCollection to set
     */
    public void setChequeClienteVOCollection(Collection<ChequeClienteVO> chequeClienteVOCollection) {
        this.chequeClienteVOCollection = chequeClienteVOCollection;
    }

    /**
     * @return the enderecoVOCollection
     */
    public Collection<EnderecoVO> getEnderecoVOCollection() {
        return enderecoVOCollection;
    }

    /**
     * @param enderecoVOCollection the enderecoVOCollection to set
     */
    public void setEnderecoVOCollection(Collection<EnderecoVO> enderecoVOCollection) {
        this.enderecoVOCollection = enderecoVOCollection;
    }

}
