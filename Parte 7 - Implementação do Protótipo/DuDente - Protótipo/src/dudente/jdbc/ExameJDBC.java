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
public class ExameJDBC extends SQLConnection{

    public ExameJDBC(){
    }
   
    public boolean ProcurarExame(ExameTable exameTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM exame WHERE id_exame = "+exameTable.GetIdExame()+";")){
        
            if(this.resultSet.next()){

                exameTable.SetData(this.resultSet.getDate("data"));
                exameTable.SetDescricao(this.resultSet.getString("descricao"));
                exameTable.SetObservacao(this.resultSet.getString("observacao"));
                exameTable.SetResultado(this.resultSet.getString("resultado"));
                exameTable.SetIdExame(this.resultSet.getInt("id_exame"));
                exameTable.SetIdExame(this.resultSet.getInt("id_exame"));
                exameTable.SetIdExame(this.resultSet.getInt("id_paciente"));

            }    
            return true;
        }
        return false;
    }

    public ArrayList <ExameTable> ListarExames() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM exame;")){
        
            ArrayList <ExameTable> listaExames = new ArrayList <ExameTable>();
            while(this.resultSet.next()){

                ExameTable exameIndex = new ExameTable(); 
                exameIndex.SetData(this.resultSet.getDate("data"));
                exameIndex.SetDescricao(this.resultSet.getString("descricao"));
                exameIndex.SetObservacao(this.resultSet.getString("observacao"));
                exameIndex.SetResultado(this.resultSet.getString("resultado"));
                exameIndex.SetIdExame(this.resultSet.getInt("id_exame"));
                exameIndex.SetIdExame(this.resultSet.getInt("id_exame"));
                exameIndex.SetIdExame(this.resultSet.getInt("id_paciente"));

                listaExames.add(exameIndex);
            }    
            return listaExames;
        }
        return null;
    }
    
    public boolean AdicionarExame(ExameTable exameTable) throws SQLException{
            
        return this.ExecutarUpdate("INSERT INTO exame (data, descricao, observacao, resultado, id_exame, id_paciente) VALUES (\""+exameTable.GetData()+"\", \""+exameTable.GetDescricao()+"\", \""+exameTable.GetObservacao()+"\", \""+exameTable.GetResultado()+"\", \""+exameTable.GetIdExame()+"\", \""+exameTable.GetIdPaciente()+"\");");
    }
    
    public boolean RemoverExame(ExameTable exameTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM exame WHERE id_exame = \""+exameTable.GetIdExame()+"\";");
    }
    
    public boolean AtualizarExame(ExameTable exameTable) throws SQLException{

        return this.ExecutarUpdate("UPDATE exame SET data=\""+exameTable.GetData()+"\", descricao=\""+exameTable.GetDescricao()+"\", observacao=\""+exameTable.GetObservacao()+"\", resultado=\""+exameTable.GetResultado()+"\", id_exame=\""+exameTable.GetIdExame()+"\", id_paciente=\""+exameTable.GetIdPaciente()+"\" WHERE id_exame = \""+exameTable.GetIdExame()+"\";");
    }
}
