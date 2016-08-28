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
    
    public boolean buscarFilme(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }
    
    //TODO:
   /** public boolean buscarFilmePorGenero(String nome) {
        for(Filme filme :filmes) {
            if (filme.getGenero().equals(nome)) {
                return true;
            }
        }
        return false;
    }*/
    
    
}
