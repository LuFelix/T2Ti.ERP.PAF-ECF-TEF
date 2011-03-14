package com.t2tierp.pafecf.vo;

import java.util.Collection;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Caixa.</p>
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
public class CaixaVO {
    private Integer id;
    private String nome;
    private OperadorVO operadorVO;
    private ImpressoraVO impressoraVO;
    private Collection<VendaCabecalhoVO> vendaCabecalhoVOCollection;
    private Collection<MovimentoVO> movimentoVOCollection;
    private Collection<DocumentosEmitidosVO> documentosEmitidosVOCollection;
    private Collection<R02VO> r02VOCollection;
    private Collection<R06VO> r06VOCollection;

    public CaixaVO() {
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
     * @return the operadorVO
     */
    public OperadorVO getOperadorVO() {
        return operadorVO;
    }

    /**
     * @param operadorVO the operadorVO to set
     */
    public void setOperadorVO(OperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    /**
     * @return the impressoraVO
     */
    public ImpressoraVO getImpressoraVO() {
        return impressoraVO;
    }

    /**
     * @param impressoraVO the impressoraVO to set
     */
    public void setImpressoraVO(ImpressoraVO impressoraVO) {
        this.impressoraVO = impressoraVO;
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
     * @return the movimentoVOCollection
     */
    public Collection<MovimentoVO> getMovimentoVOCollection() {
        return movimentoVOCollection;
    }

    /**
     * @param movimentoVOCollection the movimentoVOCollection to set
     */
    public void setMovimentoVOCollection(Collection<MovimentoVO> movimentoVOCollection) {
        this.movimentoVOCollection = movimentoVOCollection;
    }

    /**
     * @return the documentosEmitidosVOCollection
     */
    public Collection<DocumentosEmitidosVO> getDocumentosEmitidosVOCollection() {
        return documentosEmitidosVOCollection;
    }

    /**
     * @param documentosEmitidosVOCollection the documentosEmitidosVOCollection to set
     */
    public void setDocumentosEmitidosVOCollection(Collection<DocumentosEmitidosVO> documentosEmitidosVOCollection) {
        this.documentosEmitidosVOCollection = documentosEmitidosVOCollection;
    }

    /**
     * @return the r02VOCollection
     */
    public Collection<R02VO> getR02VOCollection() {
        return r02VOCollection;
    }

    /**
     * @param r02VOCollection the r02VOCollection to set
     */
    public void setR02VOCollection(Collection<R02VO> r02VOCollection) {
        this.r02VOCollection = r02VOCollection;
    }

    /**
     * @return the r06VOCollection
     */
    public Collection<R06VO> getR06VOCollection() {
        return r06VOCollection;
    }

    /**
     * @param r06VOCollection the r06VOCollection to set
     */
    public void setR06VOCollection(Collection<R06VO> r06VOCollection) {
        this.r06VOCollection = r06VOCollection;
    }

}