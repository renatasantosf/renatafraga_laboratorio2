
package model;

import java.util.Date;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Sessao {
    private static int CODIGO_GERADO = 0;
    private int codigo;
    private Sala sala;
    private Filme filme;
    private Date horario;
    private int quantidade;
 

    public Sessao(Sala sala, Filme filme, Date horario) {
        this.codigo = gerarCodigo();
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.quantidade = sala.getQuantidade();
        
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
    public int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
}
