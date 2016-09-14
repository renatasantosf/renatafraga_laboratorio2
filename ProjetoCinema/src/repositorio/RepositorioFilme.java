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
    
    public void buscarFilme(String titulo) {
            for (Filme filmes : filmes) {
                if(filmes.getTitulo().equals(titulo)) {
                    System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                       + String.format("%-20s","TÍTULO")+"\t"+String.format("%-20s","GÊNERO")  +
                            "\t"+ String.format("%-30s", "SINOPSE"));
                     System.out.println(String.format("%-10s", filmes.getCodigo()) + "\t"
                       + String.format("%-20s",filmes.getTitulo())+ "\t"+String.format("%-20s",filmes.getGenero().getNome())  +
                            "\t"+ String.format("%-30s", filmes.getSinopse()));  
                     break;
                } else {
                    System.out.println("Não há este filme cadastrado.");
                }
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
    
    public void listarFilmes() {
         System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-40s","TÍTULO")+"\t" +
                     "\t"+ String.format("%-30s", "SINOPSE"));
            for (Filme filmes : filmes) {
              System.out.println(String.format("%-10s", filmes.getCodigo()) + "\t"
                + String.format("%-40s",filmes.getTitulo()) +
                     "\t"+ String.format("%-30s", filmes.getSinopse()));  
            }
    }
}
