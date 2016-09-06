/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author renat
 */
public class Assento {
    
    private static int CODIGO_GERADO = 1;
    private int identificacao;
    private boolean liberada;
    
    
    public Assento() {
        this.identificacao = gerarCodigo();
        this.liberada = false;
    }

   

    public int getIdentificacao() {
        return identificacao;
    }

   
    public boolean getLiberada() {
        return liberada;
    }

    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }

    
    private int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
}
