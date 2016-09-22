/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Sessao;
import model.Venda;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVenda;
import util.Console;
import view.menu.RelatorioMenu;

/**
 *
 * @author renat
 */
public class RelatorioUI {
    private RepositorioVenda listaVendas;
    private RepositorioSessao listaSessoes;
    private RepositorioFilme listaFilmes;
    private RepositorioSala listaSalas;
    
    
    public RelatorioUI(RepositorioVenda rv, RepositorioSessao rse,
            RepositorioFilme rf, RepositorioSala rs) {
        this.listaVendas = rv;
        this.listaSessoes = rse;
        this.listaFilmes = rf;
        this.listaSalas = rs;
    }
    
      public void iniciar() {
        int opcao = 0;
        while (opcao!= RelatorioMenu.SAIR) {
            System.out.println(RelatorioMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case RelatorioMenu.OPFILMESMAISVENDIDOS:
                     filmeMaisVendido();
                    break;
                case RelatorioMenu.OPSESSAOMAISLOTADA:
                     sessaoMaisLotada();
                    break;
                case RelatorioMenu.OPFILMEMENOSVENDIDO:
                    filmeMenosVendido();
                    break;
                case RelatorioMenu.OPSESSAOMENOSLOTADA:
                    sessaoMenosLotada();
                    break;
                case RelatorioMenu.SAIR:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void filmeMaisVendido() {
        
    }

    private void sessaoMaisLotada() {
        int menor = 0, indice = 0;
        
        if(listaVendas.getVendas().get(0).getSessao().getQuantidade() > 0) {
              menor = listaVendas.getVendas().get(0).getSessao().getQuantidade();
        }
        for(int i=0;i<listaVendas.getVendas().size();i++) {
            if(listaVendas.getVendas().get(i).getSessao().getQuantidade()<menor){
              menor = listaVendas.getVendas().get(i).getSessao().getQuantidade();
              indice = i;
            }
        }
        System.out.println(listaVendas.getVendas().get(indice));
    }

    private void filmeMenosVendido() {
       
    }

    private void sessaoMenosLotada() {
     
    }

}
