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
public class GestaoCirurgias extends JPanel{
    
    private ArrayList <CirurgiaTable> cirurgias;
    private CirurgiaJDBC cirurgiaJDBC;
    
    private ArrayList <DentistaTable> dentistas;
    private DentistaJDBC dentistaJDBC;
    
    private ArrayList <PacienteTable> pacientes;
    private PacienteJDBC pacienteJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JFormattedTextField dataCirurgiaInputCadastro;
    private JTextField descricaoCirurgiaInputCadastro;
    private JTextField observacaoCirurgiaInputCadastro;
    private JTextField tipoCirurgiaInputCadastro;
    JPanel panelDentistaCirurgiaInputCadastro;
    JTable tableDentistaCirurgiaInputCadastro;
    JPanel panelPacienteCirurgiaInputCadastro;
    JTable tablePacienteCirurgiaInputCadastro;

    private JFormattedTextField dataCirurgiaInputAtualizacao;
    private JTextField descricaoCirurgiaInputAtualizacao;
    private JTextField observacaoCirurgiaInputAtualizacao;
    private JTextField tipoCirurgiaInputAtualizacao;
    JPanel panelDentistaCirurgiaInputAtualizacao;
    JTable tableDentistaCirurgiaInputAtualizacao;
    JPanel panelPacienteCirurgiaInputAtualizacao;
    JTable tablePacienteCirurgiaInputAtualizacao;
    
    private JFormattedTextField dataCirurgiaInputRemocao;
    private JTextField descricaoCirurgiaInputRemocao;
    private JTextField observacaoCirurgiaInputRemocao;
    private JTextField tipoCirurgiaInputRemocao;
    JPanel panelDentistaCirurgiaInputRemocao;
    JTable tableDentistaCirurgiaInputRemocao;
    JPanel panelPacienteCirurgiaInputRemocao;
    JTable tablePacienteCirurgiaInputRemocao;

    JPanel panelCadastrarLinha5;
    JPanel panelAtualizarLinha5;
    JPanel panelRemoverLinha5;
    
    JPanel panelCadastrarLinha6;
    JPanel panelAtualizarLinha6;
    JPanel panelRemoverLinha6;
    
    JPanel panelAtualizarLinha7;
    JPanel panelRemoverLinha7;
    
    JTable tableCirurgiasAtualizacao;
    JTable tableCirurgiasRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        cirurgiaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaCirurgias();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void Desconectar() throws SQLException{
        
        cirurgiaJDBC.EncerrarConexao();
        dentistaJDBC.EncerrarConexao();
        pacienteJDBC.EncerrarConexao();
    }

    public void AtualizarListaCirurgias() throws SQLException{
        
        String nomeColunasCirurgias[] = {"Data", "Descrição", "Observacao", "Tipo", "Dentista", "Paciente"};
    
        cirurgias = cirurgiaJDBC.ListarCirurgias();
        if(cirurgias.size() > 0)
        {
            String[][] listaCirurgias = new String[cirurgias.size()][6];
            for(int count = 0; count < cirurgias.size(); count++)
            {
                DentistaTable dentistaTable = new DentistaTable();
                dentistaTable.SetIdDentista(cirurgias.get(count).GetIdDentista());
                dentistaJDBC.ProcurarDentista(dentistaTable);

                PacienteTable pacienteTable = new PacienteTable();
                pacienteTable.SetIdPaciente(cirurgias.get(count).GetIdPaciente());
                pacienteJDBC.ProcurarPaciente(pacienteTable);
                
                listaCirurgias[count][0] = cirurgias.get(count).GetData().toString();
                listaCirurgias[count][1] = cirurgias.get(count).GetDescricao();
                listaCirurgias[count][2] = cirurgias.get(count).GetObservacao();
                listaCirurgias[count][3] = cirurgias.get(count).GetTipo();
                listaCirurgias[count][4] = dentistaTable.GetNome();
                listaCirurgias[count][5] = pacienteTable.GetNome();
            }
            
            tableCirurgiasAtualizacao = new JTable(listaCirurgias, nomeColunasCirurgias);

            JScrollPane listCirurgiasAtualizarScroll = new JScrollPane(tableCirurgiasAtualizacao);
            listCirurgiasAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha7.removeAll();
            panelAtualizarLinha7.add(listCirurgiasAtualizarScroll);
            
            panelAtualizarLinha7.setVisible(true);
            panelAtualizarLinha7.updateUI();
            
            tableCirurgiasAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableCirurgiasAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableCirurgiasAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableCirurgiasRemocao = new JTable(listaCirurgias, nomeColunasCirurgias);

            JScrollPane listCirurgiasRemoverScroll = new JScrollPane(tableCirurgiasRemocao);
            listCirurgiasRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha7.removeAll();
            panelRemoverLinha7.add(listCirurgiasRemoverScroll);
            
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

            tableDentistaCirurgiaInputCadastro = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaCirurgiaInputCadastro = new JScrollPane(tableDentistaCirurgiaInputCadastro);
            paneDentistaCirurgiaInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha5.removeAll();
            panelCadastrarLinha5.add(paneDentistaCirurgiaInputCadastro);
            
            panelCadastrarLinha5.setVisible(true);
            panelCadastrarLinha5.updateUI();

            tableDentistaCirurgiaInputAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaCirurgiaInputAtualizacao = new JScrollPane(tableDentistaCirurgiaInputAtualizacao);
            paneDentistaCirurgiaInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha5.removeAll();
            panelAtualizarLinha5.add(paneDentistaCirurgiaInputAtualizacao);
            
            panelAtualizarLinha5.setVisible(true);
            panelAtualizarLinha5.updateUI();
            
            tableDentistaCirurgiaInputRemocao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaCirurgiaInputRemocao = new JScrollPane(tableDentistaCirurgiaInputRemocao);
            paneDentistaCirurgiaInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha5.removeAll();
            panelRemoverLinha5.add(paneDentistaCirurgiaInputRemocao);
            
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

            tablePacienteCirurgiaInputCadastro = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteCirurgiaInputCadastro = new JScrollPane(tablePacienteCirurgiaInputCadastro);
            panePacienteCirurgiaInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha6.removeAll();
            panelCadastrarLinha6.add(panePacienteCirurgiaInputCadastro);
            
            panelCadastrarLinha6.setVisible(true);
            panelCadastrarLinha6.updateUI();

            tablePacienteCirurgiaInputAtualizacao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteCirurgiaInputAtualizacao = new JScrollPane(tablePacienteCirurgiaInputAtualizacao);
            panePacienteCirurgiaInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha6.removeAll();
            panelAtualizarLinha6.add(panePacienteCirurgiaInputAtualizacao);
            
            panelAtualizarLinha6.setVisible(true);
            panelAtualizarLinha6.updateUI();
            
            tablePacienteCirurgiaInputRemocao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteCirurgiaInputRemocao = new JScrollPane(tablePacienteCirurgiaInputRemocao);
            panePacienteCirurgiaInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha6.removeAll();
            panelRemoverLinha6.add(panePacienteCirurgiaInputRemocao);
            
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

        FileWriter fileRelatorioCirurgias = new FileWriter("relatorio_cirurgias");
        BufferedWriter bufferFileRelatorioCirurgias = new BufferedWriter(fileRelatorioCirurgias);

        bufferFileRelatorioCirurgias.write("***********************\n");
        bufferFileRelatorioCirurgias.write("Relatorio de cirurgias.\n");
        bufferFileRelatorioCirurgias.write("***********************\n\n");

        for(int count = 0; count < cirurgias.size(); count++)
        {
            DentistaTable dentistaTable = new DentistaTable();
            dentistaTable.SetIdDentista(cirurgias.get(count).GetIdDentista());
            dentistaJDBC.ProcurarDentista(dentistaTable);
            
            PacienteTable pacienteTable = new PacienteTable();
            pacienteTable.SetIdPaciente(cirurgias.get(count).GetIdPaciente());
            pacienteJDBC.ProcurarPaciente(pacienteTable);
 
            bufferFileRelatorioCirurgias.write("Data: "+cirurgias.get(count).GetData().toString()+"\n");
            bufferFileRelatorioCirurgias.write("Descricao: "+cirurgias.get(count).GetDescricao()+"\n");
            bufferFileRelatorioCirurgias.write("Observacao: "+cirurgias.get(count).GetObservacao()+"\n");
            bufferFileRelatorioCirurgias.write("Tipo: "+cirurgias.get(count).GetTipo()+"\n");
            bufferFileRelatorioCirurgias.write("Nome Dentista: "+dentistaTable.GetNome()+"\n");
            bufferFileRelatorioCirurgias.write("Nome Paciente: "+pacienteTable.GetNome()+"\n");
        }

        bufferFileRelatorioCirurgias.close();
        fileRelatorioCirurgias.close();
    }
    
    public void AdicionarCirurgia() throws SQLException{

        if(dataCirurgiaInputCadastro.isEditValid() && 
           descricaoCirurgiaInputCadastro.getText().length() > 0 &&
           observacaoCirurgiaInputCadastro.getText().length() > 0 &&
           tipoCirurgiaInputCadastro.getText().length() > 0 &&
           tableDentistaCirurgiaInputCadastro.getSelectedRow() >= 0 && tableDentistaCirurgiaInputCadastro.getSelectedRow() < dentistas.size() &&
           tablePacienteCirurgiaInputCadastro.getSelectedRow() >= 0 && tablePacienteCirurgiaInputCadastro.getSelectedRow() < pacientes.size())
        {
            CirurgiaTable cirurgia = new CirurgiaTable();
            cirurgia.SetData(Date.valueOf(dataCirurgiaInputCadastro.getText()));
            cirurgia.SetDescricao(descricaoCirurgiaInputCadastro.getText());
            cirurgia.SetObservacao(observacaoCirurgiaInputCadastro.getText());
            cirurgia.SetTipo(tipoCirurgiaInputCadastro.getText());
            cirurgia.SetIdDentista(dentistas.get(tableDentistaCirurgiaInputCadastro.getSelectedRow()).GetIdDentista());
            cirurgia.SetIdPaciente(pacientes.get(tablePacienteCirurgiaInputCadastro.getSelectedRow()).GetIdPaciente());
            
            cirurgiaJDBC.AdicionarCirurgia(cirurgia);

            dataCirurgiaInputCadastro.setText("    -  -  ");
            descricaoCirurgiaInputCadastro.setText("");
            observacaoCirurgiaInputCadastro.setText("");
            tipoCirurgiaInputCadastro.setText("");
        }

        AtualizarListaCirurgias();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void AtualizarCirurgia() throws SQLException{

        if(dataCirurgiaInputAtualizacao.isEditValid() && 
           descricaoCirurgiaInputAtualizacao.getText().length() > 0 &&
           observacaoCirurgiaInputAtualizacao.getText().length() > 0 &&
           tipoCirurgiaInputAtualizacao.getText().length() > 0 &&
           tableDentistaCirurgiaInputAtualizacao.getSelectedRow() >= 0 && tableDentistaCirurgiaInputAtualizacao.getSelectedRow() < dentistas.size() &&
           tablePacienteCirurgiaInputAtualizacao.getSelectedRow() >= 0 && tablePacienteCirurgiaInputAtualizacao.getSelectedRow() < pacientes.size() &&
           tableCirurgiasAtualizacao.getSelectedRow() >= 0 && tableCirurgiasAtualizacao.getSelectedRow() < cirurgias.size())
        {
            CirurgiaTable cirurgia = new CirurgiaTable();
            cirurgia.SetIdCirurgia(cirurgias.get(tableCirurgiasAtualizacao.getSelectedRow()).GetIdCirurgia());
            cirurgia.SetData(Date.valueOf(dataCirurgiaInputAtualizacao.getText()));
            cirurgia.SetDescricao(descricaoCirurgiaInputAtualizacao.getText());
            cirurgia.SetObservacao(observacaoCirurgiaInputAtualizacao.getText());
            cirurgia.SetTipo(tipoCirurgiaInputAtualizacao.getText());
            cirurgia.SetIdDentista(dentistas.get(tableDentistaCirurgiaInputAtualizacao.getSelectedRow()).GetIdDentista());
            cirurgia.SetIdPaciente(pacientes.get(tablePacienteCirurgiaInputAtualizacao.getSelectedRow()).GetIdPaciente());
            
            cirurgiaJDBC.AtualizarCirurgia(cirurgia);
            
            dataCirurgiaInputAtualizacao.setText("    -  -  ");
            descricaoCirurgiaInputAtualizacao.setText("");
            observacaoCirurgiaInputAtualizacao.setText("");
            tipoCirurgiaInputAtualizacao.setText("");
        }
        
        AtualizarListaCirurgias();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void RemoverCirurgia() throws SQLException{
        
        if(tableCirurgiasRemocao.getSelectedRow() >= 0 && tableCirurgiasRemocao.getSelectedRow() < cirurgias.size())
        {
            cirurgiaJDBC.RemoverCirurgia(cirurgias.get(tableCirurgiasRemocao.getSelectedRow()));
        }
        
        AtualizarListaCirurgias();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableCirurgiasAtualizacao.getSelectedRow() >= 0 && tableCirurgiasAtualizacao.getSelectedRow() < cirurgias.size())
        {
            dataCirurgiaInputAtualizacao.setEnabled(true);
            descricaoCirurgiaInputAtualizacao.setEnabled(true);
            observacaoCirurgiaInputAtualizacao.setEnabled(true);
            tipoCirurgiaInputAtualizacao.setEnabled(true);
                    
            dataCirurgiaInputAtualizacao.setText(cirurgias.get(tableCirurgiasAtualizacao.getSelectedRow()).GetData().toString());
            descricaoCirurgiaInputAtualizacao.setText(cirurgias.get(tableCirurgiasAtualizacao.getSelectedRow()).GetDescricao());
            observacaoCirurgiaInputAtualizacao.setText(cirurgias.get(tableCirurgiasAtualizacao.getSelectedRow()).GetObservacao());
            tipoCirurgiaInputAtualizacao.setText(cirurgias.get(tableCirurgiasAtualizacao.getSelectedRow()).GetTipo());
            dataCirurgiaInputAtualizacao.validate();
        }
        else
        {
            dataCirurgiaInputAtualizacao.setEnabled(false);
            descricaoCirurgiaInputAtualizacao.setEnabled(false);
            observacaoCirurgiaInputAtualizacao.setEnabled(false);
            tipoCirurgiaInputAtualizacao.setEnabled(false);
                    
            dataCirurgiaInputAtualizacao.setText("    -  -  ");
            descricaoCirurgiaInputAtualizacao.setText("");
            observacaoCirurgiaInputAtualizacao.setText("");
            tipoCirurgiaInputAtualizacao.setText("");
            dataCirurgiaInputAtualizacao.validate();
        }
    }
        
    public GestaoCirurgias() throws ClassNotFoundException, SQLException{
        
        cirurgiaJDBC = new CirurgiaJDBC();
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
        tabbedPane.addTab("Cadastrar Cirurgia", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCirurgiaText1 = new JTextArea();
        dataCirurgiaText1.setText("Data da cirurgia: ");
        dataCirurgiaText1.setEditable(false);
        dataCirurgiaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataCirurgiaText1);

        dataCirurgiaInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataCirurgiaInputCadastro.setColumns(10);
        panelCadastrarLinha1.add(dataCirurgiaInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoCirurgiaText1 = new JTextArea();
        descricaoCirurgiaText1.setText("Descrição da cirurgia: ");
        descricaoCirurgiaText1.setEditable(false);
        descricaoCirurgiaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(descricaoCirurgiaText1);

        descricaoCirurgiaInputCadastro = new JTextField();
        descricaoCirurgiaInputCadastro.setColumns(50);
        panelCadastrarLinha2.add(descricaoCirurgiaInputCadastro);
        
        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoCirurgiaText1 = new JTextArea();
        observacaoCirurgiaText1.setText("Observação da cirurgia: ");
        observacaoCirurgiaText1.setEditable(false);
        observacaoCirurgiaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(observacaoCirurgiaText1);

        observacaoCirurgiaInputCadastro = new JTextField();
        observacaoCirurgiaInputCadastro.setColumns(50);
        panelCadastrarLinha3.add(observacaoCirurgiaInputCadastro);

        JPanel panelCadastrarLinha4 = new JPanel();
        panelCadastrarLinha4.setLayout(new BoxLayout(panelCadastrarLinha4, BoxLayout.X_AXIS));
        panelCadastrarLinha4.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoCirurgiaText1 = new JTextArea();
        tipoCirurgiaText1.setText("Tipo da cirurgia: ");
        tipoCirurgiaText1.setEditable(false);
        tipoCirurgiaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha4.add(tipoCirurgiaText1);

        tipoCirurgiaInputCadastro = new JTextField();
        tipoCirurgiaInputCadastro.setColumns(50);
        panelCadastrarLinha4.add(tipoCirurgiaInputCadastro);
        
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

        JButton cadastrarCirurgia = new JButton("Cadastrar Cirurgia");
        panelCadastrar.add(cadastrarCirurgia);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarCirurgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarCirurgia();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCirurgias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Cirurgia", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCirurgiaText2 = new JTextArea();
        dataCirurgiaText2.setText("Data da cirurgia: ");
        dataCirurgiaText2.setEditable(false);
        dataCirurgiaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataCirurgiaText2);

        dataCirurgiaInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataCirurgiaInputAtualizacao.setColumns(10);
        panelAtualizarLinha1.add(dataCirurgiaInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoCirurgiaText2 = new JTextArea();
        descricaoCirurgiaText2.setText("Descrição da cirurgia: ");
        descricaoCirurgiaText2.setEditable(false);
        descricaoCirurgiaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(descricaoCirurgiaText2);

        descricaoCirurgiaInputAtualizacao = new JTextField();
        descricaoCirurgiaInputAtualizacao.setColumns(50);
        panelAtualizarLinha2.add(descricaoCirurgiaInputAtualizacao);
        
        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoCirurgiaText2 = new JTextArea();
        observacaoCirurgiaText2.setText("Observação da cirurgia: ");
        observacaoCirurgiaText2.setEditable(false);
        observacaoCirurgiaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(observacaoCirurgiaText2);

        observacaoCirurgiaInputAtualizacao = new JTextField();
        observacaoCirurgiaInputAtualizacao.setColumns(50);
        panelAtualizarLinha3.add(observacaoCirurgiaInputAtualizacao);

        JPanel panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoCirurgiaText2 = new JTextArea();
        tipoCirurgiaText2.setText("Tipo da cirurgia: ");
        tipoCirurgiaText2.setEditable(false);
        tipoCirurgiaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha4.add(tipoCirurgiaText2);

        tipoCirurgiaInputAtualizacao = new JTextField();
        tipoCirurgiaInputAtualizacao.setColumns(50);
        panelAtualizarLinha4.add(tipoCirurgiaInputAtualizacao);
        
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

        JButton atualizarCirurgia = new JButton("Atualizar Cirurgia");
        panelAtualizar.add(atualizarCirurgia);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarCirurgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarCirurgia();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCirurgias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Cirurgia", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCirurgiaText3 = new JTextArea();
        dataCirurgiaText3.setText("Data da cirurgia: ");
        dataCirurgiaText3.setEditable(false);
        dataCirurgiaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataCirurgiaText3);

        dataCirurgiaInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataCirurgiaInputRemocao.setColumns(10);
        panelRemoverLinha1.add(dataCirurgiaInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoCirurgiaText3 = new JTextArea();
        descricaoCirurgiaText3.setText("Descrição da cirurgia: ");
        descricaoCirurgiaText3.setEditable(false);
        descricaoCirurgiaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(descricaoCirurgiaText3);

        descricaoCirurgiaInputRemocao = new JTextField();
        descricaoCirurgiaInputRemocao.setColumns(50);
        panelRemoverLinha2.add(descricaoCirurgiaInputRemocao);
        
        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoCirurgiaText3 = new JTextArea();
        observacaoCirurgiaText3.setText("Observação da cirurgia: ");
        observacaoCirurgiaText3.setEditable(false);
        observacaoCirurgiaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(observacaoCirurgiaText3);

        observacaoCirurgiaInputRemocao = new JTextField();
        observacaoCirurgiaInputRemocao.setColumns(50);
        panelRemoverLinha3.add(observacaoCirurgiaInputRemocao);

        JPanel panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoCirurgiaText3 = new JTextArea();
        tipoCirurgiaText3.setText("Tipo da cirurgia: ");
        tipoCirurgiaText3.setEditable(false);
        tipoCirurgiaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha4.add(tipoCirurgiaText3);

        tipoCirurgiaInputRemocao = new JTextField();
        tipoCirurgiaInputRemocao.setColumns(50);
        panelRemoverLinha4.add(tipoCirurgiaInputRemocao);
        
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

        JButton removerCirurgia = new JButton("Remover Cirurgia");
        panelRemover.add(removerCirurgia);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerCirurgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverCirurgia();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCirurgias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Cirurgia", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoCirurgia = new JButton("Relatório Padrão dos Cirurgias");
        panelRelatorio.add(relatorioPadraoCirurgia);
        
        relatorioPadraoCirurgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoCirurgias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoCirurgias.class.getName()).log(Level.SEVERE, null, ex);
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
        
        dataCirurgiaInputAtualizacao.setEnabled(false);
        descricaoCirurgiaInputAtualizacao.setEnabled(false);
        observacaoCirurgiaInputAtualizacao.setEnabled(false);
        tipoCirurgiaInputAtualizacao.setEnabled(false);
        
        dataCirurgiaInputRemocao.setEnabled(false);
        descricaoCirurgiaInputRemocao.setEnabled(false);
        observacaoCirurgiaInputRemocao.setEnabled(false);
        tipoCirurgiaInputRemocao.setEnabled(false);
    }   
}
