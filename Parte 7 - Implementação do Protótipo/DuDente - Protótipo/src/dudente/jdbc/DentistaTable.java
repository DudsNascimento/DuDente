/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

/**
 *
 * @author duds-ufjf
 */
public class DentistaTable {
    
    private String especializacao;
    private String nome;
    private float salario;
    private int idDentista;
    
    public DentistaTable(){        
    }
    
    public void SetEspecializacao(String especializacao){
        this.especializacao = especializacao;
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetSalario(float salario){
        this.salario = salario;
    }
            
    public void SetIdDentista(int idDentista){
        this.idDentista = idDentista;
    }
    
    public String GetEspecializacao(){
        return this.especializacao;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public float GetSalario(){
        return this.salario;
    }
            
    public int GetIdDentista(){
        return this.idDentista;
    }
}
