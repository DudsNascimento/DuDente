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
public class GestaoDentistas extends JPanel{
    
    private ArrayList <DentistaTable> dentistas;
    private DentistaJDBC dentistaJDBC;
    
    private MaskFormatter salarioMaskFormat;
    
    private JTextField nomeDentistaInputCadastro;
    private JFormattedTextField salarioDentistaInputCadastro;
    private JTextField especializacaoDentistaInputCadastro;        
    
    private JTextField nomeDentistaInputAtualizacao;
    private JFormattedTextField salarioDentistaInputAtualizacao;
    private JTextField especializacaoDentistaInputAtualizacao;
    
    private JTextField nomeDentistaInputRemocao;
    private JFormattedTextField salarioDentistaInputRemocao;
    private JTextField especializacaoDentistaInputRemocao;
    
    JPanel panelAtualizarLinha4;
    JPanel panelRemoverLinha4;
    
    JTable tableDentistasAtualizacao;
    JTable tableDentistasRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        AtualizarListaDentistas();
    }
    
    public void Desconectar() throws SQLException{
        
        dentistaJDBC.EncerrarConexao();
    }

    public void AtualizarListaDentistas() throws SQLException{
        
        String nomeColunasDentistas[] = {"Nome", "Salário", "Especialização"};
    
        dentistas = dentistaJDBC.ListarDentistas();
        if(dentistas.size() > 0)
        {
            String[][] listaDentistas = new String[dentistas.size()][3];
            for(int count = 0; count < dentistas.size(); count++)
            {
                listaDentistas[count][0] = dentistas.get(count).GetNome();
                listaDentistas[count][1] = String.valueOf(dentistas.get(count).GetSalario());
                listaDentistas[count][2] = dentistas.get(count).GetEspecializacao();
            }
            
            tableDentistasAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane listDentistasAtualizarScroll = new JScrollPane(tableDentistasAtualizacao);
            listDentistasAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha4.removeAll();
            panelAtualizarLinha4.add(listDentistasAtualizarScroll);
            
            panelAtualizarLinha4.setVisible(true);
            panelAtualizarLinha4.updateUI();
            
            tableDentistasAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableDentistasAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableDentistasAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableDentistasRemocao = new JTable(listaDentistas, nomeColunasDentistas);

            JScrollPane listDentistasRemoverScroll = new JScrollPane(tableDentistasRemocao);
            listDentistasRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha4.removeAll();
            panelRemoverLinha4.add(listDentistasRemoverScroll);
            
            panelRemoverLinha4.setVisible(true);
            panelRemoverLinha4.updateUI();
        }
        else
        {
            panelAtualizarLinha4.setVisible(false);
            panelRemoverLinha4.setVisible(false);
        }
    }

    public void GerarRelatorioPadrao() throws IOException{
        
        FileWriter fileRelatorioDentistas = new FileWriter("relatorio_dentistas");
        BufferedWriter bufferFileRelatorioDentistas = new BufferedWriter(fileRelatorioDentistas);
        
        bufferFileRelatorioDentistas.write("***********************\n");
        bufferFileRelatorioDentistas.write("Relatorio de dentistas.\n");
        bufferFileRelatorioDentistas.write("***********************\n\n");
        
        for(int count = 0; count < dentistas.size(); count++)
        {
            bufferFileRelatorioDentistas.write("Nome: "+dentistas.get(count).GetNome()+"\n");
            bufferFileRelatorioDentistas.write("Salario: "+dentistas.get(count).GetSalario()+"\n");
            bufferFileRelatorioDentistas.write("Especialização: "+dentistas.get(count).GetEspecializacao()+"\n\n");
        }
        
        bufferFileRelatorioDentistas.close();
        fileRelatorioDentistas.close();
    }
    
    public void AdicionarDentista() throws SQLException{

        if(nomeDentistaInputCadastro.getText().length() > 0 && especializacaoDentistaInputCadastro.getText().length() > 0 &&
           salarioDentistaInputCadastro.isEditValid())
        {
            DentistaTable dentista = new DentistaTable();
            dentista.SetNome(nomeDentistaInputCadastro.getText());
            dentista.SetSalario(Float.valueOf(salarioDentistaInputCadastro.getText().substring(3)));
            dentista.SetEspecializacao(especializacaoDentistaInputCadastro.getText());
            
            dentistaJDBC.AdicionarDentista(dentista);
            
            nomeDentistaInputCadastro.setText("");
            salarioDentistaInputCadastro.setText("R$     .  ");
            salarioDentistaInputCadastro.validate();
            especializacaoDentistaInputCadastro.setText("");
        }
        
        AtualizarListaDentistas();
    }
    
    public void AtualizarDentista() throws SQLException{

        if(nomeDentistaInputAtualizacao.getText().length() > 0 && especializacaoDentistaInputAtualizacao.getText().length() > 0 &&
           salarioDentistaInputAtualizacao.isEditValid() &&
           tableDentistasAtualizacao.getSelectedRow() >= 0 && tableDentistasAtualizacao.getSelectedRow() < dentistas.size())
        {
            DentistaTable dentista = new DentistaTable();
            dentista.SetNome(nomeDentistaInputAtualizacao.getText());
            dentista.SetSalario(Float.valueOf(salarioDentistaInputAtualizacao.getText().substring(3)));
            dentista.SetEspecializacao(especializacaoDentistaInputAtualizacao.getText());
            dentista.SetIdDentista(dentistas.get(tableDentistasAtualizacao.getSelectedRow()).GetIdDentista());

            dentistaJDBC.AtualizarDentista(dentista);
            
            nomeDentistaInputAtualizacao.setText("");
            salarioDentistaInputAtualizacao.setText("R$     .  ");
            salarioDentistaInputAtualizacao.validate();
            especializacaoDentistaInputAtualizacao.setText("");
        }
        
        AtualizarListaDentistas();
    }
    
    public void RemoverDentista() throws SQLException{
        
        if(tableDentistasRemocao.getSelectedRow() >= 0 && tableDentistasRemocao.getSelectedRow() < dentistas.size())
        {
            dentistaJDBC.RemoverDentista(dentistas.get(tableDentistasRemocao.getSelectedRow()));
        }
        
        AtualizarListaDentistas();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableDentistasAtualizacao.getSelectedRow() >= 0 && tableDentistasAtualizacao.getSelectedRow() < dentistas.size())
        {
            nomeDentistaInputAtualizacao.setEnabled(true);
            salarioDentistaInputAtualizacao.setEnabled(true);
            especializacaoDentistaInputAtualizacao.setEnabled(true);
                    
            nomeDentistaInputAtualizacao.setText(dentistas.get(tableDentistasAtualizacao.getSelectedRow()).GetNome());
            salarioDentistaInputAtualizacao.setText("R$ "+String.valueOf(dentistas.get(tableDentistasAtualizacao.getSelectedRow()).GetSalario())+"0");
            salarioDentistaInputAtualizacao.validate();
            especializacaoDentistaInputAtualizacao.setText(dentistas.get(tableDentistasAtualizacao.getSelectedRow()).GetEspecializacao());
        }
        else
        {
            nomeDentistaInputAtualizacao.setEnabled(false);
            salarioDentistaInputAtualizacao.setEnabled(false);
            especializacaoDentistaInputAtualizacao.setEnabled(false);
                    
            nomeDentistaInputAtualizacao.setText("");
            salarioDentistaInputAtualizacao.setText("R$     .  ");
            salarioDentistaInputAtualizacao.validate();
            especializacaoDentistaInputAtualizacao.setText("");            
        }
    }
        
    public GestaoDentistas() throws ClassNotFoundException, SQLException{
        
        dentistaJDBC = new DentistaJDBC();
                
        try{
        salarioMaskFormat = new MaskFormatter("R$ ####.##");
        }
        catch(ParseException excp){}
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();
                
        //*******************
        //Painel de cadastro.
        //*******************

        JPanel panelCadastrar = new JPanel();
        panelCadastrar.setLayout(new BoxLayout(panelCadastrar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Cadastrar Dentista", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeDentistaText1 = new JTextArea();
        nomeDentistaText1.setText("Nome do dentista: ");
        nomeDentistaText1.setEditable(false);
        nomeDentistaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomeDentistaText1);

        nomeDentistaInputCadastro = new JTextField();
        nomeDentistaInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomeDentistaInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioDentistaText1 = new JTextArea();
        salarioDentistaText1.setText("Salario do dentista: ");
        salarioDentistaText1.setEditable(false);
        salarioDentistaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(salarioDentistaText1);

        salarioDentistaInputCadastro = new JFormattedTextField(salarioMaskFormat);
        salarioDentistaInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(salarioDentistaInputCadastro);

        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea especializacaoDentistaText1 = new JTextArea();
        especializacaoDentistaText1.setText("Especializacao do dentista: ");
        especializacaoDentistaText1.setEditable(false);
        especializacaoDentistaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(especializacaoDentistaText1);

        especializacaoDentistaInputCadastro = new JTextField();
        especializacaoDentistaInputCadastro.setColumns(50);
        panelCadastrarLinha3.add(especializacaoDentistaInputCadastro);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(panelCadastrarLinha3);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarDentista = new JButton("Cadastrar Dentista");
        panelCadastrar.add(cadastrarDentista);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarDentista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarDentista();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoDentistas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Dentista", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeDentistaText2 = new JTextArea();
        nomeDentistaText2.setText("Nome do dentista: ");
        nomeDentistaText2.setEditable(false);
        nomeDentistaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomeDentistaText2);

        nomeDentistaInputAtualizacao = new JTextField();
        nomeDentistaInputAtualizacao.setColumns(20);
        nomeDentistaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomeDentistaInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioDentistaText2 = new JTextArea();
        salarioDentistaText2.setText("Salario do dentista: ");
        salarioDentistaText2.setEditable(false);
        salarioDentistaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(salarioDentistaText2);

        salarioDentistaInputAtualizacao = new JFormattedTextField(salarioMaskFormat);
        salarioDentistaInputAtualizacao.setColumns(10);
        salarioDentistaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(salarioDentistaInputAtualizacao);

        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea especializacaoDentistaText2 = new JTextArea();
        especializacaoDentistaText2.setText("Especializacao do dentista: ");
        especializacaoDentistaText2.setEditable(false);
        especializacaoDentistaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(especializacaoDentistaText2);

        especializacaoDentistaInputAtualizacao = new JTextField();
        especializacaoDentistaInputAtualizacao.setColumns(50);
        especializacaoDentistaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha3.add(especializacaoDentistaInputAtualizacao);
        
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

        JButton atualizarDentista = new JButton("Atualizar Dentista");
        panelAtualizar.add(atualizarDentista);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarDentista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarDentista();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoDentistas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Dentista", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeDentistaText3 = new JTextArea();
        nomeDentistaText3.setText("Nome do dentista: ");
        nomeDentistaText3.setEditable(false);
        nomeDentistaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomeDentistaText3);

        nomeDentistaInputRemocao = new JTextField();
        nomeDentistaInputRemocao.setColumns(20);
        nomeDentistaInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomeDentistaInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioDentistaText3 = new JTextArea();
        salarioDentistaText3.setText("Salario do dentista: ");
        salarioDentistaText3.setEditable(false);
        salarioDentistaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(salarioDentistaText3);

        salarioDentistaInputRemocao = new JFormattedTextField(salarioMaskFormat);
        salarioDentistaInputRemocao.setColumns(10);
        salarioDentistaInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(salarioDentistaInputRemocao);

        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea especializacaoDentistaText3 = new JTextArea();
        especializacaoDentistaText3.setText("Especializacao do dentista: ");
        especializacaoDentistaText3.setEditable(false);
        especializacaoDentistaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(especializacaoDentistaText3);

        especializacaoDentistaInputRemocao = new JTextField();
        especializacaoDentistaInputRemocao.setColumns(50);
        especializacaoDentistaInputRemocao.setEnabled(false);
        panelRemoverLinha3.add(especializacaoDentistaInputRemocao);
        
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

        JButton removerDentista = new JButton("Remover Dentista");
        panelRemover.add(removerDentista);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerDentista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverDentista();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoDentistas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Dentista", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoDentista = new JButton("Relatório Padrão dos Dentistas");
        panelRelatorio.add(relatorioPadraoDentista);
        
        relatorioPadraoDentista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GerarRelatorioPadrao();
                } catch (IOException ex) {
                    Logger.getLogger(GestaoDentistas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
