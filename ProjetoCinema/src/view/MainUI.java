
package view;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class MainUI {

     public void executar() {
        int opcao = 0;
        
        while(opcao!=MainMenu.SAIR) {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");
            switch(opcao) {
                case MainMenu.OP_FILME:
                    new FilmeUI().iniciar();
                    break;
                case MainMenu.OP_SALA:
                    new SalaUI().iniciar();
                    break;
                case MainMenu.OP_SESSAO:
                    new SessaoUI().iniciar();
                    break;
                case MainMenu.OP_VENDA:
                    new VendaUI().iniciar();
                    break;
                case MainMenu.OP_RELATORIO:
                    new RelatorioUI().iniciar();
                case MainMenu.SAIR:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }
}
