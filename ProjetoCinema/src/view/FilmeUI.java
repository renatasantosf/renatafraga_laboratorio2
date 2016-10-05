
package view;

import dominio.Filme;
import dominio.Genero;
import repositorio.RepositorioFilme;
import util.Console;
import view.menu.FilmeMenu;
import repositorio.RepositorioGenero;

/**
 *
 * @authors Diego Pinto e Renata Fraga
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
            System.out.println(FilmeMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

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
                    System.out.println("Opcao invalida!");
            }
            
        }
    }

    private void cadastrarFilme() {
      String titulo = Console.scanString("Titulo: ");
      if(listaFilmes.filmeExiste(titulo)) {
          System.out.println("Este filme ja foi cadastrado.");
      } else if(listaGeneros.getGeneros().isEmpty()) {
          System.out.println("Nao ha generos cadastrados.");
      } else {
          String sinopse = Console.scanString("Sinopse: ");
          System.out.println("Generos: ");
          listaGeneros.listarGeneros();
          int codigo = Console.scanInt("Escolha o código que deseja: ");
          Genero genero = listaGeneros.getGeneros().get(codigo);
          listaFilmes.addFilme(new Filme(titulo,genero,sinopse));
      }
      
    }
    
    private void listarFilmes() {
        if(listaFilmes.getFilmes().isEmpty()) {
            System.out.println("Nao ha filmes cadastrados.");
        } else {
            System.out.println(String.format("%-10s", "CODIGO") + "\t"
                + String.format("%-40s","TÍTULO")+"\t"+String.format("%-20s","GENERO")  +
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
            System.out.println("Nao ha filmes cadastrados.");
        } else {  
            listarFilmes();
            String titulo = Console.scanString("Titulo que deseja remover: ");
            listaFilmes.removerFilme(titulo);
        }
    }

    private void buscarFilme() {
       String titulo = Console.scanString("Titulo: ");
       if(listaFilmes.getFilmes().isEmpty()) {
           System.out.println("Nao ha filmes cadastrados.");
       } else {
           listaFilmes.buscarFilme(titulo);
       }
    }
    
    private void cadastrarGenero() {
        String nome = Console.scanString("Genero: ");
      if(listaGeneros.generoExiste(nome)) {
          System.out.println("Este genero já foi cadastrado.");
      } else {
          String descricao = Console.scanString("Descricao: ");
          listaGeneros.addGenero(new Genero(nome,descricao));
      }
    }

    private void buscarPorGenero() {
        String nome = Console.scanString("Genero: ");
       if(listaGeneros.getGeneros().isEmpty()) {
           System.out.println("Nao ha generos cadastrados.");
       } else {
           for(Filme filme: listaFilmes.getFilmes()) {
               if(filme.getGenero().getNome().equals(nome)) {
                    System.out.println(String.format("%-10s", "CODIGO") + "\t"
                  + String.format("%-40s","TITULO")+"\t"+String.format("%-20s","GENERO")  +
                       "\t"+ String.format("%-30s", "SINOPSE"));       
                   System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
                  + String.format("%-40s",filme.getTitulo())+ "\t"+String.format("%-20s",filme.getGenero().getNome())  +
                       "\t"+ String.format("%-30s", filme.getSinopse()));
                   break;
               } else {
                   System.out.println("Genero nao encontrado.");
               }
           }
       }
    }

    private void removerGenero() {
        if(listaGeneros.getGeneros().isEmpty()) {
            System.out.println("Nao ha generos cadastrados.");
        } else {
            int indice;
            listaGeneros.listarGeneros();
            System.out.println("Digite o nome genero que deseja remover:");
            String nome = Console.scanString("");           
            for(int i=0;i<listaFilmes.getFilmes().size();i++) {
                if(listaFilmes.getFilmes().get(i).getGenero().getNome().equals(nome)) {
                    System.out.println("Este genero nao pode ser removido, pois esta vinculado a um filme.");
                    break;
                } else {
                   for(int x=0;x<listaGeneros.getGeneros().size();x++) {
                         if(listaGeneros.getGeneros().get(x).getNome().equals(nome)) {
                            indice = x;
                            listaGeneros.getGeneros().remove(indice);
                            System.out.println("Removido com sucesso!");
                            break;
                         } 
                            
                       
                   }
                
                }
            }
        }   
    }
}
    

    
    
   
    
