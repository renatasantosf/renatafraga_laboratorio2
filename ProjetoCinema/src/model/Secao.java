/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author renat
 */
public class Secao {
    private int codigo;
    private Sala sala;
    private Filme filme;
    private LocalDate horario;

    public Secao(int codigo, Sala sala, Filme filme, LocalDate horario) {
        this.codigo = codigo;
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
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

    public LocalDate getHorario() {
        return horario;
    }

    public void setHorario(LocalDate horario) {
        this.horario = horario;
    }
    
    
    
}
