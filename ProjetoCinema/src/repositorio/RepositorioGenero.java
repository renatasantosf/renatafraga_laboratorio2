/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import model.Genero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renat
 */
public class RepositorioGenero {
    private List<Genero> generos;
    
    
    public RepositorioGenero() {
        generos = new ArrayList<>();
    }
    
    public boolean addGenero(Genero genero) {
        return (generos.add(genero));
    }
    
    public List<Genero> getGeneros() {
        return generos;
    }
    
     public void listarGeneros() {
        for(Genero genero : generos) {
            System.out.println(genero);
       }
    }
     
      public boolean generoExiste(String nome) {
        if(generos.isEmpty()) {
            return false;
        } else {
            for(Genero genero: generos) {
                if(genero.getNome().equalsIgnoreCase(nome)) {
                    return true;
                } 
            }
        }
        return false;
    }
    
     
       public void removerGenero(String nome) {
          for(int i=0;i<generos.size();i++) {
                if(generos.get(i).getNome().equalsIgnoreCase(nome)) {
                    generos.remove(i);
                    System.out.println("Removido com sucesso.");
                    break;
                } 
                        
            }
    }
}
