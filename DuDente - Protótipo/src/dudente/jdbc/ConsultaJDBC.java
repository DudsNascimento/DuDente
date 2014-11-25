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
public class ConsultaJDBC extends SQLConnection{

    public ConsultaJDBC(){
    }
    
    ArrayList <ConsultaTable> ListarConsultas() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM consulta;")){
        
            ArrayList <ConsultaTable> listaConsultas = new ArrayList <ConsultaTable>();
            while(this.resultSet.next()){

                ConsultaTable consultaIndex = new ConsultaTable(); 
                consultaIndex.SetData(this.resultSet.getDate("data"));
                consultaIndex.SetDescricao(this.resultSet.getString("descricao"));
                consultaIndex.SetObservacao(this.resultSet.getString("observacao"));
                consultaIndex.SetDiagnostico(this.resultSet.getString("diagnostico"));
                consultaIndex.SetIdConsulta(this.resultSet.getInt("id_consulta"));
                consultaIndex.SetIdDentista(this.resultSet.getInt("id_dentista"));
                consultaIndex.SetIdPaciente(this.resultSet.getInt("id_paciente"));

                listaConsultas.add(consultaIndex);
            }    
            return listaConsultas;
        }
        return null;
    }
    
    boolean AdicionarConsulta(ConsultaTable consultaTable) throws SQLException{
            
        return this.ExecutarUpdate("INSERT INTO consulta (data, descricao, observacao, diagnostico, id_dentista, id_paciente) VALUES (\""+consultaTable.GetData()+"\", \""+consultaTable.GetDescricao()+"\", \""+consultaTable.GetObservacao()+"\", \""+consultaTable.GetDiagnostico()+"\", \""+consultaTable.GetIdDentista()+"\", \""+consultaTable.GetIdPaciente()+"\");");
    }
    
    boolean RemoverConsulta(ConsultaTable consultaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM consulta WHERE id_consulta = \""+consultaTable.GetIdConsulta()+"\";");
    }
    
    boolean AtualizarConsulta(ConsultaTable consultaTable) throws SQLException{

        return this.ExecutarUpdate("UPDATE consulta SET data=\""+consultaTable.GetData()+"\", descricao=\""+consultaTable.GetDescricao()+"\", observacao=\""+consultaTable.GetObservacao()+"\", diagnostico=\""+consultaTable.GetDiagnostico()+"\", id_dentista=\""+consultaTable.GetIdDentista()+"\", id_paciente=\""+consultaTable.GetIdPaciente()+"\" WHERE id_consulta = \""+consultaTable.GetIdConsulta()+"\";");
    }
}
