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
public class OrtodonticoTable {

    private Date data;
    private String descricao;
    private String observacao;
    private String tipo;
    private int idOrtodontico;
    private int idDentista;
    private int idPaciente;
    
    public OrtodonticoTable(){        
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
    
    public void SetTipo(String tipo){
        
        this.tipo = tipo;
    }
    
    public void SetIdOrtodontico(int idOrtodontico){
        
        this.idOrtodontico = idOrtodontico;
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
    
    public String GetTipo(){
        
        return tipo;
    }
    
    public int GetIdOrtodontico(){
        
        return idOrtodontico;
    }
    
    public int GetIdDentista(){
        
        return idDentista;
    }
    
    public int GetIdPaciente(){
        
        return idPaciente;
    }
}
