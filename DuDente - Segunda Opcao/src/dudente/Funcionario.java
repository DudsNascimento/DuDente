/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

/**
 *
 * @author Igor Couto
 */
public class Funcionario {
    private String nome;
    private String salario;
    private String cargo;
    
    public Funcionario(String nome, String salario){
        this.nome = nome;
        this.salario = salario;
    }
    
    public Funcionario(){
        
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    void setCargo(String cargo) {
        this.cargo = cargo;
    }

    void setSalario(String salario) {
        this.salario = salario;
    }
    
    String getNome(){
        return this.nome;
    }

    String getCargo(){
        return this.cargo;
    }

    String getSalario() {
        return this.salario;
    }
}
