package com.t2tierp.pafecf.vo;

import java.util.Collection;
import java.sql.Date;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Movimento.</p>
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
public class MovimentoVO {
    private Integer id;
    private Date dataHoraAbertura;
    private Date dataHoraFechamento;
    private Double totalSuprimento;
    private Double totalSangria;
    private Double totalNaoFiscal;
    private Double totalVenda;
    private Double totalDesconto;
    private Double totalAcrescimo;
    private Double totalFinal;
    private Double totalRecebido;
    private Double totalTroco;
    private Double totalCancelado;
    private String statusMovimento;
    private String sincronizado;
    private Integer idOperador;
    private Integer idCaixa;
    private Integer idImpressora;
    private Collection<RecebimentoNaoFiscalVO> recebimentoNaoFiscalVOCollection;
    private Collection<SuprimentoVO> suprimentoVOCollection;
    private Collection<SangriaVO> sangriaVOCollection;
    private Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection;

    public MovimentoVO() {
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
     * @return the dataHoraAbertura
     */
    public Date getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    /**
     * @param dataHoraAbertura the dataHoraAbertura to set
     */
    public void setDataHoraAbertura(Date dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    /**
     * @return the dataHoraFechamento
     */
    public Date getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    /**
     * @param dataHoraFechamento the dataHoraFechamento to set
     */
    public void setDataHoraFechamento(Date dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    /**
     * @return the totalSuprimento
     */
    public Double getTotalSuprimento() {
        return totalSuprimento;
    }

    /**
     * @param totalSuprimento the totalSuprimento to set
     */
    public void setTotalSuprimento(Double totalSuprimento) {
        this.totalSuprimento = totalSuprimento;
    }

    /**
     * @return the totalSangria
     */
    public Double getTotalSangria() {
        return totalSangria;
    }

    /**
     * @param totalSangria the totalSangria to set
     */
    public void setTotalSangria(Double totalSangria) {
        this.totalSangria = totalSangria;
    }

    /**
     * @return the totalNaoFiscal
     */
    public Double getTotalNaoFiscal() {
        return totalNaoFiscal;
    }

    /**
     * @param totalNaoFiscal the totalNaoFiscal to set
     */
    public void setTotalNaoFiscal(Double totalNaoFiscal) {
        this.totalNaoFiscal = totalNaoFiscal;
    }

    /**
     * @return the totalVenda
     */
    public Double getTotalVenda() {
        return totalVenda;
    }

    /**
     * @param totalVenda the totalVenda to set
     */
    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    /**
     * @return the totalDesconto
     */
    public Double getTotalDesconto() {
        return totalDesconto;
    }

    /**
     * @param totalDesconto the totalDesconto to set
     */
    public void setTotalDesconto(Double totalDesconto) {
        this.totalDesconto = totalDesconto;
    }

    /**
     * @return the totalAcrescimo
     */
    public Double getTotalAcrescimo() {
        return totalAcrescimo;
    }

    /**
     * @param totalAcrescimo the totalAcrescimo to set
     */
    public void setTotalAcrescimo(Double totalAcrescimo) {
        this.totalAcrescimo = totalAcrescimo;
    }

    /**
     * @return the totalFinal
     */
    public Double getTotalFinal() {
        return totalFinal;
    }

    /**
     * @param totalFinal the totalFinal to set
     */
    public void setTotalFinal(Double totalFinal) {
        this.totalFinal = totalFinal;
    }

    /**
     * @return the totalRecebido
     */
    public Double getTotalRecebido() {
        return totalRecebido;
    }

    /**
     * @param totalRecebido the totalRecebido to set
     */
    public void setTotalRecebido(Double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    /**
     * @return the totalTroco
     */
    public Double getTotalTroco() {
        return totalTroco;
    }

    /**
     * @param totalTroco the totalTroco to set
     */
    public void setTotalTroco(Double totalTroco) {
        this.totalTroco = totalTroco;
    }

    /**
     * @return the totalCancelado
     */
    public Double getTotalCancelado() {
        return totalCancelado;
    }

    /**
     * @param totalCancelado the totalCancelado to set
     */
    public void setTotalCancelado(Double totalCancelado) {
        this.totalCancelado = totalCancelado;
    }

    /**
     * @return the statusMovimento
     */
    public String getStatusMovimento() {
        return statusMovimento;
    }

    /**
     * @param statusMovimento the statusMovimento to set
     */
    public void setStatusMovimento(String statusMovimento) {
        this.statusMovimento = statusMovimento;
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
     * @return the recebimentoNaoFiscalVOCollection
     */
    public Collection<RecebimentoNaoFiscalVO> getRecebimentoNaoFiscalVOCollection() {
        return recebimentoNaoFiscalVOCollection;
    }

    /**
     * @param recebimentoNaoFiscalVOCollection the recebimentoNaoFiscalVOCollection to set
     */
    public void setRecebimentoNaoFiscalVOCollection(Collection<RecebimentoNaoFiscalVO> recebimentoNaoFiscalVOCollection) {
        this.recebimentoNaoFiscalVOCollection = recebimentoNaoFiscalVOCollection;
    }

    /**
     * @return the suprimentoVOCollection
     */
    public Collection<SuprimentoVO> getSuprimentoVOCollection() {
        return suprimentoVOCollection;
    }

    /**
     * @param suprimentoVOCollection the suprimentoVOCollection to set
     */
    public void setSuprimentoVOCollection(Collection<SuprimentoVO> suprimentoVOCollection) {
        this.suprimentoVOCollection = suprimentoVOCollection;
    }

    /**
     * @return the sangriaVOCollection
     */
    public Collection<SangriaVO> getSangriaVOCollection() {
        return sangriaVOCollection;
    }

    /**
     * @param sangriaVOCollection the sangriaVOCollection to set
     */
    public void setSangriaVOCollection(Collection<SangriaVO> sangriaVOCollection) {
        this.sangriaVOCollection = sangriaVOCollection;
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
