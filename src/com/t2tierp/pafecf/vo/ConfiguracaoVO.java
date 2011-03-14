package com.t2tierp.pafecf.vo;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Configuração.</p>
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
public class ConfiguracaoVO {
    private Integer id;
    private String mensagemCupom;
    private String portaEcf;
    private String portaPinpad;
    private String portaBalanca;
    private String ipServidor;
    private String ipSitef;
    private String tipoTef;
    private String sincronizado;
    private EmpresaVO empresaVO;
    private ImpressoraVO impressoraVO;

    public ConfiguracaoVO() {
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
     * @return the mensagemCupom
     */
    public String getMensagemCupom() {
        return mensagemCupom;
    }

    /**
     * @param mensagemCupom the mensagemCupom to set
     */
    public void setMensagemCupom(String mensagemCupom) {
        this.mensagemCupom = mensagemCupom;
    }

    /**
     * @return the portaEcf
     */
    public String getPortaEcf() {
        return portaEcf;
    }

    /**
     * @param portaEcf the portaEcf to set
     */
    public void setPortaEcf(String portaEcf) {
        this.portaEcf = portaEcf;
    }

    /**
     * @return the portaPinpad
     */
    public String getPortaPinpad() {
        return portaPinpad;
    }

    /**
     * @param portaPinpad the portaPinpad to set
     */
    public void setPortaPinpad(String portaPinpad) {
        this.portaPinpad = portaPinpad;
    }

    /**
     * @return the portaBalanca
     */
    public String getPortaBalanca() {
        return portaBalanca;
    }

    /**
     * @param portaBalanca the portaBalanca to set
     */
    public void setPortaBalanca(String portaBalanca) {
        this.portaBalanca = portaBalanca;
    }

    /**
     * @return the ipServidor
     */
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     * @param ipServidor the ipServidor to set
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     * @return the ipSitef
     */
    public String getIpSitef() {
        return ipSitef;
    }

    /**
     * @param ipSitef the ipSitef to set
     */
    public void setIpSitef(String ipSitef) {
        this.ipSitef = ipSitef;
    }

    /**
     * @return the tipoTef
     */
    public String getTipoTef() {
        return tipoTef;
    }

    /**
     * @param tipoTef the tipoTef to set
     */
    public void setTipoTef(String tipoTef) {
        this.tipoTef = tipoTef;
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
     * @return the empresaVO
     */
    public EmpresaVO getEmpresaVO() {
        return empresaVO;
    }

    /**
     * @param empresaVO the empresaVO to set
     */
    public void setEmpresaVO(EmpresaVO empresaVO) {
        this.empresaVO = empresaVO;
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

}
