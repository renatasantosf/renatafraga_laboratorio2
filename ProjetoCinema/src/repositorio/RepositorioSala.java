
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
    
    public Sala buscarSala(int numero) {
            for (Sala sala: salas) {
                if (sala.getNumero()==numero) {
                    return sala;
                }
            }
            return null;
            
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
    
   
    
    public void removerSala(int numero) {
       for(int i=0;i<salas.size();i++) {
           if(salas.get(i).getNumero()==numero) {
               salas.remove(i);
               break;
           } else {
               System.out.println("Título inválido.");
               break;
           }
       }
    }
}
