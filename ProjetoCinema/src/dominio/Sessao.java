
package dominio;

import java.util.Date;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Sessao {
    private int codigo;
    private Sala sala;
    private Filme filme;
    private Date horario;
    private int quantidade;
    private String dateAuxiliar;
    private String tituloAuxiliar;
    private int numeroSalaAuxiliar;
 

    public Sessao(Date horario,Sala sala, Filme filme) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.quantidade = sala.getQuantidade();
        
    }
    
     public Sessao(int codigo, String dateAuxiliar,Sala sala, Filme filme) {
        this.codigo = codigo;
        this.sala = sala;
        this.filme = filme;
        this.dateAuxiliar = dateAuxiliar;
        this.quantidade = sala.getQuantidade();
        
    }
    
     public Sessao(int codigo,Date horario, Sala sala, Filme filme) {
        this.codigo = codigo;
        this.horario = horario;
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.quantidade = sala.getQuantidade();
        
    }
    
    
    public Sessao(int codigo,Date horario, Sala sala, Filme filme, int quanitidade) {
        this.codigo = codigo;
        this.horario = horario;
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.quantidade = sala.getQuantidade();
        
    }

    public Sessao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Sessao(int codigo, String dateHourToString, String titulo, int numero_sala) {
       this.codigo = codigo;
       this.dateAuxiliar = dateHourToString;
       this.tituloAuxiliar = titulo;
       this.numeroSalaAuxiliar = numero_sala;
    }
    
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidadeSoma() {
        this.quantidade += 1;
    }
    
    public void setQuantidadeSubtracao() {
        this.quantidade -= 1;
    }
   
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
