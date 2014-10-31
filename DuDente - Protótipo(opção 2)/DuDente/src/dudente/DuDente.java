/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Box;

/**
 *
 * @author duds
 */
public class DuDente extends JFrame {

    private JPanel janelaPrincipal;
    private GestaoDentistas gestaoDentistas;
    private GestaoSecretarias gestaoSecretarias;
    private GestaoPacientes gestaoPacientes;
    
    public void AbrirJanelaGestaoDentistas(){

        gestaoDentistas.setVisible(true);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
    }
    
    public void AbrirJanelaGestaoSecretarias(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(true);
        gestaoPacientes.setVisible(false);
    }

    public void AbrirJanelaGestaoPacientes(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(true);
    }
    
    public DuDente() {
        
    janelaPrincipal = new JPanel();
    janelaPrincipal.setLayout(new BoxLayout(janelaPrincipal, BoxLayout.X_AXIS));
    janelaPrincipal.add(Box.createHorizontalGlue());
    add(janelaPrincipal);

    JMenuBar menu = new JMenuBar();
    
    JMenu menuArquivo = new JMenu("Arquivo");
    JMenuItem itemMenuArquivoSair = new JMenuItem("Sair", null);
    
    JMenu menuGestao = new JMenu("Gestão");
    JMenuItem itemMenuGestaoDentistas = new JMenuItem("Dentistas", null);
    JMenuItem itemMenuGestaoSecretarias = new JMenuItem("Secretárias", null);
    JMenuItem itemMenuGestaoPacientes = new JMenuItem("Pacientes", null);
    
    itemMenuArquivoSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
    itemMenuGestaoDentistas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
    itemMenuGestaoSecretarias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
    itemMenuGestaoPacientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));

    itemMenuGestaoDentistas.setToolTipText("Cadastrar, editar e excluir dentistas.");
    itemMenuGestaoSecretarias.setToolTipText("Cadastrar, editar e excluir secretárias.");
    itemMenuGestaoPacientes.setToolTipText("Cadastrar, editar e excluir pacientes.");
    
    menuArquivo.add(itemMenuArquivoSair);
    menu.add(menuArquivo);

    menuGestao.add(itemMenuGestaoDentistas);
    menuGestao.add(itemMenuGestaoSecretarias);
    menuGestao.add(itemMenuGestaoPacientes);
    menu.add(menuGestao);
    
    itemMenuArquivoSair.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    });
    
    itemMenuGestaoDentistas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoDentistas();
        }
    });
    
    itemMenuGestaoSecretarias.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoSecretarias();
        }
    });
    
    itemMenuGestaoPacientes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoPacientes();
        }
    });

    gestaoDentistas = new GestaoDentistas();
    gestaoDentistas.setVisible(false);
    janelaPrincipal.add(gestaoDentistas);
    
    gestaoSecretarias = new GestaoSecretarias();
    gestaoSecretarias.setVisible(false);
    janelaPrincipal.add(gestaoSecretarias);
    
    gestaoPacientes = new GestaoPacientes();
    gestaoPacientes.setVisible(false);
    janelaPrincipal.add(gestaoPacientes);
    
    setTitle("DuDente - Sistema Integrado de Gestão de Clínicas Odontológicas");
    setJMenuBar(menu);
    setSize(500, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DuDente ex = new DuDente();
        ex.setVisible(true);        
    }
}
