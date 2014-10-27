/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author duds
 */
public class DuDente extends JFrame {

    public DuDente() {
        
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

    JPanel pane = (JPanel) getContentPane();
    GroupLayout gl = new GroupLayout(pane);
    pane.setLayout(gl);
    
    gl.setAutoCreateContainerGaps(true);
    gl.setHorizontalGroup(gl.createSequentialGroup());
    gl.setVerticalGroup(gl.createSequentialGroup());

    pack();

    setTitle("DuDente - Sistema Integrado de Gestão de Clínicas Odontológicas");
    setJMenuBar(menu);
    setSize(800, 600);
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
