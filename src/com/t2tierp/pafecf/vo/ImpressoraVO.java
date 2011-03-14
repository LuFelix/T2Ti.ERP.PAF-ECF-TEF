package com.t2tierp.pafecf.vo;

import java.util.Collection;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Impressora.</p>
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
public class ImpressoraVO {
    private Integer id;
    private String codigo;
    private String serie;
    private String identificacao;
    private String mc;
    private String md;
    private String vr;
    private String tipo;
    private String marca;
    private String modelo;
    private String versao;
    private String le;
    private String lef;
    private String mfd;
    private String crenamfd;
    private String docto;
    private Collection<ConfiguracaoVO> configuracaoVOCollection;
    private Collection<CaixaVO> caixaVOCollection;

    public ImpressoraVO() {
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the identificacao
     */
    public String getIdentificacao() {
        return identificacao;
    }

    /**
     * @param identificacao the identificacao to set
     */
    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    /**
     * @return the mc
     */
    public String getMc() {
        return mc;
    }

    /**
     * @param mc the mc to set
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * @return the md
     */
    public String getMd() {
        return md;
    }

    /**
     * @param md the md to set
     */
    public void setMd(String md) {
        this.md = md;
    }

    /**
     * @return the vr
     */
    public String getVr() {
        return vr;
    }

    /**
     * @param vr the vr to set
     */
    public void setVr(String vr) {
        this.vr = vr;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the versao
     */
    public String getVersao() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersao(String versao) {
        this.versao = versao;
    }

    /**
     * @return the le
     */
    public String getLe() {
        return le;
    }

    /**
     * @param le the le to set
     */
    public void setLe(String le) {
        this.le = le;
    }

    /**
     * @return the lef
     */
    public String getLef() {
        return lef;
    }

    /**
     * @param lef the lef to set
     */
    public void setLef(String lef) {
        this.lef = lef;
    }

    /**
     * @return the mfd
     */
    public String getMfd() {
        return mfd;
    }

    /**
     * @param mfd the mfd to set
     */
    public void setMfd(String mfd) {
        this.mfd = mfd;
    }

    /**
     * @return the crenamfd
     */
    public String getCrenamfd() {
        return crenamfd;
    }

    /**
     * @param crenamfd the crenamfd to set
     */
    public void setCrenamfd(String crenamfd) {
        this.crenamfd = crenamfd;
    }

    /**
     * @return the docto
     */
    public String getDocto() {
        return docto;
    }

    /**
     * @param docto the docto to set
     */
    public void setDocto(String docto) {
        this.docto = docto;
    }

    /**
     * @return the configuracaoVOCollection
     */
    public Collection<ConfiguracaoVO> getConfiguracaoVOCollection() {
        return configuracaoVOCollection;
    }

    /**
     * @param configuracaoVOCollection the configuracaoVOCollection to set
     */
    public void setConfiguracaoVOCollection(Collection<ConfiguracaoVO> configuracaoVOCollection) {
        this.configuracaoVOCollection = configuracaoVOCollection;
    }

    /**
     * @return the caixaVOCollection
     */
    public Collection<CaixaVO> getCaixaVOCollection() {
        return caixaVOCollection;
    }

    /**
     * @param caixaVOCollection the caixaVOCollection to set
     */
    public void setCaixaVOCollection(Collection<CaixaVO> caixaVOCollection) {
        this.caixaVOCollection = caixaVOCollection;
    }

}
