/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author renat
 */
public class Sessao {
    private static int CODIGO_GERADO = 1;
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
    
    public int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
}
