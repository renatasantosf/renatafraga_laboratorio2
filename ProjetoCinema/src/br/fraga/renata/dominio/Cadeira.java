/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.dominio;

/**
 *
 * @author renat
 */
class Cadeira {
    
    private int numero;
    private boolean status;

    public Cadeira(int numero, boolean status) {
        this.numero = numero;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cadeira{" + "numero=" + numero + ", status=" + status + '}';
    }
    
    
}
