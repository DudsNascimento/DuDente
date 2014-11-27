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
public class PlanoTable {
    
    private String nome;
    private Date validade;
    private int idPlano;
    
    public PlanoTable(){        
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetValidade(Date validade){
        this.validade = validade;
    }
            
    public void SetIdPlano(int idPlano){
        this.idPlano = idPlano;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public Date GetValidade(){
        return this.validade;
    }
            
    public int GetIdPlano(){
        return this.idPlano;
    }
}
