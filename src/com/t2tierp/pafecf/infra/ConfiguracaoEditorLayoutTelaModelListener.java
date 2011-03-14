package com.t2tierp.pafecf.infra;

/**
 * Interface ConfiguracaoEditorLayoutTelaModelListener.
 *
 * @author Leonardo Ono
 * @version 1.0 (21/11/2010)
 */
public interface ConfiguracaoEditorLayoutTelaModelListener 
        extends ConfiguracaoComponenteModelListener{

    void historicoFoiSalvo(int indice, String descricao);
    void historicoFoiAvancado(int indice, String descricao);
    void historicoFoiVoltado(int indice, String descricao);

    void visibilidadeDaGrideAlterada();
    void tamanhoDaGrideAlterada();
}
