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
public class GestaoOrtodonticos extends JPanel{
    
    private ArrayList <OrtodonticoTable> ortodonticos;
    private OrtodonticoJDBC ortodonticoJDBC;
    
    private ArrayList <DentistaTable> dentistas;
    private DentistaJDBC dentistaJDBC;
    
    private ArrayList <PacienteTable> pacientes;
    private PacienteJDBC pacienteJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JFormattedTextField dataOrtodonticoInputCadastro;
    private JTextField descricaoOrtodonticoInputCadastro;
    private JTextField observacaoOrtodonticoInputCadastro;
    private JTextField tipoOrtodonticoInputCadastro;
    JPanel panelDentistaOrtodonticoInputCadastro;
    JTable tableDentistaOrtodonticoInputCadastro;
    JPanel panelPacienteOrtodonticoInputCadastro;
    JTable tablePacienteOrtodonticoInputCadastro;

    private JFormattedTextField dataOrtodonticoInputAtualizacao;
    private JTextField descricaoOrtodonticoInputAtualizacao;
    private JTextField observacaoOrtodonticoInputAtualizacao;
    private JTextField tipoOrtodonticoInputAtualizacao;
    JPanel panelDentistaOrtodonticoInputAtualizacao;
    JTable tableDentistaOrtodonticoInputAtualizacao;
    JPanel panelPacienteOrtodonticoInputAtualizacao;
    JTable tablePacienteOrtodonticoInputAtualizacao;
    
    private JFormattedTextField dataOrtodonticoInputRemocao;
    private JTextField descricaoOrtodonticoInputRemocao;
    private JTextField observacaoOrtodonticoInputRemocao;
    private JTextField tipoOrtodonticoInputRemocao;
    JPanel panelDentistaOrtodonticoInputRemocao;
    JTable tableDentistaOrtodonticoInputRemocao;
    JPanel panelPacienteOrtodonticoInputRemocao;
    JTable tablePacienteOrtodonticoInputRemocao;

    JPanel panelCadastrarLinha5;
    JPanel panelAtualizarLinha5;
    JPanel panelRemoverLinha5;
    
    JPanel panelCadastrarLinha6;
    JPanel panelAtualizarLinha6;
    JPanel panelRemoverLinha6;
    
    JPanel panelAtualizarLinha7;
    JPanel panelRemoverLinha7;
    
    JTable tableOrtodonticosAtualizacao;
    JTable tableOrtodonticosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        ortodonticoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaOrtodonticos();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void Desconectar() throws SQLException{
        
        ortodonticoJDBC.EncerrarConexao();
        dentistaJDBC.EncerrarConexao();
        pacienteJDBC.EncerrarConexao();
    }

    public void AtualizarListaOrtodonticos() throws SQLException{
        
        String nomeColunasOrtodonticos[] = {"Data", "Descrição", "Observacao", "Tipo", "Dentista", "Paciente"};
    
        ortodonticos = ortodonticoJDBC.ListarOrtodonticos();
        if(ortodonticos.size() > 0)
        {
            String[][] listaOrtodonticos = new String[ortodonticos.size()][6];
            for(int count = 0; count < ortodonticos.size(); count++)
            {
                DentistaTable dentistaTable = new DentistaTable();
                dentistaTable.SetIdDentista(ortodonticos.get(count).GetIdDentista());
                dentistaJDBC.ProcurarDentista(dentistaTable);

                PacienteTable pacienteTable = new PacienteTable();
                pacienteTable.SetIdPaciente(ortodonticos.get(count).GetIdPaciente());
                pacienteJDBC.ProcurarPaciente(pacienteTable);
                
                listaOrtodonticos[count][0] = ortodonticos.get(count).GetData().toString();
                listaOrtodonticos[count][1] = ortodonticos.get(count).GetDescricao();
                listaOrtodonticos[count][2] = ortodonticos.get(count).GetObservacao();
                listaOrtodonticos[count][3] = ortodonticos.get(count).GetTipo();
                listaOrtodonticos[count][4] = dentistaTable.GetNome();
                listaOrtodonticos[count][5] = pacienteTable.GetNome();
            }
            
            tableOrtodonticosAtualizacao = new JTable(listaOrtodonticos, nomeColunasOrtodonticos);

            JScrollPane listOrtodonticosAtualizarScroll = new JScrollPane(tableOrtodonticosAtualizacao);
            listOrtodonticosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha7.removeAll();
            panelAtualizarLinha7.add(listOrtodonticosAtualizarScroll);
            
            panelAtualizarLinha7.setVisible(true);
            panelAtualizarLinha7.updateUI();
            
            tableOrtodonticosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableOrtodonticosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableOrtodonticosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableOrtodonticosRemocao = new JTable(listaOrtodonticos, nomeColunasOrtodonticos);

            JScrollPane listOrtodonticosRemoverScroll = new JScrollPane(tableOrtodonticosRemocao);
            listOrtodonticosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha7.removeAll();
            panelRemoverLinha7.add(listOrtodonticosRemoverScroll);
            
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

            tableDentistaOrtodonticoInputCadastro = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaOrtodonticoInputCadastro = new JScrollPane(tableDentistaOrtodonticoInputCadastro);
            paneDentistaOrtodonticoInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha5.removeAll();
            panelCadastrarLinha5.add(paneDentistaOrtodonticoInputCadastro);
            
            panelCadastrarLinha5.setVisible(true);
            panelCadastrarLinha5.updateUI();

            tableDentistaOrtodonticoInputAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaOrtodonticoInputAtualizacao = new JScrollPane(tableDentistaOrtodonticoInputAtualizacao);
            paneDentistaOrtodonticoInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha5.removeAll();
            panelAtualizarLinha5.add(paneDentistaOrtodonticoInputAtualizacao);
            
            panelAtualizarLinha5.setVisible(true);
            panelAtualizarLinha5.updateUI();
            
            tableDentistaOrtodonticoInputRemocao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane paneDentistaOrtodonticoInputRemocao = new JScrollPane(tableDentistaOrtodonticoInputRemocao);
            paneDentistaOrtodonticoInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha5.removeAll();
            panelRemoverLinha5.add(paneDentistaOrtodonticoInputRemocao);
            
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

            tablePacienteOrtodonticoInputCadastro = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteOrtodonticoInputCadastro = new JScrollPane(tablePacienteOrtodonticoInputCadastro);
            panePacienteOrtodonticoInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha6.removeAll();
            panelCadastrarLinha6.add(panePacienteOrtodonticoInputCadastro);
            
            panelCadastrarLinha6.setVisible(true);
            panelCadastrarLinha6.updateUI();

            tablePacienteOrtodonticoInputAtualizacao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteOrtodonticoInputAtualizacao = new JScrollPane(tablePacienteOrtodonticoInputAtualizacao);
            panePacienteOrtodonticoInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha6.removeAll();
            panelAtualizarLinha6.add(panePacienteOrtodonticoInputAtualizacao);
            
            panelAtualizarLinha6.setVisible(true);
            panelAtualizarLinha6.updateUI();
            
            tablePacienteOrtodonticoInputRemocao = new JTable(listaPacientes, nomeColunasPacientes);

            JScrollPane panePacienteOrtodonticoInputRemocao = new JScrollPane(tablePacienteOrtodonticoInputRemocao);
            panePacienteOrtodonticoInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha6.removeAll();
            panelRemoverLinha6.add(panePacienteOrtodonticoInputRemocao);
            
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

        FileWriter fileRelatorioOrtodonticos = new FileWriter("relatorio_ortodonticos");
        BufferedWriter bufferFileRelatorioOrtodonticos = new BufferedWriter(fileRelatorioOrtodonticos);

        bufferFileRelatorioOrtodonticos.write("***********************\n");
        bufferFileRelatorioOrtodonticos.write("Relatorio de ortodonticos.\n");
        bufferFileRelatorioOrtodonticos.write("***********************\n\n");

        for(int count = 0; count < ortodonticos.size(); count++)
        {
            DentistaTable dentistaTable = new DentistaTable();
            dentistaTable.SetIdDentista(ortodonticos.get(count).GetIdDentista());
            dentistaJDBC.ProcurarDentista(dentistaTable);
            
            PacienteTable pacienteTable = new PacienteTable();
            pacienteTable.SetIdPaciente(ortodonticos.get(count).GetIdPaciente());
            pacienteJDBC.ProcurarPaciente(pacienteTable);
 
            bufferFileRelatorioOrtodonticos.write("Data: "+ortodonticos.get(count).GetData().toString()+"\n");
            bufferFileRelatorioOrtodonticos.write("Descricao: "+ortodonticos.get(count).GetDescricao()+"\n");
            bufferFileRelatorioOrtodonticos.write("Observacao: "+ortodonticos.get(count).GetObservacao()+"\n");
            bufferFileRelatorioOrtodonticos.write("Tipo: "+ortodonticos.get(count).GetTipo()+"\n");
            bufferFileRelatorioOrtodonticos.write("Nome Dentista: "+dentistaTable.GetNome()+"\n");
            bufferFileRelatorioOrtodonticos.write("Nome Paciente: "+pacienteTable.GetNome()+"\n");
        }

        bufferFileRelatorioOrtodonticos.close();
        fileRelatorioOrtodonticos.close();
    }
    
    public void AdicionarOrtodontico() throws SQLException{

        if(dataOrtodonticoInputCadastro.isEditValid() && 
           descricaoOrtodonticoInputCadastro.getText().length() > 0 &&
           observacaoOrtodonticoInputCadastro.getText().length() > 0 &&
           tipoOrtodonticoInputCadastro.getText().length() > 0 &&
           tableDentistaOrtodonticoInputCadastro.getSelectedRow() >= 0 && tableDentistaOrtodonticoInputCadastro.getSelectedRow() < dentistas.size() &&
           tablePacienteOrtodonticoInputCadastro.getSelectedRow() >= 0 && tablePacienteOrtodonticoInputCadastro.getSelectedRow() < pacientes.size())
        {
            OrtodonticoTable ortodontico = new OrtodonticoTable();
            ortodontico.SetData(Date.valueOf(dataOrtodonticoInputCadastro.getText()));
            ortodontico.SetDescricao(descricaoOrtodonticoInputCadastro.getText());
            ortodontico.SetObservacao(observacaoOrtodonticoInputCadastro.getText());
            ortodontico.SetTipo(tipoOrtodonticoInputCadastro.getText());
            ortodontico.SetIdDentista(dentistas.get(tableDentistaOrtodonticoInputCadastro.getSelectedRow()).GetIdDentista());
            ortodontico.SetIdPaciente(pacientes.get(tablePacienteOrtodonticoInputCadastro.getSelectedRow()).GetIdPaciente());
            
            ortodonticoJDBC.AdicionarOrtodontico(ortodontico);

            dataOrtodonticoInputCadastro.setText("    -  -  ");
            descricaoOrtodonticoInputCadastro.setText("");
            observacaoOrtodonticoInputCadastro.setText("");
            tipoOrtodonticoInputCadastro.setText("");
        }

        AtualizarListaOrtodonticos();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void AtualizarOrtodontico() throws SQLException{

        if(dataOrtodonticoInputAtualizacao.isEditValid() && 
           descricaoOrtodonticoInputAtualizacao.getText().length() > 0 &&
           observacaoOrtodonticoInputAtualizacao.getText().length() > 0 &&
           tipoOrtodonticoInputAtualizacao.getText().length() > 0 &&
           tableDentistaOrtodonticoInputAtualizacao.getSelectedRow() >= 0 && tableDentistaOrtodonticoInputAtualizacao.getSelectedRow() < dentistas.size() &&
           tablePacienteOrtodonticoInputAtualizacao.getSelectedRow() >= 0 && tablePacienteOrtodonticoInputAtualizacao.getSelectedRow() < pacientes.size() &&
           tableOrtodonticosAtualizacao.getSelectedRow() >= 0 && tableOrtodonticosAtualizacao.getSelectedRow() < ortodonticos.size())
        {
            OrtodonticoTable ortodontico = new OrtodonticoTable();
            ortodontico.SetIdOrtodontico(ortodonticos.get(tableOrtodonticosAtualizacao.getSelectedRow()).GetIdOrtodontico());
            ortodontico.SetData(Date.valueOf(dataOrtodonticoInputAtualizacao.getText()));
            ortodontico.SetDescricao(descricaoOrtodonticoInputAtualizacao.getText());
            ortodontico.SetObservacao(observacaoOrtodonticoInputAtualizacao.getText());
            ortodontico.SetTipo(tipoOrtodonticoInputAtualizacao.getText());
            ortodontico.SetIdDentista(dentistas.get(tableDentistaOrtodonticoInputAtualizacao.getSelectedRow()).GetIdDentista());
            ortodontico.SetIdPaciente(pacientes.get(tablePacienteOrtodonticoInputAtualizacao.getSelectedRow()).GetIdPaciente());
            
            ortodonticoJDBC.AtualizarOrtodontico(ortodontico);
            
            dataOrtodonticoInputAtualizacao.setText("    -  -  ");
            descricaoOrtodonticoInputAtualizacao.setText("");
            observacaoOrtodonticoInputAtualizacao.setText("");
            tipoOrtodonticoInputAtualizacao.setText("");
        }
        
        AtualizarListaOrtodonticos();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
    }
    
    public void RemoverOrtodontico() throws SQLException{
        
        if(tableOrtodonticosRemocao.getSelectedRow() >= 0 && tableOrtodonticosRemocao.getSelectedRow() < ortodonticos.size())
        {
            ortodonticoJDBC.RemoverOrtodontico(ortodonticos.get(tableOrtodonticosRemocao.getSelectedRow()));
        }
        
        AtualizarListaOrtodonticos();
        AtualizarListaDentistas();
        AtualizarListaPacientes();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableOrtodonticosAtualizacao.getSelectedRow() >= 0 && tableOrtodonticosAtualizacao.getSelectedRow() < ortodonticos.size())
        {
            dataOrtodonticoInputAtualizacao.setEnabled(true);
            descricaoOrtodonticoInputAtualizacao.setEnabled(true);
            observacaoOrtodonticoInputAtualizacao.setEnabled(true);
            tipoOrtodonticoInputAtualizacao.setEnabled(true);
                    
            dataOrtodonticoInputAtualizacao.setText(ortodonticos.get(tableOrtodonticosAtualizacao.getSelectedRow()).GetData().toString());
            descricaoOrtodonticoInputAtualizacao.setText(ortodonticos.get(tableOrtodonticosAtualizacao.getSelectedRow()).GetDescricao());
            observacaoOrtodonticoInputAtualizacao.setText(ortodonticos.get(tableOrtodonticosAtualizacao.getSelectedRow()).GetObservacao());
            tipoOrtodonticoInputAtualizacao.setText(ortodonticos.get(tableOrtodonticosAtualizacao.getSelectedRow()).GetTipo());
            dataOrtodonticoInputAtualizacao.validate();
        }
        else
        {
            dataOrtodonticoInputAtualizacao.setEnabled(false);
            descricaoOrtodonticoInputAtualizacao.setEnabled(false);
            observacaoOrtodonticoInputAtualizacao.setEnabled(false);
            tipoOrtodonticoInputAtualizacao.setEnabled(false);
                    
            dataOrtodonticoInputAtualizacao.setText("    -  -  ");
            descricaoOrtodonticoInputAtualizacao.setText("");
            observacaoOrtodonticoInputAtualizacao.setText("");
            tipoOrtodonticoInputAtualizacao.setText("");
            dataOrtodonticoInputAtualizacao.validate();
        }
    }
        
    public GestaoOrtodonticos() throws ClassNotFoundException, SQLException{
        
        ortodonticoJDBC = new OrtodonticoJDBC();
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
        tabbedPane.addTab("Cadastrar Procedimento Ortodontico", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataOrtodonticoText1 = new JTextArea();
        dataOrtodonticoText1.setText("Data do procedimento ortodontico: ");
        dataOrtodonticoText1.setEditable(false);
        dataOrtodonticoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataOrtodonticoText1);

        dataOrtodonticoInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataOrtodonticoInputCadastro.setColumns(10);
        panelCadastrarLinha1.add(dataOrtodonticoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoOrtodonticoText1 = new JTextArea();
        descricaoOrtodonticoText1.setText("Descrição do procedimento ortodontico: ");
        descricaoOrtodonticoText1.setEditable(false);
        descricaoOrtodonticoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(descricaoOrtodonticoText1);

        descricaoOrtodonticoInputCadastro = new JTextField();
        descricaoOrtodonticoInputCadastro.setColumns(50);
        panelCadastrarLinha2.add(descricaoOrtodonticoInputCadastro);
        
        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoOrtodonticoText1 = new JTextArea();
        observacaoOrtodonticoText1.setText("Observação do procedimento ortodontico: ");
        observacaoOrtodonticoText1.setEditable(false);
        observacaoOrtodonticoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(observacaoOrtodonticoText1);

        observacaoOrtodonticoInputCadastro = new JTextField();
        observacaoOrtodonticoInputCadastro.setColumns(50);
        panelCadastrarLinha3.add(observacaoOrtodonticoInputCadastro);

        JPanel panelCadastrarLinha4 = new JPanel();
        panelCadastrarLinha4.setLayout(new BoxLayout(panelCadastrarLinha4, BoxLayout.X_AXIS));
        panelCadastrarLinha4.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoOrtodonticoText1 = new JTextArea();
        tipoOrtodonticoText1.setText("Tipo do procedimento ortodontico: ");
        tipoOrtodonticoText1.setEditable(false);
        tipoOrtodonticoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha4.add(tipoOrtodonticoText1);

        tipoOrtodonticoInputCadastro = new JTextField();
        tipoOrtodonticoInputCadastro.setColumns(50);
        panelCadastrarLinha4.add(tipoOrtodonticoInputCadastro);
        
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

        JButton cadastrarOrtodontico = new JButton("Cadastrar Procedimento Ortodontico");
        panelCadastrar.add(cadastrarOrtodontico);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarOrtodontico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarOrtodontico();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoOrtodonticos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Procedimento Ortodontico", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataOrtodonticoText2 = new JTextArea();
        dataOrtodonticoText2.setText("Data do procedimento ortodontico: ");
        dataOrtodonticoText2.setEditable(false);
        dataOrtodonticoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataOrtodonticoText2);

        dataOrtodonticoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataOrtodonticoInputAtualizacao.setColumns(10);
        panelAtualizarLinha1.add(dataOrtodonticoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoOrtodonticoText2 = new JTextArea();
        descricaoOrtodonticoText2.setText("Descrição do procedimento ortodontico: ");
        descricaoOrtodonticoText2.setEditable(false);
        descricaoOrtodonticoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(descricaoOrtodonticoText2);

        descricaoOrtodonticoInputAtualizacao = new JTextField();
        descricaoOrtodonticoInputAtualizacao.setColumns(50);
        panelAtualizarLinha2.add(descricaoOrtodonticoInputAtualizacao);
        
        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoOrtodonticoText2 = new JTextArea();
        observacaoOrtodonticoText2.setText("Observação do procedimento ortodontico: ");
        observacaoOrtodonticoText2.setEditable(false);
        observacaoOrtodonticoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(observacaoOrtodonticoText2);

        observacaoOrtodonticoInputAtualizacao = new JTextField();
        observacaoOrtodonticoInputAtualizacao.setColumns(50);
        panelAtualizarLinha3.add(observacaoOrtodonticoInputAtualizacao);

        JPanel panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoOrtodonticoText2 = new JTextArea();
        tipoOrtodonticoText2.setText("Tipo do procedimento ortodontico: ");
        tipoOrtodonticoText2.setEditable(false);
        tipoOrtodonticoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha4.add(tipoOrtodonticoText2);

        tipoOrtodonticoInputAtualizacao = new JTextField();
        tipoOrtodonticoInputAtualizacao.setColumns(50);
        panelAtualizarLinha4.add(tipoOrtodonticoInputAtualizacao);
        
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

        JButton atualizarOrtodontico = new JButton("Atualizar Procedimento Ortodontico");
        panelAtualizar.add(atualizarOrtodontico);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarOrtodontico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarOrtodontico();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoOrtodonticos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Procedimento Ortodontico", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataOrtodonticoText3 = new JTextArea();
        dataOrtodonticoText3.setText("Data do procedimento ortodontico: ");
        dataOrtodonticoText3.setEditable(false);
        dataOrtodonticoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataOrtodonticoText3);

        dataOrtodonticoInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataOrtodonticoInputRemocao.setColumns(10);
        panelRemoverLinha1.add(dataOrtodonticoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea descricaoOrtodonticoText3 = new JTextArea();
        descricaoOrtodonticoText3.setText("Descrição do procedimento ortodontico: ");
        descricaoOrtodonticoText3.setEditable(false);
        descricaoOrtodonticoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(descricaoOrtodonticoText3);

        descricaoOrtodonticoInputRemocao = new JTextField();
        descricaoOrtodonticoInputRemocao.setColumns(50);
        panelRemoverLinha2.add(descricaoOrtodonticoInputRemocao);
        
        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea observacaoOrtodonticoText3 = new JTextArea();
        observacaoOrtodonticoText3.setText("Observação do procedimento ortodontico: ");
        observacaoOrtodonticoText3.setEditable(false);
        observacaoOrtodonticoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(observacaoOrtodonticoText3);

        observacaoOrtodonticoInputRemocao = new JTextField();
        observacaoOrtodonticoInputRemocao.setColumns(50);
        panelRemoverLinha3.add(observacaoOrtodonticoInputRemocao);

        JPanel panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea tipoOrtodonticoText3 = new JTextArea();
        tipoOrtodonticoText3.setText("Tipo do procedimento ortodontico: ");
        tipoOrtodonticoText3.setEditable(false);
        tipoOrtodonticoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha4.add(tipoOrtodonticoText3);

        tipoOrtodonticoInputRemocao = new JTextField();
        tipoOrtodonticoInputRemocao.setColumns(50);
        panelRemoverLinha4.add(tipoOrtodonticoInputRemocao);
        
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

        JButton removerOrtodontico = new JButton("Remover Procedimento Ortodontico");
        panelRemover.add(removerOrtodontico);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerOrtodontico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverOrtodontico();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoOrtodonticos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Procedimento Ortodontico", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoOrtodontico = new JButton("Relatório Padrão dos Procedimentos Ortodonticos");
        panelRelatorio.add(relatorioPadraoOrtodontico);
        
        relatorioPadraoOrtodontico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoOrtodonticos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoOrtodonticos.class.getName()).log(Level.SEVERE, null, ex);
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
        
        dataOrtodonticoInputAtualizacao.setEnabled(false);
        descricaoOrtodonticoInputAtualizacao.setEnabled(false);
        observacaoOrtodonticoInputAtualizacao.setEnabled(false);
        tipoOrtodonticoInputAtualizacao.setEnabled(false);
        
        dataOrtodonticoInputRemocao.setEnabled(false);
        descricaoOrtodonticoInputRemocao.setEnabled(false);
        observacaoOrtodonticoInputRemocao.setEnabled(false);
        tipoOrtodonticoInputRemocao.setEnabled(false);
    }   
}
