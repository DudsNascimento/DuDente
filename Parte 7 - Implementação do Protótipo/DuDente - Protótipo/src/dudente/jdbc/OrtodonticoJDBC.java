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
public class OrtodonticoJDBC extends SQLConnection{

    public OrtodonticoJDBC(){
    }
    
    public boolean ProcurarOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM ortodontico WHERE id_ortodontico = "+ortodonticoTable.GetIdOrtodontico()+";")){
        
            if(this.resultSet.next()){

                ortodonticoTable.SetData(this.resultSet.getDate("data"));
                ortodonticoTable.SetDescricao(this.resultSet.getString("descricao"));
                ortodonticoTable.SetObservacao(this.resultSet.getString("observacao"));
                ortodonticoTable.SetTipo(this.resultSet.getString("tipo"));
                ortodonticoTable.SetIdOrtodontico(this.resultSet.getInt("id_ortodontico"));
                ortodonticoTable.SetIdDentista(this.resultSet.getInt("id_dentista"));
                ortodonticoTable.SetIdOrtodontico(this.resultSet.getInt("id_paciente"));

            }    
            return true;
        }
        return false;
    }

    public ArrayList <OrtodonticoTable> ListarOrtodonticos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM ortodontico;")){
        
            ArrayList <OrtodonticoTable> listaOrtodonticos = new ArrayList <OrtodonticoTable>();
            while(this.resultSet.next()){

                OrtodonticoTable ortodonticoTable = new OrtodonticoTable(); 
                ortodonticoTable.SetData(this.resultSet.getDate("data"));
                ortodonticoTable.SetDescricao(this.resultSet.getString("descricao"));
                ortodonticoTable.SetObservacao(this.resultSet.getString("observacao"));
                ortodonticoTable.SetTipo(this.resultSet.getString("tipo"));
                ortodonticoTable.SetIdOrtodontico(this.resultSet.getInt("id_ortodontico"));
                ortodonticoTable.SetIdDentista(this.resultSet.getInt("id_dentista"));
                ortodonticoTable.SetIdOrtodontico(this.resultSet.getInt("id_paciente"));

                listaOrtodonticos.add(ortodonticoTable);
            }    
            return listaOrtodonticos;
        }
        return null;
    }
    
    public boolean AdicionarOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{
            
        return this.ExecutarUpdate("INSERT INTO ortodontico (data, descricao, observacao, tipo, id_dentista, id_paciente) VALUES (\""+ortodonticoTable.GetData()+"\", \""+ortodonticoTable.GetDescricao()+"\", \""+ortodonticoTable.GetObservacao()+"\", \""+ortodonticoTable.GetTipo()+"\", \""+ortodonticoTable.GetIdDentista()+"\", \""+ortodonticoTable.GetIdPaciente()+"\");");
    }
    
    public boolean RemoverOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM ortodontico WHERE id_ortodontico = \""+ortodonticoTable.GetIdOrtodontico()+"\";");
    }
    
    public boolean AtualizarOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{

        return this.ExecutarUpdate("UPDATE ortodontico SET data=\""+ortodonticoTable.GetData()+"\", descricao=\""+ortodonticoTable.GetDescricao()+"\", observacao=\""+ortodonticoTable.GetObservacao()+"\", tipo=\""+ortodonticoTable.GetTipo()+"\", id_dentista=\""+ortodonticoTable.GetIdDentista()+"\", id_paciente=\""+ortodonticoTable.GetIdPaciente()+"\" WHERE id_ortodontico = \""+ortodonticoTable.GetIdOrtodontico()+"\";");
    }
}
