/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Assento;
import java.util.List;
import repositorio.RepositorioAssento;

/**
 *
 * @author renat
 */
public class Sala {
    
    private static int CODIGO_GERADO = 10;
    private int numero;
    private Assento assento;

    
    public Sala() {
        this.numero = gerarCodigo();
            
    }
    
    
    
    public int getNumero() {
        return numero;
    }
    
    public Assento Assento() {
        return this.assento;
    }
    
    public int gerarCodigo() {
        return (CODIGO_GERADO+=10);
    }
    
    
    
}
