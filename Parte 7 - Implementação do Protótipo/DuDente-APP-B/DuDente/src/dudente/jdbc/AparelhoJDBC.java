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
public class AparelhoJDBC extends SQLConnection{

    public AparelhoJDBC(){
    }
    
    public boolean ProcurarAparelho(AparelhoTable aparelhoTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM aparelho WHERE id_aparelho = "+aparelhoTable.GetIdAparelho()+";")){
        
            if(this.resultSet.next()){

                aparelhoTable.SetNome(this.resultSet.getString("nome"));
                aparelhoTable.SetUltimaManutencao(this.resultSet.getDate("ultima_manutencao"));
                aparelhoTable.SetIdAparelho(this.resultSet.getInt("id_aparelho"));
            }    
            return true;
        }
        return false;
    }
    
    public ArrayList <AparelhoTable> ListarAparelhos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM aparelho;")){
        
            ArrayList <AparelhoTable> listaAparelhos = new ArrayList <AparelhoTable>();
            while(this.resultSet.next()){

                AparelhoTable aparelhoIndex = new AparelhoTable(); 
                aparelhoIndex.SetNome(this.resultSet.getString("nome"));
                aparelhoIndex.SetUltimaManutencao(this.resultSet.getDate("ultima_manutencao"));
                aparelhoIndex.SetIdAparelho(this.resultSet.getInt("id_aparelho"));

                listaAparelhos.add(aparelhoIndex);
            }    
            return listaAparelhos;
        }
        return null;
    }
    
    public boolean AdicionarAparelho(AparelhoTable aparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO aparelho (nome, ultima_manutencao) VALUES (\""+aparelhoTable.GetNome()+"\", \""+aparelhoTable.GetUltimaManutencao()+"\");");
    }
    
    public boolean RemoverAparelho(AparelhoTable aparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM aparelho WHERE id_aparelho = \""+aparelhoTable.GetIdAparelho()+"\";");
    }
    
    public boolean AtualizarAparelho(AparelhoTable aparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE aparelho SET nome=\""+aparelhoTable.GetNome()+"\", ultima_manutencao=\""+aparelhoTable.GetUltimaManutencao()+"\" WHERE id_aparelho = \""+aparelhoTable.GetIdAparelho()+"\";");
    }
}
