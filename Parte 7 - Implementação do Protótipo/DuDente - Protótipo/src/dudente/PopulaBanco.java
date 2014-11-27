/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import dudente.jdbc.AparelhoJDBC;
import dudente.jdbc.AparelhoTable;
import dudente.jdbc.CirurgiaJDBC;
import dudente.jdbc.CirurgiaTable;
import dudente.jdbc.ClinicaJDBC;
import dudente.jdbc.ClinicaTable;
import dudente.jdbc.CompraAparelhoJDBC;
import dudente.jdbc.CompraAparelhoTable;
import dudente.jdbc.CompraMedicamentoJDBC;
import dudente.jdbc.ConsultaJDBC;
import dudente.jdbc.DentistaJDBC;
import dudente.jdbc.DentistaTable;
import dudente.jdbc.ExameJDBC;
import dudente.jdbc.ExameTable;
import dudente.jdbc.MedicamentoJDBC;
import dudente.jdbc.MedicamentoTable;
import dudente.jdbc.OrtodonticoJDBC;
import dudente.jdbc.OrtodonticoTable;
import dudente.jdbc.PacienteJDBC;
import dudente.jdbc.PacienteTable;
import dudente.jdbc.PlanoJDBC;
import dudente.jdbc.PlanoTable;
import dudente.jdbc.SecretariaJDBC;
import dudente.jdbc.SecretariaTable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Couto
 */
public class PopulaBanco {

    public PopulaBanco() {
        AparelhoJDBC aparelhoJDBC = new AparelhoJDBC();
        CirurgiaJDBC cirurgiaJDBC = new CirurgiaJDBC();
        ClinicaJDBC clinicaJDBC = new ClinicaJDBC();
        CompraAparelhoJDBC compraAparelhoJDBC = new CompraAparelhoJDBC();
        CompraMedicamentoJDBC compraMedicamentoJDBC = new CompraMedicamentoJDBC();
        ConsultaJDBC consultaJDBC = new ConsultaJDBC();
        DentistaJDBC dentistaJDBC = new DentistaJDBC();
        ExameJDBC exameJDBC = new ExameJDBC();
        MedicamentoJDBC medicamentoJDBC = new MedicamentoJDBC();
        OrtodonticoJDBC ortodonticoJDBC = new OrtodonticoJDBC();
        PacienteJDBC pacienteJDBC = new PacienteJDBC();
        PlanoJDBC planoJDBC = new PlanoJDBC();
        SecretariaJDBC secretariaJDBC = new SecretariaJDBC();
        try {
            populaAparelho(aparelhoJDBC);
            populaCirurgia(cirurgiaJDBC);
            populaClinica(clinicaJDBC);
            populaCompraAparelho(compraAparelhoJDBC);
            populaCompraMedicamento(compraMedicamentoJDBC);
            populaConsultaJDBC(consultaJDBC);
            populaDentista(dentistaJDBC);
            populaExame(exameJDBC);
            populaMedicamento(medicamentoJDBC);
            populaOrtodontico(ortodonticoJDBC);
            populaPaciente(pacienteJDBC);
            populaPlano(planoJDBC);
            populaSecretaria(secretariaJDBC);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PopulaBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PopulaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void populaAparelho(AparelhoJDBC aparelhoJDBC) throws ClassNotFoundException, SQLException {
        aparelhoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        AparelhoTable ap0 = new AparelhoTable();
        ap0.SetIdAparelho(0);
        ap0.SetNome("Irrigador Oral WaterPik");
        ap0.SetUltimaManutencao(new Date(2014, 11, 1));
        aparelhoJDBC.AdicionarAparelho(ap0);

        AparelhoTable ap1 = new AparelhoTable();
        ap1.SetIdAparelho(1);
        ap1.SetNome("Unidade Suctora GP");
        ap1.SetUltimaManutencao(new Date(2014, 11, 4));
        aparelhoJDBC.AdicionarAparelho(ap1);

        AparelhoTable ap2 = new AparelhoTable();
        ap2.SetIdAparelho(2);
        ap2.SetNome("Micromotor Alta Rotação Kavo");
        ap2.SetUltimaManutencao(new Date(2014, 11, 1));
        aparelhoJDBC.AdicionarAparelho(ap2);

        AparelhoTable ap3 = new AparelhoTable();
        ap3.SetIdAparelho(3);
        ap3.SetNome("Contra-ângulo SL30 Gnatus");
        ap3.SetUltimaManutencao(new Date(2014, 11, 5));
        aparelhoJDBC.AdicionarAparelho(ap3);

        AparelhoTable ap4 = new AparelhoTable();
        ap4.SetIdAparelho(4);
        ap4.SetNome("Compressor Odontológico Silencioso Dr. EVO");
        ap4.SetUltimaManutencao(new Date(2014, 11, 12));
        aparelhoJDBC.AdicionarAparelho(ap4);

        AparelhoTable ap5 = new AparelhoTable();
        ap5.SetIdAparelho(5);
        ap5.SetNome("Motor de implante Dforce 1000");
        ap5.SetUltimaManutencao(new Date(2014, 11, 20));
        aparelhoJDBC.AdicionarAparelho(ap5);
    }

    private void populaCirurgia(CirurgiaJDBC cirurgiaJDBC) throws SQLException, ClassNotFoundException {
        cirurgiaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        CirurgiaTable c0 = new CirurgiaTable();
        c0.SetIdCirurgia(0);
        c0.SetData(new Date(2014, 11,1));
        c0.SetDescricao("");
        c0.SetIdDentista(5);
        c0.SetIdPaciente(2);
        c0.SetObservacao("");
        c0.SetTipo("");
        cirurgiaJDBC.AdicionarCirurgia(c0);

        CirurgiaTable c1 = new CirurgiaTable();
        c1.SetIdCirurgia(1);
        c1.SetData(new Date(2014, 11,18));
        c1.SetDescricao("");
        c1.SetIdDentista(5);
        c1.SetIdPaciente(0);
        c1.SetObservacao("");
        c1.SetTipo("");
        cirurgiaJDBC.AdicionarCirurgia(c1);

        CirurgiaTable c2 = new CirurgiaTable();
        c2.SetIdCirurgia(2);
        c2.SetData(new Date(2014, 11,21));
        c2.SetDescricao("");
        c2.SetIdDentista(5);
        c2.SetIdPaciente(0);
        c2.SetObservacao("");
        c2.SetTipo("");
        cirurgiaJDBC.AdicionarCirurgia(c2);

        cirurgiaJDBC.EncerrarConexao();
    }

    private void populaClinica(ClinicaJDBC clinicaJDBC) throws SQLException, ClassNotFoundException {
        clinicaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        ClinicaTable clinica = new ClinicaTable();
        clinica.SetIdClinica(0);
        clinica.SetNome("Odonto JF");
        clinica.SetEndereco("Rua Aristoteles Braga n35 Bairro São Pedro");
        clinicaJDBC.AdicionarClinica(clinica);
        clinicaJDBC.EncerrarConexao();
    }

    private void populaCompraAparelho(CompraAparelhoJDBC compraAparelhoJDBC) throws SQLException, ClassNotFoundException {
        compraAparelhoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        CompraAparelhoTable cp0 = new CompraAparelhoTable();
        cp0.SetData(new Date(2014,05,01));
        cp0.SetIdAparelho(0);
        cp0.SetIdCompra(0);
        cp0.SetValor(200f);
        compraAparelhoJDBC.AdicionarCompraAparelho(cp0);
        
        compraAparelhoJDBC.EncerrarConexao();
    }

    private void populaCompraMedicamento(CompraMedicamentoJDBC compraMedicamentoJDBC) throws SQLException, ClassNotFoundException {
        compraMedicamentoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        compraMedicamentoJDBC.EncerrarConexao();
    }

    private void populaConsultaJDBC(ConsultaJDBC consultaJDBC) throws SQLException, ClassNotFoundException {
        consultaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");
        
        consultaJDBC.EncerrarConexao();
    }

    private void populaDentista(DentistaJDBC dentistaJDBC) throws SQLException, ClassNotFoundException {
        // Especializações possiveis:
        // "Radiologia", "Dentística", "Endodontia",
        // "Periodontia", "Cirurgia Bucomaxilofacial",
        // "Próteses", "Odontopediatria", "Ortodontia "

        dentistaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        DentistaTable d0 = new DentistaTable();
        d0.SetIdDentista(0);
        d0.SetNome("João Silva");
        d0.SetEspecializacao("Radiologia");
        d0.SetSalario(1650f);
        dentistaJDBC.AdicionarDentista(d0);

        DentistaTable d1 = new DentistaTable();
        d1.SetIdDentista(1);
        d1.SetNome("João Silva");
        d1.SetEspecializacao("Dentística");
        d1.SetSalario(1550f);
        dentistaJDBC.AdicionarDentista(d1);

        DentistaTable d2 = new DentistaTable();
        d2.SetIdDentista(2);
        d2.SetNome("Paulo Andrade");
        d2.SetEspecializacao("Endodontia");
        d2.SetSalario(1700f);
        dentistaJDBC.AdicionarDentista(d2);

        DentistaTable d3 = new DentistaTable();
        d2.SetIdDentista(3);
        d2.SetNome("Marcelo Vieira");
        d2.SetEspecializacao("Periodontia");
        d2.SetSalario(1600f);
        dentistaJDBC.AdicionarDentista(d3);

        DentistaTable d4 = new DentistaTable();
        d2.SetIdDentista(4);
        d2.SetNome("Carlos Pereira");
        d2.SetEspecializacao("Ortodontia");
        d2.SetSalario(1500f);
        dentistaJDBC.AdicionarDentista(d4);

        DentistaTable d5 = new DentistaTable();
        d2.SetIdDentista(5);
        d2.SetNome("Marcia Costa");
        d2.SetEspecializacao("Cirurgia Bucomaxilofacial");
        d2.SetSalario(1900f);
        dentistaJDBC.AdicionarDentista(d5);

        dentistaJDBC.EncerrarConexao();
    }

    private void populaExame(ExameJDBC exameJDBC) throws SQLException, ClassNotFoundException {
        exameJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        ExameTable e0 = new ExameTable();
        e0.SetIdExame(0);
        e0.SetData(new Date(2014, 11, 2));
        e0.SetIdDentista(2);
        e0.SetIdPaciente(2);
        e0.SetDescricao("Rotina");
        e0.SetResultado("OK");
        e0.SetObservacao("");
        exameJDBC.AdicionarExame(e0);

        ExameTable e1 = new ExameTable();
        e1.SetIdExame(1);
        e1.SetData(new Date(2014, 11, 3));
        e1.SetIdDentista(1);
        e1.SetIdPaciente(1);
        e1.SetDescricao("Rotina");
        e1.SetResultado("OK");
        e1.SetObservacao("");
        exameJDBC.AdicionarExame(e1);

        ExameTable e2 = new ExameTable();
        e2.SetIdExame(2);
        e2.SetData(new Date(2014, 11, 5));
        e2.SetIdDentista(3);
        e2.SetIdPaciente(2);
        e2.SetDescricao("Rotina");
        e2.SetResultado("OK");
        e2.SetObservacao("");
        exameJDBC.AdicionarExame(e2);

        ExameTable e3 = new ExameTable();
        e3.SetIdExame(3);
        e3.SetData(new Date(2014, 11, 9));
        e3.SetIdDentista(4);
        e3.SetIdPaciente(0);
        e3.SetDescricao("Rotina");
        e3.SetResultado("OK");
        e3.SetObservacao("");
        exameJDBC.AdicionarExame(e3);

        exameJDBC.EncerrarConexao();
    }

    private void populaMedicamento(MedicamentoJDBC medicamentoJDBC) throws SQLException, ClassNotFoundException {
        medicamentoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        MedicamentoTable m0 = new MedicamentoTable();
        m0.SetIdMedicamento(0);
        m0.SetNome("Xilocaína");
        m0.SetValidade(new Date(2016, 5, 20));
        medicamentoJDBC.AdicionarMedicamento(m0);

        MedicamentoTable m1 = new MedicamentoTable();
        m0.SetIdMedicamento(1);
        m0.SetNome("Acido Acetil Salicilico(AS)");
        m0.SetValidade(new Date(2016, 9, 10));
        medicamentoJDBC.AdicionarMedicamento(m1);

        MedicamentoTable m2 = new MedicamentoTable();
        m0.SetIdMedicamento(2);
        m0.SetNome("Cloridrato de Tramadol ");
        m0.SetValidade(new Date(2017, 4, 10));
        medicamentoJDBC.AdicionarMedicamento(m2);

        medicamentoJDBC.EncerrarConexao();
    }

    private void populaOrtodontico(OrtodonticoJDBC ortodonticoJDBC) throws SQLException, ClassNotFoundException {
        ortodonticoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        OrtodonticoTable o0 = new OrtodonticoTable();
        o0.SetIdOrtodontico(0);
        o0.SetData(new Date(2014, 11, 02));
        o0.SetIdDentista(0);
        o0.SetIdPaciente(1);
        o0.SetDescricao("");
        o0.SetObservacao("");
        o0.SetTipo("");
        ortodonticoJDBC.AdicionarOrtodontico(o0);

        OrtodonticoTable o1 = new OrtodonticoTable();
        o1.SetIdOrtodontico(1);
        o1.SetData(new Date(2014, 11, 20));
        o1.SetIdDentista(3);
        o1.SetIdPaciente(0);
        o1.SetDescricao("");
        o1.SetObservacao("");
        o1.SetTipo("");
        ortodonticoJDBC.AdicionarOrtodontico(o1);

        OrtodonticoTable o2 = new OrtodonticoTable();
        o2.SetIdOrtodontico(2);
        o2.SetData(new Date(2014, 11, 25));
        o2.SetIdDentista(4);
        o2.SetIdPaciente(2);
        o2.SetDescricao("");
        o2.SetObservacao("");
        o2.SetTipo("");
        ortodonticoJDBC.AdicionarOrtodontico(o2);

        ortodonticoJDBC.EncerrarConexao();
    }

    private void populaPaciente(PacienteJDBC pacienteJDBC) throws SQLException, ClassNotFoundException {
        pacienteJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        PacienteTable p0 = new PacienteTable();
        p0.SetIdPaciente(0);
        p0.SetNome("Aline Carvalho");
        p0.SetIdade(18);
        p0.SetIdPlano(0);
        pacienteJDBC.AdicionarPaciente(p0);

        PacienteTable p1 = new PacienteTable();
        p0.SetIdPaciente(1);
        p0.SetNome("Bruno Castro");
        p0.SetIdade(27);
        p0.SetIdPlano(2);
        pacienteJDBC.AdicionarPaciente(p1);

        PacienteTable p2 = new PacienteTable();
        p0.SetIdPaciente(2);
        p0.SetNome("Eveline Silva");
        p0.SetIdade(39);
        p0.SetIdPlano(1);
        pacienteJDBC.AdicionarPaciente(p2);

        pacienteJDBC.EncerrarConexao();
    }

    private void populaPlano(PlanoJDBC planoJDBC) throws SQLException, ClassNotFoundException {
        planoJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        PlanoTable p0 = new PlanoTable();
        p0.SetIdPlano(0);
        p0.SetNome("Unimed");
        p0.SetValidade(new Date(2015, 5, 1));
        planoJDBC.AdicionarPlano(p0);

        PlanoTable p1 = new PlanoTable();
        p0.SetIdPlano(1);
        p0.SetNome("LiderPlan");
        p0.SetValidade(new Date(2015, 8, 1));
        planoJDBC.AdicionarPlano(p1);

        PlanoTable p2 = new PlanoTable();
        p0.SetIdPlano(2);
        p0.SetNome("Prefeitura Municipal de Juiz de Fora");
        p0.SetValidade(new Date(2015, 6, 1));
        planoJDBC.AdicionarPlano(p2);

        planoJDBC.EncerrarConexao();
    }

    private void populaSecretaria(SecretariaJDBC secretariaJDBC) throws SQLException, ClassNotFoundException {
        secretariaJDBC.IniciarConexao("jdbc:mysql://localhost/DUDENTE", "guest", "122333");

        SecretariaTable s0 = new SecretariaTable();
        s0.SetIdSecretaria(0);
        s0.SetNome("Jessica Fernandes");
        s0.SetSalario(800f);
        s0.SetBonificacao(30f);
        secretariaJDBC.AdicionarSecretaria(s0);

        SecretariaTable s1 = new SecretariaTable();
        s0.SetIdSecretaria(1);
        s0.SetNome("Patricia Motta");
        s0.SetSalario(800f);
        s0.SetBonificacao(20f);
        secretariaJDBC.AdicionarSecretaria(s1);

        secretariaJDBC.EncerrarConexao();
    }
}
