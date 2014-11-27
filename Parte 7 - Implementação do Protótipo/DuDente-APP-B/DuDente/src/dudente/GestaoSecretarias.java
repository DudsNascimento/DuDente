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
public class GestaoSecretarias extends JPanel{
    
    private ArrayList <SecretariaTable> secretarias;
    private SecretariaJDBC secretariaJDBC;
    
    private MaskFormatter salarioMaskFormat;
    
    private JTextField nomeSecretariaInputCadastro;
    private JFormattedTextField salarioSecretariaInputCadastro;
    private JFormattedTextField bonificacaoSecretariaInputCadastro;        
    
    private JTextField nomeSecretariaInputAtualizacao;
    private JFormattedTextField salarioSecretariaInputAtualizacao;
    private JFormattedTextField bonificacaoSecretariaInputAtualizacao;
    
    private JTextField nomeSecretariaInputRemocao;
    private JFormattedTextField salarioSecretariaInputRemocao;
    private JFormattedTextField bonificacaoSecretariaInputRemocao;
    
    JPanel panelAtualizarLinha4;
    JPanel panelRemoverLinha4;
    
    JTable tableSecretariasAtualizacao;
    JTable tableSecretariasRemocao;
    
    public void Conectar() throws ClassNotFoundException, SQLException{
        
        secretariaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        AtualizarListaSecretarias();
    }
    
    public void Desconectar() throws SQLException{
        
        secretariaJDBC.EncerrarConexao();
    }

    public void AtualizarListaSecretarias() throws SQLException{
        
        String nomeColunasSecretarias[] = {"Nome", "Salário", "Bonificação"};
    
        secretarias = secretariaJDBC.ListarSecretarias();
        if(secretarias.size() > 0)
        {
            String[][] listaSecretarias = new String[secretarias.size()][3];
            for(int count = 0; count < secretarias.size(); count++)
            {
                listaSecretarias[count][0] = secretarias.get(count).GetNome();
                listaSecretarias[count][1] = String.valueOf(secretarias.get(count).GetSalario());
                listaSecretarias[count][2] = String.valueOf(secretarias.get(count).GetBonificacao());
            }
            
            tableSecretariasAtualizacao = new JTable(listaSecretarias, nomeColunasSecretarias);

            JScrollPane listSecretariasAtualizarScroll = new JScrollPane(tableSecretariasAtualizacao);
            listSecretariasAtualizarScroll.setPreferredSize(new Dimension(250, 80));
            
            panelAtualizarLinha4.removeAll();
            panelAtualizarLinha4.add(listSecretariasAtualizarScroll);
            
            panelAtualizarLinha4.setVisible(true);
            panelAtualizarLinha4.updateUI();
            
            tableSecretariasAtualizacao.addMouseListener(new MouseListener() {

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
            
            tableSecretariasAtualizacao.addKeyListener(new KeyListener() {

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
            
            tableSecretariasAtualizacao.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    PreencherInputsAtualizacao();
                }

                @Override
                public void focusLost(FocusEvent fe) {
                }
            });

            tableSecretariasRemocao = new JTable(listaSecretarias, nomeColunasSecretarias);

            JScrollPane listSecretariasRemoverScroll = new JScrollPane(tableSecretariasRemocao);
            listSecretariasRemoverScroll.setPreferredSize(new Dimension(250, 80));
                        
            panelRemoverLinha4.removeAll();
            panelRemoverLinha4.add(listSecretariasRemoverScroll);
            
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
        
        FileWriter fileRelatorioSecretarias = new FileWriter("relatorio_secretarias");
        BufferedWriter bufferFileRelatorioSecretarias = new BufferedWriter(fileRelatorioSecretarias);
        
        bufferFileRelatorioSecretarias.write("***********************\n");
        bufferFileRelatorioSecretarias.write("Relatorio de secretarias.\n");
        bufferFileRelatorioSecretarias.write("***********************\n\n");
        
        for(int count = 0; count < secretarias.size(); count++)
        {
            bufferFileRelatorioSecretarias.write("Nome: "+secretarias.get(count).GetNome()+"\n");
            bufferFileRelatorioSecretarias.write("Salario: "+secretarias.get(count).GetSalario()+"\n");
            bufferFileRelatorioSecretarias.write("Bonificação: "+secretarias.get(count).GetBonificacao()+"\n\n");
        }
        
        bufferFileRelatorioSecretarias.close();
        fileRelatorioSecretarias.close();
    }
    
    public void AdicionarSecretaria() throws SQLException{

        if(nomeSecretariaInputCadastro.getText().length() > 0 && bonificacaoSecretariaInputCadastro.isEditValid() &&
           salarioSecretariaInputCadastro.isEditValid())
        {
            SecretariaTable secretaria = new SecretariaTable();
            secretaria.SetNome(nomeSecretariaInputCadastro.getText());
            secretaria.SetSalario(Float.valueOf(salarioSecretariaInputCadastro.getText().substring(3)));
            secretaria.SetBonificacao(Float.valueOf(bonificacaoSecretariaInputCadastro.getText().substring(3)));
            
            secretariaJDBC.AdicionarSecretaria(secretaria);

            nomeSecretariaInputCadastro.setText("");
            salarioSecretariaInputCadastro.setText("R$     .  ");
            salarioSecretariaInputCadastro.validate();
            bonificacaoSecretariaInputCadastro.setText("R$     .  ");
            bonificacaoSecretariaInputCadastro.validate();
        }
        
        AtualizarListaSecretarias();
    }
    
    public void AtualizarSecretaria() throws SQLException{

        if(nomeSecretariaInputAtualizacao.getText().length() > 0 && bonificacaoSecretariaInputAtualizacao.isEditValid() &&
           salarioSecretariaInputAtualizacao.isEditValid() &&
           tableSecretariasAtualizacao.getSelectedRow() >= 0 && tableSecretariasAtualizacao.getSelectedRow() < secretarias.size())
        {
            SecretariaTable secretaria = new SecretariaTable();
            secretaria.SetNome(nomeSecretariaInputAtualizacao.getText());
            secretaria.SetSalario(Float.valueOf(salarioSecretariaInputAtualizacao.getText().substring(3)));
            secretaria.SetBonificacao(Float.valueOf(bonificacaoSecretariaInputAtualizacao.getText().substring(3)));
            secretaria.SetIdSecretaria(secretarias.get(tableSecretariasAtualizacao.getSelectedRow()).GetIdSecretaria());

            secretariaJDBC.AtualizarSecretaria(secretaria);
            
            nomeSecretariaInputAtualizacao.setText("");
            salarioSecretariaInputAtualizacao.setText("R$     .  ");
            salarioSecretariaInputAtualizacao.validate();
            bonificacaoSecretariaInputAtualizacao.setText("");
        }
        
        AtualizarListaSecretarias();
    }
    
    public void RemoverSecretaria() throws SQLException{
        
        if(tableSecretariasRemocao.getSelectedRow() >= 0 && tableSecretariasRemocao.getSelectedRow() < secretarias.size())
        {
            secretariaJDBC.RemoverSecretaria(secretarias.get(tableSecretariasRemocao.getSelectedRow()));
        }
        
        AtualizarListaSecretarias();
        PreencherInputsAtualizacao();
    }
    
    public void PreencherInputsAtualizacao()
    {
        if(tableSecretariasAtualizacao.getSelectedRow() >= 0 && tableSecretariasAtualizacao.getSelectedRow() < secretarias.size())
        {
            nomeSecretariaInputAtualizacao.setEnabled(true);
            salarioSecretariaInputAtualizacao.setEnabled(true);
            bonificacaoSecretariaInputAtualizacao.setEnabled(true);
                    
            nomeSecretariaInputAtualizacao.setText(secretarias.get(tableSecretariasAtualizacao.getSelectedRow()).GetNome());
            salarioSecretariaInputAtualizacao.setText("R$ "+String.valueOf(secretarias.get(tableSecretariasAtualizacao.getSelectedRow()).GetSalario())+"0");
            salarioSecretariaInputAtualizacao.validate();
            bonificacaoSecretariaInputAtualizacao.setText("R$ "+String.valueOf(secretarias.get(tableSecretariasAtualizacao.getSelectedRow()).GetBonificacao())+"0");
        }
        else
        {
            nomeSecretariaInputAtualizacao.setEnabled(false);
            salarioSecretariaInputAtualizacao.setEnabled(false);
            bonificacaoSecretariaInputAtualizacao.setEnabled(false);
                    
            nomeSecretariaInputAtualizacao.setText("");
            salarioSecretariaInputAtualizacao.setText("R$     .  ");
            salarioSecretariaInputAtualizacao.validate();
            bonificacaoSecretariaInputAtualizacao.setText("");            
        }
    }
        
    public GestaoSecretarias() throws ClassNotFoundException, SQLException{
        
        secretariaJDBC = new SecretariaJDBC();
                
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
        tabbedPane.addTab("Cadastrar Secretaria", panelCadastrar);

        JPanel panelCadastrarLinha1 = new JPanel();
        panelCadastrarLinha1.setLayout(new BoxLayout(panelCadastrarLinha1, BoxLayout.X_AXIS));
        panelCadastrarLinha1.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeSecretariaText1 = new JTextArea();
        nomeSecretariaText1.setText("Nome do secretaria: ");
        nomeSecretariaText1.setEditable(false);
        nomeSecretariaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha1.add(nomeSecretariaText1);

        nomeSecretariaInputCadastro = new JTextField();
        nomeSecretariaInputCadastro.setColumns(20);
        panelCadastrarLinha1.add(nomeSecretariaInputCadastro);

        JPanel panelCadastrarLinha2 = new JPanel();
        panelCadastrarLinha2.setLayout(new BoxLayout(panelCadastrarLinha2, BoxLayout.X_AXIS));
        panelCadastrarLinha2.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioSecretariaText1 = new JTextArea();
        salarioSecretariaText1.setText("Salario do secretaria: ");
        salarioSecretariaText1.setEditable(false);
        salarioSecretariaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha2.add(salarioSecretariaText1);

        salarioSecretariaInputCadastro = new JFormattedTextField(salarioMaskFormat);
        salarioSecretariaInputCadastro.setColumns(10);
        panelCadastrarLinha2.add(salarioSecretariaInputCadastro);

        JPanel panelCadastrarLinha3 = new JPanel();
        panelCadastrarLinha3.setLayout(new BoxLayout(panelCadastrarLinha3, BoxLayout.X_AXIS));
        panelCadastrarLinha3.setMaximumSize(new Dimension(400, 20));
        panelCadastrarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea bonificacaoSecretariaText1 = new JTextArea();
        bonificacaoSecretariaText1.setText("Bonificacao da secretaria: ");
        bonificacaoSecretariaText1.setEditable(false);
        bonificacaoSecretariaText1.setBackground(new Color(0, 0, 0, 0));
        panelCadastrarLinha3.add(bonificacaoSecretariaText1);

        bonificacaoSecretariaInputCadastro = new JFormattedTextField(salarioMaskFormat);
        bonificacaoSecretariaInputCadastro.setColumns(10);
        panelCadastrarLinha3.add(bonificacaoSecretariaInputCadastro);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCadastrar.add(panelCadastrarLinha1);
        panelCadastrar.add(panelCadastrarLinha2);
        panelCadastrar.add(panelCadastrarLinha3);
        panelCadastrar.add(Box.createVerticalGlue());

        JButton cadastrarSecretaria = new JButton("Cadastrar Secretaria");
        panelCadastrar.add(cadastrarSecretaria);

        panelCadastrar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        cadastrarSecretaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AdicionarSecretaria();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoSecretarias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Painel de atualizacao.
        //**********************

        JPanel panelAtualizar = new JPanel();
        panelAtualizar.setLayout(new BoxLayout(panelAtualizar, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Atualizar Secretaria", panelAtualizar);
        
        JPanel panelAtualizarLinha1 = new JPanel();
        panelAtualizarLinha1.setLayout(new BoxLayout(panelAtualizarLinha1, BoxLayout.X_AXIS));
        panelAtualizarLinha1.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeSecretariaText2 = new JTextArea();
        nomeSecretariaText2.setText("Nome do secretaria: ");
        nomeSecretariaText2.setEditable(false);
        nomeSecretariaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha1.add(nomeSecretariaText2);

        nomeSecretariaInputAtualizacao = new JTextField();
        nomeSecretariaInputAtualizacao.setColumns(20);
        nomeSecretariaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha1.add(nomeSecretariaInputAtualizacao);

        JPanel panelAtualizarLinha2 = new JPanel();
        panelAtualizarLinha2.setLayout(new BoxLayout(panelAtualizarLinha2, BoxLayout.X_AXIS));
        panelAtualizarLinha2.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioSecretariaText2 = new JTextArea();
        salarioSecretariaText2.setText("Salario do secretaria: ");
        salarioSecretariaText2.setEditable(false);
        salarioSecretariaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha2.add(salarioSecretariaText2);

        salarioSecretariaInputAtualizacao = new JFormattedTextField(salarioMaskFormat);
        salarioSecretariaInputAtualizacao.setColumns(10);
        salarioSecretariaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha2.add(salarioSecretariaInputAtualizacao);

        JPanel panelAtualizarLinha3 = new JPanel();
        panelAtualizarLinha3.setLayout(new BoxLayout(panelAtualizarLinha3, BoxLayout.X_AXIS));
        panelAtualizarLinha3.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea bonificacaoSecretariaText2 = new JTextArea();
        bonificacaoSecretariaText2.setText("Bonificacao da secretaria: ");
        bonificacaoSecretariaText2.setEditable(false);
        bonificacaoSecretariaText2.setBackground(new Color(0, 0, 0, 0));
        panelAtualizarLinha3.add(bonificacaoSecretariaText2);

        bonificacaoSecretariaInputAtualizacao = new JFormattedTextField(salarioMaskFormat);
        bonificacaoSecretariaInputAtualizacao.setColumns(10);
        bonificacaoSecretariaInputAtualizacao.setEnabled(false);
        panelAtualizarLinha3.add(bonificacaoSecretariaInputAtualizacao);
        
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

        JButton atualizarSecretaria = new JButton("Atualizar Secretaria");
        panelAtualizar.add(atualizarSecretaria);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        atualizarSecretaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    AtualizarSecretaria();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoSecretarias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //******************
        //Painel de remocao.
        //******************

        JPanel panelRemover = new JPanel();
        panelRemover.setLayout(new BoxLayout(panelRemover, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Remover Secretaria", panelRemover);
        
        JPanel panelRemoverLinha1 = new JPanel();
        panelRemoverLinha1.setLayout(new BoxLayout(panelRemoverLinha1, BoxLayout.X_AXIS));
        panelRemoverLinha1.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea nomeSecretariaText3 = new JTextArea();
        nomeSecretariaText3.setText("Nome da secretaria: ");
        nomeSecretariaText3.setEditable(false);
        nomeSecretariaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha1.add(nomeSecretariaText3);

        nomeSecretariaInputRemocao = new JTextField();
        nomeSecretariaInputRemocao.setColumns(20);
        nomeSecretariaInputRemocao.setEnabled(false);
        panelRemoverLinha1.add(nomeSecretariaInputRemocao);

        JPanel panelRemoverLinha2 = new JPanel();
        panelRemoverLinha2.setLayout(new BoxLayout(panelRemoverLinha2, BoxLayout.X_AXIS));
        panelRemoverLinha2.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea salarioSecretariaText3 = new JTextArea();
        salarioSecretariaText3.setText("Salario do secretaria: ");
        salarioSecretariaText3.setEditable(false);
        salarioSecretariaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha2.add(salarioSecretariaText3);

        salarioSecretariaInputRemocao = new JFormattedTextField(salarioMaskFormat);
        salarioSecretariaInputRemocao.setColumns(10);
        salarioSecretariaInputRemocao.setEnabled(false);
        panelRemoverLinha2.add(salarioSecretariaInputRemocao);

        JPanel panelRemoverLinha3 = new JPanel();
        panelRemoverLinha3.setLayout(new BoxLayout(panelRemoverLinha3, BoxLayout.X_AXIS));
        panelRemoverLinha3.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea bonificacaoSecretariaText3 = new JTextArea();
        bonificacaoSecretariaText3.setText("Bonificacao da secretaria: ");
        bonificacaoSecretariaText3.setEditable(false);
        bonificacaoSecretariaText3.setBackground(new Color(0, 0, 0, 0));
        panelRemoverLinha3.add(bonificacaoSecretariaText3);

        bonificacaoSecretariaInputRemocao = new JFormattedTextField(salarioMaskFormat);
        bonificacaoSecretariaInputRemocao.setColumns(50);
        bonificacaoSecretariaInputRemocao.setEnabled(false);
        panelRemoverLinha3.add(bonificacaoSecretariaInputRemocao);
        
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

        JButton removerSecretaria = new JButton("Remover Secretaria");
        panelRemover.add(removerSecretaria);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        removerSecretaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    RemoverSecretaria();
                } catch (SQLException ex) {
                    Logger.getLogger(GestaoSecretarias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //********************
        //Painel de relatorio.
        //********************

        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BoxLayout(panelRelatorio, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Relatorio Secretaria", panelRelatorio);
        
        panelRelatorio.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton relatorioPadraoSecretaria = new JButton("Relatório Padrão dos Secretarias");
        panelRelatorio.add(relatorioPadraoSecretaria);
        
        relatorioPadraoSecretaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GerarRelatorioPadrao();
                } catch (IOException ex) {
                    Logger.getLogger(GestaoSecretarias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }   
}
