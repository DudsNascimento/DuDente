/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Igor Couto
 */
public class ParserXML {

    public ArrayList<Funcionario> parse(String funcionario) {

        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {

            File fXmlFile = new File("./resources/" + funcionario + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName(funcionario);


            //System.out.println("\nLista: " + nList.getLength());
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Funcionario f = new Funcionario();
                    
                    f.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
                    f.setCargo(eElement.getElementsByTagName("ocupacao").item(0).getTextContent());
                    f.setSalario(eElement.getElementsByTagName("salario").item(0).getTextContent());
                    
                    funcionarios.add(f);

//                    System.out.println("Nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
//                    System.out.println("Ocupacao : " + eElement.getElementsByTagName("ocupacao").item(0).getTextContent());
//                    System.out.println("Salario : " + eElement.getElementsByTagName("salario").item(0).getTextContent());

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return funcionarios;
    }
}
