/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

import java.sql.Date;

/**
 *
 * @author duds-ufjf
 */
public class CompraMedicamentoTable {
    
    private Date data;
    private float valor;
    private int idCompra;
    private int idMedicamento;
    
    public CompraMedicamentoTable(){        
    }

    public void SetData(Date data){
        
        this.data = data;
    }
    
    public void SetValor(float valor){
        
        this.valor = valor;
    }
    
    public void SetIdCompra(int idCompra){

        this.idCompra = idCompra;
    }
    
    public void SetIdMedicamento(int idMedicamento){
        
        this.idMedicamento = idMedicamento;
    }

    public Date GetData(){
        
        return this.data;
    }
    
    public float GetValor(){
        
        return this.valor;
    }
    
    public int GetIdCompra(){

        return this.idCompra;
    }
    
    public int GetIdMedicamento(){
        
        return this.idMedicamento;
    }
}
