/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Sala;
import repositorio.RepositorioAssento;
import repositorio.RepositorioSala;
import util.Console;
import view.menu.SalaMenu;

/**
 *
 * @author 631510049
 */
public class SalaUI {
    
    private RepositorioSala listaSalas;
    
    
    public SalaUI(RepositorioSala rs) {
        this.listaSalas = rs;
     
    }
    
    
     public void iniciar() {
        int opcao = 0;
        while (opcao!= SalaMenu.OP_VOLTAR) {
            System.out.println(SalaMenu.menuSala());
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
                case SalaMenu.OPPESQUISAR_LIBERADAS:
                    //pesquisarLiberadas();
                    break;
                case SalaMenu.OPPESQUISAR_OCUPADAS:
                    pesquisarOcupadas();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }
    }

    private void cadastrarSala() {
        int id = Console.scanInt("Digite a identificação da sala: ");
        if(listaSalas.getSala().isEmpty()) {
            listaSalas.addSala(new Sala(id));
        } else {
                for(int i=0;i<listaSalas.getSala().size();i++) {
                if(listaSalas.getSala().get(i).getNumero()==id){
                    System.out.println("Este número já existe.");
                    break;
                } else {
                    listaSalas.addSala(new Sala(id));
                }
            }
        }
    }

    private void removerSala() {
     
        
    }

   /* private void pesquisarLiberadas() {
      int id_sala = Console.scanInt("Digite o nº da sala que deseja buscar:");
        for (int i = 0; i < listaSalas.getSala().size(); i++) {
           if(listaSalas.getSala().get(i).getNumero()==id_sala) {
               System.out.println("Assentos Liberados: ");
               for(int j=0;j < listaSalas.getRepAssentos().getAssentos().size(); j++) {
                   if(listaSalas.getRepAssentos().getAssentos().get(i).isLiberada()) {
                       System.out.println(listaSalas.getRepAssentos().getAssentos().get(i).getIdentificacao());
                   }
               }
           }    
            
        }
    }*/

    private void pesquisarOcupadas() {
        
    }

    private void listarSalas() {
      
    }
    
}
