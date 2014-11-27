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
public class GestaoMedicamentos extends JPanel{
    
    private ArrayList <MedicamentoTable> medicamentos;
    private MedicamentoJDBC medicamentoJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JTextField nomeMedicamentoInputCadastro;
    private JFormattedTextField validadeMedicamentoInputCadastro;
    
    private JTextField nomeMedicamentoInputAtualizacao;
    private JFormattedTextField validadeMedicamentoInputAtualizacao;
    
    private JTextField nomeMedicamentoInputRemocao;
    private JFormattedTextField validadeMedicamentoInputRemocao;
    private JTextField especializacaoMedicamentoInputRemocao;
    
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JTable tableMedicamentosAtualizacao;
    JTable tableMedicamentosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        medicamentoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        AtualizarListaMedicamentos();
    }
    
    public void Desconectar() throws SQLException{
        
        medicamentoJDBC.EncerrarConexao();
    }

    public void AtualizarListaMedicamentos() throws SQLException{
        
        String nomeColunasMedicamentos[] = {"Nome", "Validade"};
    
        medicamentos = medicamentoJDBC.ListarMedicamentos();
        if(medicamentos.size() > 0)
        {
            String[][] listaMedicamentos = new String[medicamentos.size()][3];
            for(int count = 0; count < medicamentos.size(); count++)
            {
                listaMedicamentos[count][0] = medicamentos.get(count).GetNome();
                listaMedicamentos[count][1] = medicamentos.get(count).GetValidade().toString();
            }
            
            tableMedicamentosAtualizacao = new JTable(listaMedicamentos, nomeColunasMedicamentos);

            JScrollPane listMedicamentosAtualizarScroll = new JScrollPane(tableMedicamentosAtualizacao);
            listMedicamentosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(listMedicamentosAtualizarScroll);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tableMedicamentosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableMedicamentosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableMedicamentosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableMedicamentosRemocao = new JTable(listaMedicamentos, nomeColunasMedicamentos);

            JScrollPane listMedicamentosRemoverScroll = new JScrollPane(tableMedicamentosRemocao);
            listMedicamentosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(listMedicamentosRemoverScroll);
            
            panelRemoverLinha3.setVisible(true);
            panelRemoverLinha3.updateUI();
        }
        else
        {
            panelAtualizarLinha3.setVisible(false);
            panelRemoverLinha3.setVisible(false);
        }
    }

    public void GerarRelatorioPadrao() throws IOException{
        
        FileWriter fileRelatorioMedicamentos = new FileWriter("relatorio_medicamentos");
        BufferedWriter bufferFileRelatorioMedicamentos = new BufferedWriter(fileRelatorioMedicamentos);
        
        bufferFileRelatorioMedicamentos.write("***********************\n");
        bufferFileRelatorioMedicamentos.write("Relatorio de medicamentos.\n");
        bufferFileRelatorioMedicamentos.write("***********************\n\n");
        
        for(int count = 0; count < medicamentos.size(); count++)
        {
            bufferFileRelatorioMedicamentos.write("Nome: "+medicamentos.get(count).GetNome()+"\n");
            bufferFileRelatorioMedicamentos.write("Validade: "+medicamentos.get(count).GetValidade().toString()+"\n\n");
        }
        
        bufferFileRelatorioMedicamentos.close();
        fileRelatorioMedicamentos.close();
    }
    
    public void AdicionarMedicamento() throws SQLException{

        if(nomeMedicamentoInputCadastro.getText().length() > 0 &&
           validadeMedicamentoInputCadastro.isEditValid())
        {
            MedicamentoTable medicamento = new MedicamentoTable();
            medicamento.SetNome(nomeMedicamentoInputCadastro.getText());
            medicamento.SetValidade(Date.valueOf(validadeMedicamentoInputCadastro.getText()));
            
            medicamentoJDBC.AdicionarMedicamento(medicamento);
            
            nomeMedicamentoInputCadastro.setText("");
            validadeMedicamentoInputCadastro.setText("    -  -  ");
            validadeMedicamentoInputCadastro.validate();
        }
        
        AtualizarListaMedicamentos();
    }
    
    public void AtualizarMedicamento() throws SQLException{

        if(nomeMedicamentoInputAtualizacao.getText().length() > 0 &&
           validadeMedicamentoInputAtualizacao.isEditValid() &&
           tableMedicamentosAtualizacao.getSelectedRow() >= 0 && tableMedicamentosAtualizacao.getSelectedRow() < medicamentos.size())
        {
            MedicamentoTable medicamento = new MedicamentoTable();
            medicamento.SetNome(nomeMedicamentoInputAtualizacao.getText());
            medicamento.SetValidade(Date.valueOf(validadeMedicamentoInputAtualizacao.getText()));
            medicamento.SetIdMedicamento(medicamentos.get(tableMedicamentosAtualizacao.getSelectedRow()).GetIdMedicamento());

            medicamentoJDBC.AtualizarMedicamento(medicamento);
            
            nomeMedicamentoInputAtualizacao.setText("");
            validadeMedicamentoInputAtualizacao.setText("    -  -  ");
            validadeMedicamentoInputAtualizacao.validate();
        }
        
        AtualizarListaMedicamentos();
    }
    
    public void RemoverMedicamento() throws SQLException{
        
        if(tableMedicamentosRemocao.getSelectedRow() >= 0 && tableMedicamentosRemocao.getSelectedRow() < medicamentos.size())
        {
            medicamentoJDBC.RemoverMedicamento(medicamentos.get(tableMedicamentosRemocao.getSelectedRow()));
        }
        
        AtualizarListaMedicamentos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableMedicamentosAtualizacao.getSelectedRow() >= 0 && tableMedicamentosAtualizacao.getSelectedRow() < medicamentos.size())
        {
            nomeMedicamentoInputAtualizacao.setEnabled(true);
            validadeMedicamentoInputAtualizacao.setEnabled(true);
                    
            nomeMedicamentoInputAtualizacao.setText(medicamentos.get(tableMedicamentosAtualizacao.getSelectedRow()).GetNome());
            validadeMedicamentoInputAtualizacao.setText(medicamentos.get(tableMedicamentosAtualizacao.getSelectedRow()).GetValidade().toString());
            validadeMedicamentoInputAtualizacao.validate();
        }
        else
        {
            nomeMedicamentoInputAtualizacao.setEnabled(false);
            validadeMedicamentoInputAtualizacao.setEnabled(false);
                    
            nomeMedicamentoInputAtualizacao.setText("");
            validadeMedicamentoInputAtualizacao.setText("    -  -  ");
            validadeMedicamentoInputAtualizacao.validate();
        }
    }
        
    public GestaoMedicamentos() throws ClassNotFoundException, SQLException{
        
        medicamentoJDBC = new MedicamentoJDBC();
                
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
        tabbedPane.addTab("Cadastrar Medicamento", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeMedicamentoText1 = new JTextArea();
        nomeMedicamentoText1.setText("Nome do medicamento: ");
        nomeMedicamentoText1.setEditable(false);
        nomeMedicamentoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomeMedicamentoText1);

        nomeMedicamentoInputCadastro = new JTextField();
        nomeMedicamentoInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomeMedicamentoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadeMedicamentoText1 = new JTextArea();
        validadeMedicamentoText1.setText("Validade do medicamento: ");
        validadeMedicamentoText1.setEditable(false);
        validadeMedicamentoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(validadeMedicamentoText1);

        validadeMedicamentoInputCadastro = new JFormattedTextField(dataMaskFormat);
        validadeMedicamentoInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(validadeMedicamentoInputCadastro);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarMedicamento = new JButton("Cadastrar Medicamento");
        panelCadastrar.add(cadastrarMedicamento);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Medicamento", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeMedicamentoText2 = new JTextArea();
        nomeMedicamentoText2.setText("Nome do medicamento: ");
        nomeMedicamentoText2.setEditable(false);
        nomeMedicamentoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomeMedicamentoText2);

        nomeMedicamentoInputAtualizacao = new JTextField();
        nomeMedicamentoInputAtualizacao.setColumns(20);
        nomeMedicamentoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomeMedicamentoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadeMedicamentoText2 = new JTextArea();
        validadeMedicamentoText2.setText("Validade do medicamento: ");
        validadeMedicamentoText2.setEditable(false);
        validadeMedicamentoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(validadeMedicamentoText2);

        validadeMedicamentoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        validadeMedicamentoInputAtualizacao.setColumns(10);
        validadeMedicamentoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(validadeMedicamentoInputAtualizacao);
        
        panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelAtualizar.add(panelAtualizarLinha1);
        panelAtualizar.add(panelAtualizarLinha2);
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 20)));

        panelAtualizar.add(panelAtualizarLinha3);
        
        panelAtualizar.add(Box.createVerticalGlue());

        JButton atualizarMedicamento = new JButton("Atualizar Medicamento");
        panelAtualizar.add(atualizarMedicamento);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Medicamento", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeMedicamentoText3 = new JTextArea();
        nomeMedicamentoText3.setText("Nome do medicamento: ");
        nomeMedicamentoText3.setEditable(false);
        nomeMedicamentoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomeMedicamentoText3);

        nomeMedicamentoInputRemocao = new JTextField();
        nomeMedicamentoInputRemocao.setColumns(20);
        nomeMedicamentoInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomeMedicamentoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadeMedicamentoText3 = new JTextArea();
        validadeMedicamentoText3.setText("Validade do medicamento: ");
        validadeMedicamentoText3.setEditable(false);
        validadeMedicamentoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(validadeMedicamentoText3);

        validadeMedicamentoInputRemocao = new JFormattedTextField(dataMaskFormat);
        validadeMedicamentoInputRemocao.setColumns(10);
        validadeMedicamentoInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(validadeMedicamentoInputRemocao);

        panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelRemover.add(panelRemoverLinha1);
        panelRemover.add(panelRemoverLinha2);
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 20)));

        panelRemover.add(panelRemoverLinha3);
        
        panelRemover.add(Box.createVerticalGlue());

        JButton removerMedicamento = new JButton("Remover Medicamento");
        panelRemover.add(removerMedicamento);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Medicamento", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoMedicamento = new JButton("Relatório Padrão dos Medicamentos");
        panelRelatorio.add(relatorioPadraoMedicamento);
        
        relatorioPadraoMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GerarRelatorioPadrao();
                } catch (IOException ex) {
                    Logger.getLogger(GestaoMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
