
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
    
   
    
   
}
