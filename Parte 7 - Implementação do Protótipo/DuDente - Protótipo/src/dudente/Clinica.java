package dudente;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Igor Couto
 */
public class Clinica {

    ArrayList<Funcionario> dentistas;
    ArrayList<Funcionario> empregados;
    ArrayList<Medicamento> medicamentos;
    ArrayList<Aparelho> aparelhos;
    static Image icone;


    public Clinica() {
        
        
        icone = new ImageIcon("./resources/tooth3.png").getImage();
        ParserXML parser = new ParserXML();
        
        dentistas = parser.parse("dentista");
        empregados = parser.parse("empregado");
        
        Cadastro cadastro = new Cadastro(dentistas, empregados);
        
       

//        DuDenteUI ui = new DuDenteUI(dentistas, empregados);
//
//        JFrame janela = new JFrame("DuDente - Sistema Integrado de Gestão de Clínicas Odontológicas");
//        
//        janela.add(ui);
//        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        janela.pack();
//        //janela.setSize(width, height - 40);
//        janela.setVisible(true);
//
//        ImageIcon img = new ImageIcon("C:/tooth3.png");
//        janela.setIconImage(img.getImage());
    }    
}
