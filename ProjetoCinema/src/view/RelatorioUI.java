
package view;

import DAO.VendaDAO;
import DAO.impl_bd.VendaDAOBD;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVenda;
import util.Console;
import util.DateUtil;
import view.menu.RelatorioMenu;

/**
 *
 * @author Diego Pinto e Renata Fraga
 */
public class RelatorioUI {
    
    private VendaDAO vendaDao;
    
    public RelatorioUI() {
       vendaDao = new VendaDAOBD();
    }
    
      public void iniciar() {
        int opcao = 0;
        while (opcao!= RelatorioMenu.SAIR) {
            System.out.println(RelatorioMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

            switch(opcao) {
                case RelatorioMenu.OPVENDASPORFILME:
                     vendasPorFilme();
                    break;
                case RelatorioMenu.OPVENDASPORHORARIO:
                     vendasPorHorario();
                    break;
                case RelatorioMenu.OPVENDASPORSALA:
                     vendasPorSala();
                    break;
                case RelatorioMenu.OPFILMEMAISVENDIDO:
                    filmeMaisVendido();
                    break;
                case RelatorioMenu.OPSALAMAISUTILIZADA:
                    System.out.println(salaMaisUtilizada());
                    break;
                case RelatorioMenu.SAIR:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
      
    private void vendasPorFilme() {
       
    }
    
    private void vendasPorHorario() {
       
    }
    
    private void vendasPorSala() {
       
    }
    
    private void filmeMaisVendido() {
       
         
    }
    
    private String salaMaisUtilizada() {
      
        
    
    }

     
     
}
