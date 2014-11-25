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
public class PacienteJDBC extends SQLConnection{

    public PacienteJDBC(){
    }
    
    ArrayList <PacienteTable> ListarPacientes() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM paciente;")){
        
            ArrayList <PacienteTable> listaPacientes = new ArrayList <PacienteTable>();
            while(this.resultSet.next()){

                PacienteTable pacienteIndex = new PacienteTable(); 
                pacienteIndex.SetNome(this.resultSet.getString("nome"));
                pacienteIndex.SetIdade(this.resultSet.getInt("idade"));
                pacienteIndex.SetIdPlano(this.resultSet.getInt("id_plano"));
                pacienteIndex.SetIdPaciente(this.resultSet.getInt("id_paciente"));

                listaPacientes.add(pacienteIndex);
            }    
            return listaPacientes;
        }
        return null;
    }
    
    boolean AdicionarPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO paciente (nome, idade, id_plano) VALUES (\""+pacienteTable.GetNome()+"\", \""+pacienteTable.GetIdade()+"\", \""+pacienteTable.GetIdPlano()+"\");");
    }
    
    boolean RemoverPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM paciente WHERE id_paciente = \""+pacienteTable.GetIdPaciente()+"\";");
    }
    
    boolean AtualizarPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE paciente SET nome=\""+pacienteTable.GetNome()+"\", idade=\""+pacienteTable.GetIdade()+"\", id_plano=\""+pacienteTable.GetIdPlano()+"\" WHERE id_paciente = \""+pacienteTable.GetIdPaciente()+"\";");
    }
}
