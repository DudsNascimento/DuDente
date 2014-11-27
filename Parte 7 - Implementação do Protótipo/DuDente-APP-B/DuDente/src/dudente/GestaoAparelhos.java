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
public class GestaoAparelhos extends JPanel{
    
    private ArrayList <AparelhoTable> aparelhos;
    private AparelhoJDBC aparelhoJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JTextField nomeAparelhoInputCadastro;
    private JFormattedTextField ultimaManutencaoAparelhoInputCadastro;
    
    private JTextField nomeAparelhoInputAtualizacao;
    private JFormattedTextField ultimaManutencaoAparelhoInputAtualizacao;
    
    private JTextField nomeAparelhoInputRemocao;
    private JFormattedTextField ultimaManutencaoAparelhoInputRemocao;
    private JTextField especializacaoAparelhoInputRemocao;
    
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JTable tableAparelhosAtualizacao;
    JTable tableAparelhosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        aparelhoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        AtualizarListaAparelhos();
    }
    
    public void Desconectar() throws SQLException{
        
        aparelhoJDBC.EncerrarConexao();
    }

    public void AtualizarListaAparelhos() throws SQLException{
        
        String nomeColunasAparelhos[] = {"Nome", "Ultima Manutenção"};
    
        aparelhos = aparelhoJDBC.ListarAparelhos();
        if(aparelhos.size() > 0)
        {
            String[][] listaAparelhos = new String[aparelhos.size()][3];
            for(int count = 0; count < aparelhos.size(); count++)
            {
                listaAparelhos[count][0] = aparelhos.get(count).GetNome();
                listaAparelhos[count][1] = aparelhos.get(count).GetUltimaManutencao().toString();
            }
            
            tableAparelhosAtualizacao = new JTable(listaAparelhos, nomeColunasAparelhos);

            JScrollPane listAparelhosAtualizarScroll = new JScrollPane(tableAparelhosAtualizacao);
            listAparelhosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(listAparelhosAtualizarScroll);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tableAparelhosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableAparelhosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableAparelhosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableAparelhosRemocao = new JTable(listaAparelhos, nomeColunasAparelhos);

            JScrollPane listAparelhosRemoverScroll = new JScrollPane(tableAparelhosRemocao);
            listAparelhosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(listAparelhosRemoverScroll);
            
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
        
        FileWriter fileRelatorioAparelhos = new FileWriter("relatorio_aparelhos");
        BufferedWriter bufferFileRelatorioAparelhos = new BufferedWriter(fileRelatorioAparelhos);
        
        bufferFileRelatorioAparelhos.write("***********************\n");
        bufferFileRelatorioAparelhos.write("Relatorio de aparelhos.\n");
        bufferFileRelatorioAparelhos.write("***********************\n\n");
        
        for(int count = 0; count < aparelhos.size(); count++)
        {
            bufferFileRelatorioAparelhos.write("Nome: "+aparelhos.get(count).GetNome()+"\n");
            bufferFileRelatorioAparelhos.write("Data da ultima manutencao: "+aparelhos.get(count).GetUltimaManutencao().toString()+"\n\n");
        }
        
        bufferFileRelatorioAparelhos.close();
        fileRelatorioAparelhos.close();
    }
    
    public void AdicionarAparelho() throws SQLException{

        if(nomeAparelhoInputCadastro.getText().length() > 0 &&
           ultimaManutencaoAparelhoInputCadastro.isEditValid())
        {
            AparelhoTable aparelho = new AparelhoTable();
            aparelho.SetNome(nomeAparelhoInputCadastro.getText());
            aparelho.SetUltimaManutencao(Date.valueOf(ultimaManutencaoAparelhoInputCadastro.getText()));
            
            aparelhoJDBC.AdicionarAparelho(aparelho);
            
            nomeAparelhoInputCadastro.setText("");
            ultimaManutencaoAparelhoInputCadastro.setText("    -  -  ");
            ultimaManutencaoAparelhoInputCadastro.validate();
        }
        
        AtualizarListaAparelhos();
    }
    
    public void AtualizarAparelho() throws SQLException{

        if(nomeAparelhoInputAtualizacao.getText().length() > 0 &&
           ultimaManutencaoAparelhoInputAtualizacao.isEditValid() &&
           tableAparelhosAtualizacao.getSelectedRow() >= 0 && tableAparelhosAtualizacao.getSelectedRow() < aparelhos.size())
        {
            AparelhoTable aparelho = new AparelhoTable();
            aparelho.SetNome(nomeAparelhoInputAtualizacao.getText());
            aparelho.SetUltimaManutencao(Date.valueOf(ultimaManutencaoAparelhoInputAtualizacao.getText()));
            aparelho.SetIdAparelho(aparelhos.get(tableAparelhosAtualizacao.getSelectedRow()).GetIdAparelho());

            aparelhoJDBC.AtualizarAparelho(aparelho);
            
            nomeAparelhoInputAtualizacao.setText("");
            ultimaManutencaoAparelhoInputAtualizacao.setText("    -  -  ");
            ultimaManutencaoAparelhoInputAtualizacao.validate();
        }
        
        AtualizarListaAparelhos();
    }
    
    public void RemoverAparelho() throws SQLException{
        
        if(tableAparelhosRemocao.getSelectedRow() >= 0 && tableAparelhosRemocao.getSelectedRow() < aparelhos.size())
        {
            aparelhoJDBC.RemoverAparelho(aparelhos.get(tableAparelhosRemocao.getSelectedRow()));
        }
        
        AtualizarListaAparelhos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableAparelhosAtualizacao.getSelectedRow() >= 0 && tableAparelhosAtualizacao.getSelectedRow() < aparelhos.size())
        {
            nomeAparelhoInputAtualizacao.setEnabled(true);
            ultimaManutencaoAparelhoInputAtualizacao.setEnabled(true);
                    
            nomeAparelhoInputAtualizacao.setText(aparelhos.get(tableAparelhosAtualizacao.getSelectedRow()).GetNome());
            ultimaManutencaoAparelhoInputAtualizacao.setText(aparelhos.get(tableAparelhosAtualizacao.getSelectedRow()).GetUltimaManutencao().toString());
            ultimaManutencaoAparelhoInputAtualizacao.validate();
        }
        else
        {
            nomeAparelhoInputAtualizacao.setEnabled(false);
            ultimaManutencaoAparelhoInputAtualizacao.setEnabled(false);
                    
            nomeAparelhoInputAtualizacao.setText("");
            ultimaManutencaoAparelhoInputAtualizacao.setText("    -  -  ");
            ultimaManutencaoAparelhoInputAtualizacao.validate();
        }
    }
        
    public GestaoAparelhos() throws ClassNotFoundException, SQLException{
        
        aparelhoJDBC = new AparelhoJDBC();
                
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
        tabbedPane.addTab("Cadastrar Aparelho", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeAparelhoText1 = new JTextArea();
        nomeAparelhoText1.setText("Nome do aparelho: ");
        nomeAparelhoText1.setEditable(false);
        nomeAparelhoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomeAparelhoText1);

        nomeAparelhoInputCadastro = new JTextField();
        nomeAparelhoInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomeAparelhoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea ultimaManutencaoAparelhoText1 = new JTextArea();
        ultimaManutencaoAparelhoText1.setText("Ultima manutencao do aparelho: ");
        ultimaManutencaoAparelhoText1.setEditable(false);
        ultimaManutencaoAparelhoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(ultimaManutencaoAparelhoText1);

        ultimaManutencaoAparelhoInputCadastro = new JFormattedTextField(dataMaskFormat);
        ultimaManutencaoAparelhoInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(ultimaManutencaoAparelhoInputCadastro);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarAparelho = new JButton("Cadastrar Aparelho");
        panelCadastrar.add(cadastrarAparelho);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Aparelho", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeAparelhoText2 = new JTextArea();
        nomeAparelhoText2.setText("Nome do aparelho: ");
        nomeAparelhoText2.setEditable(false);
        nomeAparelhoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomeAparelhoText2);

        nomeAparelhoInputAtualizacao = new JTextField();
        nomeAparelhoInputAtualizacao.setColumns(20);
        nomeAparelhoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomeAparelhoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea ultimaManutencaoAparelhoText2 = new JTextArea();
        ultimaManutencaoAparelhoText2.setText("Ultima manutencao do aparelho: ");
        ultimaManutencaoAparelhoText2.setEditable(false);
        ultimaManutencaoAparelhoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(ultimaManutencaoAparelhoText2);

        ultimaManutencaoAparelhoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        ultimaManutencaoAparelhoInputAtualizacao.setColumns(10);
        ultimaManutencaoAparelhoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(ultimaManutencaoAparelhoInputAtualizacao);
        
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

        JButton atualizarAparelho = new JButton("Atualizar Aparelho");
        panelAtualizar.add(atualizarAparelho);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Aparelho", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeAparelhoText3 = new JTextArea();
        nomeAparelhoText3.setText("Nome do aparelho: ");
        nomeAparelhoText3.setEditable(false);
        nomeAparelhoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomeAparelhoText3);

        nomeAparelhoInputRemocao = new JTextField();
        nomeAparelhoInputRemocao.setColumns(20);
        nomeAparelhoInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomeAparelhoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea ultimaManutencaoAparelhoText3 = new JTextArea();
        ultimaManutencaoAparelhoText3.setText("Ultima manutencao do aparelho: ");
        ultimaManutencaoAparelhoText3.setEditable(false);
        ultimaManutencaoAparelhoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(ultimaManutencaoAparelhoText3);

        ultimaManutencaoAparelhoInputRemocao = new JFormattedTextField(dataMaskFormat);
        ultimaManutencaoAparelhoInputRemocao.setColumns(10);
        ultimaManutencaoAparelhoInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(ultimaManutencaoAparelhoInputRemocao);

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

        JButton removerAparelho = new JButton("Remover Aparelho");
        panelRemover.add(removerAparelho);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverAparelho();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Aparelho", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoAparelho = new JButton("Relatório Padrão dos Aparelhos");
        panelRelatorio.add(relatorioPadraoAparelho);
        
        relatorioPadraoAparelho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GerarRelatorioPadrao();
                } catch (IOException ex) {
                    Logger.getLogger(GestaoAparelhos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
