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
    
    public boolean ProcurarPaciente(PacienteTable pacienteTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM paciente WHERE id_paciente = "+pacienteTable.GetIdPaciente()+";")){
        
            if(this.resultSet.next()){

                pacienteTable.SetNome(this.resultSet.getString("nome"));
                pacienteTable.SetIdade(this.resultSet.getInt("idade"));
                pacienteTable.SetIdPlano(this.resultSet.getInt("id_plano"));
                pacienteTable.SetIdPaciente(this.resultSet.getInt("id_paciente"));

            }    
            return true;
        }
        return false;
    }
    
    public ArrayList <PacienteTable> ListarPacientes() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM paciente;")){
        
            ArrayList <PacienteTable> listaPacientes = new ArrayList <PacienteTable>();
            while(this.resultSet.next()){

                PacienteTable pacienteTable = new PacienteTable(); 
                pacienteTable.SetNome(this.resultSet.getString("nome"));
                pacienteTable.SetIdade(this.resultSet.getInt("idade"));
                pacienteTable.SetIdPlano(this.resultSet.getInt("id_plano"));
                pacienteTable.SetIdPaciente(this.resultSet.getInt("id_paciente"));

                listaPacientes.add(pacienteTable);
            }    
            return listaPacientes;
        }
        return null;
    }
    
    public boolean AdicionarPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO paciente (nome, idade, id_plano) VALUES (\""+pacienteTable.GetNome()+"\", \""+pacienteTable.GetIdade()+"\", \""+pacienteTable.GetIdPlano()+"\");");
    }
    
    public boolean RemoverPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM paciente WHERE id_paciente = \""+pacienteTable.GetIdPaciente()+"\";");
    }
    
    public boolean AtualizarPaciente(PacienteTable pacienteTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE paciente SET nome=\""+pacienteTable.GetNome()+"\", idade=\""+pacienteTable.GetIdade()+"\", id_plano=\""+pacienteTable.GetIdPlano()+"\" WHERE id_paciente = \""+pacienteTable.GetIdPaciente()+"\";");
    }
}
