

package view;

import model.Filme;
import repositorio.RepositorioFilme;
import repositorio.RepositorioGenero;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVenda;

import util.Console;
import view.menu.MainMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class MainUI {
    
    private RepositorioFilme listaFilmes;
    private RepositorioGenero listaGeneros;
    private RepositorioSala listaSalas;
    private RepositorioSessao listaSessoes;
    private RepositorioVenda listaVendas;
    
    public MainUI() {
        listaFilmes = new RepositorioFilme();
        listaGeneros = new RepositorioGenero();
        listaSalas = new RepositorioSala();
        listaSessoes = new RepositorioSessao();
        listaVendas = new RepositorioVenda();
    }
    
    public void executar() {
        int opcao = 0;
        
        while(opcao!=MainMenu.SAIR) {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção desejada: ");
            switch(opcao) {
                case MainMenu.OP_FILME:
                    new FilmeUI(listaFilmes,listaGeneros).iniciar();
                    break;
                case MainMenu.OP_SALA:
                    new SalaUI(listaSalas).iniciar();
                    break;
                case MainMenu.OP_SESSAO:
                    new SessaoUI(listaSessoes, listaFilmes,listaSalas).iniciar();
                    break;
                case MainMenu.OP_VENDA:
                    new VendaUI(listaVendas,listaSessoes).iniciar();
                    break;
                case MainMenu.SAIR:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
