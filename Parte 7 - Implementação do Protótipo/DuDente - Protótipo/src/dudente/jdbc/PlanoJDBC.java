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
public class PlanoJDBC extends SQLConnection{

    public PlanoJDBC(){
    }
    
    public boolean ProcurarPlano(PlanoTable planoTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM plano WHERE id_plano = "+planoTable.GetIdPlano()+";")){
        
            if(this.resultSet.next()){

                planoTable.SetNome(this.resultSet.getString("nome"));
                planoTable.SetValidade(this.resultSet.getDate("validade"));
                planoTable.SetIdPlano(this.resultSet.getInt("id_plano"));

            }    
            return true;
        }
        return false;
    }
    
    public ArrayList <PlanoTable> ListarPlanos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM plano;")){
        
            ArrayList <PlanoTable> listaPlanos = new ArrayList <PlanoTable>();
            while(this.resultSet.next()){

                PlanoTable planoIndex = new PlanoTable(); 
                planoIndex.SetNome(this.resultSet.getString("nome"));
                planoIndex.SetValidade(this.resultSet.getDate("validade"));
                planoIndex.SetIdPlano(this.resultSet.getInt("id_plano"));

                listaPlanos.add(planoIndex);
            }    
            return listaPlanos;
        }
        return null;
    }
    
    public boolean AdicionarPlano(PlanoTable planoTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO plano (nome, validade) VALUES (\""+planoTable.GetNome()+"\", \""+planoTable.GetValidade()+"\");");
    }
    
    public boolean RemoverPlano(PlanoTable planoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM plano WHERE id_plano = \""+planoTable.GetIdPlano()+"\";");
    }
    
    public boolean AtualizarPlano(PlanoTable planoTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE plano SET nome=\""+planoTable.GetNome()+"\", validade=\""+planoTable.GetValidade()+"\" WHERE id_plano = \""+planoTable.GetIdPlano()+"\";");
    }
}
