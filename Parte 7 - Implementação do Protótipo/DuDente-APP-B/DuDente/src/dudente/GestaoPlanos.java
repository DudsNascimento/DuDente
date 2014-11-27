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
public class GestaoPlanos extends JPanel{
    
    private ArrayList <PlanoTable> planos;
    private PlanoJDBC planoJDBC;
    
    private MaskFormatter dataMaskFormat;
    
    private JTextField nomePlanoInputCadastro;
    private JFormattedTextField validadePlanoInputCadastro;
    
    private JTextField nomePlanoInputAtualizacao;
    private JFormattedTextField validadePlanoInputAtualizacao;
    
    private JTextField nomePlanoInputRemocao;
    private JFormattedTextField validadePlanoInputRemocao;
    private JTextField especializacaoPlanoInputRemocao;
    
    JPanel panelAtualizarLinha3;
    JPanel panelRemoverLinha3;
    
    JTable tablePlanosAtualizacao;
    JTable tablePlanosRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        planoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        AtualizarListaPlanos();
    }
    
    public void Desconectar() throws SQLException{
        
        planoJDBC.EncerrarConexao();
    }

    public void AtualizarListaPlanos() throws SQLException{
        
        String nomeColunasPlanos[] = {"Nome", "Validade"};
    
        planos = planoJDBC.ListarPlanos();
        if(planos.size() > 0)
        {
            String[][] listaPlanos = new String[planos.size()][3];
            for(int count = 0; count < planos.size(); count++)
            {
                listaPlanos[count][0] = planos.get(count).GetNome();
                listaPlanos[count][1] = planos.get(count).GetValidade().toString();
            }
            
            tablePlanosAtualizacao = new JTable(listaPlanos, nomeColunasPlanos);

            JScrollPane listPlanosAtualizarScroll = new JScrollPane(tablePlanosAtualizacao);
            listPlanosAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha3.removeAll();
            panelAtualizarLinha3.add(listPlanosAtualizarScroll);
            
            panelAtualizarLinha3.setVisible(true);
            panelAtualizarLinha3.updateUI();
            
            tablePlanosAtualizacao.addMouseListener(new MouseListener() {

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
            
            tablePlanosAtualizacao.addKeyListener(new KeyListener() {

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
            
            tablePlanosAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tablePlanosRemocao = new JTable(listaPlanos, nomeColunasPlanos);

            JScrollPane listPlanosRemoverScroll = new JScrollPane(tablePlanosRemocao);
            listPlanosRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha3.removeAll();
            panelRemoverLinha3.add(listPlanosRemoverScroll);
            
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
        
        FileWriter fileRelatorioPlanos = new FileWriter("relatorio_planos");
        BufferedWriter bufferFileRelatorioPlanos = new BufferedWriter(fileRelatorioPlanos);
        
        bufferFileRelatorioPlanos.write("***********************\n");
        bufferFileRelatorioPlanos.write("Relatorio de planos.\n");
        bufferFileRelatorioPlanos.write("***********************\n\n");
        
        for(int count = 0; count < planos.size(); count++)
        {
            bufferFileRelatorioPlanos.write("Nome: "+planos.get(count).GetNome()+"\n");
            bufferFileRelatorioPlanos.write("Validade: "+planos.get(count).GetValidade().toString()+"\n\n");
        }
        
        bufferFileRelatorioPlanos.close();
        fileRelatorioPlanos.close();
    }
    
    public void AdicionarPlano() throws SQLException{

        if(nomePlanoInputCadastro.getText().length() > 0 &&
           validadePlanoInputCadastro.isEditValid())
        {
            PlanoTable plano = new PlanoTable();
            plano.SetNome(nomePlanoInputCadastro.getText());
            plano.SetValidade(Date.valueOf(validadePlanoInputCadastro.getText()));
            
            planoJDBC.AdicionarPlano(plano);
            
            nomePlanoInputCadastro.setText("");
            validadePlanoInputCadastro.setText("    -  -  ");
            validadePlanoInputCadastro.validate();
        }
        
        AtualizarListaPlanos();
    }
    
    public void AtualizarPlano() throws SQLException{

        if(nomePlanoInputAtualizacao.getText().length() > 0 &&
           validadePlanoInputAtualizacao.isEditValid() &&
           tablePlanosAtualizacao.getSelectedRow() >= 0 && tablePlanosAtualizacao.getSelectedRow() < planos.size())
        {
            PlanoTable plano = new PlanoTable();
            plano.SetNome(nomePlanoInputAtualizacao.getText());
            plano.SetValidade(Date.valueOf(validadePlanoInputAtualizacao.getText()));
            plano.SetIdPlano(planos.get(tablePlanosAtualizacao.getSelectedRow()).GetIdPlano());

            planoJDBC.AtualizarPlano(plano);
            
            nomePlanoInputAtualizacao.setText("");
            validadePlanoInputAtualizacao.setText("    -  -  ");
            validadePlanoInputAtualizacao.validate();
        }
        
        AtualizarListaPlanos();
    }
    
    public void RemoverPlano() throws SQLException{
        
        if(tablePlanosRemocao.getSelectedRow() >= 0 && tablePlanosRemocao.getSelectedRow() < planos.size())
        {
            planoJDBC.RemoverPlano(planos.get(tablePlanosRemocao.getSelectedRow()));
        }
        
        AtualizarListaPlanos();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tablePlanosAtualizacao.getSelectedRow() >= 0 && tablePlanosAtualizacao.getSelectedRow() < planos.size())
        {
            nomePlanoInputAtualizacao.setEnabled(true);
            validadePlanoInputAtualizacao.setEnabled(true);
                    
            nomePlanoInputAtualizacao.setText(planos.get(tablePlanosAtualizacao.getSelectedRow()).GetNome());
            validadePlanoInputAtualizacao.setText(planos.get(tablePlanosAtualizacao.getSelectedRow()).GetValidade().toString());
            validadePlanoInputAtualizacao.validate();
        }
        else
        {
            nomePlanoInputAtualizacao.setEnabled(false);
            validadePlanoInputAtualizacao.setEnabled(false);
                    
            nomePlanoInputAtualizacao.setText("");
            validadePlanoInputAtualizacao.setText("    -  -  ");
            validadePlanoInputAtualizacao.validate();
        }
    }
        
    public GestaoPlanos() throws ClassNotFoundException, SQLException{
        
        planoJDBC = new PlanoJDBC();
                
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
        tabbedPane.addTab("Cadastrar Plano", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePlanoText1 = new JTextArea();
        nomePlanoText1.setText("Nome do plano: ");
        nomePlanoText1.setEditable(false);
        nomePlanoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomePlanoText1);

        nomePlanoInputCadastro = new JTextField();
        nomePlanoInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomePlanoInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadePlanoText1 = new JTextArea();
        validadePlanoText1.setText("Validade do plano: ");
        validadePlanoText1.setEditable(false);
        validadePlanoText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(validadePlanoText1);

        validadePlanoInputCadastro = new JFormattedTextField(dataMaskFormat);
        validadePlanoInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(validadePlanoInputCadastro);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarPlano = new JButton("Cadastrar Plano");
        panelCadastrar.add(cadastrarPlano);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarPlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarPlano();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPlanos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Plano", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePlanoText2 = new JTextArea();
        nomePlanoText2.setText("Nome do plano: ");
        nomePlanoText2.setEditable(false);
        nomePlanoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomePlanoText2);

        nomePlanoInputAtualizacao = new JTextField();
        nomePlanoInputAtualizacao.setColumns(20);
        nomePlanoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomePlanoInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadePlanoText2 = new JTextArea();
        validadePlanoText2.setText("Validade do plano: ");
        validadePlanoText2.setEditable(false);
        validadePlanoText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(validadePlanoText2);

        validadePlanoInputAtualizacao = new JFormattedTextField(dataMaskFormat);
        validadePlanoInputAtualizacao.setColumns(10);
        validadePlanoInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(validadePlanoInputAtualizacao);
        
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

        JButton atualizarPlano = new JButton("Atualizar Plano");
        panelAtualizar.add(atualizarPlano);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarPlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarPlano();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPlanos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Plano", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomePlanoText3 = new JTextArea();
        nomePlanoText3.setText("Nome do plano: ");
        nomePlanoText3.setEditable(false);
        nomePlanoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomePlanoText3);

        nomePlanoInputRemocao = new JTextField();
        nomePlanoInputRemocao.setColumns(20);
        nomePlanoInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomePlanoInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea validadePlanoText3 = new JTextArea();
        validadePlanoText3.setText("Validade do plano: ");
        validadePlanoText3.setEditable(false);
        validadePlanoText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(validadePlanoText3);

        validadePlanoInputRemocao = new JFormattedTextField(dataMaskFormat);
        validadePlanoInputRemocao.setColumns(10);
        validadePlanoInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(validadePlanoInputRemocao);

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

        JButton removerPlano = new JButton("Remover Plano");
        panelRemover.add(removerPlano);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerPlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverPlano();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoPlanos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Plano", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoPlano = new JButton("Relatório Padrão dos Planos");
        panelRelatorio.add(relatorioPadraoPlano);
        
        relatorioPadraoPlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GerarRelatorioPadrao();
                } catch (IOException ex) {
                    Logger.getLogger(GestaoPlanos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
