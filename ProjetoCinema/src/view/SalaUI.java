package view;

import DAO.SalaDAO;
import DAO.impl_bd.SalaDAOBD;
import dominio.Sala;
import java.util.List;

import util.Console;
import view.menu.SalaMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class SalaUI {
    
    private SalaDAO salaDao;
    
    
    public SalaUI() {
        salaDao = new SalaDAOBD();
     
    }
    
    
     public void iniciar() {
        int opcao = 0;
        while (opcao!= SalaMenu.OP_VOLTAR) {
            System.out.println(SalaMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

            switch(opcao) {
                case SalaMenu.OPCADASTRAR_SALA:
                     cadastrarSala();
                    break;
                case SalaMenu.OPREMOVER_SALA:
                     removerSala();
                    break;
                case SalaMenu.OP_LISTARSALAS:
                    listarSalas();
                    break;
                case SalaMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opcao inválida.");
            }
        }
    }

    private void cadastrarSala() {
        salaDao.cadastrar(new Sala());
        
        System.out.println("Cadastrado com sucesso!");
    }

    private void removerSala() {
        int codigo = Console.scanInt("Numero da sala: ");
        Sala sala = salaDao.buscarPorCodigo(codigo);
        
        if (UIUtil.getConfirmacao("Realmente deseja excluir essa sala?")) {
            salaDao.remover(sala);
            System.out.println("Sala removida com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        } 
    }

   

   

    public void listarSalas() {
        List<Sala> listaSalas = salaDao.listar();
        this.mostrarSala(listaSalas);
    }
    
    private void mostrarSala(Sala s) {
        System.out.println("-----------------------------");
        System.out.println("Sala");
        System.out.println("Numero: "+ s.getNumero());
        System.out.println("Capacidade: " + s.getQuantidade());
        System.out.println("-----------------------------");
    }

    private void mostrarSala(List<Sala> listaSalas) {
        if (listaSalas.isEmpty()) {
            System.out.println("Salas nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NUMERO") + "\t"
                    + String.format("%-20s", "|QUANTIDADE"));
          
            for (Sala sala: listaSalas) {
                System.out.println(String.format("%-10s", sala.getNumero()) + "\t"
                        + String.format("%-20s", "|" + sala.getQuantidade()));
            }
        }
    }
     
    
}
