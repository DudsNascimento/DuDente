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
public class GestaoCompraAparelhos extends JPanel{
    
    private ArrayList <CompraAparelhoTable> compraAparelhos;
    private CompraAparelhoJDBC compraAparelhoJDBC;
    
    private ArrayList <AparelhoTable> aparelhos;
    private AparelhoJDBC aparelhoJDBC;
    
    private MaskFormatter dataMaskFormat;
    private MaskFormatter valorMaskFormat;
    
    private JFormattedTextField dataCompraAparelhoInputCadastro;
    private JFormattedTextField valorCompraAparelhoInputCadastro;
    JPanel panelAparelhoCompraAparelhoInputCadastro;
    JTable tableAparelhoCompraAparelhoInputCadastro;
    
    private JFormattedTextField dataCompraAparelhoInputAtualizacao;
    private JFormattedTextField valorCompraAparelhoInputAtualizacao;
    JPanel panelAparelhoCompraAparelhoInputAtualizacao;
    JTable tableAparelhoCompraAparelhoInputAtualizacao;
    
    private JFormattedTextField dataCompraAparelhoInputRemocao;
    private JFormattedTextField valorCompraAparelhoInputRemocao;
    JPanel panelAparelhoCompraAparelhoInputRemocao;
    JTable tableAparelhoCompraAparelhoInputRemocao;

    JPanel panelCadastrarLinha3;
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JPanel panelAtualizarLinha4;
    JPanel panelRemoverLinha4;
    
    JTable tableCompraAparelhosAtualizacao;
    JTable tableCompraAparelhosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        compraAparelhoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        aparelhoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        AtualizarListaCompraAparelhos();
        AtualizarListaAparelhos();
    }
    
    public void Desconectar() throws SQLException{
        
        compraAparelhoJDBC.EncerrarConexao();
        aparelhoJDBC.EncerrarConexao();
    }

    public void AtualizarListaCompraAparelhos() throws SQLException{
        
        String nomeColunasCompraAparelhos[] = {"Data", "Valor", "Aparelho"};
    
        compraAparelhos = compraAparelhoJDBC.ListarCompraAparelhos();
        if(compraAparelhos.size() > 0)
        {
            String[][] listaCompraAparelhos = new String[compraAparelhos.size()][3];
            for(int count = 0; count < compraAparelhos.size(); count++)
            {
                AparelhoTable aparelhoTable = new AparelhoTable();
                aparelhoTable.SetIdAparelho(compraAparelhos.get(count).GetIdAparelho());
                aparelhoJDBC.ProcurarAparelho(aparelhoTable);
                
                listaCompraAparelhos[count][0] = compraAparelhos.get(count).GetData().toString();
                listaCompraAparelhos[count][1] = Float.toString(compraAparelhos.get(count).GetValor());
                listaCompraAparelhos[count][2] = aparelhoTable.GetNome();
            }
            
            tableCompraAparelhosAtualizacao = new JTable(listaCompraAparelhos, nomeColunasCompraAparelhos);

            JScrollPane listCompraAparelhosAtualizarScroll = new JScrollPane(tableCompraAparelhosAtualizacao);
            listCompraAparelhosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha4.removeAll();
            panelAtualizarLinha4.add(listCompraAparelhosAtualizarScroll);
            
            panelAtualizarLinha4.setVisible(true);
            panelAtualizarLinha4.updateUI();
            
            tableCompraAparelhosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableCompraAparelhosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableCompraAparelhosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableCompraAparelhosRemocao = new JTable(listaCompraAparelhos, nomeColunasCompraAparelhos);

            JScrollPane listCompraAparelhosRemoverScroll = new JScrollPane(tableCompraAparelhosRemocao);
            listCompraAparelhosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha4.removeAll();
            panelRemoverLinha4.add(listCompraAparelhosRemoverScroll);
            
            panelRemoverLinha4.setVisible(true);
            panelRemoverLinha4.updateUI();
        }
        else
        {
            panelAtualizarLinha4.setVisible(false);
            panelRemoverLinha4.setVisible(false);
        }
    }
    
    public void AtualizarListaAparelhos() throws SQLException{
        
        String nomeColunasAparelhos[] = {"Nome", "Ultima Manutencao"};
    
        aparelhos = aparelhoJDBC.ListarAparelhos();
        if(aparelhos.size() > 0)
        {
            String[][] listaAparelhos = new String[aparelhos.size()][2];
            for(int count = 0; count < aparelhos.size(); count++)
            {
                AparelhoTable aparelhoTable = new AparelhoTable();
                aparelhoTable.SetIdAparelho(aparelhos.get(count).GetIdAparelho());
                aparelhoJDBC.ProcurarAparelho(aparelhoTable);
                
                listaAparelhos[count][0] = aparelhos.get(count).GetNome();
                listaAparelhos[count][1] = aparelhos.get(count).GetUltimaManutencao().toString();
            }

            tableAparelhoCompraAparelhoInputCadastro = new JTable(listaAparelhos, nomeColunasAparelhos);

            JScrollPane paneAparelhoCompraAparelhoInputCadastro = new JScrollPane(tableAparelhoCompraAparelhoInputCadastro);
            paneAparelhoCompraAparelhoInputCadastro.setPreferredSize(new Dimension(250, 80));
            
            panelCadastrarLinha3.removeAll();
            panelCadastrarLinha3.add(paneAparelhoCompraAparelhoInputCadastro);
            
            panelCadastrarLinha3.setVisible(true);
            panelCadastrarLinha3.updateUI();

            tableAparelhoCompraAparelhoInputAtualizacao = new JTable(listaAparelhos, nomeColunasAparelhos);

            JScrollPane paneAparelhoCompraAparelhoInputAtualizacao = new JScrollPane(tableAparelhoCompraAparelhoInputAtualizacao);
            paneAparelhoCompraAparelhoInputAtualizacao.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(paneAparelhoCompraAparelhoInputAtualizacao);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tableAparelhoCompraAparelhoInputRemocao = new JTable(listaAparelhos, nomeColunasAparelhos);

            JScrollPane paneAparelhoCompraAparelhoInputRemocao = new JScrollPane(tableAparelhoCompraAparelhoInputRemocao);
            paneAparelhoCompraAparelhoInputRemocao.setPreferredSize(new Dimension(250, 80));
            
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(paneAparelhoCompraAparelhoInputRemocao);
            
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

        FileWriter fileRelatorioCompraAparelhos = new FileWriter("relatorio_compra_aparelhos");
        BufferedWriter bufferFileRelatorioCompraAparelhos = new BufferedWriter(fileRelatorioCompraAparelhos);

        bufferFileRelatorioCompraAparelhos.write("************************************\n");
        bufferFileRelatorioCompraAparelhos.write("Relatorio de compra de aparelhos.\n");
        bufferFileRelatorioCompraAparelhos.write("************************************\n\n");

        for(int count = 0; count < compraAparelhos.size(); count++)
        {
            AparelhoTable aparelhoTable = new AparelhoTable();
            aparelhoTable.SetIdAparelho(compraAparelhos.get(count).GetIdAparelho());
            aparelhoJDBC.ProcurarAparelho(aparelhoTable);
 
            bufferFileRelatorioCompraAparelhos.write("Data: "+compraAparelhos.get(count).GetData().toString()+"\n");
            bufferFileRelatorioCompraAparelhos.write("Valor: "+compraAparelhos.get(count).GetValor()+"\n");
            bufferFileRelatorioCompraAparelhos.write("Aparelho: "+aparelhoTable.GetNome()+"\n\n");
        }

        bufferFileRelatorioCompraAparelhos.close();
        fileRelatorioCompraAparelhos.close();
    }
    
    public void AdicionarCompraAparelho() throws SQLException{

        if(dataCompraAparelhoInputCadastro.isEditValid() &&
           valorCompraAparelhoInputCadastro.isEditValid() &&
           tableAparelhoCompraAparelhoInputCadastro.getSelectedRow() >= 0 && tableAparelhoCompraAparelhoInputCadastro.getSelectedRow() < aparelhos.size())
        {
            CompraAparelhoTable compraAparelho = new CompraAparelhoTable();
            compraAparelho.SetData(Date.valueOf(dataCompraAparelhoInputCadastro.getText()));
            compraAparelho.SetValor(Float.valueOf(valorCompraAparelhoInputCadastro.getText().substring(2)));
            compraAparelho.SetIdAparelho(aparelhos.get(tableAparelhoCompraAparelhoInputCadastro.getSelectedRow()).GetIdAparelho());
            
            compraAparelhoJDBC.AdicionarCompraAparelho(compraAparelho);

            dataCompraAparelhoInputCadastro.setText("    -  -  ");
            valorCompraAparelhoInputCadastro.setText("R$     .  ");
        }

        AtualizarListaCompraAparelhos();
        AtualizarListaAparelhos();
    }
    
    public void AtualizarCompraAparelho() throws SQLException{

        if(dataCompraAparelhoInputAtualizacao.isEditValid() &&
           valorCompraAparelhoInputAtualizacao.isEditValid() &&
           tableAparelhoCompraAparelhoInputAtualizacao.getSelectedRow() >= 0 && tableAparelhoCompraAparelhoInputAtualizacao.getSelectedRow() < aparelhos.size() &&
           tableCompraAparelhosAtualizacao.getSelectedRow() >= 0 && tableCompraAparelhosAtualizacao.getSelectedRow() < compraAparelhos.size())
        {
            CompraAparelhoTable compraAparelho = new CompraAparelhoTable();
            compraAparelho.SetIdCompra(compraAparelhos.get(tableCompraAparelhosAtualizacao.getSelectedRow()).GetIdCompra());
            compraAparelho.SetIdAparelho(aparelhos.get(tableAparelhoCompraAparelhoInputAtualizacao.getSelectedRow()).GetIdAparelho());
            compraAparelho.SetData(Date.valueOf(dataCompraAparelhoInputAtualizacao.getText()));
            compraAparelho.SetValor(Float.valueOf(valorCompraAparelhoInputAtualizacao.getText().substring(2)));
            
            compraAparelhoJDBC.AtualizarCompraAparelho(compraAparelho);
            
            dataCompraAparelhoInputAtualizacao.setText("    -  -  ");
            valorCompraAparelhoInputAtualizacao.setText("R$     .  ");
        }
        
        AtualizarListaCompraAparelhos();
        AtualizarListaAparelhos();
    }
    
    public void RemoverCompraAparelho() throws SQLException{
        
        if(tableCompraAparelhosRemocao.getSelectedRow() >= 0 && tableCompraAparelhosRemocao.getSelectedRow() < compraAparelhos.size())
        {
            compraAparelhoJDBC.RemoverCompraAparelho(compraAparelhos.get(tableCompraAparelhosRemocao.getSelectedRow()));
        }
        
        AtualizarListaCompraAparelhos();
        AtualizarListaAparelhos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableCompraAparelhosAtualizacao.getSelectedRow() >= 0 && tableCompraAparelhosAtualizacao.getSelectedRow() < compraAparelhos.size())
        {
            dataCompraAparelhoInputAtualizacao.setEnabled(true);
            valorCompraAparelhoInputAtualizacao.setEnabled(true);
                    
            dataCompraAparelhoInputAtualizacao.setText(compraAparelhos.get(tableCompraAparelhosAtualizacao.getSelectedRow()).GetData().toString());
            valorCompraAparelhoInputAtualizacao.setText(Float.toString(compraAparelhos.get(tableCompraAparelhosAtualizacao.getSelectedRow()).GetValor()));
            dataCompraAparelhoInputAtualizacao.validate();
            valorCompraAparelhoInputAtualizacao.validate();
        }
        else
        {
            dataCompraAparelhoInputAtualizacao.setEnabled(false);
            valorCompraAparelhoInputAtualizacao.setEnabled(false);
                    
            dataCompraAparelhoInputAtualizacao.setText("    -  -  ");
            valorCompraAparelhoInputAtualizacao.setText("R$     .  ");
            dataCompraAparelhoInputAtualizacao.validate();
            valorCompraAparelhoInputAtualizacao.validate();
        }
    }
        
    public GestaoCompraAparelhos() throws ClassNotFoundException, SQLException{
        
        compraAparelhoJDBC = new CompraAparelhoJDBC();
        aparelhoJDBC = new AparelhoJDBC();

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
        tabbedPane.addTab("Cadastrar Compra de Aparelho", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraAparelhoText1 = new JTextArea();
        dataCompraAparelhoText1.setText("Data da compra: ");
        dataCompraAparelhoText1.setEditable(false);
        dataCompraAparelhoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(dataCompraAparelhoText1);

        dataCompraAparelhoInputCadastro = new JFormattedTextField(dataMaskFormat);
        dataCompraAparelhoInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(dataCompraAparelhoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraAparelhoText1 = new JTextArea();
        valorCompraAparelhoText1.setText("Valor da compra: ");
        valorCompraAparelhoText1.setEditable(false);
        valorCompraAparelhoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(valorCompraAparelhoText1);

        valorCompraAparelhoInputCadastro = new JFormattedTextField(valorMaskFormat);
        valorCompraAparelhoInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(valorCompraAparelhoInputCadastro);

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

        JButton cadastrarCompraAparelho = new JButton("Cadastrar Compra de Aparelho");
        panelCadastrar.add(cadastrarCompraAparelho);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarCompraAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarCompraAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Compra de Aparelho", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraAparelhoText2 = new JTextArea();
        dataCompraAparelhoText2.setText("Data da compra: ");
        dataCompraAparelhoText2.setEditable(false);
        dataCompraAparelhoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(dataCompraAparelhoText2);

        dataCompraAparelhoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        dataCompraAparelhoInputAtualizacao.setColumns(20);
        dataCompraAparelhoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(dataCompraAparelhoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraAparelhoText2 = new JTextArea();
        valorCompraAparelhoText2.setText("Valor da compra: ");
        valorCompraAparelhoText2.setEditable(false);
        valorCompraAparelhoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(valorCompraAparelhoText2);

        valorCompraAparelhoInputAtualizacao = new JFormattedTextField(valorMaskFormat);
        valorCompraAparelhoInputAtualizacao.setColumns(10);
        valorCompraAparelhoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(valorCompraAparelhoInputAtualizacao);

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

        JButton atualizarCompraAparelho = new JButton("Atualizar Compra de Aparelho");
        panelAtualizar.add(atualizarCompraAparelho);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarCompraAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarCompraAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Compra de Aparelho", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea dataCompraAparelhoText3 = new JTextArea();
        dataCompraAparelhoText3.setText("Data da compra: ");
        dataCompraAparelhoText3.setEditable(false);
        dataCompraAparelhoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(dataCompraAparelhoText3);

        dataCompraAparelhoInputRemocao = new JFormattedTextField(dataMaskFormat);
        dataCompraAparelhoInputRemocao.setColumns(20);
        dataCompraAparelhoInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(dataCompraAparelhoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea valorCompraAparelhoText3 = new JTextArea();
        valorCompraAparelhoText3.setText("Valor da compra: ");
        valorCompraAparelhoText3.setEditable(false);
        valorCompraAparelhoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(valorCompraAparelhoText3);

        valorCompraAparelhoInputRemocao = new JFormattedTextField(valorMaskFormat);
        valorCompraAparelhoInputRemocao.setColumns(10);
        valorCompraAparelhoInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(valorCompraAparelhoInputRemocao);

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

        JButton removerCompraAparelho = new JButton("Remover Compra de Aparelho");
        panelRemover.add(removerCompraAparelho);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerCompraAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverCompraAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCompraAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Compra de Aparelho", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoCompraAparelho = new JButton("Relatório Padrão dos Compra de Aparelhos");
        panelRelatorio.add(relatorioPadraoCompraAparelho);
        
        relatorioPadraoCompraAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    try {
                        GerarRelatorioPadrao();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoCompraAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestaoCompraAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
