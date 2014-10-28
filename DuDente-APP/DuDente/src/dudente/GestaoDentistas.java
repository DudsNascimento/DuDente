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
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author duds
 */
public class GestaoDentistas extends JPanel{
    
    public GestaoDentistas(){
        
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JTabbedPane tabbedPane = new JTabbedPane();
            
            JPanel panelCadastrar = new JPanel();
            panelCadastrar.setPreferredSize(new Dimension(800, 600));
            tabbedPane.addTab("Cadastrar Dentista", panelCadastrar);
            
            JPanel panelAtualizar = new JPanel();
            panelAtualizar.setPreferredSize(new Dimension(800, 600));
            tabbedPane.addTab("Atualizar Dentista", panelAtualizar);
            
            JPanel panelRemover = new JPanel();
            panelRemover.setPreferredSize(new Dimension(800, 600));
            tabbedPane.addTab("Remover Dentista", panelRemover);

            add(tabbedPane);
    }
    
}
