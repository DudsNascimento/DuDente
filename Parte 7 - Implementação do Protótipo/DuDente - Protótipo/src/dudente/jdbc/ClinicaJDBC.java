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
public class ClinicaJDBC extends SQLConnection{

    public ClinicaJDBC(){
    }
    
    ArrayList <ClinicaTable> ListarClinicas() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM clinica;")){
        
            ArrayList <ClinicaTable> listaClinicas = new ArrayList <ClinicaTable>();
            while(this.resultSet.next()){

                ClinicaTable clinicaIndex = new ClinicaTable(); 
                clinicaIndex.SetNome(this.resultSet.getString("nome"));
                clinicaIndex.SetEndereco(this.resultSet.getString("endereco"));
                clinicaIndex.SetIdClinica(this.resultSet.getInt("id_clinica"));

                listaClinicas.add(clinicaIndex);
            }    
            return listaClinicas;
        }
        return null;
    }
    
    boolean AdicionarClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO clinica (nome, endereco) VALUES (\""+clinicaTable.GetNome()+"\", \""+clinicaTable.GetEndereco()+"\");");
    }
    
    boolean RemoverClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM clinica WHERE id_clinica = \""+clinicaTable.GetIdClinica()+"\";");
    }
    
    boolean AtualizarClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE clinica SET nome=\""+clinicaTable.GetNome()+"\", endereco=\""+clinicaTable.GetEndereco()+"\", WHERE id_clinica = \""+clinicaTable.GetIdClinica()+"\";");
    }
}
