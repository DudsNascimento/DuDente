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
    
    public boolean ProcurarDentista(DentistaTable dentistaTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM dentista WHERE id_dentista = "+dentistaTable.GetIdDentista()+";")){
        
            if(this.resultSet.next()){

                dentistaTable.SetEspecializacao(this.resultSet.getString("especializacao"));
                dentistaTable.SetNome(this.resultSet.getString("nome"));
                dentistaTable.SetSalario(this.resultSet.getFloat("salario"));
                dentistaTable.SetIdDentista(this.resultSet.getInt("id_dentista"));

            }    
            return true;
        }
        return false;
    }

    public ArrayList <DentistaTable> ListarDentistas() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM dentista;")){
        
            ArrayList <DentistaTable> listaDentistas = new ArrayList <DentistaTable>();
            while(this.resultSet.next()){

                DentistaTable dentistaTable = new DentistaTable(); 
                dentistaTable.SetEspecializacao(this.resultSet.getString("especializacao"));
                dentistaTable.SetNome(this.resultSet.getString("nome"));
                dentistaTable.SetSalario(this.resultSet.getFloat("salario"));
                dentistaTable.SetIdDentista(this.resultSet.getInt("id_dentista"));

                listaDentistas.add(dentistaTable);
            }    
            return listaDentistas;
        }
        return null;
    }
    
    public boolean AdicionarDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO dentista (especializacao, nome, salario) VALUES (\""+dentistaTable.GetEspecializacao()+"\", \""+dentistaTable.GetNome()+"\", \""+dentistaTable.GetSalario()+"\");");
    }
    
    public boolean RemoverDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM dentista WHERE id_dentista = \""+dentistaTable.GetIdDentista()+"\";");
    }
    
    public boolean AtualizarDentista(DentistaTable dentistaTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE dentista SET especializacao=\""+dentistaTable.GetEspecializacao()+"\", nome=\""+dentistaTable.GetNome()+"\", salario=\""+dentistaTable.GetSalario()+"\" WHERE id_dentista = \""+dentistaTable.GetIdDentista()+"\";");
    }
}
