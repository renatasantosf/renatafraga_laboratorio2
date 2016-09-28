
package repositorio;

import model.Genero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
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
        System.out.println(String.format("%-10s", "CODIGO") + "\t"
                + String.format("%-30s","NOME")+"\t"+String.format("%-30s","DESCRICAO"));
        for(Genero genero : generos) {
             System.out.println(String.format("%-10s",genero.getCodigo())+"\t"+
                     String.format("%-30s", genero.getNome()) + "\t"+
                 String.format("%-30s",genero.getDescricao()));
       }
    }
     
      public boolean generoExiste(String nome) {
        if(generos.isEmpty()) {
            return false;
        } else {
            for(Genero genero: generos) {
                if(genero.getNome().equals(nome)) {
                    return true;
                } 
            }
        }
        return false;
    }
           
    
}
