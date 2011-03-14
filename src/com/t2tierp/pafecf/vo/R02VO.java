package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.util.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela R02.</p>
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
public class R02VO {
    private Integer id;
    private Integer idOperador;
    private Integer idImpressora;
    private String numeroUsuario;
    private Integer crz;
    private Integer coo;
    private Integer cro;
    private Date dataMovimento;
    private Date dataEmissao;
    private Date horaEmissao;
    private Double vendaBruta;
    private Double grandeTotal;
    private String sincronizado;
    private CaixaVO caixaVO;
    private Collection<R03VO> r03VOCollection;
    private Collection<R07VO> r07VOCollection;

    public R02VO() {
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
     * @return the numeroUsuario
     */
    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    /**
     * @param numeroUsuario the numeroUsuario to set
     */
    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    /**
     * @return the crz
     */
    public Integer getCrz() {
        return crz;
    }

    /**
     * @param crz the crz to set
     */
    public void setCrz(Integer crz) {
        this.crz = crz;
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
     * @return the cro
     */
    public Integer getCro() {
        return cro;
    }

    /**
     * @param cro the cro to set
     */
    public void setCro(Integer cro) {
        this.cro = cro;
    }

    /**
     * @return the dataMovimento
     */
    public Date getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the horaEmissao
     */
    public Date getHoraEmissao() {
        return horaEmissao;
    }

    /**
     * @param horaEmissao the horaEmissao to set
     */
    public void setHoraEmissao(Date horaEmissao) {
        this.horaEmissao = horaEmissao;
    }

    /**
     * @return the vendaBruta
     */
    public Double getVendaBruta() {
        return vendaBruta;
    }

    /**
     * @param vendaBruta the vendaBruta to set
     */
    public void setVendaBruta(Double vendaBruta) {
        this.vendaBruta = vendaBruta;
    }

    /**
     * @return the grandeTotal
     */
    public Double getGrandeTotal() {
        return grandeTotal;
    }

    /**
     * @param grandeTotal the grandeTotal to set
     */
    public void setGrandeTotal(Double grandeTotal) {
        this.grandeTotal = grandeTotal;
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
     * @return the caixaVO
     */
    public CaixaVO getCaixaVO() {
        return caixaVO;
    }

    /**
     * @param caixaVO the caixaVO to set
     */
    public void setCaixaVO(CaixaVO caixaVO) {
        this.caixaVO = caixaVO;
    }

    /**
     * @return the r03VOCollection
     */
    public Collection<R03VO> getR03VOCollection() {
        return r03VOCollection;
    }

    /**
     * @param r03VOCollection the r03VOCollection to set
     */
    public void setR03VOCollection(Collection<R03VO> r03VOCollection) {
        this.r03VOCollection = r03VOCollection;
    }

    /**
     * @return the r07VOCollection
     */
    public Collection<R07VO> getR07VOCollection() {
        return r07VOCollection;
    }

    /**
     * @param r07VOCollection the r07VOCollection to set
     */
    public void setR07VOCollection(Collection<R07VO> r07VOCollection) {
        this.r07VOCollection = r07VOCollection;
    }

}