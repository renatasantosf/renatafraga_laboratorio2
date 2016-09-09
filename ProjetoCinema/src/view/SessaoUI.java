/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Secao;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import util.Console;
import view.menu.SecaoMenu;

/**
 *
 * @author 631510049
 */
public class SessaoUI {
    
    private RepositorioSala listaSalas;
    private RepositorioFilme listaFilmes;
    
    public SessaoUI(RepositorioSala rs, RepositorioFilme rf) {
        listaSalas = rs;
        listaFilmes = rf;
    }
    
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= SecaoMenu.SAIR) {
            System.out.println(SecaoMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case SecaoMenu.OP_CADASTRARSECAO:
                    cadastrarSessao();
                    break;
                case SecaoMenu.OP_LISTARSECOES:
                    listaSessoes();
                    break;
                case SecaoMenu.OP_REMOVERSECAO:
                    removerSessao();
                    break;
                case SecaoMenu.OP_BUSCARSECAO:
                    buscarSessao();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }
    }

    private void cadastrarSessao() {
       
    }

    private void listaSessoes() {
       
    }

    private void removerSessao() {
        
    }

    private void buscarSessao() {
        
    }
    
    
    
    
}
