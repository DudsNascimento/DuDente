/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.io.FileWriter;
import java.io.BufferedWriter;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.FocusListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.Box;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import dudente.jdbc.*;

/**
 *
 * @author duds
 */
public class GestaoPacientes extends JPanel{
    
    private ArrayList <PacienteTable> pacientes;
    private PacienteJDBC pacienteJDBC;
    
    private ArrayList <PlanoTable> planos;
    private PlanoJDBC planoJDBC;
    
    private MaskFormatter idadeMaskFormat;
    
    private JTextField nomePacienteInputCadastro;
    private JFormattedTextField idadePacienteInputCadastro;
    JPanel panelPlanoPacienteInputCadastro;
    JTable tablePlanoPacienteInputCadastro;
    
    private JTextField nomePacienteInputAtualizacao;
    private JFormattedTextField idadePacienteInputAtualizacao;
    JPanel panelPlanoPacienteInputAtualizacao;
    JTable tablePlanoPacienteInputAtualizacao;
    
    private JTextField nomePacienteInputRemocao;
    private JFormattedTextField idadePacienteInputRemocao;
    JPanel panelPlanoPacienteInputRemocao;
    JTable tablePlanoPacienteInputRemocao;

    JPanel panelCadastrarLinha3;
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JPanel panelAtualizarLinha4;
    JPanel panelRemoverLinha4;
    
    JTable tablePacientesAtualizacao;
    JTable tablePacientesRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        planoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaPacientes();
        AtualizarListaPlanos();
    }
    
    public void Desconectar() throws SQLException{
        
        pacienteJDBC.EncerrarConexao();
        planoJDBC.EncerrarConexao();
    }

    public void AtualizarListaPacientes() throws SQLException{
        
        String nomeColunasPacientes[] = {"Nome", "Idade", "Plano"};
    
        pacientes = pacienteJDBC.ListarPacientes();
        if(pacientes.size() > 0)
        {
            String[][] listaPacientes = new String[pacientes.size()][3];
            for(int count = 0; count < pacientes.size(); count++)
            {
                PlanoTable planoTable = new PlanoTable();
                planoTable.SetIdPlano(pacientes.get(count).GetIdPlano());
                planoJDBC.ProcurarPlano(planoTable);
                
                listaPacientes[count][0] = pacientes.get(count).GetNome();
                listaPacientes[count][1] = String.valueOf(pacientes.get(count).GetIdade());
                listaPacientes[count][2] = planoTable.GetNome();
            }
            
            tablePacientesAtualizacao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane listPacientesAtualizarScroll = new JScrollPane(tablePacientesAtualizacao);
            listPacientesAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha4.removeAll();
            panelAtualizarLinha4.add(listPacientesAtualizarScroll);
            
            panelAtualizarLinha4.setVisible(true);
            panelAtualizarLinha4.updateUI();
            
            tablePacientesAtualizacao.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent me) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void mousePressed(MouseEvent me) {
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                }

                @Override
                public void mouseExited(MouseEvent me) {
                }
            });
            
            tablePacientesAtualizacao.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent ke) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void keyPressed(KeyEvent ke) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void keyReleased(KeyEvent ke) {
                    PreencherInputsAtualizacao();
                }
            });
            
            tablePacientesAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tablePacientesRemocao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane listPacientesRemoverScroll = new JScrollPane(tablePacientesRemocao);
            listPacientesRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha4.removeAll();
            panelRemoverLinha4.add(listPacientesRemoverScroll);
            
            panelRemoverLinha4.setVisible(true);
            panelRemoverLinha4.updateUI();
        }
        else
        {
            panelAtualizarLinha4.setVisible(false);
            panelRemoverLinha4.setVisible(false);
        }
    }
    
    public void AtualizarListaPlanos() throws SQLException{
        
        String nomeColunasPlanos[] = {"Nome", "Validade"};
    
        planos = planoJDBC.ListarPlanos();
        if(planos.size() > 0)
        {
            String[][] listaPlanos = new String[planos.size()][2];
            for(int count = 0; count < planos.size(); count++)
            {
                PlanoTable planoTable = new PlanoTable();
                planoTable.SetIdPlano(planos.get(count).GetIdPlano());
                planoJDBC.ProcurarPlano(planoTable);
                
                listaPlanos[count][0] = planos.get(count).GetNome();
                listaPlanos[count][1] = planos.get(count).GetValidade().toString();
            }

            tablePlanoPacienteInputCadastro = new JTable(listaPlanos, nomeColunasPlanos);

            JScrollPane panePlanoPacienteInputCadastro = new JScrollPane(tablePlanoPacienteInputCadastro);
            panePlanoPacienteInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha3.removeAll();
            panelCadastrarLinha3.add(panePlanoPacienteInputCadastro);
            
            panelCadastrarLinha3.setVisible(true);
            panelCadastrarLinha3.updateUI();

            tablePlanoPacienteInputAtualizacao = new JTable(listaPlanos, nomeColunasPlanos);

            JScrollPane panePlanoPacienteInputAtualizacao = new JScrollPane(tablePlanoPacienteInputAtualizacao);
            panePlanoPacienteInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(panePlanoPacienteInputAtualizacao);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tablePlanoPacienteInputRemocao = new JTable(listaPlanos, nomeColunasPlanos);

            JScrollPane panePlanoPacienteInputRemocao = new JScrollPane(tablePlanoPacienteInputRemocao);
            panePlanoPacienteInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(panePlanoPacienteInputRemocao);
            
            panelRemoverLinha3.setVisible(true);
            panelRemoverLinha3.updateUI();
        }
        else
        {
            panelCadastrarLinha3.setVisible(false);
            panelAtualizarLinha3.setVisible(false);
            panelRemoverLinha3.setVisible(false);
        }
    }

    public void GerarRelatorioPadrao() throws IOException, SQLException{

        FileWriter fileRelatorioPacientes = new FileWriter("relatorio_pacientes");
        BufferedWriter bufferFileRelatorioPacientes = new BufferedWriter(fileRelatorioPacientes);

        bufferFileRelatorioPacientes.write("***********************\n");
        bufferFileRelatorioPacientes.write("Relatorio de pacientes.\n");
        bufferFileRelatorioPacientes.write("***********************\n\n");

        for(int count = 0; count < pacientes.size(); count++)
        {
            PlanoTable planoTable = new PlanoTable();
            planoTable.SetIdPlano(pacientes.get(count).GetIdPlano());
            planoJDBC.ProcurarPlano(planoTable);
 
            bufferFileRelatorioPacientes.write("Nome: "+pacientes.get(count).GetNome()+"\n");
            bufferFileRelatorioPacientes.write("Idade: "+pacientes.get(count).GetIdade()+"\n");
            bufferFileRelatorioPacientes.write("Plano: "+planoTable.GetNome()+"\n\n");
        }

        bufferFileRelatorioPacientes.close();
        fileRelatorioPacientes.close();
    }
    
    public void AdicionarPaciente() throws SQLException{

        if(nomePacienteInputCadastro.getText().length() > 0 && idadePacienteInputCadastro.isEditValid() &&
           tablePlanoPacienteInputCadastro.getSelectedRow() >= 0 && tablePlanoPacienteInputCadastro.getSelectedRow() < planos.size())
        {
            PacienteTable paciente = new PacienteTable();
            paciente.SetNome(nomePacienteInputCadastro.getText());
            paciente.SetIdade(Integer.valueOf(idadePacienteInputCadastro.getText().substring(0, 2)));
            paciente.SetIdPlano(planos.get(tablePlanoPacienteInputCadastro.getSelectedRow()).GetIdPlano());
            
            pacienteJDBC.AdicionarPaciente(paciente);

            nomePacienteInputCadastro.setText("");
            idadePacienteInputCadastro.setText("   anos");
            idadePacienteInputCadastro.validate();
        }

        AtualizarListaPacientes();
        AtualizarListaPlanos();
    }
    
    public void AtualizarPaciente() throws SQLException{

        if(nomePacienteInputAtualizacao.getText().length() > 0 && idadePacienteInputAtualizacao.isEditValid() &&
           tablePlanoPacienteInputAtualizacao.getSelectedRow() >= 0 && tablePlanoPacienteInputAtualizacao.getSelectedRow() < planos.size() &&
           tablePacientesAtualizacao.getSelectedRow() >= 0 && tablePacientesAtualizacao.getSelectedRow() < pacientes.size())
        {
            PacienteTable paciente = new PacienteTable();
            paciente.SetIdPaciente(pacientes.get(tablePacientesAtualizacao.getSelectedRow()).GetIdPaciente());
            paciente.SetIdPlano(planos.get(tablePlanoPacienteInputAtualizacao.getSelectedRow()).GetIdPlano());
            paciente.SetIdade(Integer.valueOf(idadePacienteInputAtualizacao.getText().substring(0, 2)));
            paciente.SetNome(nomePacienteInputAtualizacao.getText());
            
            pacienteJDBC.AtualizarPaciente(paciente);
            
            nomePacienteInputAtualizacao.setText("");
            idadePacienteInputAtualizacao.setText("   anos");
            idadePacienteInputAtualizacao.validate();
        }
        
        AtualizarListaPacientes();
        AtualizarListaPlanos();
    }
    
    public void RemoverPaciente() throws SQLException{
        
        if(tablePacientesRemocao.getSelectedRow() >= 0 && tablePacientesRemocao.getSelectedRow() < pacientes.size())
        {
            pacienteJDBC.RemoverPaciente(pacientes.get(tablePacientesRemocao.getSelectedRow()));
        }
        
        AtualizarListaPacientes();
        AtualizarListaPlanos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tablePacientesAtualizacao.getSelectedRow() >= 0 && tablePacientesAtualizacao.getSelectedRow() < pacientes.size())
        {
            nomePacienteInputAtualizacao.setEnabled(true);
            idadePacienteInputAtualizacao.setEnabled(true);
                    
            nomePacienteInputAtualizacao.setText(pacientes.get(tablePacientesAtualizacao.getSelectedRow()).GetNome());
            idadePacienteInputAtualizacao.setText(String.valueOf(pacientes.get(tablePacientesAtualizacao.getSelectedRow()).GetIdade()));
            idadePacienteInputAtualizacao.validate();
        }
        else
        {
            nomePacienteInputAtualizacao.setEnabled(false);
            idadePacienteInputAtualizacao.setEnabled(false);
                    
            nomePacienteInputAtualizacao.setText("");
            idadePacienteInputAtualizacao.setText("   anos");
            idadePacienteInputAtualizacao.validate();
        }
    }
        
    public GestaoPacientes() throws ClassNotFoundException, SQLException{
        
        pacienteJDBC = new PacienteJDBC();
        planoJDBC = new PlanoJDBC();

        try{
        idadeMaskFormat = new MaskFormatter("## anos");
        }
        catch(ParseException excp){}
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();
                
        //*******************
        //Painel de cadastro.
        //*******************

        JPanel panelCadastrar = new JPanel();
        panelCadastrar.setLayout(new BoxLayout(panelCadastrar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Cadastrar Paciente", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePacienteText1 = new JTextArea();
        nomePacienteText1.setText("Nome do paciente: ");
        nomePacienteText1.setEditable(false);
        nomePacienteText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomePacienteText1);

        nomePacienteInputCadastro = new JTextField();
        nomePacienteInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomePacienteInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea idadePacienteText1 = new JTextArea();
        idadePacienteText1.setText("Idade do paciente: ");
        idadePacienteText1.setEditable(false);
        idadePacienteText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(idadePacienteText1);

        idadePacienteInputCadastro = new JFormattedTextField(idadeMaskFormat);
        idadePacienteInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(idadePacienteInputCadastro);

        panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //-------------------------

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(panelCadastrarLinha3);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarPaciente = new JButton("Cadastrar Paciente");
        panelCadastrar.add(cadastrarPaciente);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarPaciente();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Paciente", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePacienteText2 = new JTextArea();
        nomePacienteText2.setText("Nome do paciente: ");
        nomePacienteText2.setEditable(false);
        nomePacienteText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomePacienteText2);

        nomePacienteInputAtualizacao = new JTextField();
        nomePacienteInputAtualizacao.setColumns(20);
        nomePacienteInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomePacienteInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea idadePacienteText2 = new JTextArea();
        idadePacienteText2.setText("Idade do paciente: ");
        idadePacienteText2.setEditable(false);
        idadePacienteText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(idadePacienteText2);

        idadePacienteInputAtualizacao = new JFormattedTextField(idadeMaskFormat);
        idadePacienteInputAtualizacao.setColumns(10);
        idadePacienteInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(idadePacienteInputAtualizacao);

        panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelAtualizar.add(panelAtualizarLinha1);
        panelAtualizar.add(panelAtualizarLinha2);
        panelAtualizar.add(panelAtualizarLinha3);
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 20)));

        panelAtualizar.add(panelAtualizarLinha4);
        
        panelAtualizar.add(Box.createVerticalGlue());

        JButton atualizarPaciente = new JButton("Atualizar Paciente");
        panelAtualizar.add(atualizarPaciente);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarPaciente();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Paciente", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePacienteText3 = new JTextArea();
        nomePacienteText3.setText("Nome do paciente: ");
        nomePacienteText3.setEditable(false);
        nomePacienteText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomePacienteText3);

        nomePacienteInputRemocao = new JTextField();
        nomePacienteInputRemocao.setColumns(20);
        nomePacienteInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomePacienteInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea idadePacienteText3 = new JTextArea();
        idadePacienteText3.setText("Idade do paciente: ");
        idadePacienteText3.setEditable(false);
        idadePacienteText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(idadePacienteText3);

        idadePacienteInputRemocao = new JFormattedTextField(idadeMaskFormat);
        idadePacienteInputRemocao.setColumns(10);
        idadePacienteInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(idadePacienteInputRemocao);

        panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelRemover.add(panelRemoverLinha1);
        panelRemover.add(panelRemoverLinha2);
        panelRemover.add(panelRemoverLinha3);
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 20)));

        panelRemover.add(panelRemoverLinha4);
        
        panelRemover.add(Box.createVerticalGlue());

        JButton removerPaciente = new JButton("Remover Paciente");
        panelRemover.add(removerPaciente);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverPaciente();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Paciente", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoPaciente = new JButton("Relatório Padrão dos Pacientes");
        panelRelatorio.add(relatorioPadraoPaciente);
        
        relatorioPadraoPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoPacientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
