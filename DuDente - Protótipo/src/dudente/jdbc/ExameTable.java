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
public class ExameTable {

    private Date data;
    private String descricao;
    private String observacao;
    private String resultado;
    private int idExame;
    private int idDentista;
    private int idPaciente;
    
    public ExameTable(){        
    }
    
    public void SetData(Date data){
        
        this.data = data;
    }
    
    public void SetDescricao(String descricao){
        
        this.descricao = descricao;
    }
    
    public void SetObservacao(String observacao){
        
        this.observacao = observacao;
    }
    
    public void SetResultado(String resultado){
        
        this.resultado = resultado;
    }
    
    public void SetIdExame(int idExame){
        
        this.idExame = idExame;
    }
    
    public void SetIdDentista(int idDentista){
        
        this.idDentista = idDentista;
    }
    
    public void SetIdPaciente(int idPaciente){
        
        this.idPaciente = idPaciente;
    }
    
    public Date GetData(){
        
        return data;
    }
    
    public String GetDescricao(){
        
        return descricao;
    }
    
    public String GetObservacao(){
        
        return observacao;
    }
    
    public String GetResultado(){
        
        return resultado;
    }
    
    public int GetIdExame(){
        
        return idExame;
    }
    
    public int GetIdDentista(){
        
        return idDentista;
    }
    
    public int GetIdPaciente(){
        
        return idPaciente;
    }
}
