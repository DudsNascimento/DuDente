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
public class CirurgiaJDBC extends SQLConnection{

    public CirurgiaJDBC(){
    }
    
    public boolean ProcurarCirurgia(CirurgiaTable cirurgiaTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM cirurgia WHERE id_cirurgia = "+cirurgiaTable.GetIdCirurgia()+";")){
        
            if(this.resultSet.next()){

                cirurgiaTable.SetData(this.resultSet.getDate("data"));
                cirurgiaTable.SetDescricao(this.resultSet.getString("descricao"));
                cirurgiaTable.SetObservacao(this.resultSet.getString("observacao"));
                cirurgiaTable.SetTipo(this.resultSet.getString("tipo"));
                cirurgiaTable.SetIdCirurgia(this.resultSet.getInt("id_cirurgia"));
                cirurgiaTable.SetIdDentista(this.resultSet.getInt("id_dentista"));
                cirurgiaTable.SetIdPaciente(this.resultSet.getInt("id_paciente"));
            }    
            return true;
        }
        return false;
    }
        
    public ArrayList <CirurgiaTable> ListarCirurgias() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM cirurgia;")){
        
            ArrayList <CirurgiaTable> listaCirurgias = new ArrayList <CirurgiaTable>();
            while(this.resultSet.next()){

                CirurgiaTable cirurgiaIndex = new CirurgiaTable(); 
                cirurgiaIndex.SetData(this.resultSet.getDate("data"));
                cirurgiaIndex.SetDescricao(this.resultSet.getString("descricao"));
                cirurgiaIndex.SetObservacao(this.resultSet.getString("observacao"));
                cirurgiaIndex.SetTipo(this.resultSet.getString("tipo"));
                cirurgiaIndex.SetIdCirurgia(this.resultSet.getInt("id_cirurgia"));
                cirurgiaIndex.SetIdDentista(this.resultSet.getInt("id_dentista"));
                cirurgiaIndex.SetIdPaciente(this.resultSet.getInt("id_paciente"));

                listaCirurgias.add(cirurgiaIndex);
            }    
            return listaCirurgias;
        }
        return null;
    }
    
    public boolean AdicionarCirurgia(CirurgiaTable cirurgiaTable) throws SQLException{
            
        return this.ExecutarUpdate("INSERT INTO cirurgia (data, descricao, observacao, tipo, id_dentista, id_paciente) VALUES (\""+cirurgiaTable.GetData()+"\", \""+cirurgiaTable.GetDescricao()+"\", \""+cirurgiaTable.GetObservacao()+"\", \""+cirurgiaTable.GetTipo()+"\", \""+cirurgiaTable.GetIdDentista()+"\", \""+cirurgiaTable.GetIdPaciente()+"\");");
    }
    
    public boolean RemoverCirurgia(CirurgiaTable cirurgiaTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM cirurgia WHERE id_cirurgia = \""+cirurgiaTable.GetIdCirurgia()+"\";");
    }
    
    public boolean AtualizarCirurgia(CirurgiaTable cirurgiaTable) throws SQLException{

        return this.ExecutarUpdate("UPDATE cirurgia SET data=\""+cirurgiaTable.GetData()+"\", descricao=\""+cirurgiaTable.GetDescricao()+"\", observacao=\""+cirurgiaTable.GetObservacao()+"\", tipo=\""+cirurgiaTable.GetTipo()+"\", id_dentista=\""+cirurgiaTable.GetIdDentista()+"\", id_paciente=\""+cirurgiaTable.GetIdPaciente()+"\" WHERE id_cirurgia = \""+cirurgiaTable.GetIdCirurgia()+"\";");
    }
}
