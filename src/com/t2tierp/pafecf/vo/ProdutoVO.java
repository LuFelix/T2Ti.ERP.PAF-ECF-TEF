package com.t2tierp.pafecf.vo;

import java.util.Collection;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Produto.</p>
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
public class ProdutoVO {
    private Integer id;
    private Integer idUnidade;
    private Integer idTributo;
    private String gtin;
    private String codigoInterno;
    private String nome;
    private String descricao;
    private String descricaoPdv;
    private Double valorVenda;
    private Double qtdEstoque;
    private Double estoqueMin;
    private Double estoqueMax;
    private String iat;
    private String ippt;
    private String ncm;
    private String movimentaEstoque;
    private Collection<Nf2DetalheVO> nf2DetalheVOCollection;
    private Collection<VendaDetalheVO> vendaDetalheVOCollection;
    private Collection<DavDetalheVO> davDetalheVOCollection;
    private Collection<PreVendaDetalheVO> preVendaDetalheVOCollection;

    public ProdutoVO() {
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
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the idTributo
     */
    public Integer getIdTributo() {
        return idTributo;
    }

    /**
     * @param idTributo the idTributo to set
     */
    public void setIdTributo(Integer idTributo) {
        this.idTributo = idTributo;
    }

    /**
     * @return the gtin
     */
    public String getGtin() {
        return gtin;
    }

    /**
     * @param gtin the gtin to set
     */
    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    /**
     * @return the codigoInterno
     */
    public String getCodigoInterno() {
        return codigoInterno;
    }

    /**
     * @param codigoInterno the codigoInterno to set
     */
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
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
     * @return the descricaoPdv
     */
    public String getDescricaoPdv() {
        return descricaoPdv;
    }

    /**
     * @param descricaoPdv the descricaoPdv to set
     */
    public void setDescricaoPdv(String descricaoPdv) {
        this.descricaoPdv = descricaoPdv;
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
     * @return the qtdEstoque
     */
    public Double getQtdEstoque() {
        return qtdEstoque;
    }

    /**
     * @param qtdEstoque the qtdEstoque to set
     */
    public void setQtdEstoque(Double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    /**
     * @return the estoqueMin
     */
    public Double getEstoqueMin() {
        return estoqueMin;
    }

    /**
     * @param estoqueMin the estoqueMin to set
     */
    public void setEstoqueMin(Double estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    /**
     * @return the estoqueMax
     */
    public Double getEstoqueMax() {
        return estoqueMax;
    }

    /**
     * @param estoqueMax the estoqueMax to set
     */
    public void setEstoqueMax(Double estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    /**
     * @return the iat
     */
    public String getIat() {
        return iat;
    }

    /**
     * @param iat the iat to set
     */
    public void setIat(String iat) {
        this.iat = iat;
    }

    /**
     * @return the ippt
     */
    public String getIppt() {
        return ippt;
    }

    /**
     * @param ippt the ippt to set
     */
    public void setIppt(String ippt) {
        this.ippt = ippt;
    }

    /**
     * @return the ncm
     */
    public String getNcm() {
        return ncm;
    }

    /**
     * @param ncm the ncm to set
     */
    public void setNcm(String ncm) {
        this.ncm = ncm;
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

    /**
     * @return the davDetalheVOCollection
     */
    public Collection<DavDetalheVO> getDavDetalheVOCollection() {
        return davDetalheVOCollection;
    }

    /**
     * @param davDetalheVOCollection the davDetalheVOCollection to set
     */
    public void setDavDetalheVOCollection(Collection<DavDetalheVO> davDetalheVOCollection) {
        this.davDetalheVOCollection = davDetalheVOCollection;
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

}