
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Assento;
import model.Sala;
import util.Console;

/**
 *
 * @author renat
 */
public class RepositorioSala {
     private List<Sala> salas;
     private RepositorioAssento repAssentos;
    
    
    public RepositorioSala() {
       salas = new ArrayList<>();
       repAssentos = new RepositorioAssento();
       
    }
    
    public boolean addSala(Sala sala) {
        for(int i=0;i<40;i++) {
            repAssentos.addAssento(new Assento());
        }
        return (salas.add(sala));
        
    }
    
    public List<Sala> getSala() {
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
    
    public RepositorioAssento getRepAssentos() {
        return repAssentos;
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
    
   public void pesquisarLiberadas() {
        int id_sala = Console.scanInt("Digite o nº da sala que deseja buscar:");
        if(existeSala(id_sala)) {
            System.out.println("Assentos liberados:");
            for(int i=0;i<repAssentos.getAssentos().size();i++) {
                if(!repAssentos.getAssentos().get(i).getLiberada()) {
                    System.out.println(repAssentos.getAssentos().get(i).getIdentificacao());
                }
            }
        }
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
    
    public void listarAssentos() {
        for(int i=0;i<50;i++) {
            System.out.println(repAssentos.getAssentos().get(i).getIdentificacao());
        }
    }
}
