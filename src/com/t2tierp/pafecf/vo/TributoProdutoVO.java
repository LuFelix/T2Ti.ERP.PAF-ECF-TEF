package com.t2tierp.pafecf.vo;

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
    private Double taxaIPI;
    private Double taxaISSQN;
    private Double taxaPIS;
    private Double taxaCOFINS;
    private Double taxaICMS;
    private String ECFICMS;
    private String CST;
    private String totalizadorParcial;

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
     * @return the taxaIPI
     */
    public Double getTaxaIPI() {
        return taxaIPI;
    }

    /**
     * @param taxaIPI the taxaIPI to set
     */
    public void setTaxaIPI(Double taxaIPI) {
        this.taxaIPI = taxaIPI;
    }

    /**
     * @return the taxaISSQN
     */
    public Double getTaxaISSQN() {
        return taxaISSQN;
    }

    /**
     * @param taxaISSQN the taxaISSQN to set
     */
    public void setTaxaISSQN(Double taxaISSQN) {
        this.taxaISSQN = taxaISSQN;
    }

    /**
     * @return the taxaPIS
     */
    public Double getTaxaPIS() {
        return taxaPIS;
    }

    /**
     * @param taxaPIS the taxaPIS to set
     */
    public void setTaxaPIS(Double taxaPIS) {
        this.taxaPIS = taxaPIS;
    }

    /**
     * @return the taxaCOFINS
     */
    public Double getTaxaCOFINS() {
        return taxaCOFINS;
    }

    /**
     * @param taxaCOFINS the taxaCOFINS to set
     */
    public void setTaxaCOFINS(Double taxaCOFINS) {
        this.taxaCOFINS = taxaCOFINS;
    }

    /**
     * @return the taxaICMS
     */
    public Double getTaxaICMS() {
        return taxaICMS;
    }

    /**
     * @param taxaICMS the taxaICMS to set
     */
    public void setTaxaICMS(Double taxaICMS) {
        this.taxaICMS = taxaICMS;
    }

    /**
     * @return the ECFICMS
     */
    public String getECFICMS() {
        return ECFICMS;
    }

    /**
     * @param ECFICMS the ECFICMS to set
     */
    public void setECFICMS(String ECFICMS) {
        this.ECFICMS = ECFICMS;
    }

    /**
     * @return the CST
     */
    public String getCST() {
        return CST;
    }

    /**
     * @param CST the CST to set
     */
    public void setCST(String CST) {
        this.CST = CST;
    }

    /**
     * @return the totalizadorParcial
     */
    public String getTotalizadorParcial() {
        return totalizadorParcial;
    }

    /**
     * @param totalizadorParcial the totalizadorParcial to set
     */
    public void setTotalizadorParcial(String totalizadorParcial) {
        this.totalizadorParcial = totalizadorParcial;
    }

}