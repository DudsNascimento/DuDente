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
public class ConsultaTable {

    private Date data;
    private String descricao;
    private String observacao;
    private String diagnostico;
    private int idConsulta;
    private int idDentista;
    private int idPaciente;
    
    public ConsultaTable(){        
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
    
    public void SetDiagnostico(String diagnostico){
        
        this.diagnostico = diagnostico;
    }
    
    public void SetIdConsulta(int idConsulta){
        
        this.idConsulta = idConsulta;
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
    
    public String GetDiagnostico(){
        
        return diagnostico;
    }
    
    public int GetIdConsulta(){
        
        return idConsulta;
    }
    
    public int GetIdDentista(){
        
        return idDentista;
    }
    
    public int GetIdPaciente(){
        
        return idPaciente;
    }
}
