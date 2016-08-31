/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.Filme;
import repositorio.RepositorioFilme;
import sun.applet.Main;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author 80105681
 */
public class MainUI {
    
    private RepositorioFilme listaFilmes;
    
    public MainUI() {
        listaFilmes = new RepositorioFilme();
    }
    
    public void executar() {
        int opcao = 0;
        
        while(opcao!=2) {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção desejada: ");
            switch(opcao) {
                case MainMenu.OP_FILME:
                    new FilmeUI(listaFilmes).iniciar();
                    break;
                case MainMenu.SAIR:
                    System.out.println("Tchau!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
