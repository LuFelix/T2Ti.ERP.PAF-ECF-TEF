package com.t2tierp.pafecf.infra;

import com.t2tierp.pafecf.vo.PosicaoComponentesVO;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Implementação do Model para o Editor de Layout de Tela
 * disponível em configurações.</p>
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
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @version 1.0 (20/11/2010)
 */
public class ConfiguracaoEditorLayoutTelaModel
        implements ConfiguracaoEditorLayoutTelaModelListener {

    private ConfiguracaoComponenteModel componenteModelSelecionado;
    private List<ConfiguracaoEditorLayoutTelaModelListener> listeners
            = new ArrayList<ConfiguracaoEditorLayoutTelaModelListener>();

    private List<ConfiguracaoComponenteModel>
            listaDeConfiguracaoComponenteModel;

    private Historico historico;

    private boolean grideVisivel;
    private int tamanhoDaGrideEmPixels = 5;

    public boolean isGrideVisivel() {
        return grideVisivel;
    }

    public void setGrideVisivel(boolean grideVisivel) {
        this.grideVisivel = grideVisivel;
        visibilidadeDaGrideAlterada();
    }

    public int getTamanhoDaGrideEmPixels() {
        return tamanhoDaGrideEmPixels;
    }

    public void setTamanhoDaGrideEmPixels(int tamanhoDaGrideEmPixels) {
        this.tamanhoDaGrideEmPixels = tamanhoDaGrideEmPixels;
        tamanhoDaGrideAlterada();
    }

    public List<ConfiguracaoComponenteModel> getListaDePosicaoComponentesVO() {
        return listaDeConfiguracaoComponenteModel;
    }

    public void removeConfiguracaoEditorLayoutTelaModelListener(
            ConfiguracaoEditorLayoutTelaModelListener listener) {

        listeners.remove(listener);
    }

    public void addConfiguracaoEditorLayoutTelaModelListener(
            ConfiguracaoEditorLayoutTelaModelListener listener) {

        listeners.add(listener);
    }

    public void setListaDePosicaoComponentesVO(
            List<PosicaoComponentesVO> listaDePosicaoComponentesVO) {

        listaDeConfiguracaoComponenteModel =
                new ArrayList<ConfiguracaoComponenteModel>();
        
        for (PosicaoComponentesVO pcvo : listaDePosicaoComponentesVO) {
            ConfiguracaoComponenteModel ccm = new ConfiguracaoComponenteModel();
            ccm.setAltura(pcvo.getAltura());
            ccm.setEsquerda(pcvo.getEsquerda());
            ccm.setId(pcvo.getId());
            ccm.setIdResolucao(pcvo.getIdResolucao());
            ccm.setLargura(pcvo.getLargura());
            ccm.setNomeComponente(pcvo.getNomeComponente());
            ccm.setTamanhoFonte(pcvo.getTamanhoFonte());
            ccm.setTextoComponente(pcvo.getTextoComponente());
            ccm.setTopo(pcvo.getTopo());
            ccm.setComponenteModelListener(this);
            listaDeConfiguracaoComponenteModel.add(ccm);
        }
    }

    public ConfiguracaoComponenteModel getComponenteModelSelecionado() {
        return componenteModelSelecionado;
    }

    public void setComponenteModelSelecionado(
            ConfiguracaoComponenteModel componenteModelSelecionado) {

        if (this.componenteModelSelecionado == componenteModelSelecionado)
            return;

        desselecionarTodosComponentes();
        this.componenteModelSelecionado = componenteModelSelecionado;
        this.componenteModelSelecionado.setSelecionado(true);
    }

    public void alterarPosicaoEsquerda(int x) {
        criarHistoricoSituacaoInicialSeForPrimeiraAlteracao();
        if (componenteModelSelecionado == null) return;
        componenteModelSelecionado.setEsquerda(x);
    }

    public void alterarPosicaoTopo(int y) {
        criarHistoricoSituacaoInicialSeForPrimeiraAlteracao();
        if (componenteModelSelecionado == null) return;
        componenteModelSelecionado.setTopo(y);
    }

    public void alterarTamanhoLargura(int width) {
        criarHistoricoSituacaoInicialSeForPrimeiraAlteracao();
        if (componenteModelSelecionado == null) return;
        componenteModelSelecionado.setLargura(width);
    }

    public void alterarTamanhoAltura(int height) {
        criarHistoricoSituacaoInicialSeForPrimeiraAlteracao();
        if (componenteModelSelecionado == null) return;
        componenteModelSelecionado.setAltura(height);
    }

    // ConfiguracaoComponenteModelListener

    @Override
    public void tamanhoDoConfiguracaoComponenteModelFoiAlterado(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.tamanhoDoConfiguracaoComponenteModelFoiAlterado(
            componenteModel);
    }

    @Override
    public void posicaoDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.posicaoDoConfiguracaoComponenteModelFoiAlterada(
            componenteModel);
    }

    @Override
    public void configuracaoComponenteModelFoiSelecionado(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.configuracaoComponenteModelFoiSelecionado(componenteModel);
    }

    @Override
    public void configuracaoComponenteModelFoiDesselecionado(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.configuracaoComponenteModelFoiDesselecionado(
            componenteModel);
    }

    @Override
    public void textoDoConfiguracaoComponenteModelFoiAlterado(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.textoDoConfiguracaoComponenteModelFoiAlterado(
            componenteModel);
    }

    @Override
    public void fonteDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.fonteDoConfiguracaoComponenteModelFoiAlterada(
            componenteModel);
    }

    @Override
    public void flagDeslocavelDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.flagDeslocavelDoConfiguracaoComponenteModelFoiAlterada(
            componenteModel);
    }

    @Override
    public void flagRedimensionavelDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener
            .flagRedimensionavelDoConfiguracaoComponenteModelFoiAlterada(
            componenteModel);
    }

    @Override
    public void flagSelecionadoDoConfiguracaoComponenteModelFoiAlterada(
            ConfiguracaoComponenteModel componenteModel) {

        for (ConfiguracaoComponenteModelListener listener : listeners)
            listener.flagSelecionadoDoConfiguracaoComponenteModelFoiAlterada(
            componenteModel);
    }

    // Historico

    private void criarHistoricoSituacaoInicialSeForPrimeiraAlteracao() {
        if (historico != null) return;
        historico = new Historico("[Situacao Inicial]"
                , componenteModelSelecionado
                , componenteModelSelecionado.clone());
    }

    public void salvarHistorico(String descricao) {
        historico = historico.salvar(descricao);
        historico.exibirTodoHistorico();
        historicoFoiSalvo(0, historico.getDescricao());
    }

    public void voltarHistorico() throws Exception {
        desselecionarTodosComponentes();
        historico = historico.voltar();
        historico.exibirTodoHistorico();
        historicoFoiVoltado(1, historico.getDescricao());

    }

    public void avancarHistorico() throws Exception {
        desselecionarTodosComponentes();
        historico = historico.avancar();
        historico.exibirTodoHistorico();
        historicoFoiAvancado(2, historico.getDescricao());
    }

    private void desselecionarTodosComponentes() {
        for (ConfiguracaoComponenteModel c
                : listaDeConfiguracaoComponenteModel) {

            c.setSelecionado(false);
        }
    }

    // ConfiguracaoEditorLayoutTelaModelListener

    @Override
    public void historicoFoiSalvo(int indice, String descricao) {

        for (ConfiguracaoEditorLayoutTelaModelListener listener : listeners)
            listener.historicoFoiSalvo(indice, descricao);
    }

    @Override
    public void historicoFoiAvancado(int indice, String descricao) {

        for (ConfiguracaoEditorLayoutTelaModelListener listener : listeners)
            listener.historicoFoiSalvo(indice, descricao);
    }

    @Override
    public void historicoFoiVoltado(int indice, String descricao) {

        for (ConfiguracaoEditorLayoutTelaModelListener listener : listeners)
            listener.historicoFoiVoltado(indice, descricao);
    }

    @Override
    public void visibilidadeDaGrideAlterada() {

        for (ConfiguracaoEditorLayoutTelaModelListener listener : listeners)
            listener.visibilidadeDaGrideAlterada();
    }

    @Override
    public void tamanhoDaGrideAlterada() {

        for (ConfiguracaoEditorLayoutTelaModelListener listener : listeners)
            listener.tamanhoDaGrideAlterada();
    }

    private class Historico {

        private int indice;

        private String descricao;
        private ConfiguracaoComponenteModel configuracaoComponenteModelOriginal;
        private ConfiguracaoComponenteModel configuracaoComponenteModelSalvo;

        private Historico anterior;
        private Historico proximo;

        public Historico(String descricao
            , ConfiguracaoComponenteModel configuracaoComponenteModelOriginal
            , ConfiguracaoComponenteModel configuracaoComponenteModelSalvo) {

            this.descricao = descricao;
            this.configuracaoComponenteModelOriginal
                    = configuracaoComponenteModelOriginal;
            this.configuracaoComponenteModelSalvo
                    = configuracaoComponenteModelSalvo;
        }

        public int getIndice() {
            return indice;
        }

        public ConfiguracaoComponenteModel
                getConfiguracaoComponenteModelOriginal() {

            return configuracaoComponenteModelOriginal;
        }

        public void setConfiguracaoComponenteModelOriginal(
            ConfiguracaoComponenteModel configuracaoComponenteModelOriginal) {

            this.configuracaoComponenteModelOriginal
                    = configuracaoComponenteModelOriginal;
        }

        public ConfiguracaoComponenteModel 
                getConfiguracaoComponenteModelSalvo() {

            return configuracaoComponenteModelSalvo;
        }

        public void setConfiguracaoComponenteModelSalvo(
                ConfiguracaoComponenteModel configuracaoComponenteModelSalvo) {

            this.configuracaoComponenteModelSalvo
                    = configuracaoComponenteModelSalvo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        private void restaurar() {
            configuracaoComponenteModelOriginal.setSelecionado(true);

            configuracaoComponenteModelOriginal.setDeslocavel(
                    configuracaoComponenteModelSalvo.isDeslocavel());

            configuracaoComponenteModelOriginal.setRedimensionavel(
                    configuracaoComponenteModelSalvo.isRedimensionavel());

            configuracaoComponenteModelOriginal.setComponenteModelListener(
                configuracaoComponenteModelSalvo.getComponenteModelListener());

            configuracaoComponenteModelOriginal.setAltura(
                    configuracaoComponenteModelSalvo.getAltura());

            configuracaoComponenteModelOriginal.setEsquerda(
                    configuracaoComponenteModelSalvo.getEsquerda());

            configuracaoComponenteModelOriginal.setId(
                    configuracaoComponenteModelSalvo.getId());

            configuracaoComponenteModelOriginal.setIdResolucao(
                    configuracaoComponenteModelSalvo.getIdResolucao());

            configuracaoComponenteModelOriginal.setLargura(
                    configuracaoComponenteModelSalvo.getLargura());

            configuracaoComponenteModelOriginal.setNomeComponente(
                    configuracaoComponenteModelSalvo.getNomeComponente());

            configuracaoComponenteModelOriginal.setTamanhoFonte(
                    configuracaoComponenteModelSalvo.getTamanhoFonte());

            configuracaoComponenteModelOriginal.setTextoComponente(
                    configuracaoComponenteModelSalvo.getTextoComponente());

            configuracaoComponenteModelOriginal.setTopo(
                    configuracaoComponenteModelSalvo.getTopo());
        }

        @Override
        public String toString() {
            return "Historico{ \n" +
                    "indice=" + indice + "\n" +
                    "descricao=" + descricao + "}\n\n";
        }

        public Historico salvar(String descricao) {
            Historico novo = new Historico(descricao
                    , componenteModelSelecionado
                    , componenteModelSelecionado.clone());
            novo.indice = this.indice + 1;
            novo.anterior = this;
            this.proximo = novo;
            return novo;
        }

        public Historico avancar() throws Exception {
            if (proximo == null)
                throw new Exception("Nao ha mais historico para avancar !");
            Historico historicoRestaurar = this.proximo;
            historicoRestaurar.restaurar();
            return historicoRestaurar;
        }

        public Historico voltar() throws Exception {
            if (anterior == null)
                throw new Exception("Nao ha mais historico para voltar !");
            Historico historicoRestaurar = this.anterior;
            historicoRestaurar.restaurar();
            return historicoRestaurar;
        }

        private void exibirTodoHistorico() {
            System.out.println("=========================================");
            exibirTodoHistoricoAnterior(indice);
            System.out.println("[X]-"+ "(" + indice + ")" + descricao);
            exibirTodoHistoricoPosterior(indice);
            System.out.println("=========================================");
        }

        private void exibirTodoHistoricoAnterior(int indiceAtual) {
            if (anterior == null) {
                exibirIndiceDescricao(indice, indiceAtual, descricao);
                return;
            }
            anterior.exibirTodoHistoricoAnterior(indiceAtual);
            exibirIndiceDescricao(indice, indiceAtual, descricao);
        }

        private void exibirTodoHistoricoPosterior(int indiceAtual) {
            exibirIndiceDescricao(indice, indiceAtual, descricao);
            if (proximo == null) return;
            proximo.exibirTodoHistoricoPosterior(indiceAtual);
        }

        private void exibirIndiceDescricao(
                int indice, int indiceAtual, String descricao) {

            if (indice != indiceAtual)
                System.out.println("[ ]-"+ "(" + indice + ")" + descricao);
        }

    }

}
