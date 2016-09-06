/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Assento;

/**
 *
 * @author renat
 */
public class RepositorioAssento {
    private List<Assento> assentos;
    
    
    public RepositorioAssento() {
        assentos = new ArrayList<>();
    }
    
    public boolean addAssento(Assento assento) {
        return (assentos.add(assento));      
    }
    
    public List<Assento> getAssentos() {
        return assentos;
    }
    
    /*public Assento buscarAssento(int id) {
            for (Assento assento : assentos) {
                if (assento.getIdentificacao()==id) {
                    return assento;
                }
            }
            return null;
            
    }*/
    
   /* public boolean existeAssento(int id) {
        if(assentos.isEmpty()) {
            return false;
        } else {
            for(Assento assento: assentos) {
                if(assento.getIdentificacao()==id) {
                    return true;
                } 
            }
        }
        return false;
    }*/
    
   
    
   /* public void removerAssento(int id) {
       for(int i=0;i<assentos.size();i++) {
           if(assentos.get(i).getIdentificacao()==id) {
               assentos.remove(i);
               break;
           } else {
               System.out.println("Título inválido.");
               break;
           }
       }
    }*/
    
}
