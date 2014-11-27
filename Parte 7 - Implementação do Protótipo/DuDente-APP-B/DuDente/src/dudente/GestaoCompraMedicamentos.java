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
public class GestaoCompraMedicamentos extends JPanel{
    
    private ArrayList <CompraMedicamentoTable> compraMedicamentos;
    private CompraMedicamentoJDBC compraMedicamentoJDBC;
    
    private ArrayList <MedicamentoTable> medicamentos;
    private MedicamentoJDBC medicamentoJDBC;
    
    private MaskFormatter dataMaskFormat;
    private MaskFormatter valorMaskFormat;
    
    private JFormattedTextField dataCompraMedicamentoInputCadastro;
    private JFormattedTextField valorCompraMedicamentoInputCadastro;
    JPanel panelMedicamentoCompraMedicamentoInputCadastro;
    JTable tableMedicamentoCompraMedicamentoInputCadastro;
    
    private JFormattedTextField dataCompraMedicamentoInputAtualizacao;
    private JFormattedTextField valorCompraMedicamentoInputAtualizacao;
    JPanel panelMedicamentoCompraMedicamentoInputAtualizacao;
    JTable tableMedicamentoCompraMedicamentoInputAtualizacao;
    
    private JFormattedTextField dataCompraMedicamentoInputRemocao;
    private JFormattedTextField valorCompraMedicamentoInputRemocao;
    JPanel panelMedicamentoCompraMedicamentoInputRemocao;
    JTable tableMedicamentoCompraMedicamentoInputRemocao;

    JPanel panelCadastrarLinha3;
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JPanel panelAtualizarLinha4;
    JPanel panelRemoverLinha4;
    
    JTable tableCompraMedicamentosAtualizacao;
    JTable tableCompraMedicamentosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        compraMedicamentoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        medicamentoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaCompraMedicamentos();
        AtualizarListaMedicamentos();
    }
    
    public void Desconectar() throws SQLException{
        
        compraMedicamentoJDBC.EncerrarConexao();
        medicamentoJDBC.EncerrarConexao();
    }

    public void AtualizarListaCompraMedicamentos() throws SQLException{
        
        String nomeColunasCompraMedicamentos[] = {"Data", "Valor", "Medicamento"};
    
        compraMedicamentos = compraMedicamentoJDBC.ListarCompraMedicamentos();
        if(compraMedicamentos.size() > 0)
        {
            String[][] listaCompraMedicamentos = new String[compraMedicamentos.size()][3];
            for(int count = 0; count < compraMedicamentos.size(); count++)
            {
                MedicamentoTable medicamentoTable = new MedicamentoTable();
                medicamentoTable.SetIdMedicamento(compraMedicamentos.get(count).GetIdMedicamento());
                medicamentoJDBC.ProcurarMedicamento(medicamentoTable);
                
                listaCompraMedicamentos[count][0] = compraMedicamentos.get(count).GetData().toString();
                listaCompraMedicamentos[count][1] = Float.toString(compraMedicamentos.get(count).GetValor());
                listaCompraMedicamentos[count][2] = medicamentoTable.GetNome();
            }
            
            tableCompraMedicamentosAtualizacao = new JTable(listaCompraMedicamentos, nomeColunasCompraMedicamentos);

            JScrollPane listCompraMedicamentosAtualizarScroll = new JScrollPane(tableCompraMedicamentosAtualizacao);
            listCompraMedicamentosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha4.removeAll();
            panelAtualizarLinha4.add(listCompraMedicamentosAtualizarScroll);
            
            panelAtualizarLinha4.setVisible(true);
            panelAtualizarLinha4.updateUI();
            
            tableCompraMedicamentosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableCompraMedicamentosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableCompraMedicamentosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableCompraMedicamentosRemocao = new JTable(listaCompraMedicamentos, nomeColunasCompraMedicamentos);

            JScrollPane listCompraMedicamentosRemoverScroll = new JScrollPane(tableCompraMedicamentosRemocao);
            listCompraMedicamentosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha4.removeAll();
            panelRemoverLinha4.add(listCompraMedicamentosRemoverScroll);
            
            panelRemoverLinha4.setVisible(true);
            panelRemoverLinha4.updateUI();
        }
        else
        {
            panelAtualizarLinha4.setVisible(false);
            panelRemoverLinha4.setVisible(false);
        }
    }
    
    public void AtualizarListaMedicamentos() throws SQLException{
        
        String nomeColunasMedicamentos[] = {"Nome", "Validade"};
    
        medicamentos = medicamentoJDBC.ListarMedicamentos();
        if(medicamentos.size() > 0)
        {
            String[][] listaMedicamentos = new String[medicamentos.size()][2];
            for(int count = 0; count < medicamentos.size(); count++)
            {
                MedicamentoTable medicamentoTable = new MedicamentoTable();
                medicamentoTable.SetIdMedicamento(medicamentos.get(count).GetIdMedicamento());
                medicamentoJDBC.ProcurarMedicamento(medicamentoTable);
                
                listaMedicamentos[count][0] = medicamentos.get(count).GetNome();
                listaMedicamentos[count][1] = medicamentos.get(count).GetValidade().toString();
            }

            tableMedicamentoCompraMedicamentoInputCadastro = new JTable(listaMedicamentos, nomeColunasMedicamentos);

            JScrollPane paneMedicamentoCompraMedicamentoInputCadastro = new JScrollPane(tableMedicamentoCompraMedicamentoInputCadastro);
            paneMedicamentoCompraMedicamentoInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha3.removeAll();
            panelCadastrarLinha3.add(paneMedicamentoCompraMedicamentoInputCadastro);
            
            panelCadastrarLinha3.setVisible(true);
            panelCadastrarLinha3.updateUI();

            tableMedicamentoCompraMedicamentoInputAtualizacao = new JTable(listaMedicamentos, nomeColunasMedicamentos);

            JScrollPane paneMedicamentoCompraMedicamentoInputAtualizacao = new JScrollPane(tableMedicamentoCompraMedicamentoInputAtualizacao);
            paneMedicamentoCompraMedicamentoInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(paneMedicamentoCompraMedicamentoInputAtualizacao);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tableMedicamentoCompraMedicamentoInputRemocao = new JTable(listaMedicamentos, nomeColunasMedicamentos);

            JScrollPane paneMedicamentoCompraMedicamentoInputRemocao = new JScrollPane(tableMedicamentoCompraMedicamentoInputRemocao);
            paneMedicamentoCompraMedicamentoInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(paneMedicamentoCompraMedicamentoInputRemocao);
            
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

        FileWriter fileRelatorioCompraMedicamentos = new FileWriter("relatorio_compra_medicamentos");
        BufferedWriter bufferFileRelatorioCompraMedicamentos = new BufferedWriter(fileRelatorioCompraMedicamentos);

        bufferFileRelatorioCompraMedicamentos.write("************************************\n");
        bufferFileRelatorioCompraMedicamentos.write("Relatorio de compra de medicamentos.\n");
        bufferFileRelatorioCompraMedicamentos.write("************************************\n\n");

        for(int count = 0; count < compraMedicamentos.size(); count++)
        {
            MedicamentoTable medicamentoTable = new MedicamentoTable();
            medicamentoTable.SetIdMedicamento(compraMedicamentos.get(count).GetIdMedicamento());
            medicamentoJDBC.ProcurarMedicamento(medicamentoTable);
 
            bufferFileRelatorioCompraMedicamentos.write("Data: "+compraMedicamentos.get(count).GetData().toString()+"\n");
            bufferFileRelatorioCompraMedicamentos.write("Valor: "+compraMedicamentos.get(count).GetValor()+"\n");
            bufferFileRelatorioCompraMedicamentos.write("Medicamento: "+medicamentoTable.GetNome()+"\n\n");
        }

        bufferFileRelatorioCompraMedicamentos.close();
        fileRelatorioCompraMedicamentos.close();
    }
    
    public void AdicionarCompraMedicamento() throws SQLException{

        if(dataCompraMedicamentoInputCadastro.isEditValid() &&
           valorCompraMedicamentoInputCadastro.isEditValid() &&
           tableMedicamentoCompraMedicamentoInputCadastro.getSelectedRow() >= 0 && tableMedicamentoCompraMedicamentoInputCadastro.getSelectedRow() < medicamentos.size())
        {
            CompraMedicamentoTable compraMedicamento = new CompraMedicamentoTable();
            compraMedicamento.SetData(Date.valueOf(dataCompraMedicamentoInputCadastro.getText()));
            compraMedicamento.SetValor(Float.valueOf(valorCompraMedicamentoInputCadastro.getText().substring(2)));
            compraMedicamento.SetIdMedicamento(medicamentos.get(tableMedicamentoCompraMedicamentoInputCadastro.getSelectedRow()).GetIdMedicamento());
            
            compraMedicamentoJDBC.AdicionarCompraMedicamento(compraMedicamento);

            dataCompraMedicamentoInputCadastro.setText("    -  -  ");
            valorCompraMedicamentoInputCadastro.setText("R$     .  ");
        }

        AtualizarListaCompraMedicamentos();
        AtualizarListaMedicamentos();
    }
    
    public void AtualizarCompraMedicamento() throws SQLException{

        if(dataCompraMedicamentoInputAtualizacao.isEditValid() &&
           valorCompraMedicamentoInputAtualizacao.isEditValid() &&
           tableMedicamentoCompraMedicamentoInputAtualizacao.getSelectedRow() >= 0 && tableMedicamentoCompraMedicamentoInputAtualizacao.getSelectedRow() < medicamentos.size() &&
           tableCompraMedicamentosAtualizacao.getSelectedRow() >= 0 && tableCompraMedicamentosAtualizacao.getSelectedRow() < compraMedicamentos.size())
        {
            CompraMedicamentoTable compraMedicamento = new CompraMedicamentoTable();
            compraMedicamento.SetIdCompra(compraMedicamentos.get(tableCompraMedicamentosAtualizacao.getSelectedRow()).GetIdCompra());
            compraMedicamento.SetIdMedicamento(medicamentos.get(tableMedicamentoCompraMedicamentoInputAtualizacao.getSelectedRow()).GetIdMedicamento());
            compraMedicamento.SetData(Date.valueOf(dataCompraMedicamentoInputAtualizacao.getText()));
            compraMedicamento.SetValor(Float.valueOf(valorCompraMedicamentoInputAtualizacao.getText().substring(2)));
            
            compraMedicamentoJDBC.AtualizarCompraMedicamento(compraMedicamento);
            
            dataCompraMedicamentoInputAtualizacao.setText("    -  -  ");
            valorCompraMedicamentoInputAtualizacao.setText("R$     .  ");
        }
        
        AtualizarListaCompraMedicamentos();
        AtualizarListaMedicamentos();
    }
    
    public void RemoverCompraMedicamento() throws SQLException{
        
        if(tableCompraMedicamentosRemocao.getSelectedRow() >= 0 && tableCompraMedicamentosRemocao.getSelectedRow() < compraMedicamentos.size())
        {
            compraMedicamentoJDBC.RemoverCompraMedicamento(compraMedicamentos.get(tableCompraMedicamentosRemocao.getSelectedRow()));
        }
        
        AtualizarListaCompraMedicamentos();
        AtualizarListaMedicamentos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableCompraMedicamentosAtualizacao.getSelectedRow() >= 0 && tableCompraMedicamentosAtualizacao.getSelectedRow() < compraMedicamentos.size())
        {
            dataCompraMedicamentoInputAtualizacao.setEnabled(true);
            valorCompraMedicamentoInputAtualizacao.setEnabled(true);
                    
            dataCompraMedicamentoInputAtualizacao.setText(compraMedicamentos.get(tableCompraMedicamentosAtualizacao.getSelectedRow()).GetData().toString());
            valorCompraMedicamentoInputAtualizacao.setText(Float.toString(compraMedicamentos.get(tableCompraMedicamentosAtualizacao.getSelectedRow()).GetValor()));
            dataCompraMedicamentoInputAtualizacao.validate();
            valorCompraMedicamentoInputAtualizacao.validate();
        }
        else
        {
            dataCompraMedicamentoInputAtualizacao.setEnabled(false);
            valorCompraMedicamentoInputAtualizacao.setEnabled(false);
                    
            dataCompraMedicamentoInputAtualizacao.setText("    -  -  ");
            valorCompraMedicamentoInputAtualizacao.setText("R$     .  ");
            dataCompraMedicamentoInputAtualizacao.validate();
            valorCompraMedicamentoInputAtualizacao.validate();
        }
    }
        
    public GestaoCompraMedicamentos() throws ClassNotFoundException, SQLException{
        
        compraMedicamentoJDBC = new CompraMedicamentoJDBC();
        medicamentoJDBC = new MedicamentoJDBC();

        try{
        dataMaskFormat = new MaskFormatter("####-##-##");
        valorMaskFormat = new MaskFormatter("R$ ####.##");
        }
        catch(ParseException excp){}
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();
                
        //*******************
        //Painel de cadastro.
        //*******************

        JPanel panelCadastrar = new JPanel();
        panelCadastrar.setLayout(new BoxLayout(panelCadastrar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Cadastrar Compra de Medicamento", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraMedicamentoText1 = new JTextArea();
        dataCompraMedicamentoText1.setText("Data da compra: ");
        dataCompraMedicamentoText1.setEditable(false);
        dataCompraMedicamentoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataCompraMedicamentoText1);

        dataCompraMedicamentoInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataCompraMedicamentoInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(dataCompraMedicamentoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraMedicamentoText1 = new JTextArea();
        valorCompraMedicamentoText1.setText("Valor da compra: ");
        valorCompraMedicamentoText1.setEditable(false);
        valorCompraMedicamentoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(valorCompraMedicamentoText1);

        valorCompraMedicamentoInputCadastro = new JFormattedTextField(valorMaskFormat);
        valorCompraMedicamentoInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(valorCompraMedicamentoInputCadastro);

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

        JButton cadastrarCompraMedicamento = new JButton("Cadastrar Compra de Medicamento");
        panelCadastrar.add(cadastrarCompraMedicamento);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarCompraMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarCompraMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Compra de Medicamento", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraMedicamentoText2 = new JTextArea();
        dataCompraMedicamentoText2.setText("Data da compra: ");
        dataCompraMedicamentoText2.setEditable(false);
        dataCompraMedicamentoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataCompraMedicamentoText2);

        dataCompraMedicamentoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataCompraMedicamentoInputAtualizacao.setColumns(20);
        dataCompraMedicamentoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(dataCompraMedicamentoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraMedicamentoText2 = new JTextArea();
        valorCompraMedicamentoText2.setText("Valor da compra: ");
        valorCompraMedicamentoText2.setEditable(false);
        valorCompraMedicamentoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(valorCompraMedicamentoText2);

        valorCompraMedicamentoInputAtualizacao = new JFormattedTextField(valorMaskFormat);
        valorCompraMedicamentoInputAtualizacao.setColumns(10);
        valorCompraMedicamentoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(valorCompraMedicamentoInputAtualizacao);

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

        JButton atualizarCompraMedicamento = new JButton("Atualizar Compra de Medicamento");
        panelAtualizar.add(atualizarCompraMedicamento);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarCompraMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarCompraMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Compra de Medicamento", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraMedicamentoText3 = new JTextArea();
        dataCompraMedicamentoText3.setText("Data da compra: ");
        dataCompraMedicamentoText3.setEditable(false);
        dataCompraMedicamentoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataCompraMedicamentoText3);

        dataCompraMedicamentoInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataCompraMedicamentoInputRemocao.setColumns(20);
        dataCompraMedicamentoInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(dataCompraMedicamentoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraMedicamentoText3 = new JTextArea();
        valorCompraMedicamentoText3.setText("Valor da compra: ");
        valorCompraMedicamentoText3.setEditable(false);
        valorCompraMedicamentoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(valorCompraMedicamentoText3);

        valorCompraMedicamentoInputRemocao = new JFormattedTextField(valorMaskFormat);
        valorCompraMedicamentoInputRemocao.setColumns(10);
        valorCompraMedicamentoInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(valorCompraMedicamentoInputRemocao);

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

        JButton removerCompraMedicamento = new JButton("Remover Compra de Medicamento");
        panelRemover.add(removerCompraMedicamento);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerCompraMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverCompraMedicamento();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Compra de Medicamento", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoCompraMedicamento = new JButton("Relatório Padrão dos Compra de Medicamentos");
        panelRelatorio.add(relatorioPadraoCompraMedicamento);
        
        relatorioPadraoCompraMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoCompraMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoCompraMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
