/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.sql.Date;

/**
 *
 * @author Davi
 */
public class Aparelho{
    
    private String nome;
    private Date ultimaManutencao;
    
    public Aparelho(Date date, String nome){
        this.nome = nome;
        this.ultimaManutencao = date;
        
    }
    
    public Aparelho(){
        
    }
    
    void setNome(String nome){
        this.nome = nome;
    }
    
    void setUltimaManutencao(Date date){
        this.ultimaManutencao = date;
    }
    
    String getNome(){
        return this.nome;
    }
    Date getUltimaManutencao(){
        return this.ultimaManutencao;
    }
}
