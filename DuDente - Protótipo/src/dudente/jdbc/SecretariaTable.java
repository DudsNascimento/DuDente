/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

/**
 *
 * @author duds-ufjf
 */
public class SecretariaTable {
    
    private float bonificacao;
    private String nome;
    private float salario;
    private int idSecretaria;
    
    public SecretariaTable(){        
    }
    
    public void SetBonificacao(float bonificacao){
        this.bonificacao = bonificacao;
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetSalario(float salario){
        this.salario = salario;
    }
            
    public void SetIdSecretaria(int idSecretaria){
        this.idSecretaria = idSecretaria;
    }
    
    public float GetBonificacao(){
        return this.bonificacao;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public float GetSalario(){
        return this.salario;
    }
            
    public int GetIdSecretaria(){
        return this.idSecretaria;
    }
}
