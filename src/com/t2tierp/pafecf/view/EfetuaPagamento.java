/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Janela para informar os tipos e valores
 * dos pagamentos de uma venda.</p>
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
package com.t2tierp.pafecf.view;

import com.t2tierp.pafecf.controller.TipoPagamentoController;
import com.t2tierp.pafecf.controller.TotalTipoPagamentoController;
import com.t2tierp.pafecf.infra.Biblioteca;
import com.t2tierp.pafecf.infra.Ecf;
import com.t2tierp.pafecf.infra.EfetuaPagamentoColumnModel;
import com.t2tierp.pafecf.infra.EfetuaPagamentoTableModel;
import com.t2tierp.pafecf.infra.Tef;
import com.t2tierp.pafecf.vo.TipoPagamentoVO;
import com.t2tierp.pafecf.vo.TotalTipoPagamentoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class EfetuaPagamento extends javax.swing.JDialog {

    List<TipoPagamentoVO> listaTipoPagamento = new ArrayList<TipoPagamentoVO>();
    List<TotalTipoPagamentoVO> listaTotalTipoPagamento = new ArrayList<TotalTipoPagamentoVO>();
    NumberFormat formatter = new DecimalFormat("#,###,##0.00");
    NumberFormat formataTef = new DecimalFormat("0.00");
    //
    boolean transacaoComTef = false;
    public static boolean pagamentoOK;
    private Double totalVenda = 0.0;
    private Double desconto = 0.0;
    private Double acrescimo = 0.0;
    private Double totalReceber = 0.0;
    private Double troco = 0.0;

    public EfetuaPagamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //TODO : Pra que serve essa variável?
        pagamentoOK = false;

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        //TODO : É necessário configurar a cor de cada Panel?
        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelDados.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        //preenche os valores
        if (Caixa.vendaCabecalho.getTaxaAcrescimo() != null) {
            Caixa.vendaCabecalho.setAcrescimo(Caixa.vendaCabecalho.getTaxaAcrescimo() / 100 * Caixa.vendaCabecalho.getValorVenda());
        }
        if (Caixa.vendaCabecalho.getTaxaDesconto() != null) {
            Caixa.vendaCabecalho.setDesconto(Caixa.vendaCabecalho.getTaxaDesconto() / 100 * Caixa.vendaCabecalho.getValorVenda());
        }
        //guarda valores para calculo
        totalVenda = Caixa.vendaCabecalho.getValorVenda();
        desconto = Caixa.vendaCabecalho.getDesconto();
        acrescimo = Caixa.vendaCabecalho.getAcrescimo();
        totalReceber = Caixa.vendaCabecalho.getValorVenda() + Caixa.vendaCabecalho.getAcrescimo() - Caixa.vendaCabecalho.getDesconto();
        //formata valores para exibição
        labelTotalVenda.setText(formatter.format(Caixa.vendaCabecalho.getValorVenda()));
        labelDesconto.setText(formatter.format(Caixa.vendaCabecalho.getDesconto()));
        labelAcrescimo.setText(formatter.format(Caixa.vendaCabecalho.getAcrescimo()));
        labelTotalReceber.setText(formatter.format(Caixa.vendaCabecalho.getValorVenda() + Caixa.vendaCabecalho.getAcrescimo() - Caixa.vendaCabecalho.getDesconto()));
        //
        TipoPagamentoController tipoPagamentoControl = new TipoPagamentoController();
        configuraGridValores(tipoPagamentoControl.consulta());

        //foco no primeiro valor da grid
        gridValores.editCellAt(0, 1);

        this.setPreferredSize(new Dimension(600, 320));
        this.setBounds((1024-600)/2, 400, 600, 320);
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelDados = new javax.swing.JPanel();
        panelResumoVenda = new javax.swing.JPanel();
        panelTotalVenda = new javax.swing.JPanel();
        labelDescricaoTotalVenda = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();
        panelDesconto = new javax.swing.JPanel();
        labelDescricaoDesconto = new javax.swing.JLabel();
        labelDesconto = new javax.swing.JLabel();
        panelAcrescimo = new javax.swing.JPanel();
        labelDescricaoAcrescimo = new javax.swing.JLabel();
        labelAcrescimo = new javax.swing.JLabel();
        panelTotalReceber = new javax.swing.JPanel();
        labelDescricaoTotalReceber = new javax.swing.JLabel();
        labelTotalReceber = new javax.swing.JLabel();
        panelTotalRecebido = new javax.swing.JPanel();
        labelDescricaoTotalRecebido = new javax.swing.JLabel();
        labelTotalRecebido = new javax.swing.JLabel();
        panelTroco = new javax.swing.JPanel();
        labelDescricaoTroco = new javax.swing.JLabel();
        labelTroco = new javax.swing.JLabel();
        panelValores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridValores = new javax.swing.JTable();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Efetua Pagamento para Encerrar Venda");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaCarrinho02.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelDados.setBackground(new Color(255,255,255,0));
        panelDados.setLayout(new java.awt.GridBagLayout());

        panelResumoVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo da Venda:"));
        panelResumoVenda.setPreferredSize(new java.awt.Dimension(200, 220));
        panelResumoVenda.setLayout(new java.awt.GridBagLayout());

        panelTotalVenda.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoTotalVenda.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoTotalVenda.setText("Total Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalVenda.add(labelDescricaoTotalVenda, gridBagConstraints);

        labelTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelTotalVenda.setForeground(new java.awt.Color(0, 0, 255));
        labelTotalVenda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalVenda.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalVenda.add(labelTotalVenda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumoVenda.add(panelTotalVenda, gridBagConstraints);

        panelDesconto.setLayout(new java.awt.GridBagLayout());

        labelDescricaoDesconto.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoDesconto.setForeground(new java.awt.Color(255, 0, 0));
        labelDescricaoDesconto.setText("Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDesconto.add(labelDescricaoDesconto, gridBagConstraints);

        labelDesconto.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDesconto.setForeground(new java.awt.Color(255, 0, 0));
        labelDesconto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDesconto.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDesconto.add(labelDesconto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelDesconto, gridBagConstraints);

        panelAcrescimo.setLayout(new java.awt.GridBagLayout());

        labelDescricaoAcrescimo.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoAcrescimo.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoAcrescimo.setText("Acréscimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelAcrescimo.add(labelDescricaoAcrescimo, gridBagConstraints);

        labelAcrescimo.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelAcrescimo.setForeground(new java.awt.Color(0, 0, 255));
        labelAcrescimo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelAcrescimo.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelAcrescimo.add(labelAcrescimo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelAcrescimo, gridBagConstraints);

        panelTotalReceber.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalReceber.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoTotalReceber.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoTotalReceber.setText("Total a Receber:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalReceber.add(labelDescricaoTotalReceber, gridBagConstraints);

        labelTotalReceber.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelTotalReceber.setForeground(new java.awt.Color(0, 0, 255));
        labelTotalReceber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalReceber.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalReceber.add(labelTotalReceber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTotalReceber, gridBagConstraints);

        panelTotalRecebido.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalRecebido.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoTotalRecebido.setForeground(new java.awt.Color(0, 153, 0));
        labelDescricaoTotalRecebido.setText("Total Recebido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalRecebido.add(labelDescricaoTotalRecebido, gridBagConstraints);

        labelTotalRecebido.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelTotalRecebido.setForeground(new java.awt.Color(0, 153, 0));
        labelTotalRecebido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalRecebido.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalRecebido.add(labelTotalRecebido, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTotalRecebido, gridBagConstraints);

        panelTroco.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTroco.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelDescricaoTroco.setForeground(new java.awt.Color(255, 0, 0));
        labelDescricaoTroco.setText("Troco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco.add(labelDescricaoTroco, gridBagConstraints);

        labelTroco.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelTroco.setForeground(new java.awt.Color(255, 0, 0));
        labelTroco.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTroco.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco.add(labelTroco, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTroco, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelDados.add(panelResumoVenda, gridBagConstraints);

        panelValores.setBorder(javax.swing.BorderFactory.createTitledBorder("Informe os valores pagos:"));
        panelValores.setMinimumSize(new java.awt.Dimension(216, 220));
        panelValores.setPreferredSize(new java.awt.Dimension(200, 220));
        panelValores.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 220));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 220));

        gridValores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(gridValores);

        panelValores.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelDados.add(panelValores, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelDados, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setMinimumSize(new java.awt.Dimension(261, 30));
        panelBotoes.setPreferredSize(new java.awt.Dimension(261, 30));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        panelBotoes.add(botaoConfirma, gridBagConstraints);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        confirma();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JTable gridValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAcrescimo;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelDescricaoAcrescimo;
    private javax.swing.JLabel labelDescricaoDesconto;
    private javax.swing.JLabel labelDescricaoTotalReceber;
    private javax.swing.JLabel labelDescricaoTotalRecebido;
    private javax.swing.JLabel labelDescricaoTotalVenda;
    private javax.swing.JLabel labelDescricaoTroco;
    private javax.swing.JLabel labelTotalReceber;
    private javax.swing.JLabel labelTotalRecebido;
    private javax.swing.JLabel labelTotalVenda;
    private javax.swing.JLabel labelTroco;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelDesconto;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelResumoVenda;
    private javax.swing.JPanel panelTotalReceber;
    private javax.swing.JPanel panelTotalRecebido;
    private javax.swing.JPanel panelTotalVenda;
    private javax.swing.JPanel panelTroco;
    private javax.swing.JPanel panelValores;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            confirma();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private void confirma() {
        Double total = 0.0;
        Double valorDinheiro = 0.0;
        TipoPagamentoVO tipoPagamento = new TipoPagamentoVO();

        AbstractTableModel modelo = (AbstractTableModel) gridValores.getModel();
        for (int linha = 0; linha < modelo.getRowCount(); linha++) {
            if ((Double) modelo.getValueAt(linha, 1) != null) {
                if ((Double) modelo.getValueAt(linha, 1) > 0) {

                    TotalTipoPagamentoVO totalTipoPagamento = new TotalTipoPagamentoVO();
                    totalTipoPagamento.setTipoPagamentoVO(new TipoPagamentoVO());
                    total = total + (Double) modelo.getValueAt(linha, 1);
                    TipoPagamentoController tipoPagamentoControl = new TipoPagamentoController();
                    // consulta o tipo de pagamento pelo ID
                    tipoPagamento = tipoPagamentoControl.consultaPeloId(listaTipoPagamento.get(linha).getId());
                    // verifica se o tipo de pagamento é dinheiro
                    if (tipoPagamento.getDescricao().equals("DINHEIRO")) {
                        valorDinheiro = (Double) modelo.getValueAt(linha, 1);
                    }
                    if (tipoPagamento.getTEF().equals("S")) {
                        transacaoComTef = true;
                    }

                    totalTipoPagamento.setIdVendaCabecalho(Caixa.vendaCabecalho.getId());
                    totalTipoPagamento.getTipoPagamentoVO().setId(tipoPagamento.getId());
                    totalTipoPagamento.setValor((Double) modelo.getValueAt(linha, 1));
                    totalTipoPagamento.getTipoPagamentoVO().setCodigo(tipoPagamento.getCodigo());
                    totalTipoPagamento.getTipoPagamentoVO().setTEF(tipoPagamento.getTEF());
                    totalTipoPagamento.getTipoPagamentoVO().setImprimeVinculado(tipoPagamento.getImprimeVinculado());
                    totalTipoPagamento.getTipoPagamentoVO().setDescricao(tipoPagamento.getDescricao());
                    listaTotalTipoPagamento.add(totalTipoPagamento);
                }
            }
        }

        if (total < totalReceber) {
            JOptionPane.showMessageDialog(rootPane, "Valores pagos pelo cliente não são suficientes para efetuar o total do pagamento.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            gridValores.requestFocus();
        } else if (total >= totalReceber) {
            troco = total - totalReceber;
            if (transacaoComTef == true && troco > 0) {
                JOptionPane.showMessageDialog(rootPane, "Transação com dinheiro e cartão não pode ter troco.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                //foco no primeiro valor da grid
                gridValores.editCellAt(0, 1);
            } else {
                if (troco > valorDinheiro) {
                    JOptionPane.showMessageDialog(rootPane, "Recebimento a maior. Corrija os valores.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    //foco no primeiro valor da grid
                    gridValores.editCellAt(0, 1);
                } else {
                    Caixa.vendaCabecalho.setValorFinal(totalReceber);
                    Caixa.vendaCabecalho.setValorRecebido(total);
                    Caixa.vendaCabecalho.setTroco(troco);
                    labelTotalRecebido.setText(formatter.format(total));
                    labelTroco.setText(formatter.format(troco));
                    String[] opcoes = {"Sim", "Não"};
                    int escolha = JOptionPane.showOptionDialog(null, "Confirma os valores e encerra venda?", "Encerrar venda.",
                            JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, opcoes, null);
                    if (escolha == 0) {
                        sim();
                    } else {
                        nao();
                    }
                }
            }
        }

    }

    private void sim() {
        try {
            //TODO : Precisamos armazenas esses valores?
            String pValorPago = "";
            String pNumeroCupom = "";
            String pIdentificacao = "";
            Integer pNumeroTransacao = 0;
            //TODO : Porque essa forma de pagamento está setada para '02'? Como corrigir isso?
            String pFormaPagamento = "02";

            if (desconto > 0) {
                Ecf.subTotalizaCupom(desconto * -1);
            } else if (acrescimo > 0) {
                Ecf.subTotalizaCupom(acrescimo);
            } else {
                Ecf.subTotalizaCupom(0.0);
            }

            TotalTipoPagamentoVO totalTipoPagamento = new TotalTipoPagamentoVO();

            for (int i = 0; i < listaTotalTipoPagamento.size(); i++) {
                totalTipoPagamento = listaTotalTipoPagamento.get(i);
                //se o tipo de pagamento for tef
                if (totalTipoPagamento.getTipoPagamentoVO().getTEF().equals("S")) {
                    String valorTef = formataTef.format(totalTipoPagamento.getValor());
                    //TODO  : A escolha desses parâmetros está correta?
                    Integer retorno = Tef.realizaTransacao(Caixa.ACBrECF.getNumCOO(), Caixa.ACBrECF.getNumCOO(), valorTef, Integer.valueOf(Caixa.ACBrECF.getNumCOO()));
                    switch (retorno) {
                        case 1: // OK
                            // TODO : Implementar mais alguma coisa caso ocorra tudo bem com o comando?
                            pValorPago = totalTipoPagamento.getValor().toString();
                            pNumeroCupom = Caixa.ACBrECF.getNumCOO();
                            pIdentificacao = Caixa.ACBrECF.getNumCOO();
                            pNumeroTransacao = Integer.valueOf(Caixa.ACBrECF.getNumCOO());
                            break;
                        case -1: // Transação não realizada
                            // TODO : Devemos fazer mais alguma coisa aqui? O que?
                            JOptionPane.showMessageDialog(rootPane, "Erro na transação com cartão.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 0: // GP Inativo
                            // TODO : Se o GP estiver inativo, o que devemos fazer?
                            JOptionPane.showMessageDialog(rootPane, "Gerenciador padrão inativo.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    Ecf.efetuaFormaPagamento(totalTipoPagamento);
                }
                //caso nao seja tef
                if (!totalTipoPagamento.getTipoPagamentoVO().getTEF().equals("S")) {
                    Ecf.efetuaFormaPagamento(totalTipoPagamento);
                }
            }
            //fecha o cupom
            String mensagem = "";
            if (Caixa.vendaCabecalho.getIdPreVenda() != null) {
                mensagem = "PV" + Biblioteca.repete("0", 10 - Caixa.vendaCabecalho.getIdPreVenda().toString().length()) + Caixa.vendaCabecalho.getIdPreVenda();
            }
            if (Caixa.vendaCabecalho.getIdDAV() != null) {
                mensagem = mensagem + (char) 13 + (char) 10 + "DAV" + Biblioteca.repete("0", 10 - Caixa.vendaCabecalho.getIdDAV().toString().length()) + Caixa.vendaCabecalho.getIdDAV();
            }
            mensagem = mensagem + (char) 13 + (char) 10 + Caixa.MD5;
            mensagem = mensagem + (char) 13 + (char) 10 + Caixa.configuracao.getMensagemCupom();

            Ecf.fechaCupom(mensagem);
            if (transacaoComTef) {
                Tef.imprimeTransacao(pFormaPagamento, pValorPago, pNumeroCupom, pIdentificacao, pNumeroTransacao);
                String NSU = Tef.confirmaTransacao(pNumeroTransacao);
                //TODO : 
                //Esse procedimento para armazenar o NSU está correto?
                //Em uma situação específica vai ocorrer um erro. Qual a situação?
                listaTotalTipoPagamento.get(listaTotalTipoPagamento.size() - 1).setNSU(NSU);
            }
            //grava dados de detalhe no banco
            TotalTipoPagamentoController totalTipoPagamentoControl = new TotalTipoPagamentoController();
            totalTipoPagamentoControl.gravaTotaisVenda((ArrayList<TotalTipoPagamentoVO>) listaTotalTipoPagamento);
            pagamentoOK = true;
            dispose();
            //TODO : Fazer os roteiros de testes do TEF: Amex, Visa, Master, Hiper junto com os participantes. Acompanhamento via EAD
            //TODO : Qual seria a melhor estratégia para compra com multiplos cartoes? Por exemplo: uma compra rateada entre varios amigos
            //TODO : E se for necessário estornar um pagamento? Como devemos fazer?
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nao() {
        //foco no primeiro valor da grid
        gridValores.editCellAt(0, 1);
    }

    private void configuraGridValores(List<TipoPagamentoVO> listaTipoPagamento) {
        this.listaTipoPagamento = listaTipoPagamento;

        gridValores.setModel(new EfetuaPagamentoTableModel(listaTipoPagamento));
        gridValores.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridValores";
            }
        });

        gridValores.setAutoCreateColumnsFromModel(false);
        gridValores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridValores.getFontMetrics(gridValores.getFont());
        gridValores.setColumnModel(new EfetuaPagamentoColumnModel(fm));
    }
}
