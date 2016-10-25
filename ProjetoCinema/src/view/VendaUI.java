package view;

import DAO.SessaoDAO;
import DAO.VendaDAO;
import DAO.impl_bd.SessaoDAOBD;
import DAO.impl_bd.VendaDAOBD;
import dominio.Venda;
import java.util.List;
import util.Console;
import util.DateUtil;
import view.menu.VendaMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class VendaUI {
    
    
    private VendaDAO vendaDao;
    private SessaoDAO sessaoDao;
    
    public VendaUI() {
       vendaDao = new VendaDAOBD();
       sessaoDao = new SessaoDAOBD();
    }
    
     
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= VendaMenu.SAIR) {
            System.out.println(VendaMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

            switch(opcao) {
                case VendaMenu.OP_VENDERINGRESSO:
                    venderIngresso();
                    break;
                case VendaMenu.OP_BUSCARSESSOES:
                    buscarSessoes();
                    break;
                case VendaMenu.OP_LISTARVENDAS:
                    listarVendas();
                    break;
                case VendaMenu.OP_CANCELARVENDA:
                    cancelarVenda();
                    break;
                case VendaMenu.SAIR:
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
            
        }
    }


    private void venderIngresso() {
        sessaoDao.listar();
        int codigoSessao= Console.scanInt("Codigo da sessao: ");
        
        vendaDao.cadastrar(new Venda(sessaoDao.buscarPorCodigo(codigoSessao)));
        
        
        
        
        System.out.println("Venda efetuada com sucesso!");
    }

    private void buscarSessoes() {
       int codigo = Console.scanInt("Codigo da sessao: ");
       sessaoDao.buscarPorCodigo(codigo);
    }

    private void cancelarVenda() {
       int codigo = Console.scanInt("Codigo da venda: ");
        Venda venda =  vendaDao.buscarPorCodigo(codigo);
        
        if (UIUtil.getConfirmacao("Realmente deseja cancelar esta venda?")) {
            vendaDao.remover(venda);
            System.out.println("Venda removida com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }   
    }
    
    private void listarVendas() {
        List<Venda> listaVendas = vendaDao.listar();
        this.mostrarVenda(listaVendas);
    
    }    
      private void mostrarVenda(Venda v) {
        System.out.println("-----------------------------");
        System.out.println("Vendas: ");
        System.out.println("Venda: "+ v.getCodigo());
        System.out.println("Sessão: " + v.getSessao().getCodigo());
        System.out.println("Horário:  " + DateUtil.dateHourToString(v.getSessao().getHorario()));
        System.out.println("Filme:  " + v.getSessao().getFilme().getTitulo());
        System.out.println("Capacidade:  " + v.getSessao().getSala().getQuantidade());
        System.out.println("-----------------------------");
    }

    private void mostrarVenda(List<Venda> listaVendas) {
        if (listaVendas.isEmpty()) {
            System.out.println("Vendas nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "VENDA") + "\t"
                    + String.format("%-10s", "|SESSÃO") + "\t"
                    + String.format("%-30s", "|HORÁRIO ") + "\t" 
                     + String.format("%-40s", "|FILME ") + "\t" 
                     + String.format("%-10s", "|CAPACIDADE "));
          
            for (Venda venda:listaVendas) {
                   System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", venda.getCodigo()) + "\t"
                    + String.format("%-10s", venda.getSessao().getCodigo()) + "\t"
                    + String.format("%-30s", DateUtil.dateHourToString(venda.getSessao().getHorario())) + "\t" 
                     + String.format("%-40s", venda.getSessao().getFilme().getTitulo()) + "\t" 
                     + String.format("%-10s", venda.getSessao().getQuantidade()));
            }
        }
    }
    
    
    
    
   
}
