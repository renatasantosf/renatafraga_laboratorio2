/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Secao;

/**
 *
 * @author renat
 */
public class RepositorioSessao {
    
    private List<Secao> secoes;
    
    
    public RepositorioSessao() {
        secoes = new ArrayList<>();
    }

    public List<Secao> getSecoes() {
        return secoes;
    }
    
    
    public boolean addSecao(Secao secao) {
        return (secoes.add(secao));
        
    }
    
    public Secao buscarSecao(int codigo) {
            for (Secao secao: secoes) {
                if (secao.getCodigo()==codigo) {
                    return secao;
                }
            }
            return null;
            
    }
    
   
    
    public boolean existeSala(int codigo) {
        if(secoes.isEmpty()) {
            return false;
        } else {
            for(Secao secao: secoes) {
                if(secao.getCodigo()==codigo) {
                    return true;
                } 
            }
        }
        return false;
    }
    
   
    
    public void removerSala(int codigo) {
       for(int i=0;i<secoes.size();i++) {
           if(secoes.get(i).getCodigo()==codigo) {
               secoes.remove(i);
               break;
           } else {
               System.out.println("Código inválido.");
               break;
           }
       }
    }
}
