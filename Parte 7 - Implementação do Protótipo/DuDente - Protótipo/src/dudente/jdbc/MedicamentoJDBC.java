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
public class MedicamentoJDBC extends SQLConnection{

    public MedicamentoJDBC(){
    }
    
    public boolean ProcurarMedicamento(MedicamentoTable medicamentoTable) throws SQLException{

        if(this.ExecutarQuery("SELECT * FROM medicamento WHERE id_medicamento = "+medicamentoTable.GetIdMedicamento()+";")){
        
            if(this.resultSet.next()){

                medicamentoTable.SetNome(this.resultSet.getString("nome"));
                medicamentoTable.SetValidade(this.resultSet.getDate("validade"));
                medicamentoTable.SetIdMedicamento(this.resultSet.getInt("id_medicamento"));

            }    
            return true;
        }
        return false;
    }
    
    public ArrayList <MedicamentoTable> ListarMedicamentos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM medicamento;")){
        
            ArrayList <MedicamentoTable> listaMedicamentos = new ArrayList <MedicamentoTable>();
            while(this.resultSet.next()){

                MedicamentoTable medicamentoIndex = new MedicamentoTable(); 
                medicamentoIndex.SetNome(this.resultSet.getString("nome"));
                medicamentoIndex.SetValidade(this.resultSet.getDate("validade"));
                medicamentoIndex.SetIdMedicamento(this.resultSet.getInt("id_medicamento"));

                listaMedicamentos.add(medicamentoIndex);
            }    
            return listaMedicamentos;
        }
        return null;
    }
    
    public boolean AdicionarMedicamento(MedicamentoTable medicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO medicamento (nome, validade) VALUES (\""+medicamentoTable.GetNome()+"\", \""+medicamentoTable.GetValidade()+"\");");
    }
    
    public boolean RemoverMedicamento(MedicamentoTable medicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM medicamento WHERE id_medicamento = \""+medicamentoTable.GetIdMedicamento()+"\";");
    }
    
    public boolean AtualizarMedicamento(MedicamentoTable medicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE medicamento SET nome=\""+medicamentoTable.GetNome()+"\", validade=\""+medicamentoTable.GetValidade()+"\" WHERE id_medicamento = \""+medicamentoTable.GetIdMedicamento()+"\";");
    }
}
