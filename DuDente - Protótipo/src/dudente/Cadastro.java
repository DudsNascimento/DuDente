package dudente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Igor Couto
 */
public class Cadastro {

    javax.swing.JPanel janela = new javax.swing.JPanel();
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabelaDentistas;
    private javax.swing.JTable tabelaEmpregados;
    private final ArrayList<Funcionario> empregados;
    private final ArrayList<Funcionario> dentistas;

    public Cadastro(ArrayList<Funcionario> dentistas, ArrayList<Funcionario> empregados) {
        this.empregados = empregados;
        this.dentistas = dentistas;

        CadastroUI ui = new CadastroUI(dentistas, empregados);





        //size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();








        JFrame janela = new JFrame("DuDente - Sistema Integrado de Gestão de Clínicas Odontológicas");
        janela.add(ui);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(janela.getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;

        
        janela.pack();
        janela.setResizable(false);
        janela.setSize(screenSize.width, screenSize.height - taskBarSize);

        
        janela.setVisible(true);

        ImageIcon img = new ImageIcon("C:/tooth3.png");
        janela.setIconImage(img.getImage());



    }
}
