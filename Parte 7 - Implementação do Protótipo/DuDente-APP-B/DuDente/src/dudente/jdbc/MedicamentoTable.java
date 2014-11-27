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
public class MedicamentoTable {
    
    private String nome;
    private Date validade;
    private int idMedicamento;
    
    public MedicamentoTable(){        
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
        
    public void SetValidade(Date validade){
        this.validade = validade;
    }
            
    public void SetIdMedicamento(int idMedicamento){
        this.idMedicamento = idMedicamento;
    }
    
    public String GetNome(){
        return this.nome;
    }
        
    public Date GetValidade(){
        return this.validade;
    }
            
    public int GetIdMedicamento(){
        return this.idMedicamento;
    }
}
