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
public class ClinicaTable {
    
    private String nome;
    private String endereco;
    private int idClinica;
    
    public ClinicaTable(){        
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetEndereco(String endereco){
        this.endereco = endereco;
    }
            
    public void SetIdClinica(int idClinica){
        this.idClinica = idClinica;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public String GetEndereco(){
        return this.endereco;
    }
            
    public int GetIdClinica(){
        return this.idClinica;
    }
}
