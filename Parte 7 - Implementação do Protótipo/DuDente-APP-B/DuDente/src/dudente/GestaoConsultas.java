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
import java.sql.Date;

/**
 *
 * @author duds
 */
public class GestaoConsultas extends JPanel{
    
    private ArrayList <ConsultaTable> consultas;
    private ConsultaJDBC consultaJDBC;
    
    private ArrayList <DentistaTable> dentistas;
    private DentistaJDBC dentistaJDBC;
    
    private ArrayList <PacienteTable> pacientes;
    private PacienteJDBC pacienteJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JFormattedTextField dataConsultaInputCadastro;
    private JTextField descricaoConsultaInputCadastro;
    private JTextField observacaoConsultaInputCadastro;
    private JTextField diagnosticoConsultaInputCadastro;
    JPanel panelDentistaConsultaInputCadastro;
    JTable tableDentistaConsultaInputCadastro;
    JPanel panelPacienteConsultaInputCadastro;
    JTable tablePacienteConsultaInputCadastro;

    private JFormattedTextField dataConsultaInputAtualizacao;
    private JTextField descricaoConsultaInputAtualizacao;
    private JTextField observacaoConsultaInputAtualizacao;
    private JTextField diagnosticoConsultaInputAtualizacao;
    JPanel panelDentistaConsultaInputAtualizacao;
    JTable tableDentistaConsultaInputAtualizacao;
    JPanel panelPacienteConsultaInputAtualizacao;
    JTable tablePacienteConsultaInputAtualizacao;
    
    private JFormattedTextField dataConsultaInputRemocao;
    private JTextField descricaoConsultaInputRemocao;
    private JTextField observacaoConsultaInputRemocao;
    private JTextField diagnosticoConsultaInputRemocao;
    JPanel panelDentistaConsultaInputRemocao;
    JTable tableDentistaConsultaInputRemocao;
    JPanel panelPacienteConsultaInputRemocao;
    JTable tablePacienteConsultaInputRemocao;

    JPanel panelCadastrarLinha5;
    JPanel panelAtualizarLinha5;
    JPanel panelRemoverLinha5;
    
    JPanel panelCadastrarLinha6;
    JPanel panelAtualizarLinha6;
    JPanel panelRemoverLinha6;
    
    JPanel panelAtualizarLinha7;
    JPanel panelRemoverLinha7;
    
    JTable tableConsultasAtualizacao;
    JTable tableConsultasRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        consultaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaConsultas();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void Desconectar() throws SQLException{
        
        consultaJDBC.EncerrarConexao();
        dentistaJDBC.EncerrarConexao();
        pacienteJDBC.EncerrarConexao();
    }

    public void AtualizarListaConsultas() throws SQLException{
        
        String nomeColunasConsultas[] = {"Data", "Descrição", "Observacao", "Diagnostico", "Dentista", "Paciente"};
    
        consultas = consultaJDBC.ListarConsultas();
        if(consultas.size() > 0)
        {
            String[][] listaConsultas = new String[consultas.size()][6];
            for(int count = 0; count < consultas.size(); count++)
            {
                DentistaTable dentistaTable = new DentistaTable();
                dentistaTable.SetIdDentista(consultas.get(count).GetIdDentista());
                dentistaJDBC.ProcurarDentista(dentistaTable);

                PacienteTable pacienteTable = new PacienteTable();
                pacienteTable.SetIdPaciente(consultas.get(count).GetIdPaciente());
                pacienteJDBC.ProcurarPaciente(pacienteTable);
                
                listaConsultas[count][0] = consultas.get(count).GetData().toString();
                listaConsultas[count][1] = consultas.get(count).GetDescricao();
                listaConsultas[count][2] = consultas.get(count).GetObservacao();
                listaConsultas[count][3] = consultas.get(count).GetDiagnostico();
                listaConsultas[count][4] = dentistaTable.GetNome();
                listaConsultas[count][5] = pacienteTable.GetNome();
            }
            
            tableConsultasAtualizacao = new JTable(listaConsultas, nomeColunasConsultas);

            JScrollPane listConsultasAtualizarScroll = new JScrollPane(tableConsultasAtualizacao);
            listConsultasAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha7.removeAll();
            panelAtualizarLinha7.add(listConsultasAtualizarScroll);
            
            panelAtualizarLinha7.setVisible(true);
            panelAtualizarLinha7.updateUI();
            
            tableConsultasAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableConsultasAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableConsultasAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableConsultasRemocao = new JTable(listaConsultas, nomeColunasConsultas);

            JScrollPane listConsultasRemoverScroll = new JScrollPane(tableConsultasRemocao);
            listConsultasRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha7.removeAll();
            panelRemoverLinha7.add(listConsultasRemoverScroll);
            
            panelRemoverLinha7.setVisible(true);
            panelRemoverLinha7.updateUI();
        }
        else
        {
            panelAtualizarLinha7.setVisible(false);
            panelRemoverLinha7.setVisible(false);
        }
    }
    
    public void AtualizarListaDentistas() throws SQLException{
        
        String nomeColunasDentistas[] = {"Nome", "Salario", "Especializacao"};
    
        dentistas = dentistaJDBC.ListarDentistas();
        if(dentistas.size() > 0)
        {
            String[][] listaDentistas = new String[dentistas.size()][3];
            for(int count = 0; count < dentistas.size(); count++)
            {
                DentistaTable dentistaTable = new DentistaTable();
                dentistaTable.SetIdDentista(dentistas.get(count).GetIdDentista());
                dentistaJDBC.ProcurarDentista(dentistaTable);
                
                listaDentistas[count][0] = dentistas.get(count).GetNome();
                listaDentistas[count][1] = Float.toString(dentistas.get(count).GetSalario());
                listaDentistas[count][2] = dentistas.get(count).GetEspecializacao();
            }

            tableDentistaConsultaInputCadastro = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaConsultaInputCadastro = new JScrollPane(tableDentistaConsultaInputCadastro);
            paneDentistaConsultaInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha5.removeAll();
            panelCadastrarLinha5.add(paneDentistaConsultaInputCadastro);
            
            panelCadastrarLinha5.setVisible(true);
            panelCadastrarLinha5.updateUI();

            tableDentistaConsultaInputAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaConsultaInputAtualizacao = new JScrollPane(tableDentistaConsultaInputAtualizacao);
            paneDentistaConsultaInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha5.removeAll();
            panelAtualizarLinha5.add(paneDentistaConsultaInputAtualizacao);
            
            panelAtualizarLinha5.setVisible(true);
            panelAtualizarLinha5.updateUI();
            
            tableDentistaConsultaInputRemocao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaConsultaInputRemocao = new JScrollPane(tableDentistaConsultaInputRemocao);
            paneDentistaConsultaInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha5.removeAll();
            panelRemoverLinha5.add(paneDentistaConsultaInputRemocao);
            
            panelRemoverLinha5.setVisible(true);
            panelRemoverLinha5.updateUI();
        }
        else
        {
            panelCadastrarLinha5.setVisible(false);
            panelAtualizarLinha5.setVisible(false);
            panelRemoverLinha5.setVisible(false);
        }
    }
    
    public void AtualizarListaPacientes() throws SQLException{
        
        String nomeColunasPacientes[] = {"Nome", "Idade"};
    
        pacientes = pacienteJDBC.ListarPacientes();
        if(pacientes.size() > 0)
        {
            String[][] listaPacientes = new String[pacientes.size()][2];
            for(int count = 0; count < pacientes.size(); count++)
            {
                PacienteTable pacienteTable = new PacienteTable();
                pacienteTable.SetIdPaciente(pacientes.get(count).GetIdPaciente());
                pacienteJDBC.ProcurarPaciente(pacienteTable);
                
                listaPacientes[count][0] = pacientes.get(count).GetNome();
                listaPacientes[count][1] = Integer.toString(pacientes.get(count).GetIdade());
            }

            tablePacienteConsultaInputCadastro = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteConsultaInputCadastro = new JScrollPane(tablePacienteConsultaInputCadastro);
            panePacienteConsultaInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha6.removeAll();
            panelCadastrarLinha6.add(panePacienteConsultaInputCadastro);
            
            panelCadastrarLinha6.setVisible(true);
            panelCadastrarLinha6.updateUI();

            tablePacienteConsultaInputAtualizacao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteConsultaInputAtualizacao = new JScrollPane(tablePacienteConsultaInputAtualizacao);
            panePacienteConsultaInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha6.removeAll();
            panelAtualizarLinha6.add(panePacienteConsultaInputAtualizacao);
            
            panelAtualizarLinha6.setVisible(true);
            panelAtualizarLinha6.updateUI();
            
            tablePacienteConsultaInputRemocao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteConsultaInputRemocao = new JScrollPane(tablePacienteConsultaInputRemocao);
            panePacienteConsultaInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha6.removeAll();
            panelRemoverLinha6.add(panePacienteConsultaInputRemocao);
            
            panelRemoverLinha6.setVisible(true);
            panelRemoverLinha6.updateUI();
        }
        else
        {
            panelCadastrarLinha6.setVisible(false);
            panelAtualizarLinha6.setVisible(false);
            panelRemoverLinha6.setVisible(false);
        }
    }

    public void GerarRelatorioPadrao() throws IOException, SQLException{

        FileWriter fileRelatorioConsultas = new FileWriter("relatorio_consultas");
        BufferedWriter bufferFileRelatorioConsultas = new BufferedWriter(fileRelatorioConsultas);

        bufferFileRelatorioConsultas.write("***********************\n");
        bufferFileRelatorioConsultas.write("Relatorio de consultas.\n");
        bufferFileRelatorioConsultas.write("***********************\n\n");

        for(int count = 0; count < consultas.size(); count++)
        {
            DentistaTable dentistaTable = new DentistaTable();
            dentistaTable.SetIdDentista(consultas.get(count).GetIdDentista());
            dentistaJDBC.ProcurarDentista(dentistaTable);
            
            PacienteTable pacienteTable = new PacienteTable();
            pacienteTable.SetIdPaciente(consultas.get(count).GetIdPaciente());
            pacienteJDBC.ProcurarPaciente(pacienteTable);
 
            bufferFileRelatorioConsultas.write("Data: "+consultas.get(count).GetData().toString()+"\n");
            bufferFileRelatorioConsultas.write("Descricao: "+consultas.get(count).GetDescricao()+"\n");
            bufferFileRelatorioConsultas.write("Observacao: "+consultas.get(count).GetObservacao()+"\n");
            bufferFileRelatorioConsultas.write("Diagnostico: "+consultas.get(count).GetDiagnostico()+"\n");
            bufferFileRelatorioConsultas.write("Nome Dentista: "+dentistaTable.GetNome()+"\n");
            bufferFileRelatorioConsultas.write("Nome Paciente: "+pacienteTable.GetNome()+"\n");
        }

        bufferFileRelatorioConsultas.close();
        fileRelatorioConsultas.close();
    }
    
    public void AdicionarConsulta() throws SQLException{

        if(dataConsultaInputCadastro.isEditValid() && 
           descricaoConsultaInputCadastro.getText().length() > 0 &&
           observacaoConsultaInputCadastro.getText().length() > 0 &&
           diagnosticoConsultaInputCadastro.getText().length() > 0 &&
           tableDentistaConsultaInputCadastro.getSelectedRow() >= 0 && tableDentistaConsultaInputCadastro.getSelectedRow() < dentistas.size() &&
           tablePacienteConsultaInputCadastro.getSelectedRow() >= 0 && tablePacienteConsultaInputCadastro.getSelectedRow() < pacientes.size())
        {
            ConsultaTable consulta = new ConsultaTable();
            consulta.SetData(Date.valueOf(dataConsultaInputCadastro.getText()));
            consulta.SetDescricao(descricaoConsultaInputCadastro.getText());
            consulta.SetObservacao(observacaoConsultaInputCadastro.getText());
            consulta.SetDiagnostico(diagnosticoConsultaInputCadastro.getText());
            consulta.SetIdDentista(dentistas.get(tableDentistaConsultaInputCadastro.getSelectedRow()).GetIdDentista());
            consulta.SetIdPaciente(pacientes.get(tablePacienteConsultaInputCadastro.getSelectedRow()).GetIdPaciente());
            
            consultaJDBC.AdicionarConsulta(consulta);

            dataConsultaInputCadastro.setText("    -  -  ");
            descricaoConsultaInputCadastro.setText("");
            observacaoConsultaInputCadastro.setText("");
            diagnosticoConsultaInputCadastro.setText("");
        }

        AtualizarListaConsultas();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void AtualizarConsulta() throws SQLException{

        if(dataConsultaInputAtualizacao.isEditValid() && 
           descricaoConsultaInputAtualizacao.getText().length() > 0 &&
           observacaoConsultaInputAtualizacao.getText().length() > 0 &&
           diagnosticoConsultaInputAtualizacao.getText().length() > 0 &&
           tableDentistaConsultaInputAtualizacao.getSelectedRow() >= 0 && tableDentistaConsultaInputAtualizacao.getSelectedRow() < dentistas.size() &&
           tablePacienteConsultaInputAtualizacao.getSelectedRow() >= 0 && tablePacienteConsultaInputAtualizacao.getSelectedRow() < pacientes.size() &&
           tableConsultasAtualizacao.getSelectedRow() >= 0 && tableConsultasAtualizacao.getSelectedRow() < consultas.size())
        {
            ConsultaTable consulta = new ConsultaTable();
            consulta.SetIdConsulta(consultas.get(tableConsultasAtualizacao.getSelectedRow()).GetIdConsulta());
            consulta.SetData(Date.valueOf(dataConsultaInputAtualizacao.getText()));
            consulta.SetDescricao(descricaoConsultaInputAtualizacao.getText());
            consulta.SetObservacao(observacaoConsultaInputAtualizacao.getText());
            consulta.SetDiagnostico(diagnosticoConsultaInputAtualizacao.getText());
            consulta.SetIdDentista(dentistas.get(tableDentistaConsultaInputAtualizacao.getSelectedRow()).GetIdDentista());
            consulta.SetIdPaciente(pacientes.get(tablePacienteConsultaInputAtualizacao.getSelectedRow()).GetIdPaciente());
            
            consultaJDBC.AtualizarConsulta(consulta);
            
            dataConsultaInputAtualizacao.setText("    -  -  ");
            descricaoConsultaInputAtualizacao.setText("");
            observacaoConsultaInputAtualizacao.setText("");
            diagnosticoConsultaInputAtualizacao.setText("");
        }
        
        AtualizarListaConsultas();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void RemoverConsulta() throws SQLException{
        
        if(tableConsultasRemocao.getSelectedRow() >= 0 && tableConsultasRemocao.getSelectedRow() < consultas.size())
        {
            consultaJDBC.RemoverConsulta(consultas.get(tableConsultasRemocao.getSelectedRow()));
        }
        
        AtualizarListaConsultas();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableConsultasAtualizacao.getSelectedRow() >= 0 && tableConsultasAtualizacao.getSelectedRow() < consultas.size())
        {
            dataConsultaInputAtualizacao.setEnabled(true);
            descricaoConsultaInputAtualizacao.setEnabled(true);
            observacaoConsultaInputAtualizacao.setEnabled(true);
            diagnosticoConsultaInputAtualizacao.setEnabled(true);
                    
            dataConsultaInputAtualizacao.setText(consultas.get(tableConsultasAtualizacao.getSelectedRow()).GetData().toString());
            descricaoConsultaInputAtualizacao.setText(consultas.get(tableConsultasAtualizacao.getSelectedRow()).GetDescricao());
            observacaoConsultaInputAtualizacao.setText(consultas.get(tableConsultasAtualizacao.getSelectedRow()).GetObservacao());
            diagnosticoConsultaInputAtualizacao.setText(consultas.get(tableConsultasAtualizacao.getSelectedRow()).GetDiagnostico());
            dataConsultaInputAtualizacao.validate();
        }
        else
        {
            dataConsultaInputAtualizacao.setEnabled(false);
            descricaoConsultaInputAtualizacao.setEnabled(false);
            observacaoConsultaInputAtualizacao.setEnabled(false);
            diagnosticoConsultaInputAtualizacao.setEnabled(false);
                    
            dataConsultaInputAtualizacao.setText("    -  -  ");
            descricaoConsultaInputAtualizacao.setText("");
            observacaoConsultaInputAtualizacao.setText("");
            diagnosticoConsultaInputAtualizacao.setText("");
            dataConsultaInputAtualizacao.validate();
        }
    }
        
    public GestaoConsultas() throws ClassNotFoundException, SQLException{
        
        consultaJDBC = new ConsultaJDBC();
        dentistaJDBC = new DentistaJDBC();
        pacienteJDBC = new PacienteJDBC();

        try{
        dataMaskFormat = new MaskFormatter("####-##-##");
        }
        catch(ParseException excp){}
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();
                
        //*******************
        //Painel de cadastro.
        //*******************

        JPanel panelCadastrar = new JPanel();
        panelCadastrar.setLayout(new BoxLayout(panelCadastrar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Cadastrar Consulta", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataConsultaText1 = new JTextArea();
        dataConsultaText1.setText("Data da consulta: ");
        dataConsultaText1.setEditable(false);
        dataConsultaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataConsultaText1);

        dataConsultaInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataConsultaInputCadastro.setColumns(10);
        panelCadastrarLinha1.add(dataConsultaInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoConsultaText1 = new JTextArea();
        descricaoConsultaText1.setText("Descrição da consulta: ");
        descricaoConsultaText1.setEditable(false);
        descricaoConsultaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(descricaoConsultaText1);

        descricaoConsultaInputCadastro = new JTextField();
        descricaoConsultaInputCadastro.setColumns(50);
        panelCadastrarLinha2.add(descricaoConsultaInputCadastro);
        
        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoConsultaText1 = new JTextArea();
        observacaoConsultaText1.setText("Observação da consulta: ");
        observacaoConsultaText1.setEditable(false);
        observacaoConsultaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(observacaoConsultaText1);

        observacaoConsultaInputCadastro = new JTextField();
        observacaoConsultaInputCadastro.setColumns(50);
        panelCadastrarLinha3.add(observacaoConsultaInputCadastro);

        JPanel panelCadastrarLinha4 = new JPanel();
        panelCadastrarLinha4.setLayout(new BoxLayout(panelCadastrarLinha4, BoxLayout.X_AXIS));
        panelCadastrarLinha4.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea diagnosticoConsultaText1 = new JTextArea();
        diagnosticoConsultaText1.setText("Diagnóstico da consulta: ");
        diagnosticoConsultaText1.setEditable(false);
        diagnosticoConsultaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha4.add(diagnosticoConsultaText1);

        diagnosticoConsultaInputCadastro = new JTextField();
        diagnosticoConsultaInputCadastro.setColumns(50);
        panelCadastrarLinha4.add(diagnosticoConsultaInputCadastro);
        
        panelCadastrarLinha5 = new JPanel();
        panelCadastrarLinha5.setLayout(new BoxLayout(panelCadastrarLinha5, BoxLayout.X_AXIS));
        panelCadastrarLinha5.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelCadastrarLinha6 = new JPanel();
        panelCadastrarLinha6.setLayout(new BoxLayout(panelCadastrarLinha6, BoxLayout.X_AXIS));
        panelCadastrarLinha6.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(panelCadastrarLinha3);
        panelCadastrar.add(panelCadastrarLinha4);
        panelCadastrar.add(panelCadastrarLinha5);
        panelCadastrar.add(panelCadastrarLinha6);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarConsulta = new JButton("Cadastrar Consulta");
        panelCadastrar.add(cadastrarConsulta);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarConsulta();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Consulta", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataConsultaText2 = new JTextArea();
        dataConsultaText2.setText("Data da consulta: ");
        dataConsultaText2.setEditable(false);
        dataConsultaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataConsultaText2);

        dataConsultaInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataConsultaInputAtualizacao.setColumns(10);
        panelAtualizarLinha1.add(dataConsultaInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoConsultaText2 = new JTextArea();
        descricaoConsultaText2.setText("Descrição da consulta: ");
        descricaoConsultaText2.setEditable(false);
        descricaoConsultaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(descricaoConsultaText2);

        descricaoConsultaInputAtualizacao = new JTextField();
        descricaoConsultaInputAtualizacao.setColumns(50);
        panelAtualizarLinha2.add(descricaoConsultaInputAtualizacao);
        
        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoConsultaText2 = new JTextArea();
        observacaoConsultaText2.setText("Observação da consulta: ");
        observacaoConsultaText2.setEditable(false);
        observacaoConsultaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(observacaoConsultaText2);

        observacaoConsultaInputAtualizacao = new JTextField();
        observacaoConsultaInputAtualizacao.setColumns(50);
        panelAtualizarLinha3.add(observacaoConsultaInputAtualizacao);

        JPanel panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea diagnosticoConsultaText2 = new JTextArea();
        diagnosticoConsultaText2.setText("Diagnóstico da consulta: ");
        diagnosticoConsultaText2.setEditable(false);
        diagnosticoConsultaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha4.add(diagnosticoConsultaText2);

        diagnosticoConsultaInputAtualizacao = new JTextField();
        diagnosticoConsultaInputAtualizacao.setColumns(50);
        panelAtualizarLinha4.add(diagnosticoConsultaInputAtualizacao);
        
        panelAtualizarLinha5 = new JPanel();
        panelAtualizarLinha5.setLayout(new BoxLayout(panelAtualizarLinha5, BoxLayout.X_AXIS));
        panelAtualizarLinha5.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizarLinha6 = new JPanel();
        panelAtualizarLinha6.setLayout(new BoxLayout(panelAtualizarLinha6, BoxLayout.X_AXIS));
        panelAtualizarLinha6.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizarLinha7 = new JPanel();
        panelAtualizarLinha7.setLayout(new BoxLayout(panelAtualizarLinha7, BoxLayout.X_AXIS));
        panelAtualizarLinha7.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha7.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelAtualizar.add(panelAtualizarLinha1);
        panelAtualizar.add(panelAtualizarLinha2);
        panelAtualizar.add(panelAtualizarLinha3);
        panelAtualizar.add(panelAtualizarLinha4);
        panelAtualizar.add(panelAtualizarLinha5);
        panelAtualizar.add(panelAtualizarLinha6);
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 20)));

        panelAtualizar.add(panelAtualizarLinha7);
        
        panelAtualizar.add(Box.createVerticalGlue());

        JButton atualizarConsulta = new JButton("Atualizar Consulta");
        panelAtualizar.add(atualizarConsulta);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarConsulta();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Consulta", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataConsultaText3 = new JTextArea();
        dataConsultaText3.setText("Data da consulta: ");
        dataConsultaText3.setEditable(false);
        dataConsultaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataConsultaText3);

        dataConsultaInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataConsultaInputRemocao.setColumns(10);
        panelRemoverLinha1.add(dataConsultaInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoConsultaText3 = new JTextArea();
        descricaoConsultaText3.setText("Descrição da consulta: ");
        descricaoConsultaText3.setEditable(false);
        descricaoConsultaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(descricaoConsultaText3);

        descricaoConsultaInputRemocao = new JTextField();
        descricaoConsultaInputRemocao.setColumns(50);
        panelRemoverLinha2.add(descricaoConsultaInputRemocao);
        
        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoConsultaText3 = new JTextArea();
        observacaoConsultaText3.setText("Observação da consulta: ");
        observacaoConsultaText3.setEditable(false);
        observacaoConsultaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(observacaoConsultaText3);

        observacaoConsultaInputRemocao = new JTextField();
        observacaoConsultaInputRemocao.setColumns(50);
        panelRemoverLinha3.add(observacaoConsultaInputRemocao);

        JPanel panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea diagnosticoConsultaText3 = new JTextArea();
        diagnosticoConsultaText3.setText("Diagnóstico da consulta: ");
        diagnosticoConsultaText3.setEditable(false);
        diagnosticoConsultaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha4.add(diagnosticoConsultaText3);

        diagnosticoConsultaInputRemocao = new JTextField();
        diagnosticoConsultaInputRemocao.setColumns(50);
        panelRemoverLinha4.add(diagnosticoConsultaInputRemocao);
        
        panelRemoverLinha5 = new JPanel();
        panelRemoverLinha5.setLayout(new BoxLayout(panelRemoverLinha5, BoxLayout.X_AXIS));
        panelRemoverLinha5.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelRemoverLinha6 = new JPanel();
        panelRemoverLinha6.setLayout(new BoxLayout(panelRemoverLinha6, BoxLayout.X_AXIS));
        panelRemoverLinha6.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelRemoverLinha7 = new JPanel();
        panelRemoverLinha7.setLayout(new BoxLayout(panelRemoverLinha7, BoxLayout.X_AXIS));
        panelRemoverLinha7.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha7.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelRemover.add(panelRemoverLinha1);
        panelRemover.add(panelRemoverLinha2);
        panelRemover.add(panelRemoverLinha3);
        panelRemover.add(panelRemoverLinha4);
        panelRemover.add(panelRemoverLinha5);
        panelRemover.add(panelRemoverLinha6);
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 20)));
        
        panelRemover.add(panelRemoverLinha7);
        
        panelRemover.add(Box.createVerticalGlue());

        JButton removerConsulta = new JButton("Remover Consulta");
        panelRemover.add(removerConsulta);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverConsulta();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Consulta", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoConsulta = new JButton("Relatório Padrão dos Consultas");
        panelRelatorio.add(relatorioPadraoConsulta);
        
        relatorioPadraoConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoConsultas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
        
        //*******************
        //Desabilitar inputs.
        //*******************
        
        dataConsultaInputAtualizacao.setEnabled(false);
        descricaoConsultaInputAtualizacao.setEnabled(false);
        observacaoConsultaInputAtualizacao.setEnabled(false);
        diagnosticoConsultaInputAtualizacao.setEnabled(false);
        
        dataConsultaInputRemocao.setEnabled(false);
        descricaoConsultaInputRemocao.setEnabled(false);
        observacaoConsultaInputRemocao.setEnabled(false);
        diagnosticoConsultaInputRemocao.setEnabled(false);
    }   
}
