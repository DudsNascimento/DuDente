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

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private GestaoPlanos gestaoPlanos;
    private GestaoConsultas gestaoConsultas;
    private GestaoCirurgias gestaoCirurgias;
    private GestaoExames gestaoExames;
    private GestaoOrtodonticos gestaoOrtodonticos;
    private GestaoAparelhos gestaoAparelhos;
    private GestaoMedicamentos gestaoMedicamentos;
    private GestaoCompraMedicamentos gestaoCompraMedicamentos;
    private GestaoCompraAparelhos gestaoCompraAparelhos;
    
    public void Conectar() throws ClassNotFoundException, SQLException{

        gestaoDentistas.Conectar();
        gestaoPacientes.Conectar();
        gestaoSecretarias.Conectar();
        gestaoPlanos.Conectar();
        gestaoConsultas.Conectar();
        gestaoCirurgias.Conectar();
        gestaoExames.Conectar();
        gestaoOrtodonticos.Conectar();
        gestaoAparelhos.Conectar();
        gestaoMedicamentos.Conectar();
        gestaoCompraMedicamentos.Conectar();
        gestaoCompraAparelhos.Conectar();
    }
    
    public void Desconectar() throws SQLException{
        
        gestaoDentistas.Desconectar();        
        gestaoPacientes.Desconectar();
        gestaoSecretarias.Desconectar();
        gestaoPlanos.Desconectar();
        gestaoConsultas.Desconectar();
        gestaoCirurgias.Desconectar();
        gestaoExames.Desconectar();
        gestaoOrtodonticos.Desconectar();
        gestaoAparelhos.Desconectar();
        gestaoMedicamentos.Desconectar();
        gestaoCompraMedicamentos.Desconectar();
        gestaoCompraAparelhos.Desconectar();
    }
    
    public void AbrirJanelaGestaoDentistas(){

        gestaoDentistas.setVisible(true);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }
    
    public void AbrirJanelaGestaoSecretarias(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(true);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }

    public void AbrirJanelaGestaoPacientes(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(true);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }
    
    public void AbrirJanelaGestaoPlanos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(true);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);        
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }

    public void AbrirJanelaGestaoConsultas(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(true);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }
    
    public void AbrirJanelaGestaoCirurgias(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(true);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }    

    public void AbrirJanelaGestaoExames(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(true);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }    
    
    public void AbrirJanelaGestaoOrtodonticos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(true);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }    
    
    public void AbrirJanelaGestaoAparelhos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(true);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }    

    public void AbrirJanelaGestaoMedicamentos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(true);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(false);
    }

    public void AbrirJanelaGestaoCompraMedicamentos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(true);
        gestaoCompraAparelhos.setVisible(false);
    }

    public void AbrirJanelaGestaoCompraAparelhos(){

        gestaoDentistas.setVisible(false);
        gestaoSecretarias.setVisible(false);
        gestaoPacientes.setVisible(false);
        gestaoPlanos.setVisible(false);
        gestaoConsultas.setVisible(false);
        gestaoCirurgias.setVisible(false);
        gestaoExames.setVisible(false);
        gestaoOrtodonticos.setVisible(false);
        gestaoAparelhos.setVisible(false);
        gestaoMedicamentos.setVisible(false);
        gestaoCompraMedicamentos.setVisible(false);
        gestaoCompraAparelhos.setVisible(true);
    }
        
    public DuDente() throws ClassNotFoundException, SQLException {
        
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
    JMenuItem itemMenuGestaoPlanos = new JMenuItem("Planos", null);
    JMenuItem itemMenuGestaoConsultas = new JMenuItem("Consultas", null);
    JMenuItem itemMenuGestaoCirurgias = new JMenuItem("Cirurgias", null);
    JMenuItem itemMenuGestaoExames = new JMenuItem("Exames", null);
    JMenuItem itemMenuGestaoOrtodonticos = new JMenuItem("Procedimentos Ortodonticos", null);
    JMenuItem itemMenuGestaoAparelhos = new JMenuItem("Aparelhos", null);
    JMenuItem itemMenuGestaoMedicamentos = new JMenuItem("Medicamentos", null);
    JMenuItem itemMenuGestaoCompraMedicamentos = new JMenuItem("Compra de Medicamentos", null);
    JMenuItem itemMenuGestaoCompraAparelhos = new JMenuItem("Compra de Aparelhos", null);
    
    itemMenuArquivoSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
    itemMenuGestaoDentistas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
    itemMenuGestaoSecretarias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
    itemMenuGestaoPacientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
    itemMenuGestaoPlanos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
    itemMenuGestaoConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.CTRL_MASK));
    itemMenuGestaoCirurgias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.CTRL_MASK));
    itemMenuGestaoExames.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.CTRL_MASK));
    itemMenuGestaoOrtodonticos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.CTRL_MASK));
    itemMenuGestaoAparelhos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.CTRL_MASK));
    itemMenuGestaoMedicamentos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
    itemMenuGestaoCompraMedicamentos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.CTRL_MASK));
    itemMenuGestaoCompraAparelhos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F13, ActionEvent.CTRL_MASK));

    itemMenuGestaoDentistas.setToolTipText("Cadastrar, editar e excluir dentistas.");
    itemMenuGestaoSecretarias.setToolTipText("Cadastrar, editar e excluir secretárias.");
    itemMenuGestaoPacientes.setToolTipText("Cadastrar, editar e excluir pacientes.");
    itemMenuGestaoPlanos.setToolTipText("Cadastrar, editar e excluir planos.");
    itemMenuGestaoConsultas.setToolTipText("Cadastrar, editar e excluir consultas.");
    itemMenuGestaoCirurgias.setToolTipText("Cadastrar, editar e excluir cirurgias.");
    itemMenuGestaoExames.setToolTipText("Cadastrar, editar e excluir exames.");
    itemMenuGestaoOrtodonticos.setToolTipText("Cadastrar, editar e excluir procedimentos ortodonticos.");
    itemMenuGestaoAparelhos.setToolTipText("Cadastrar, editar e excluir aparelhos.");
    itemMenuGestaoMedicamentos.setToolTipText("Cadastrar, editar e excluir medicamentos.");
    itemMenuGestaoCompraMedicamentos.setToolTipText("Cadastrar, editar e excluir compra de medicamentos.");
    itemMenuGestaoCompraAparelhos.setToolTipText("Cadastrar, editar e excluir compra de aparelhos.");
    
    menuArquivo.add(itemMenuArquivoSair);
    menu.add(menuArquivo);

    menuGestao.add(itemMenuGestaoDentistas);
    menuGestao.add(itemMenuGestaoSecretarias);
    menuGestao.add(itemMenuGestaoPacientes);
    menuGestao.add(itemMenuGestaoPlanos);
    menuGestao.add(itemMenuGestaoConsultas);
    menuGestao.add(itemMenuGestaoCirurgias);
    menuGestao.add(itemMenuGestaoExames);
    menuGestao.add(itemMenuGestaoOrtodonticos);
    menuGestao.add(itemMenuGestaoAparelhos);
    menuGestao.add(itemMenuGestaoMedicamentos);
    menuGestao.add(itemMenuGestaoCompraMedicamentos);
    menuGestao.add(itemMenuGestaoCompraAparelhos);
    menu.add(menuGestao);
    
    itemMenuArquivoSair.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
                try {
                    Desconectar();
                } catch (SQLException ex) {
                    Logger.getLogger(DuDente.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    itemMenuGestaoPlanos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoPlanos();
        }
    });

    itemMenuGestaoConsultas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoConsultas();
        }
    });
    
    itemMenuGestaoCirurgias.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoCirurgias();
        }
    });

    itemMenuGestaoExames.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoExames();
        }
    });
    
    itemMenuGestaoOrtodonticos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoOrtodonticos();
        }
    });

    itemMenuGestaoAparelhos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoAparelhos();
        }
    });

    itemMenuGestaoMedicamentos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoMedicamentos();
        }
    });

    itemMenuGestaoCompraMedicamentos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoCompraMedicamentos();
        }
    });
    
    itemMenuGestaoCompraAparelhos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            AbrirJanelaGestaoCompraAparelhos();
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
    
    gestaoPlanos = new GestaoPlanos();
    gestaoPlanos.setVisible(false);
    janelaPrincipal.add(gestaoPlanos);

    gestaoConsultas = new GestaoConsultas();
    gestaoConsultas.setVisible(false);
    janelaPrincipal.add(gestaoConsultas);
    
    gestaoCirurgias = new GestaoCirurgias();
    gestaoCirurgias.setVisible(false);
    janelaPrincipal.add(gestaoCirurgias);

    gestaoExames = new GestaoExames();
    gestaoExames.setVisible(false);
    janelaPrincipal.add(gestaoExames);
    
    gestaoOrtodonticos = new GestaoOrtodonticos();
    gestaoOrtodonticos.setVisible(false);
    janelaPrincipal.add(gestaoOrtodonticos);
    
    gestaoAparelhos = new GestaoAparelhos();
    gestaoAparelhos.setVisible(false);
    janelaPrincipal.add(gestaoAparelhos);

    gestaoMedicamentos = new GestaoMedicamentos();
    gestaoMedicamentos.setVisible(false);
    janelaPrincipal.add(gestaoMedicamentos);

    gestaoCompraMedicamentos = new GestaoCompraMedicamentos();
    gestaoCompraMedicamentos.setVisible(false);
    janelaPrincipal.add(gestaoCompraMedicamentos);
    
    gestaoCompraAparelhos = new GestaoCompraAparelhos();
    gestaoCompraAparelhos.setVisible(false);
    janelaPrincipal.add(gestaoCompraAparelhos);
    
    setTitle("DuDente - Sistema Integrado de Gestão de Clínicas Odontológicas");
    setJMenuBar(menu);
    setSize(1000, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Conectar();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DuDente ex = new DuDente();
        ex.setVisible(true);       
        ex.Conectar();
    }
}
