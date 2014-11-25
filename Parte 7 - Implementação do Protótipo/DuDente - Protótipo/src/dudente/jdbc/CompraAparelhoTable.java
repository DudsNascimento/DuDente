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
public class CompraAparelhoTable {
    
    private Date data;
    private float valor;
    private int idCompra;
    private int idAparelho;
    
    public CompraAparelhoTable(){        
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
    
    public void SetIdAparelho(int idAparelho){
        
        this.idAparelho = idAparelho;
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
    
    public int GetIdAparelho(){
        
        return this.idAparelho;
    }
}
