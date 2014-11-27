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
    
    public boolean ProcurarClinica(ClinicaTable clinicaTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM clinica WHERE id_clinica = "+clinicaTable.GetIdClinica()+";")){
        
            if(this.resultSet.next()){

                clinicaTable.SetNome(this.resultSet.getString("nome"));
                clinicaTable.SetEndereco(this.resultSet.getString("endereco"));
                clinicaTable.SetIdClinica(this.resultSet.getInt("id_clinica"));
            }    
            return true;
        }
        return false;
    }

    public ArrayList <ClinicaTable> ListarClinicas() throws SQLException{
        
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
    
    public boolean AdicionarClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO clinica (nome, endereco) VALUES (\""+clinicaTable.GetNome()+"\", \""+clinicaTable.GetEndereco()+"\");");
    }
    
    public boolean RemoverClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM clinica WHERE id_clinica = \""+clinicaTable.GetIdClinica()+"\";");
    }
    
    public boolean AtualizarClinica(ClinicaTable clinicaTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE clinica SET nome=\""+clinicaTable.GetNome()+"\", endereco=\""+clinicaTable.GetEndereco()+"\" WHERE id_clinica = \""+clinicaTable.GetIdClinica()+"\";");
    }
}
