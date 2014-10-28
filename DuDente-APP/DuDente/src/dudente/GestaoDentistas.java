/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.text.ParseException;
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
import javax.swing.Box;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author duds
 */
public class GestaoDentistas extends JPanel{
    
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
    
    public GestaoDentistas(){
        
        try{
        salarioMaskFormat = new MaskFormatter("R$ ####,##");
        }
        catch(ParseException excp){}
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();
        
        //Criar lista de dentistas.
        String nomeColunasDentistas[] = {"Nome", "Salário", "Especialização"};
        
        String[][] listaDentistas = new String[3][3];
        listaDentistas[0][0] = "Dentista01";
        listaDentistas[0][1] = "Dentista02";
        listaDentistas[0][2] = "Dentista03";
        listaDentistas[1][0] = "Dentista04";
        listaDentistas[1][1] = "Dentista05";
        listaDentistas[1][2] = "Dentista06";
        listaDentistas[2][0] = "Dentista07";
        listaDentistas[2][1] = "Dentista08";
        listaDentistas[2][2] = "Dentista09";
        
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
        
        JPanel panelAtualizarLinha4 = new JPanel();
        panelAtualizarLinha4.setLayout(new BoxLayout(panelAtualizarLinha4, BoxLayout.X_AXIS));
        panelAtualizarLinha4.setMaximumSize(new Dimension(400, 20));
        panelAtualizarLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JTable tableDentistasAtualizacao = new JTable(listaDentistas, nomeColunasDentistas);

        JScrollPane listDentistasAtualizarScroll = new JScrollPane(tableDentistasAtualizacao);
        listDentistasAtualizarScroll.setPreferredSize(new Dimension(250, 80));
        panelAtualizarLinha4.add(listDentistasAtualizarScroll);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelAtualizar.add(panelAtualizarLinha1);
        panelAtualizar.add(panelAtualizarLinha2);
        panelAtualizar.add(panelAtualizarLinha3);
        
        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 20)));

        panelAtualizar.add(panelAtualizarLinha4);
        
        panelAtualizar.add(Box.createVerticalGlue());

        JButton AtualizarDentista = new JButton("Atualizar Dentista");
        panelAtualizar.add(AtualizarDentista);

        panelAtualizar.add(Box.createRigidArea(new Dimension(0, 50)));

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
        
        JPanel panelRemoverLinha4 = new JPanel();
        panelRemoverLinha4.setLayout(new BoxLayout(panelRemoverLinha4, BoxLayout.X_AXIS));
        panelRemoverLinha4.setMaximumSize(new Dimension(400, 20));
        panelRemoverLinha4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JTable tableDentistasRemocao = new JTable(listaDentistas, nomeColunasDentistas);

        JScrollPane listDentistasRemoverScroll = new JScrollPane(tableDentistasRemocao);
        listDentistasRemoverScroll.setPreferredSize(new Dimension(250, 80));
        panelRemoverLinha4.add(listDentistasRemoverScroll);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));
        
        panelRemover.add(panelRemoverLinha1);
        panelRemover.add(panelRemoverLinha2);
        panelRemover.add(panelRemoverLinha3);
        
        panelRemover.add(Box.createRigidArea(new Dimension(0, 20)));

        panelRemover.add(panelRemoverLinha4);
        
        panelRemover.add(Box.createVerticalGlue());

        JButton RemoverDentista = new JButton("Remover Dentista");
        panelRemover.add(RemoverDentista);

        panelRemover.add(Box.createRigidArea(new Dimension(0, 50)));

        //**********************
        //Adicionar tabbed pane.
        //**********************
        
        add(tabbedPane);
    }
    
}
