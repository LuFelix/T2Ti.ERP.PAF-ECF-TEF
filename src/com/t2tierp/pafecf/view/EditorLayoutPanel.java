/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorLayoutPanel.java
 *
 * Created on 20/11/2010, 09:02:05
 */
package com.t2tierp.pafecf.view;

import com.t2tierp.pafecf.controller.ConfiguracaoController;
import com.t2tierp.pafecf.vo.ConfiguracaoVO;
import com.t2tierp.pafecf.vo.PosicaoComponentesVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Leo
 */
public class EditorLayoutPanel extends javax.swing.JPanel {

    Container container;
    ConfiguracaoController configuracaoController;
    List<ConfiguracaoVO> listaConfiguracao = new ArrayList<ConfiguracaoVO>();
    List<PosicaoComponentesVO> listaPosicaoComponentes = new ArrayList<PosicaoComponentesVO>();
    private JLabel redimensionadorTL = new JLabel();
    private JLabel redimensionadorT = new JLabel();
    private JLabel redimensionadorTR = new JLabel();
    private JLabel redimensionadorR = new JLabel();
    private JLabel redimensionadorBR = new JLabel();
    private JLabel redimensionadorB = new JLabel();
    private JLabel redimensionadorBL = new JLabel();
    private JLabel redimensionadorL = new JLabel();
    private TrataRedimensionamentoSE trataRedimensionamentoSE = new TrataRedimensionamentoSE();

    private class TrataRedimensionamentoSE implements MouseListener, MouseMotionListener {

        private JComponent componente;
        private int xInicialTela;
        private int yInicialTela;

        private int xInicialComponente;
        private int yInicialComponente;

        private int widthInicialComp;
        private int heightInicialComp;

        public void setComponente(JComponent componente) {
            this.componente = componente;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            xInicialTela = e.getXOnScreen();
            yInicialTela = e.getYOnScreen();

            xInicialComponente = componente.getX();
            yInicialComponente = componente.getY();
            
            widthInicialComp = componente.getWidth();
            heightInicialComp = componente.getHeight();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println(e);
            int novoWidth = widthInicialComp + (e.getXOnScreen() - xInicialTela);
            int novoHeight = heightInicialComp + (e.getYOnScreen() - yInicialTela);
            int novoX = xInicialComponente;
            int novoY = yInicialComponente;
            if (novoWidth < 0) {
                novoWidth = -novoWidth;
                novoX -= novoWidth;
            }
            if (novoHeight < 0) {
                novoHeight = - novoHeight;
                novoY -= novoHeight;
            }
            componente.setLocation(novoX, novoY);
            componente.setSize(novoWidth, novoHeight);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    private class Componente extends JLabel implements FocusListener, MouseListener, MouseMotionListener {

        private int xInicialTela;
        private int yInicialTela;
        private int xInicialComp;
        private int yInicialComp;

        @SuppressWarnings("LeakingThisInConstructor")
        private Componente(String nomeComponente, int alinhamentoHorizontal) {
            super(nomeComponente, alinhamentoHorizontal);
            addFocusListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            trataRedimensionamentoSE.setComponente(this);
            
            reposicionarRedimensionador();
            exibirRedimensionador(true);
            getParent().setComponentZOrder(this, 0);
            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            setBackground(new Color(255, 255, 0, 127));
            //System.out.println("ganhou foco " + e);
            getParent().repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            exibirRedimensionador(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(new Color(255, 255, 255, 127));
            //System.out.println("perdeu foco " + e);
            getParent().repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            requestFocus();
            xInicialTela = e.getXOnScreen();
            yInicialTela = e.getYOnScreen();
            xInicialComp = getLocation().x;
            yInicialComp = getLocation().y;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //System.out.println(e);
            setLocation(xInicialComp - (xInicialTela - e.getXOnScreen()), yInicialComp - (yInicialTela - e.getYOnScreen()));
            reposicionarRedimensionador();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        private void reposicionarRedimensionador() {
            redimensionadorTL.setLocation(getX() - 6, getY() - 6);
            redimensionadorT.setLocation(getX() + (getWidth() / 2) - 3, getY() - 6);
            redimensionadorTR.setLocation(getX() + getWidth(), getY() - 6);
            redimensionadorR.setLocation(getX() + getWidth(), getY() + (getHeight() / 2) - 3);
            redimensionadorBR.setLocation(getX() + getWidth(), getY() + getHeight());
            redimensionadorB.setLocation(getX() + (getWidth() / 2) - 3, getY() + getHeight());
            redimensionadorBL.setLocation(getX() - 6, getY() + getHeight());
            redimensionadorL.setLocation(getX() - 6, getY() + (getHeight() / 2) - 3);
        }

        private void exibirRedimensionador(boolean visible) {
            redimensionadorTL.setVisible(visible);
            redimensionadorT.setVisible(visible);
            redimensionadorTR.setVisible(visible);
            redimensionadorR.setVisible(visible);
            redimensionadorBR.setVisible(visible);
            redimensionadorB.setVisible(visible);
            redimensionadorBL.setVisible(visible);
            redimensionadorL.setVisible(visible);
            if (visible) {
                getParent().setComponentZOrder(redimensionadorTL, 0);
                getParent().setComponentZOrder(redimensionadorT, 0);
                getParent().setComponentZOrder(redimensionadorTR, 0);
                getParent().setComponentZOrder(redimensionadorR, 0);
                getParent().setComponentZOrder(redimensionadorBR, 0);
                getParent().setComponentZOrder(redimensionadorB, 0);
                getParent().setComponentZOrder(redimensionadorBL, 0);
                getParent().setComponentZOrder(redimensionadorL, 0);
            }
        }

        @Override
        public void setSize(int width, int height) {
            super.setSize(width, height);
            reposicionarRedimensionador();
        }

    }

    /** Creates new form EditorLayoutPanel */
    public EditorLayoutPanel(
            Container container,
            ConfiguracaoController configuracaoController,
            List<ConfiguracaoVO> listaConfiguracao,
            List<PosicaoComponentesVO> listaPosicaoComponentes) {

        setOpaque(false);
        setLayout(null);

        initComponents();
        this.container = container;
        this.configuracaoController = configuracaoController;
        this.listaConfiguracao = listaConfiguracao;
        this.listaPosicaoComponentes = listaPosicaoComponentes;

        setPreferredSize(new Dimension(1024, 768));
        setMinimumSize(new Dimension(1024, 768));

        Componente c = null;

        for (PosicaoComponentesVO p : listaPosicaoComponentes) {
            System.out.println("Criando componente " + p.getNomeComponente() + " ...");
            c = new Componente(p.getNomeComponente(), JLabel.CENTER);
            c.setOpaque(true);
            c.setBackground(new Color(255, 255, 255, 127));
            c.setBorder(BorderFactory.createLineBorder(Color.black));
            c.setBounds(p.getEsquerda(), p.getTopo(), p.getLargura(), p.getAltura());
            c.setFocusable(true);
            c.setVisible(true);
            add(c);
        }

        // Imagem de fundo
        JLabel label = new JLabel(new javax.swing.ImageIcon(getClass().getResource(configuracaoController.pegaConfiguracao().getCaminhoImagensLayout() + configuracaoController.pegaConfiguracao().getResolucaoVO().getImagemTela())));
        label.setBounds(0, 0, 800, 600);
        label.setVisible(true);
        add(label);

        criarRedimensionador();

        add(redimensionadorTL);
        add(redimensionadorT);
        add(redimensionadorTR);
        add(redimensionadorR);
        add(redimensionadorBR);
        add(redimensionadorB);
        add(redimensionadorBL);
        add(redimensionadorL);
    }

    private void criarRedimensionador() {
        redimensionadorTL.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        redimensionadorTL.setSize(6, 6);
        redimensionadorTL.setBackground(Color.black);
        redimensionadorTL.setOpaque(true);

        redimensionadorT.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        redimensionadorT.setSize(6, 6);
        redimensionadorT.setBackground(Color.black);
        redimensionadorT.setOpaque(true);

        redimensionadorTR.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        redimensionadorTR.setSize(6, 6);
        redimensionadorTR.setBackground(Color.black);
        redimensionadorTR.setOpaque(true);

        redimensionadorR.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        redimensionadorR.setSize(6, 6);
        redimensionadorR.setBackground(Color.black);
        redimensionadorR.setOpaque(true);

        redimensionadorBR.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        redimensionadorBR.setSize(6, 6);
        redimensionadorBR.setBackground(Color.black);
        redimensionadorBR.setOpaque(true);
        redimensionadorBR.addMouseListener(trataRedimensionamentoSE);
        redimensionadorBR.addMouseMotionListener(trataRedimensionamentoSE);

        redimensionadorB.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        redimensionadorB.setSize(6, 6);
        redimensionadorB.setBackground(Color.black);
        redimensionadorB.setOpaque(true);

        redimensionadorBL.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        redimensionadorBL.setSize(6, 6);
        redimensionadorBL.setBackground(Color.black);
        redimensionadorBL.setOpaque(true);

        redimensionadorL.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        redimensionadorL.setSize(6, 6);
        redimensionadorL.setBackground(Color.black);
        redimensionadorL.setOpaque(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        g.setColor(Color.blue);
//        g.drawLine(0, 0, 1024, 768);
//        g.drawLine(0, 768, 1024, 0);

//        for (PosicaoComponentesVO p : listaPosicaoComponentes) {
//            //g.setColor(Color.blue);
//           //g.fillRect(p.getEsquerda(), p.getTopo(), p.getLargura(), p.getAltura());
//            g.setColor(Color.black);
//            g.drawRect(p.getEsquerda(), p.getTopo(), p.getLargura(), p.getAltura());
//            g.drawString(p.getNomeComponente(), p.getEsquerda() + 20, p.getTopo() + 20);
//        }

        //container.paint(g);
        //System.out.println("paint(Graphics g)");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.yellow);
        //g.fillRect(0, 0, 1024, 768);
        //       g.setColor(Color.red);
        //       g.drawLine(0, 0, 100, 100);
        //       g.drawLine(0, 100, 100, 0);
        //container.paint(g);
        //System.out.println("paintComponent(Graphics g)");
    }

    @Override
    public void paintComponents(Graphics g) {
        //super.paintComponents(g);
        //container.paint(g);
        //System.out.println("paintComponents(Graphics g)");
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        //container.paint(g);
        //System.out.println("update(Graphics g)");
    }

    @Override
    public void repaint(Rectangle r) {
        super.repaint(r);
        //System.out.println("repaint(Rectangle r)");
    }

    @Override
    public void repaint() {
        super.repaint();
        //System.out.println("repaint()");
    }

    @Override
    public void repaint(int x, int y, int width, int height) {
        super.repaint(x, y, width, height);
        //System.out.println("repaint(int x, int y, int width, int height)");
    }

    @Override
    public void repaint(long tm) {
        super.repaint(tm);
        //System.out.println("repaint(long tm)");
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
        super.repaint(tm, x, y, width, height);
        //System.out.println("repaint(long tm, int x, int y, int width, int height)");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
