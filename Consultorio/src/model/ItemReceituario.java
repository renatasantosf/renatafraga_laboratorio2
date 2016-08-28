/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author lhries
 */
public class ItemReceituario {
    private Medicamento medicamento;
    private String posologia;

    public ItemReceituario(Medicamento medicamento, String posologia) {
        this.medicamento = medicamento;
        this.posologia = posologia;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public String getPosologia() {
        return posologia;
    }

    @Override
    public String toString() {
        return medicamento.getNome()+" - "+posologia;
    }
    
    
}
