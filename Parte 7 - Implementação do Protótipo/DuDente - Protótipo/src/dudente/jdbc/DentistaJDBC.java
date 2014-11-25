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
public class DentistaJDBC extends SQLConnection{

    public DentistaJDBC(){
    }
    
    ArrayList <DentistaTable> ListarDentistas() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM dentista;")){
        
            ArrayList <DentistaTable> listaDentistas = new ArrayList <DentistaTable>();
            while(this.resultSet.next()){

                DentistaTable dentistaIndex = new DentistaTable(); 
                dentistaIndex.SetEspecializacao(this.resultSet.getString("especializacao"));
                dentistaIndex.SetNome(this.resultSet.getString("nome"));
                dentistaIndex.SetSalario(this.resultSet.getFloat("salario"));
                dentistaIndex.SetIdDentista(this.resultSet.getInt("id_dentista"));

                listaDentistas.add(dentistaIndex);
            }    
            return listaDentistas;
        }
        return null;
    }
    
    boolean AdicionarDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO dentista (especializacao, nome, salario) VALUES (\""+dentistaTable.GetEspecializacao()+"\", \""+dentistaTable.GetNome()+"\", \""+dentistaTable.GetSalario()+"\");");
    }
    
    boolean RemoverDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM dentista WHERE id_dentista = \""+dentistaTable.GetIdDentista()+"\";");
    }
    
    boolean AtualizarDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE dentista SET especializacao=\""+dentistaTable.GetEspecializacao()+"\", nome=\""+dentistaTable.GetNome()+"\", salario=\""+dentistaTable.GetSalario()+"\", WHERE id_dentista = \""+dentistaTable.GetIdDentista()+"\";");
    }
}
