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
public class CompraAparelhoJDBC extends SQLConnection{

    public CompraAparelhoJDBC(){
    }
    
    ArrayList <CompraAparelhoTable> ListarCompraAparelhos() throws SQLException{
        
        if(this.ExecutarQuery("SELECT * FROM compra_aparelho;")){
        
            ArrayList <CompraAparelhoTable> listaCompraAparelhos = new ArrayList <CompraAparelhoTable>();
            while(this.resultSet.next()){

                CompraAparelhoTable compraAparelhoIndex = new CompraAparelhoTable(); 
                compraAparelhoIndex.SetData(this.resultSet.getDate("data"));
                compraAparelhoIndex.SetValor(this.resultSet.getFloat("valor"));
                compraAparelhoIndex.SetIdCompra(this.resultSet.getInt("id_compra"));
                compraAparelhoIndex.SetIdAparelho(this.resultSet.getInt("id_aparelho"));

                listaCompraAparelhos.add(compraAparelhoIndex);
            }    
            return listaCompraAparelhos;
        }
        return null;
    }
    
    boolean AdicionarCompraAparelho(CompraAparelhoTable compraAparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("INSERT INTO compra_aparelho (data, valor, id_aparelho) VALUES (\""+compraAparelhoTable.GetData()+"\", \""+compraAparelhoTable.GetValor()+"\", \""+compraAparelhoTable.GetIdAparelho()+"\");");
    }
    
    boolean RemoverCompraAparelho(CompraAparelhoTable compraAparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("DELETE FROM compra_aparelho WHERE id_compra = \""+compraAparelhoTable.GetIdCompra()+"\";");
    }
    
    boolean AtualizarCompraAparelho(CompraAparelhoTable compraAparelhoTable) throws SQLException{
        
        return this.ExecutarUpdate("UPDATE compra_aparelho SET data=\""+compraAparelhoTable.GetData()+"\", valor=\""+compraAparelhoTable.GetValor()+"\", id_aparelho=\""+compraAparelhoTable.GetIdAparelho()+"\", WHERE id_compra = \""+compraAparelhoTable.GetIdCompra()+"\";");
    }
}
