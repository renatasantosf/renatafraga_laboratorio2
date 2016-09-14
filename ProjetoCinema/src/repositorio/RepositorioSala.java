
package repositorio;

import java.util.ArrayList;
import java.util.List;

import model.Sala;

/**
 *
 * @author renat
 */
public class RepositorioSala {
     private List<Sala> salas;
  
    
    
    public RepositorioSala() {
       salas = new ArrayList<>();
       
    }
    
    public boolean addSala(Sala sala) {
        System.out.println("Cadastrado com sucesso!");
        return (salas.add(sala));
        
        
    }
    
    public List<Sala> getSalas() {
        return salas;
    }
    
  
    
   
    
    public boolean existeSala(int numero) {
        if(salas.isEmpty()) {
            return false;
        } else {
            for(Sala sala: salas) {
                if(sala.getNumero()==numero) {
                    return true;
                } 
            }
        }
        return false;
    }

    public void listarSalas() {
       if(salas.isEmpty()) {
           System.out.println("Não há salas cadastradas.");
       } else {
           System.out.println(String.format("%-10s", "IDENTIFICAÇÃO") + "\t"
                + String.format("%-10s","CACIDADE"));
        for (Sala salas: salas) {
            System.out.println(String.format("%-10s",salas.getNumero()) + "\t"
                + String.format("%-10s",salas.getQuantidade()));
        }
       }
    }
    
   
    
   
}
