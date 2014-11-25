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
    
    ArrayList <OrtodonticoTable> ListarOrtodonticos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM ortodontico;")){
        
            ArrayList <OrtodonticoTable> listaOrtodonticos = new ArrayList <OrtodonticoTable>();
            while(this.resultSet.next()){

                OrtodonticoTable ortodonticoIndex = new OrtodonticoTable(); 
                ortodonticoIndex.SetData(this.resultSet.getDate("data"));
                ortodonticoIndex.SetDescricao(this.resultSet.getString("descricao"));
                ortodonticoIndex.SetObservacao(this.resultSet.getString("observacao"));
                ortodonticoIndex.SetTipo(this.resultSet.getString("tipo"));
                ortodonticoIndex.SetIdOrtodontico(this.resultSet.getInt("id_ortodontico"));
                ortodonticoIndex.SetIdDentista(this.resultSet.getInt("id_dentista"));
                ortodonticoIndex.SetIdOrtodontico(this.resultSet.getInt("id_paciente"));

                listaOrtodonticos.add(ortodonticoIndex);
            }    
            return listaOrtodonticos;
        }
        return null;
    }
    
    boolean AdicionarOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{
            
        return this.ExecutarUpdate("INSERT INTO ortodontico (data, descricao, observacao, tipo, id_dentista, id_paciente) VALUES (\""+ortodonticoTable.GetData()+"\", \""+ortodonticoTable.GetDescricao()+"\", \""+ortodonticoTable.GetObservacao()+"\", \""+ortodonticoTable.GetTipo()+"\", \""+ortodonticoTable.GetIdDentista()+"\", \""+ortodonticoTable.GetIdPaciente()+"\");");
    }
    
    boolean RemoverOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM ortodontico WHERE id_ortodontico = \""+ortodonticoTable.GetIdOrtodontico()+"\";");
    }
    
    boolean AtualizarOrtodontico(OrtodonticoTable ortodonticoTable) throws SQLException{

        return this.ExecutarUpdate("UPDATE ortodontico SET data=\""+ortodonticoTable.GetData()+"\", descricao=\""+ortodonticoTable.GetDescricao()+"\", observacao=\""+ortodonticoTable.GetObservacao()+"\", tipo=\""+ortodonticoTable.GetTipo()+"\", id_dentista=\""+ortodonticoTable.GetIdDentista()+"\", id_paciente=\""+ortodonticoTable.GetIdPaciente()+"\" WHERE id_ortodontico = \""+ortodonticoTable.GetIdOrtodontico()+"\";");
    }
}
