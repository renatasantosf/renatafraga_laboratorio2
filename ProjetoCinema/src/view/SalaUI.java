/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Sala;

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
                    
                    break;
                case SalaMenu.OPPESQUISAR_OCUPADAS:
                    
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }
    }

    private void cadastrarSala() {
            listaSalas.addSala(new Sala());
    }

    private void removerSala() {
     
        
    }

   

    private void pesquisarOcupadas() {
        
    }

    private void listarSalas() {
        for (int i = 0; i < listaSalas.getSalas().size(); i++) {
            System.out.println(listaSalas.getSalas().get(i).getNumero());
        }
    }
    
}
