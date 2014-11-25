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
public class CompraMedicamentoJDBC extends SQLConnection{

    public CompraMedicamentoJDBC(){
    }
    
    ArrayList <CompraMedicamentoTable> ListarCompraMedicamentos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM compra_medicamento;")){
        
            ArrayList <CompraMedicamentoTable> listaCompraMedicamentos = new ArrayList <CompraMedicamentoTable>();
            while(this.resultSet.next()){

                CompraMedicamentoTable compraMedicamentoIndex = new CompraMedicamentoTable(); 
                compraMedicamentoIndex.SetData(this.resultSet.getDate("data"));
                compraMedicamentoIndex.SetValor(this.resultSet.getFloat("valor"));
                compraMedicamentoIndex.SetIdCompra(this.resultSet.getInt("id_compra"));
                compraMedicamentoIndex.SetIdMedicamento(this.resultSet.getInt("id_medicamento"));

                listaCompraMedicamentos.add(compraMedicamentoIndex);
            }    
            return listaCompraMedicamentos;
        }
        return null;
    }
    
    boolean AdicionarCompraMedicamento(CompraMedicamentoTable compraMedicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO compra_medicamento (data, valor, id_medicamento) VALUES (\""+compraMedicamentoTable.GetData()+"\", \""+compraMedicamentoTable.GetValor()+"\", \""+compraMedicamentoTable.GetIdMedicamento()+"\");");
    }
    
    boolean RemoverCompraMedicamento(CompraMedicamentoTable compraMedicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM compra_medicamento WHERE id_compra = \""+compraMedicamentoTable.GetIdCompra()+"\";");
    }
    
    boolean AtualizarCompraMedicamento(CompraMedicamentoTable compraMedicamentoTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE compra_medicamento SET data=\""+compraMedicamentoTable.GetData()+"\", valor=\""+compraMedicamentoTable.GetValor()+"\", id_medicamento=\""+compraMedicamentoTable.GetIdMedicamento()+"\", WHERE id_compra = \""+compraMedicamentoTable.GetIdCompra()+"\";");
    }
}
