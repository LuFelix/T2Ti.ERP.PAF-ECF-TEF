package com.t2tierp.pafecf.infra;

import com.t2tierp.pafecf.vo.PosicaoComponentesVO;

/**
 * Classe ConfiguracaoComponenteModel.
 *
 * @author Leonardo Ono
 * @version 1.0 (20/11/2010)
 */
public class ConfiguracaoComponenteModel extends PosicaoComponentesVO {

    private boolean selecionado = false;
    private boolean deslocavel = true;
    private boolean redimensionavel = true;
    private ConfiguracaoComponenteModelListener componenteModelListener;

    public ConfiguracaoComponenteModel() {
    }

    public ConfiguracaoComponenteModel(
            ConfiguracaoComponenteModelListener ComponenteModelListener) {
        
        this.componenteModelListener = ComponenteModelListener;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        if (this.selecionado == selecionado) return;
        this.selecionado = selecionado;
        if (componenteModelListener != null) componenteModelListener
            .flagSelecionadoDoConfiguracaoComponenteModelFoiAlterada(this); 
    }

    public boolean isDeslocavel() {
        return deslocavel;
    }

    public void setDeslocavel(boolean deslocavel) {
        if (this.deslocavel == deslocavel) return;
        this.deslocavel = deslocavel;
        if (componenteModelListener != null) componenteModelListener
            .flagDeslocavelDoConfiguracaoComponenteModelFoiAlterada(this);
    }

    public boolean isRedimensionavel() {
        return redimensionavel;
    }

    public void setRedimensionavel(boolean redimensionavel) {
        if (this.redimensionavel == redimensionavel) return;
        this.redimensionavel = redimensionavel;
        if (componenteModelListener != null) componenteModelListener
            .flagRedimensionavelDoConfiguracaoComponenteModelFoiAlterada(this);
    }

    public ConfiguracaoComponenteModelListener getComponenteModelListener() {
        return componenteModelListener;
    }

    public void setComponenteModelListener(
            ConfiguracaoComponenteModelListener ComponenteModelListener) {

        this.componenteModelListener = ComponenteModelListener;
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public void setIdResolucao(Integer idResolucao) {
        super.setIdResolucao(idResolucao);
    }

    @Override
    public void setNomeComponente(String nomeComponente) {
        super.setNomeComponente(nomeComponente);
    }

    @Override
    public void setAltura(Integer altura) {
        if (altura == getAltura()) return;
        super.setAltura(altura);
        if (componenteModelListener != null)
            componenteModelListener
            .tamanhoDoConfiguracaoComponenteModelFoiAlterado(this);
    }

    @Override
    public void setLargura(Integer largura) {
        if (largura == getLargura()) return;
        super.setLargura(largura);
        if (componenteModelListener != null)
            componenteModelListener
            .tamanhoDoConfiguracaoComponenteModelFoiAlterado(this);
    }

    @Override
    public void setTopo(Integer topo) {
        if (topo == getTopo()) return;
        super.setTopo(topo);
        if (componenteModelListener != null)
            componenteModelListener
            .posicaoDoConfiguracaoComponenteModelFoiAlterada(this);
    }

    @Override
    public void setEsquerda(Integer esquerda) {
        if (esquerda == getEsquerda()) return;
        super.setEsquerda(esquerda);
        if (componenteModelListener != null)
            componenteModelListener
            .posicaoDoConfiguracaoComponenteModelFoiAlterada(this);
    }

    @Override
    public void setTamanhoFonte(Integer tamanhoFonte) {
        if (tamanhoFonte == getTamanhoFonte()) return;
        super.setTamanhoFonte(tamanhoFonte);
        if (componenteModelListener != null)
            componenteModelListener
            .tamanhoDoConfiguracaoComponenteModelFoiAlterado(this);
    }

    @Override
    public void setTextoComponente(String textoComponente) {
        if (textoComponente.equals(getTextoComponente())) return;
        super.setTextoComponente(textoComponente);
        if (componenteModelListener != null)
            componenteModelListener
            .textoDoConfiguracaoComponenteModelFoiAlterado(this);
    }

    @Override
    public ConfiguracaoComponenteModel clone() {
        ConfiguracaoComponenteModel ccm = new ConfiguracaoComponenteModel();
        ccm.setSelecionado(selecionado);
        ccm.setDeslocavel(deslocavel);
        ccm.setRedimensionavel(redimensionavel);
        ccm.setAltura(getAltura());
        ccm.setEsquerda(getEsquerda());
        ccm.setId(getId());
        ccm.setIdResolucao(getIdResolucao());
        ccm.setLargura(getLargura());
        ccm.setNomeComponente(getNomeComponente());
        ccm.setTamanhoFonte(getTamanhoFonte());
        ccm.setTextoComponente(getTextoComponente());
        ccm.setTopo(getTopo());
        ccm.setComponenteModelListener(componenteModelListener);
        return ccm;
    }

    @Override
    public String toString() {
        return super.getNomeComponente();
    }


}