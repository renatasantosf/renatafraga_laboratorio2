package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Consulta {

    private static int CODIGO_GERADO = 1;
    private int codigo;
    private Date horario;
    private Paciente paciente;
    private String prontuario;
    private List<ItemReceituario> receituario;
    private boolean finalizada;

    public Consulta(Paciente p, String prontuario) {
        this.codigo = generateCodigo();        
        this.horario = new Date();
        this.paciente = p;
        this.prontuario = prontuario;
        this.receituario = new ArrayList<>();
        this.finalizada = false;
    }

    public Consulta(Date h, Paciente p, String prontuario) {
        this.codigo = generateCodigo();
        this.horario = h;
        this.paciente = p;
        this.prontuario = prontuario;
        this.receituario = new ArrayList<>();
        this.finalizada = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getProntuario() {
        return prontuario;
    }
    
    public Date getHorario() {
        return horario;
    }

    public List<ItemReceituario> getReceituario() {
        return receituario;
    }

    public void adicionarReceita(ItemReceituario r) {
        receituario.add(r);
    }

    public ItemReceituario removeReceita(int posicao) {
        return (receituario.remove(posicao));
    }
    
    public boolean temMedicamentoNoReceituario(Medicamento m){
        for(ItemReceituario receita: receituario)
        {
            if(receita.getMedicamento().getCodigo()==m.getCodigo())
                return true;
        }
        return false;
    }

    public int quantidadeReceitas() {
        return (receituario.size());
    }

    public boolean estaFinalizada() {
        return finalizada;
    }

    public void finalizar() {
        finalizada = true;
    }

    private int generateCodigo() {
        return (CODIGO_GERADO++);
    }

}
