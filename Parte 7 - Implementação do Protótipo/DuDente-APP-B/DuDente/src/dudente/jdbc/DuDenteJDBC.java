/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author duds-ufjf
 */
public class DuDenteJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        System.out.println("Ola mundo....\n");
        
        DentistaJDBC dentistaJDBC = new DentistaJDBC();
        
        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        DentistaTable dentistaTable = new DentistaTable();
        dentistaTable.SetEspecializacao("Ortodontia");
        dentistaTable.SetNome("Cebola");
        dentistaTable.SetSalario(1200.00f);
        dentistaJDBC.AdicionarDentista(dentistaTable);
        ArrayList <DentistaTable> listaDentistas = dentistaJDBC.ListarDentistas();
        dentistaJDBC.EncerrarConexao();
        
        if(listaDentistas.size() > 0){
            System.out.println(listaDentistas.get(0).GetNome());
        }
    }
}
