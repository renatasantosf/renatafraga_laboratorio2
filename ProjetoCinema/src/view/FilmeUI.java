/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Filme;
import repositorio.RepositorioFilme;
import util.Console;
import view.menu.FilmeMenu;
import repositorio.RepositorioGenero;

/**
 *
 * @author renat
 */
public class FilmeUI {
    
    private RepositorioFilme listaFilmes;
    
    public FilmeUI(RepositorioFilme listaF) {
        this.listaFilmes = listaF;
       
    }
    
   //TODO: menu em outra classe
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= FilmeMenu.OP_VOLTAR) {
            System.out.println(FilmeMenu.menuFilme());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case FilmeMenu.OP_INSERIR:
                      cadastrarFilme();
                    break;
                case FilmeMenu.OP_LISTAR:
                    listarFilmes();
                    break;
                case FilmeMenu.OP_BUSCARNOME:
                    buscarFilme();
                    break;
                case FilmeMenu.OP_BUSCARGENERO:
                    break;
                case FilmeMenu.OP_ALTERAR:
                    break;
                case FilmeMenu.OP_REMOVER:
                    removerFilme();
                    
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }
    }

    private void cadastrarFilme() {
      String titulo = Console.scanString("Título do filme: ");
      if(listaFilmes.filmeExiste(titulo)) {
          System.out.println("Este filme já foi cadastrado.");
      } else {
          String genero = Console.scanString("Gênero: ");
          String sinopse = Console.scanString("Sinopse: ");
          listaFilmes.addFilme(new Filme(titulo,genero,sinopse));
      }
      
    }
    
    private void listarFilmes() {
        if(listaFilmes.getFilmes().isEmpty()) {
            System.out.println("Não há filmes cadastrados.");
        } else {
            for (Filme filmes : listaFilmes.getFilmes()) {
                System.out.println(filmes);
            }
        }
    }
    
    
    private void removerFilme() {
       if(listaFilmes.getFilmes().isEmpty()) {
            System.out.println("Não há filmes cadastrados.");
        } else {
           
            for (Filme filme : listaFilmes.getFilmes()) {
                System.out.println(filme.getTitulo());
            }
            
            String titulo = Console.scanString("Título que deseja remover: ");
            listaFilmes.removerFilme(titulo);
        }
    }

    private void buscarFilme() {
       String titulo = Console.scanString("Título: ");
       listaFilmes.buscarFilme(titulo);
    }

    
    
   
    }
