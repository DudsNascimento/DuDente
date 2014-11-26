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
public class SecretariaJDBC extends SQLConnection{

    public SecretariaJDBC(){
    }
    
    public boolean ProcurarSecretaria(SecretariaTable secretariaTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM secretaria WHERE id_secretaria = "+secretariaTable.GetIdSecretaria()+";")){
        
            if(this.resultSet.next()){

                secretariaTable.SetBonificacao(this.resultSet.getFloat("bonificacao"));
                secretariaTable.SetNome(this.resultSet.getString("nome"));
                secretariaTable.SetSalario(this.resultSet.getFloat("salario"));
                secretariaTable.SetIdSecretaria(this.resultSet.getInt("id_secretaria"));

            }    
            return true;
        }
        return false;
    }

    public ArrayList <SecretariaTable> ListarSecretarias() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM secretaria;")){
        
            ArrayList <SecretariaTable> listaSecretarias = new ArrayList <SecretariaTable>();
            while(this.resultSet.next()){

                SecretariaTable secretariaIndex = new SecretariaTable(); 
                secretariaIndex.SetBonificacao(this.resultSet.getFloat("bonificacao"));
                secretariaIndex.SetNome(this.resultSet.getString("nome"));
                secretariaIndex.SetSalario(this.resultSet.getFloat("salario"));
                secretariaIndex.SetIdSecretaria(this.resultSet.getInt("id_secretaria"));

                listaSecretarias.add(secretariaIndex);
            }    
            return listaSecretarias;
        }
        return null;
    }
    
    public boolean AdicionarSecretaria(SecretariaTable secretariaTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO secretaria (bonificacao, nome, salario) VALUES (\""+secretariaTable.GetBonificacao()+"\", \""+secretariaTable.GetNome()+"\", \""+secretariaTable.GetSalario()+"\");");
    }
    
    public boolean RemoverSecretaria(SecretariaTable secretariaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM secretaria WHERE id_secretaria = \""+secretariaTable.GetIdSecretaria()+"\";");
    }
    
    public boolean AtualizarSecretaria(SecretariaTable secretariaTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE secretaria SET bonificacao=\""+secretariaTable.GetBonificacao()+"\", nome=\""+secretariaTable.GetNome()+"\", salario=\""+secretariaTable.GetSalario()+"\" WHERE id_secretaria = \""+secretariaTable.GetIdSecretaria()+"\";");
    }
}
