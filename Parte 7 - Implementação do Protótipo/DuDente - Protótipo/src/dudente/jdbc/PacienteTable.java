/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

/**
 *
 * @author duds-ufjf
 */
public class PacienteTable {
    
    private String nome;
    private int idade;
    private int idPlano;
    private int idPaciente;
    
    public PacienteTable(){        
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetIdade(int idade){
        this.idade = idade;
    }
            
    public void SetIdPlano(int idPlano){
        this.idPlano = idPlano;
    }
    
    public void SetIdPaciente(int idPaciente){
        this.idPaciente = idPaciente;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public int GetIdade(){
        return this.idade;
    }
            
    public int GetIdPlano(){
        return this.idPlano;
    }
    
    public int GetIdPaciente(){
        return this.idPaciente;
    }

}
