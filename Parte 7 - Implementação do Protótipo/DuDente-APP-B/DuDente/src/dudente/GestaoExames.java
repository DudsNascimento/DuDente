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
public class GestaoExames extends JPanel{
    
    private ArrayList <ExameTable> exames;
    private ExameJDBC exameJDBC;
    
    private ArrayList <DentistaTable> dentistas;
    private DentistaJDBC dentistaJDBC;
    
    private ArrayList <PacienteTable> pacientes;
    private PacienteJDBC pacienteJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JFormattedTextField dataExameInputCadastro;
    private JTextField descricaoExameInputCadastro;
    private JTextField observacaoExameInputCadastro;
    private JTextField resultadoExameInputCadastro;
    JPanel panelDentistaExameInputCadastro;
    JTable tableDentistaExameInputCadastro;
    JPanel panelPacienteExameInputCadastro;
    JTable tablePacienteExameInputCadastro;

    private JFormattedTextField dataExameInputAtualizacao;
    private JTextField descricaoExameInputAtualizacao;
    private JTextField observacaoExameInputAtualizacao;
    private JTextField resultadoExameInputAtualizacao;
    JPanel panelDentistaExameInputAtualizacao;
    JTable tableDentistaExameInputAtualizacao;
    JPanel panelPacienteExameInputAtualizacao;
    JTable tablePacienteExameInputAtualizacao;
    
    private JFormattedTextField dataExameInputRemocao;
    private JTextField descricaoExameInputRemocao;
    private JTextField observacaoExameInputRemocao;
    private JTextField resultadoExameInputRemocao;
    JPanel panelDentistaExameInputRemocao;
    JTable tableDentistaExameInputRemocao;
    JPanel panelPacienteExameInputRemocao;
    JTable tablePacienteExameInputRemocao;

    JPanel panelCadastrarLinha5;
    JPanel panelAtualizarLinha5;
    JPanel panelRemoverLinha5;
    
    JPanel panelCadastrarLinha6;
    JPanel panelAtualizarLinha6;
    JPanel panelRemoverLinha6;
    
    JPanel panelAtualizarLinha7;
    JPanel panelRemoverLinha7;
    
    JTable tableExamesAtualizacao;
    JTable tableExamesRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        exameJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaExames();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void Desconectar() throws SQLException{
        
        exameJDBC.EncerrarConexao();
        dentistaJDBC.EncerrarConexao();
        pacienteJDBC.EncerrarConexao();
    }

    public void AtualizarListaExames() throws SQLException{
        
        String nomeColunasExames[] = {"Data", "Descrição", "Observacao", "Resultado", "Dentista", "Paciente"};
    
        exames = exameJDBC.ListarExames();
        if(exames.size() > 0)
        {
            String[][] listaExames = new String[exames.size()][6];
            for(int count = 0; count < exames.size(); count++)
            {
                DentistaTable dentistaTable = new DentistaTable();
                dentistaTable.SetIdDentista(exames.get(count).GetIdDentista());
                dentistaJDBC.ProcurarDentista(dentistaTable);

                PacienteTable pacienteTable = new PacienteTable();
                pacienteTable.SetIdPaciente(exames.get(count).GetIdPaciente());
                pacienteJDBC.ProcurarPaciente(pacienteTable);
                
                listaExames[count][0] = exames.get(count).GetData().toString();
                listaExames[count][1] = exames.get(count).GetDescricao();
                listaExames[count][2] = exames.get(count).GetObservacao();
                listaExames[count][3] = exames.get(count).GetResultado();
                listaExames[count][4] = dentistaTable.GetNome();
                listaExames[count][5] = pacienteTable.GetNome();
            }
            
            tableExamesAtualizacao = new JTable(listaExames, nomeColunasExames);

            JScrollPane listExamesAtualizarScroll = new JScrollPane(tableExamesAtualizacao);
            listExamesAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha7.removeAll();
            panelAtualizarLinha7.add(listExamesAtualizarScroll);
            
            panelAtualizarLinha7.setVisible(true);
            panelAtualizarLinha7.updateUI();
            
            tableExamesAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableExamesAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableExamesAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableExamesRemocao = new JTable(listaExames, nomeColunasExames);

            JScrollPane listExamesRemoverScroll = new JScrollPane(tableExamesRemocao);
            listExamesRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha7.removeAll();
            panelRemoverLinha7.add(listExamesRemoverScroll);
            
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

            tableDentistaExameInputCadastro = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaExameInputCadastro = new JScrollPane(tableDentistaExameInputCadastro);
            paneDentistaExameInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha5.removeAll();
            panelCadastrarLinha5.add(paneDentistaExameInputCadastro);
            
            panelCadastrarLinha5.setVisible(true);
            panelCadastrarLinha5.updateUI();

            tableDentistaExameInputAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaExameInputAtualizacao = new JScrollPane(tableDentistaExameInputAtualizacao);
            paneDentistaExameInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha5.removeAll();
            panelAtualizarLinha5.add(paneDentistaExameInputAtualizacao);
            
            panelAtualizarLinha5.setVisible(true);
            panelAtualizarLinha5.updateUI();
            
            tableDentistaExameInputRemocao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaExameInputRemocao = new JScrollPane(tableDentistaExameInputRemocao);
            paneDentistaExameInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha5.removeAll();
            panelRemoverLinha5.add(paneDentistaExameInputRemocao);
            
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

            tablePacienteExameInputCadastro = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteExameInputCadastro = new JScrollPane(tablePacienteExameInputCadastro);
            panePacienteExameInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha6.removeAll();
            panelCadastrarLinha6.add(panePacienteExameInputCadastro);
            
            panelCadastrarLinha6.setVisible(true);
            panelCadastrarLinha6.updateUI();

            tablePacienteExameInputAtualizacao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteExameInputAtualizacao = new JScrollPane(tablePacienteExameInputAtualizacao);
            panePacienteExameInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha6.removeAll();
            panelAtualizarLinha6.add(panePacienteExameInputAtualizacao);
            
            panelAtualizarLinha6.setVisible(true);
            panelAtualizarLinha6.updateUI();
            
            tablePacienteExameInputRemocao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteExameInputRemocao = new JScrollPane(tablePacienteExameInputRemocao);
            panePacienteExameInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha6.removeAll();
            panelRemoverLinha6.add(panePacienteExameInputRemocao);
            
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

        FileWriter fileRelatorioExames = new FileWriter("relatorio_exames");
        BufferedWriter bufferFileRelatorioExames = new BufferedWriter(fileRelatorioExames);

        bufferFileRelatorioExames.write("***********************\n");
        bufferFileRelatorioExames.write("Relatorio de exames.\n");
        bufferFileRelatorioExames.write("***********************\n\n");

        for(int count = 0; count < exames.size(); count++)
        {
            DentistaTable dentistaTable = new DentistaTable();
            dentistaTable.SetIdDentista(exames.get(count).GetIdDentista());
            dentistaJDBC.ProcurarDentista(dentistaTable);
            
            PacienteTable pacienteTable = new PacienteTable();
            pacienteTable.SetIdPaciente(exames.get(count).GetIdPaciente());
            pacienteJDBC.ProcurarPaciente(pacienteTable);
 
            bufferFileRelatorioExames.write("Data: "+exames.get(count).GetData().toString()+"\n");
            bufferFileRelatorioExames.write("Descricao: "+exames.get(count).GetDescricao()+"\n");
            bufferFileRelatorioExames.write("Observacao: "+exames.get(count).GetObservacao()+"\n");
            bufferFileRelatorioExames.write("Resultado: "+exames.get(count).GetResultado()+"\n");
            bufferFileRelatorioExames.write("Nome Dentista: "+dentistaTable.GetNome()+"\n");
            bufferFileRelatorioExames.write("Nome Paciente: "+pacienteTable.GetNome()+"\n");
        }

        bufferFileRelatorioExames.close();
        fileRelatorioExames.close();
    }
    
    public void AdicionarExame() throws SQLException{

        if(dataExameInputCadastro.isEditValid() && 
           descricaoExameInputCadastro.getText().length() > 0 &&
           observacaoExameInputCadastro.getText().length() > 0 &&
           resultadoExameInputCadastro.getText().length() > 0 &&
           tableDentistaExameInputCadastro.getSelectedRow() >= 0 && tableDentistaExameInputCadastro.getSelectedRow() < dentistas.size() &&
           tablePacienteExameInputCadastro.getSelectedRow() >= 0 && tablePacienteExameInputCadastro.getSelectedRow() < pacientes.size())
        {
            ExameTable exame = new ExameTable();
            exame.SetData(Date.valueOf(dataExameInputCadastro.getText()));
            exame.SetDescricao(descricaoExameInputCadastro.getText());
            exame.SetObservacao(observacaoExameInputCadastro.getText());
            exame.SetResultado(resultadoExameInputCadastro.getText());
            exame.SetIdDentista(dentistas.get(tableDentistaExameInputCadastro.getSelectedRow()).GetIdDentista());
            exame.SetIdPaciente(pacientes.get(tablePacienteExameInputCadastro.getSelectedRow()).GetIdPaciente());
            
            exameJDBC.AdicionarExame(exame);

            dataExameInputCadastro.setText("    -  -  ");
            descricaoExameInputCadastro.setText("");
            observacaoExameInputCadastro.setText("");
            resultadoExameInputCadastro.setText("");
        }

        AtualizarListaExames();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void AtualizarExame() throws SQLException{

        if(dataExameInputAtualizacao.isEditValid() && 
           descricaoExameInputAtualizacao.getText().length() > 0 &&
           observacaoExameInputAtualizacao.getText().length() > 0 &&
           resultadoExameInputAtualizacao.getText().length() > 0 &&
           tableDentistaExameInputAtualizacao.getSelectedRow() >= 0 && tableDentistaExameInputAtualizacao.getSelectedRow() < dentistas.size() &&
           tablePacienteExameInputAtualizacao.getSelectedRow() >= 0 && tablePacienteExameInputAtualizacao.getSelectedRow() < pacientes.size() &&
           tableExamesAtualizacao.getSelectedRow() >= 0 && tableExamesAtualizacao.getSelectedRow() < exames.size())
        {
            ExameTable exame = new ExameTable();
            exame.SetIdExame(exames.get(tableExamesAtualizacao.getSelectedRow()).GetIdExame());
            exame.SetData(Date.valueOf(dataExameInputAtualizacao.getText()));
            exame.SetDescricao(descricaoExameInputAtualizacao.getText());
            exame.SetObservacao(observacaoExameInputAtualizacao.getText());
            exame.SetResultado(resultadoExameInputAtualizacao.getText());
            exame.SetIdDentista(dentistas.get(tableDentistaExameInputAtualizacao.getSelectedRow()).GetIdDentista());
            exame.SetIdPaciente(pacientes.get(tablePacienteExameInputAtualizacao.getSelectedRow()).GetIdPaciente());
            
            exameJDBC.AtualizarExame(exame);
            
            dataExameInputAtualizacao.setText("    -  -  ");
            descricaoExameInputAtualizacao.setText("");
            observacaoExameInputAtualizacao.setText("");
            resultadoExameInputAtualizacao.setText("");
        }
        
        AtualizarListaExames();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void RemoverExame() throws SQLException{
        
        if(tableExamesRemocao.getSelectedRow() >= 0 && tableExamesRemocao.getSelectedRow() < exames.size())
        {
            exameJDBC.RemoverExame(exames.get(tableExamesRemocao.getSelectedRow()));
        }
        
        AtualizarListaExames();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableExamesAtualizacao.getSelectedRow() >= 0 && tableExamesAtualizacao.getSelectedRow() < exames.size())
        {
            dataExameInputAtualizacao.setEnabled(true);
            descricaoExameInputAtualizacao.setEnabled(true);
            observacaoExameInputAtualizacao.setEnabled(true);
            resultadoExameInputAtualizacao.setEnabled(true);
                    
            dataExameInputAtualizacao.setText(exames.get(tableExamesAtualizacao.getSelectedRow()).GetData().toString());
            descricaoExameInputAtualizacao.setText(exames.get(tableExamesAtualizacao.getSelectedRow()).GetDescricao());
            observacaoExameInputAtualizacao.setText(exames.get(tableExamesAtualizacao.getSelectedRow()).GetObservacao());
            resultadoExameInputAtualizacao.setText(exames.get(tableExamesAtualizacao.getSelectedRow()).GetResultado());
            dataExameInputAtualizacao.validate();
        }
        else
        {
            dataExameInputAtualizacao.setEnabled(false);
            descricaoExameInputAtualizacao.setEnabled(false);
            observacaoExameInputAtualizacao.setEnabled(false);
            resultadoExameInputAtualizacao.setEnabled(false);
                    
            dataExameInputAtualizacao.setText("    -  -  ");
            descricaoExameInputAtualizacao.setText("");
            observacaoExameInputAtualizacao.setText("");
            resultadoExameInputAtualizacao.setText("");
            dataExameInputAtualizacao.validate();
        }
    }
        
    public GestaoExames() throws ClassNotFoundException, SQLException{
        
        exameJDBC = new ExameJDBC();
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
        tabbedPane.addTab("Cadastrar Exame", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataExameText1 = new JTextArea();
        dataExameText1.setText("Data do exame: ");
        dataExameText1.setEditable(false);
        dataExameText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataExameText1);

        dataExameInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataExameInputCadastro.setColumns(10);
        panelCadastrarLinha1.add(dataExameInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoExameText1 = new JTextArea();
        descricaoExameText1.setText("Descrição do exame: ");
        descricaoExameText1.setEditable(false);
        descricaoExameText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(descricaoExameText1);

        descricaoExameInputCadastro = new JTextField();
        descricaoExameInputCadastro.setColumns(50);
        panelCadastrarLinha2.add(descricaoExameInputCadastro);
        
        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoExameText1 = new JTextArea();
        observacaoExameText1.setText("Observação do exame: ");
        observacaoExameText1.setEditable(false);
        observacaoExameText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(observacaoExameText1);

        observacaoExameInputCadastro = new JTextField();
        observacaoExameInputCadastro.setColumns(50);
        panelCadastrarLinha3.add(observacaoExameInputCadastro);

        JPanel panelCadastrarLinha4 = new JPanel();
        panelCadastrarLinha4.setLayout(new BoxLayout(panelCadastrarLinha4, BoxLayout.X_AXIS));
        panelCadastrarLinha4.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea resultadoExameText1 = new JTextArea();
        resultadoExameText1.setText("Resultado do exame: ");
        resultadoExameText1.setEditable(false);
        resultadoExameText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha4.add(resultadoExameText1);

        resultadoExameInputCadastro = new JTextField();
        resultadoExameInputCadastro.setColumns(50);
        panelCadastrarLinha4.add(resultadoExameInputCadastro);
        
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

        JButton cadastrarExame = new JButton("Cadastrar Exame");
        panelCadastrar.add(cadastrarExame);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarExame();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoExames.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Exame", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataExameText2 = new JTextArea();
        dataExameText2.setText("Data do exame: ");
        dataExameText2.setEditable(false);
        dataExameText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataExameText2);

        dataExameInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataExameInputAtualizacao.setColumns(10);
        panelAtualizarLinha1.add(dataExameInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoExameText2 = new JTextArea();
        descricaoExameText2.setText("Descrição do exame: ");
        descricaoExameText2.setEditable(false);
        descricaoExameText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(descricaoExameText2);

        descricaoExameInputAtualizacao = new JTextField();
        descricaoExameInputAtualizacao.setColumns(50);
        panelAtualizarLinha2.add(descricaoExameInputAtualizacao);
        
        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoExameText2 = new JTextArea();
        observacaoExameText2.setText("Observação do exame: ");
        observacaoExameText2.setEditable(false);
        observacaoExameText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(observacaoExameText2);

        observacaoExameInputAtualizacao = new JTextField();
        observacaoExameInputAtualizacao.setColumns(50);
        panelAtualizarLinha3.add(observacaoExameInputAtualizacao);

        JPanel panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea resultadoExameText2 = new JTextArea();
        resultadoExameText2.setText("Resultado do exame: ");
        resultadoExameText2.setEditable(false);
        resultadoExameText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha4.add(resultadoExameText2);

        resultadoExameInputAtualizacao = new JTextField();
        resultadoExameInputAtualizacao.setColumns(50);
        panelAtualizarLinha4.add(resultadoExameInputAtualizacao);
        
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

        JButton atualizarExame = new JButton("Atualizar Exame");
        panelAtualizar.add(atualizarExame);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarExame();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoExames.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Exame", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataExameText3 = new JTextArea();
        dataExameText3.setText("Data do exame: ");
        dataExameText3.setEditable(false);
        dataExameText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataExameText3);

        dataExameInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataExameInputRemocao.setColumns(10);
        panelRemoverLinha1.add(dataExameInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoExameText3 = new JTextArea();
        descricaoExameText3.setText("Descrição do exame: ");
        descricaoExameText3.setEditable(false);
        descricaoExameText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(descricaoExameText3);

        descricaoExameInputRemocao = new JTextField();
        descricaoExameInputRemocao.setColumns(50);
        panelRemoverLinha2.add(descricaoExameInputRemocao);
        
        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoExameText3 = new JTextArea();
        observacaoExameText3.setText("Observação do exame: ");
        observacaoExameText3.setEditable(false);
        observacaoExameText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(observacaoExameText3);

        observacaoExameInputRemocao = new JTextField();
        observacaoExameInputRemocao.setColumns(50);
        panelRemoverLinha3.add(observacaoExameInputRemocao);

        JPanel panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea resultadoExameText3 = new JTextArea();
        resultadoExameText3.setText("Resultado do exame: ");
        resultadoExameText3.setEditable(false);
        resultadoExameText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha4.add(resultadoExameText3);

        resultadoExameInputRemocao = new JTextField();
        resultadoExameInputRemocao.setColumns(50);
        panelRemoverLinha4.add(resultadoExameInputRemocao);
        
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

        JButton removerExame = new JButton("Remover Exame");
        panelRemover.add(removerExame);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverExame();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoExames.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Exame", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoExame = new JButton("Relatório Padrão dos Exames");
        panelRelatorio.add(relatorioPadraoExame);
        
        relatorioPadraoExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoExames.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoExames.class.getName()).log(Level.SEVERE, null, ex);
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
        
        dataExameInputAtualizacao.setEnabled(false);
        descricaoExameInputAtualizacao.setEnabled(false);
        observacaoExameInputAtualizacao.setEnabled(false);
        resultadoExameInputAtualizacao.setEnabled(false);
        
        dataExameInputRemocao.setEnabled(false);
        descricaoExameInputRemocao.setEnabled(false);
        observacaoExameInputRemocao.setEnabled(false);
        resultadoExameInputRemocao.setEnabled(false);
    }   
}
