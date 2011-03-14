package com.t2tierp.pafecf.infra;

/**
 * Interface ConfiguracaoComponenteModelListener.
 * 
 * @author Leonardo Ono
 * @version 1.0 (20/11/2010)
 */
public interface ConfiguracaoComponenteModelListener {

    void tamanhoDoConfiguracaoComponenteModelFoiAlterado(
            ConfiguracaoComponenteModel componenteModel);

    void posicaoDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel);

    void configuracaoComponenteModelFoiSelecionado(
            ConfiguracaoComponenteModel componenteModel);

    void configuracaoComponenteModelFoiDesselecionado(
            ConfiguracaoComponenteModel componenteModel);

    void textoDoConfiguracaoComponenteModelFoiAlterado(
            ConfiguracaoComponenteModel componenteModel);
    
    void fonteDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel);

    void flagDeslocavelDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel);

    void flagRedimensionavelDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel);

    void flagSelecionadoDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel);
 
}