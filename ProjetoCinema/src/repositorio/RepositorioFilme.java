/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Filme;

/**
 *
 * @author renat
 */
public class RepositorioFilme {
    
    private List<Filme> filmes;
  
    
    public RepositorioFilme() {
        filmes = new ArrayList<>();
    }
    
    public boolean addFilme(Filme filme) {
        return (filmes.add(filme));
    }
    
    public List<Filme> getFilmes() {
        return filmes;
    }
    
    public Filme buscarFilme(String titulo) {
        if(filmes.isEmpty()) {
            return null;
        } else {
            for (Filme filme : filmes) {
                if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                    return filme;
                }
            }
        return null;
        }
    }
    
    public boolean filmeExiste(String titulo) {
        if(filmes.isEmpty()) {
            return false;
        } else {
            for(Filme filme: filmes) {
                if(filme.getTitulo().equalsIgnoreCase(titulo)) {
                    return true;
                } 
            }
        }
        return false;
    }
    
    
 /*  public Filme buscarFilmePorGenero(String nome) {
      
        for(int i=0;i<filmes.size();i++) {
            if () {
               
            }
        }
        
    }*/
    
    public void removerFilme(String titulo) {
       for(int i=0;i<filmes.size();i++) {
           if(filmes.get(i).getTitulo().equals(titulo)) {
               filmes.remove(i);
               break;
           } else {
               System.out.println("Título inválido.");
               break;
           }
       }
    }
}
