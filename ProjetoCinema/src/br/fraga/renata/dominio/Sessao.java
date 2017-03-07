
package br.fraga.renata.dominio;

import java.util.Date;
import br.fraga.renata.util.DateUtil;

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
  
 

    public Sessao(Date horario,Sala sala, Filme filme) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
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
    
    
    public Sessao(int codigo,Date horario, Sala sala, Filme filme, int quantidade) {
        this.codigo = codigo;
        this.horario = horario;
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.quantidade = quantidade;
        
    }

    public Sessao() {
     
    }

    public Sessao(int codigo) {
        this.codigo = codigo;
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

    @Override
    public String toString() {
        return ""+codigo;
    }
    
    
}
