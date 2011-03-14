/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Janela para iniciar um movimento.</p>
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

import com.t2tierp.pafecf.controller.MovimentoController;
import com.t2tierp.pafecf.controller.OperadorController;
import com.t2tierp.pafecf.controller.TurnoController;
import com.t2tierp.pafecf.infra.TurnoColumnModel;
import com.t2tierp.pafecf.infra.TurnoTableModel;
import com.t2tierp.pafecf.vo.MovimentoVO;
import com.t2tierp.pafecf.vo.OperadorVO;
import com.t2tierp.pafecf.vo.SuprimentoVO;
import com.t2tierp.pafecf.vo.TurnoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class IniciaMovimento extends javax.swing.JDialog {

    MovimentoVO movimento = new MovimentoVO();
    List<TurnoVO> listaTurno = new ArrayList<TurnoVO>();

    public IniciaMovimento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        //TODO : É necessário configurar a cor de cada Panel?
        panelPrincipal.setBackground(new Color(r, g, b));
        panelAbertura.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelGerente.setBackground(new Color(r, g, b));
        panelOperador.setBackground(new Color(r, g, b));
        panelTurno.setBackground(new Color(r, g, b));

        TurnoController turnoControl = new TurnoController();
        configuraGridTurno(turnoControl.tabelaTurno());
        gridTurno.setRowSelectionInterval(0, 0);
        gridTurno.requestFocus();

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
        gridTurno.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        this.setPreferredSize(new Dimension(635, 350));
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelAbertura = new javax.swing.JPanel();
        panelTurno = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridTurno = new javax.swing.JTable();
        panelOperador = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editSenhaOperador = new org.openswing.swing.client.PasswordControl();
        editLoginOperador = new org.openswing.swing.client.TextControl();
        panelGerente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editSenhaGerente = new org.openswing.swing.client.PasswordControl();
        editLoginGerente = new org.openswing.swing.client.TextControl();
        jLabel5 = new javax.swing.JLabel();
        editSuprimento = new javax.swing.JFormattedTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicia Movimento do Caixa");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaMonitor04.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelAbertura.setBackground(new Color(255,255,255,0));
        panelAbertura.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados para abertura do movimento:"));
        panelAbertura.setLayout(new java.awt.GridBagLayout());

        panelTurno.setBorder(javax.swing.BorderFactory.createTitledBorder("Turno:"));
        panelTurno.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setViewportView(gridTurno);

        panelTurno.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelTurno, gridBagConstraints);

        panelOperador.setBackground(new Color(255,255,255,0));
        panelOperador.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Operador:"));
        panelOperador.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelOperador.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelOperador.add(jLabel7, gridBagConstraints);

        editSenhaOperador.setUpperCase(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelOperador.add(editSenhaOperador, gridBagConstraints);

        editLoginOperador.setUpperCase(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelOperador.add(editLoginOperador, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelOperador, gridBagConstraints);

        panelGerente.setBackground(new Color(255,255,255,0));
        panelGerente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Gerente:"));
        panelGerente.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelGerente.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelGerente.add(jLabel3, gridBagConstraints);

        editSenhaGerente.setUpperCase(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGerente.add(editSenhaGerente, gridBagConstraints);

        editLoginGerente.setUpperCase(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelGerente.add(editLoginGerente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelGerente, gridBagConstraints);

        jLabel5.setText("Fundo de Caixa (Suprimento):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelAbertura.add(jLabel5, gridBagConstraints);

        editSuprimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0,00"))));
        editSuprimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelAbertura.add(editSuprimento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelAbertura, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
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
        gridBagConstraints.weighty = 1.0;
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
        System.exit(0);
    }//GEN-LAST:event_botaoCancelaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private org.openswing.swing.client.TextControl editLoginGerente;
    private org.openswing.swing.client.TextControl editLoginOperador;
    private org.openswing.swing.client.PasswordControl editSenhaGerente;
    private org.openswing.swing.client.PasswordControl editSenhaOperador;
    private javax.swing.JFormattedTextField editSuprimento;
    private javax.swing.JTable gridTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAbertura;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGerente;
    private javax.swing.JPanel panelOperador;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTurno;
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
            System.exit(0);
        }
    }

    private void configuraGridTurno(List<TurnoVO> listaTurno) {

        this.listaTurno = listaTurno;

        gridTurno.setModel(new TurnoTableModel(listaTurno));
        gridTurno.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridTurno";
            }
        });

        gridTurno.setAutoCreateColumnsFromModel(false);
        gridTurno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridTurno.getFontMetrics(gridTurno.getFont());
        gridTurno.setColumnModel(new TurnoColumnModel(fm));
    }

    private void confirma() {
        // verifica se senha do operador esta correta
        OperadorController operadorControl = new OperadorController();
        OperadorVO operador = operadorControl.consultaUsuario(editLoginOperador.getText(), editSenhaOperador.getText());
        if (operador != null) {
            // verifica se senha do gerente esta correta
            OperadorVO gerente = operadorControl.consultaUsuario(editLoginGerente.getText(), editSenhaGerente.getText());
            if (gerente != null) {
                //verifica nivel de acesso do gerente/supervisor
                if (gerente.getFuncionarioVO().getNivelAutorizacao().equals("G") || gerente.getFuncionarioVO().getNivelAutorizacao().equals("S")) {
                    MovimentoController movimentoControl = new MovimentoController();
                    TurnoVO turno = listaTurno.get(gridTurno.getSelectedRow());
                    movimento.setIdTurno(turno.getId());
                    //TODO : Devemos verificar se o ECF que está nas configurações é o mesmo que está conectado ao terminal?
                    movimento.setIdImpressora(Caixa.configuracao.getImpressoraVO().getId());
                    movimento.setIdOperador(operador.getId());
                    movimento.setIdCaixa(Caixa.configuracao.getIdCaixa());
                    movimento.setIdGerenteSupervisor(gerente.getId());
                    java.util.Date data = new java.util.Date();
                    java.sql.Timestamp hoje = new java.sql.Timestamp(data.getTime());
                    movimento.setDataHoraAbertura(hoje);
                    if (!editSuprimento.getText().equals("")) {
                        if (Double.valueOf(editSuprimento.getText()) > 0) {
                            movimento.setTotalSuprimento(Double.valueOf(editSuprimento.getText()));
                        } else {
                            movimento.setTotalSuprimento(0.0);
                        }
                    } else {
                        movimento.setTotalSuprimento(0.0);
                    }
                    movimento.setStatusMovimento("A");
                    movimento.setSincronizado("N");
                    movimento = movimentoControl.iniciaMovimento(movimento);

                    if (!editSuprimento.getText().equals("")) {
                        if (Double.valueOf(editSuprimento.getText()) > 0) {
                            //insere suprimento
                            SuprimentoVO suprimento = new SuprimentoVO();
                            suprimento.setIdMovimento(movimento.getId());
                            java.sql.Date dia = new java.sql.Date(data.getTime());
                            suprimento.setDataSuprimento(dia);
                            suprimento.setValor(Double.valueOf(editSuprimento.getText()));
                            //TODO : Devemos imprimir um comprovante de suprimento no ECF nessa ocasião?
                            movimentoControl.suprimento(suprimento);
                        }
                    }

                    Caixa.movimento = movimento;
                    dispose();
                    JOptionPane.showMessageDialog(rootPane, "Movimento aberto com sucesso.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Gerente ou Supervisor: nível de acesso incorreto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Gerente ou Supervisor: dados incorretos.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Operador: dados incorretos!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
