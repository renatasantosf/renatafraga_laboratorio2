
package view;

import DAO.GeneroDAO;
import DAO.impl_bd.GeneroDAOBD;
import dominio.Filme;
import dominio.Genero;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import repositorio.RepositorioFilme;
import util.Console;
import view.menu.FilmeMenu;
import repositorio.RepositorioGenero;
import util.DateUtil;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class FilmeUI {
    
   private GeneroDAO generoDao;

    public FilmeUI() {
        generoDao = new GeneroDAOBD();
    }
    
    
    
    public void iniciar() {
        int opcao = 0;
        do {
            try {
                System.out.println(FilmeMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");

            switch(opcao) {
                case FilmeMenu.OP_INSERIRFILME:
                     // cadastrarFilme();
                    break;
                case FilmeMenu.OP_INSERIRGENERO:
                     cadastrarGenero();
                    break;
                case FilmeMenu.OP_BUSCARNOME:
                    //buscarFilme();
                    break;
                case FilmeMenu.OP_BUSCARGENERO:
                    
                    break;
                case FilmeMenu.OP_LISTARFILMES:
                    //listarFilmes();
                    break;
                case FilmeMenu.OP_LISTARGENEROS:
                     mostrarGenero();
                    break;
                case FilmeMenu.OP_REMOVERFILME:
                    // removerFilme();
                    break;
                case FilmeMenu.OP_REMOVERGENERO:
                     removerGenero();
                     break;
                case FilmeMenu.OP_VOLTAR:
                     break;
                default:
                    System.out.println("Opcao invalida!");
            }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }
        } while(opcao != FilmeMenu.OP_VOLTAR);
    }  
        
    
    /*

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
    */
    
     
   
     private void cadastrarGenero() {
        String nome = Console.scanString("Gênero: ");
        String descricao = Console.scanString("Descricao: ");
        generoDao.cadastrar(new Genero(nome, descricao));
        
        System.out.println("Gênero" + nome + " cadastrado com sucesso!");
    }
     
     /* private void buscarGenero() {
     int codigo = Console.scanInt("Codigo: ");
     List<Genero> listaGeneros = generoDao.buscarPorCodigo(codigo);
     this.mostrarGenero(listaGeneros);
     
     }*/

   /* private void buscarPorGenero() {
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
    }*/

    private void removerGenero() {
        
        int codigo = Console.scanInt("Codigo do genero: ");
        Genero gen = generoDao.buscarPorCodigo(codigo);
        this.mostrarGenero(gen);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse paciente?")) {
            generoDao.remover(gen);
            System.out.println("Gênero removido com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        } 
    }
    
    public void mostrarGenero() {
        List<Genero> listaGeneros = generoDao.listar();
        this.mostrarGenero(listaGeneros);
    }
    
    private void mostrarGenero(Genero g) {
        System.out.println("-----------------------------");
        System.out.println("Genero");
        System.out.println("Nome: " + g.getNome());
        System.out.println("Descricao: " + g.getDescricao());
        System.out.println("-----------------------------");
    }

    private void mostrarGenero(List<Genero> listaGeneros) {
        if (listaGeneros.isEmpty()) {
            System.out.println("Generos nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-30s", "|DESCRICAO"));
            for (Genero genero : listaGeneros) {
                System.out.println(String.format("%-10s", genero.getCodigo()) + "\t"
                        + String.format("%-20s", "|" + genero.getNome()) + "\t"
                        + String.format("%-20s", "|" + genero.getDescricao()));
            }
        }
    }
    
    
    
}
    

    
    
   
    
