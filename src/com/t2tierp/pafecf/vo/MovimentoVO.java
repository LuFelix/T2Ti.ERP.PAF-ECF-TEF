package com.t2tierp.pafecf.vo;

import java.sql.Timestamp;

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
    private Integer idImpressora;
    private String identificacaoImpressora;
    private Integer idTurno;
    private String descricaoTurno;
    private Integer idOperador;
    private String loginOperador;
    private Integer idCaixa;
    private String nomeCaixa;
    private Integer idGerenteSupervisor;
    private Timestamp dataHoraAbertura;
    private Timestamp dataHoraFechamento;
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
     * @return the identificacaoImpressora
     */
    public String getIdentificacaoImpressora() {
        return identificacaoImpressora;
    }

    /**
     * @param identificacaoImpressora the identificacaoImpressora to set
     */
    public void setIdentificacaoImpressora(String identificacaoImpressora) {
        this.identificacaoImpressora = identificacaoImpressora;
    }

    /**
     * @return the idTurno
     */
    public Integer getIdTurno() {
        return idTurno;
    }

    /**
     * @param idTurno the idTurno to set
     */
    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    /**
     * @return the descricaoTurno
     */
    public String getDescricaoTurno() {
        return descricaoTurno;
    }

    /**
     * @param descricaoTurno the descricaoTurno to set
     */
    public void setDescricaoTurno(String descricaoTurno) {
        this.descricaoTurno = descricaoTurno;
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
     * @return the loginOperador
     */
    public String getLoginOperador() {
        return loginOperador;
    }

    /**
     * @param loginOperador the loginOperador to set
     */
    public void setLoginOperador(String loginOperador) {
        this.loginOperador = loginOperador;
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
     * @return the nomeCaixa
     */
    public String getNomeCaixa() {
        return nomeCaixa;
    }

    /**
     * @param nomeCaixa the nomeCaixa to set
     */
    public void setNomeCaixa(String nomeCaixa) {
        this.nomeCaixa = nomeCaixa;
    }

    /**
     * @return the idGerenteSupervisor
     */
    public Integer getIdGerenteSupervisor() {
        return idGerenteSupervisor;
    }

    /**
     * @param idGerenteSupervisor the idGerenteSupervisor to set
     */
    public void setIdGerenteSupervisor(Integer idGerenteSupervisor) {
        this.idGerenteSupervisor = idGerenteSupervisor;
    }

    /**
     * @return the dataHoraAbertura
     */
    public Timestamp getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    /**
     * @param dataHoraAbertura the dataHoraAbertura to set
     */
    public void setDataHoraAbertura(Timestamp dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    /**
     * @return the dataHoraFechamento
     */
    public Timestamp getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    /**
     * @param dataHoraFechamento the dataHoraFechamento to set
     */
    public void setDataHoraFechamento(Timestamp dataHoraFechamento) {
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

}