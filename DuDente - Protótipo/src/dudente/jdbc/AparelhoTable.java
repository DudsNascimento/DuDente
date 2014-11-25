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
public class AparelhoTable {
    
    private String nome;
    private Date ultimaManutencao;
    private int idAparelho;
    
    public AparelhoTable(){        
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetUltimaManutencao(Date ultimaManutencao){
        this.ultimaManutencao = ultimaManutencao;
    }
            
    public void SetIdAparelho(int idAparelho){
        this.idAparelho = idAparelho;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public Date GetUltimaManutencao(){
        return this.ultimaManutencao;
    }
            
    public int GetIdAparelho(){
        return this.idAparelho;
    }
}
