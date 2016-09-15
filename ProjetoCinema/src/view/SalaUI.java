package view;

import model.Sala;

import repositorio.RepositorioSala;
import util.Console;
import view.menu.SalaMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class SalaUI {
    
    private RepositorioSala listaSalas;
    
    
    public SalaUI(RepositorioSala rs) {
        this.listaSalas = rs;
     
    }
    
    
     public void iniciar() {
        int opcao = 0;
        while (opcao!= SalaMenu.OP_VOLTAR) {
            System.out.println(SalaMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case SalaMenu.OPCADASTRAR_SALA:
                     cadastrarSala();
                    break;
                case SalaMenu.OPREMOVER_SALA:
                     removerSala();
                    break;
                case SalaMenu.OP_LISTARSALAS:
                    listarSalas();
                    break;
                case SalaMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarSala() {
            listaSalas.addSala(new Sala());
    }

    private void removerSala() {
        listarSalas();
        int numero = Console.scanInt("Digite o número da sala: ");
        for(int y=0;y<listaSalas.getSalas().size();y++) {
            if(listaSalas.getSalas().get(y).getNumero()==numero) {
                listaSalas.getSalas().remove(y);
                System.out.println("Removido com sucesso!");
            } else {
                System.out.println("Sala inválida.");
                
            }
           
        }
        
    }

   

   

    private void listarSalas() {
        System.out.println(String.format("%-10s", "IDENTIFICAÇÃO") + "\t"
                + String.format("%-10s","CACIDADE"));
        for (Sala salas: listaSalas.getSalas()) {
            System.out.println(String.format("%-10s",salas.getNumero()) + "\t"
                + String.format("%-10s",salas.getQuantidade()));
        }
    }
    
}
