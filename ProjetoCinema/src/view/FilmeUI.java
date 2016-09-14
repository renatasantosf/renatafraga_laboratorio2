/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Filme;
import model.Genero;
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
    private RepositorioGenero listaGeneros;
    
    public FilmeUI(RepositorioFilme listaF, RepositorioGenero listaG) {
        this.listaFilmes = listaF;
        this.listaGeneros = listaG;
       
    }
    
    
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= FilmeMenu.OP_VOLTAR) {
            System.out.println(FilmeMenu.menuFilme());
            opcao = Console.scanInt("Digite a opção que deseja: ");

            switch(opcao) {
                case FilmeMenu.OP_INSERIRFILME:
                      cadastrarFilme();
                    break;
                case FilmeMenu.OP_INSERIRGENERO:
                     cadastrarGenero();
                    break;
                case FilmeMenu.OP_BUSCARNOME:
                    buscarFilme();
                    break;
                case FilmeMenu.OP_BUSCARGENERO:
                    buscarPorGenero();
                    break;
                case FilmeMenu.OP_LISTARFILMES:
                    listarFilmes();
                    break;
                case FilmeMenu.OP_LISTARGENEROS:
                     listaGeneros.listarGeneros();
                    break;
                case FilmeMenu.OP_REMOVERFILME:
                     removerFilme();
                    break;
                case FilmeMenu.OP_REMOVERGENERO:
                     removerGenero();
                     break;
                case FilmeMenu.OP_VOLTAR:
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
      } else if(listaGeneros.getGeneros().isEmpty()) {
          System.out.println("Não há gêneros cadastrados.");
      } else {
          String sinopse = Console.scanString("Sinopse: ");
          System.out.println("Gêneros: ");
          listaGeneros.listarGeneros();
          int codigo = Console.scanInt("Escolha o código que deseja: ");
          Genero genero = listaGeneros.getGeneros().get(codigo);
          listaFilmes.addFilme(new Filme(titulo,genero,sinopse));
      }
      
    }
    
    private void listarFilmes() {
        if(listaFilmes.getFilmes().isEmpty()) {
            System.out.println("Não há filmes cadastrados.");
        } else {
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-40s","TÍTULO")+"\t"+String.format("%-20s","GÊNERO")  +
                     "\t"+ String.format("%-30s", "SINOPSE"));
            for (Filme filmes : listaFilmes.getFilmes()) {
              System.out.println(String.format("%-10s", filmes.getCodigo()) + "\t"
                + String.format("%-40s",filmes.getTitulo())+ "\t"+String.format("%-20s",filmes.getGenero().getNome())  +
                     "\t"+ String.format("%-30s", filmes.getSinopse()));  
            }
        }
    }
    
    
    
    private void removerFilme() {
       if(listaFilmes.getFilmes().isEmpty()) {
            System.out.println("Não há filmes cadastrados.");
        } else {  
            listarFilmes();
            String titulo = Console.scanString("Título que deseja remover: ");
            listaFilmes.removerFilme(titulo);
        }
    }

    private void buscarFilme() {
       String titulo = Console.scanString("Título: ");
       if(listaFilmes.getFilmes().isEmpty()) {
           System.out.println("Não há filmes cadastrados.");
       } else {
           listaFilmes.buscarFilme(titulo);
       }
    }
    
    private void cadastrarGenero() {
        String nome = Console.scanString("Gênero: ");
      if(listaGeneros.generoExiste(nome)) {
          System.out.println("Este gênero já foi cadastrado.");
      } else {
          String descricao = Console.scanString("Descrição: ");
          listaGeneros.addGenero(new Genero(nome,descricao));
      }
    }

    private void buscarPorGenero() {
        String nome = Console.scanString("Gênero: ");
       if(listaGeneros.getGeneros().isEmpty()) {
           System.out.println("Não há gêneros cadastrados.");
       } else {
           for(Filme filme: listaFilmes.getFilmes()) {
               if(filme.getGenero().getNome().equals(nome)) {
                    System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                  + String.format("%-40s","TÍTULO")+"\t"+String.format("%-20s","GÊNERO")  +
                       "\t"+ String.format("%-30s", "SINOPSE"));       
                   System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
                  + String.format("%-40s",filme.getTitulo())+ "\t"+String.format("%-20s",filme.getGenero().getNome())  +
                       "\t"+ String.format("%-30s", filme.getSinopse()));
                   break;
               } else {
                   System.out.println("Gênero não encontrado.");
               }
           }
       }
    }

    private void removerGenero() {
        if(listaGeneros.getGeneros().isEmpty()) {
            System.out.println("Não há gêneros cadastrados.");
        } else {
            int indice;
            listaGeneros.listarGeneros();
            System.out.println("Digite o nome gênero que deseja remover:");
            String nome = Console.scanString("");           
            for(int i=0;i<listaFilmes.getFilmes().size();i++) {
                if(listaFilmes.getFilmes().get(i).getGenero().getNome().equals(nome)) {
                    System.out.println("Este gênero não pode ser removido, pois está vinculado a um filme.");
                    break;
                } else {
                   for(int x=0;x<listaGeneros.getGeneros().size();x++) {
                         if(listaGeneros.getGeneros().get(x).getNome().equals(nome)) {
                            indice = x;
                            listaGeneros.getGeneros().remove(indice);
                            System.out.println("Removido com sucesso!");
                            break;
                         } else {
                             System.out.println("Gênero não encontrado.");
                         }
                            
                       
                   }
                
                }
            }
        }   
    }
}
    

    
    
   
    
