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
public class Medicamento{
    private String nome;
    private Date validade;
    
    public Medicamento(String nome, Date date){
        this.nome = nome;
        this.validade = date;        
    }
    
    public Medicamento(){
    }
    
    Date getValidade(){
    return this.validade;
    }
    
    void setValidade(Date dataDeValidade){
        this.validade = dataDeValidade;
    }
}
    
    
