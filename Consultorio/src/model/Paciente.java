/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import java.util.Objects;
import util.DateUtil;

/**
 *
 * @author lhries
 */
public class Paciente {
    private String rg, nome;
    private Date dataNascimento;

    public Paciente(String nome, String rg, Date dataNascimento) {
        this.nome = nome;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }


    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.rg);
        return hash;
    }

    @Override
    public String toString() {
        return rg+" - "+nome + ", " + DateUtil.dateToString(dataNascimento);
    }
    
    
    
    
    
}
