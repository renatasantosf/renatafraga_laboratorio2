/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import repositorio.RepositorioVenda;
import util.Console;
import view.menu.VendaMenu;

/**
 *
 * @author 631510049
 */
public class VendaUI {
    
    private RepositorioVenda listaVendas;
    
    public VendaUI(RepositorioVenda rv) {
        listaVendas = rv;
    }
    
     
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= VendaMenu.SAIR) {
            System.out.println(VendaMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case VendaMenu.OP_VENDERINGRESSO:
                    venderIngresso();
                    break;
                case VendaMenu.OP_BUSCARSESSOES:
                    buscarSessoes();
                    break;
                case VendaMenu.OP_CANCELARVENDA:
                    cancelarVenda();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }
    }


    private void venderIngresso() {
        
    }

    private void buscarSessoes() {
       
    }

    private void cancelarVenda() {
       
    }
    
    private void listarSessoes() {
        
    }
    
    
    
    
    
   
}
