package com.t2tierp.pafecf.vo;

import java.util.Collection;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela CFOP.</p>
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
public class CfopVO {
    private Integer id;
    private Integer cfop;
    private String descricao;
    private String aplicacao;
    private Collection<Nf2DetalheVO> nf2DetalheVOCollection;
    private Collection<VendaDetalheVO> vendaDetalheVOCollection;
    private Collection<Nf2CabecalhoVO> nf2CabecalhoVOCollection;
    private Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection;

    public CfopVO() {
    }

    public CfopVO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public Collection<Nf2DetalheVO> getNf2DetalheVOCollection() {
        return nf2DetalheVOCollection;
    }

    public void setNf2DetalheVOCollection(Collection<Nf2DetalheVO> nf2DetalheVOCollection) {
        this.nf2DetalheVOCollection = nf2DetalheVOCollection;
    }

    public Collection<VendaDetalheVO> getVendaDetalheVOCollection() {
        return vendaDetalheVOCollection;
    }

    public void setVendaDetalheVOCollection(Collection<VendaDetalheVO> vendaDetalheVOCollection) {
        this.vendaDetalheVOCollection = vendaDetalheVOCollection;
    }

    public Collection<Nf2CabecalhoVO> getNf2CabecalhoVOCollection() {
        return nf2CabecalhoVOCollection;
    }

    public void setNf2CabecalhoVOCollection(Collection<Nf2CabecalhoVO> nf2CabecalhoVOCollection) {
        this.nf2CabecalhoVOCollection = nf2CabecalhoVOCollection;
    }

    public Collection<VendaCabecalhoVO> getVendaCabecalhoVOCollection() {
        return vendaCabecalhoVOCollection;
    }

    public void setVendaCabecalhoVOCollection(Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection) {
        this.vendaCabecalhoVOCollection = vendaCabecalhoVOCollection;
    }

}
