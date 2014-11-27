/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import java.util.Date;

/**
 *
 * @author Davi
 */
public class Planos {
    private String nome;
    private Date data; 

public Planos(String nome, Date data){
    this.nome = nome;
    this.data = data;
}
    
public Planos(){
        
}

    void setNome(String nome) {
        this.nome = nome;
    }

    void setData(Date data) {
        this.data = data;
    }

    
    String getNome(){
        return this.nome;
    }

    Date getCargo(){
        return this.data;
    }


}