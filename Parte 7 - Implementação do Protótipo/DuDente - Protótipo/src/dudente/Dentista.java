package dudente;

/**
 *
 * @author Igor Couto
 */
public class Dentista extends Funcionario{
    
    String especialidade;
    
    public Dentista(String nome, String salario){
        super(nome, salario);
    }
    
    public Dentista(String nome, String salario, String especialidade){
        super(nome, salario);
        this.especialidade = especialidade;
    }
}
