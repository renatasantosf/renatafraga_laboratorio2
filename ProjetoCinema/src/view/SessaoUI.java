package view;

import DAO.FilmeDAO;
import DAO.SalaDAO;
import DAO.SessaoDAO;
import DAO.impl_bd.FilmeDAOBD;
import DAO.impl_bd.SalaDAOBD;
import DAO.impl_bd.SessaoDAOBD;
import java.text.ParseException;
import java.util.Date;
import dominio.Sessao;
import java.util.List;
import util.Console;
import util.DateUtil;
import view.menu.SessaoMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class SessaoUI {
     
    private SessaoDAO sessaoDao;
    private FilmeDAO filmeDao;
    private SalaDAO salaDao;
    
    
    public SessaoUI() {
        sessaoDao = new SessaoDAOBD();
        filmeDao = new FilmeDAOBD();
        salaDao = new SalaDAOBD();
    }
    
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= SessaoMenu.SAIR) {
            System.out.println(SessaoMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

            switch(opcao) {
                case SessaoMenu.OP_CADASTRARSESSAO:
                    cadastrarSessao();
                    break;
                case SessaoMenu.OP_LISTARSESSOES:
                    listarSessoes();
                    break;
                case SessaoMenu.OP_REMOVERSESSAO:
                    removerSessao();
                    break;
                case SessaoMenu.OP_BUSCARSESSAO:
                    buscarSessao();
                    break;
                case SessaoMenu.SAIR:
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
            
        }
    }

    private void cadastrarSessao() {
         System.out.println("Selecione o filme pelo codigo: ");
         
         FilmeUI filmeUi = new FilmeUI();
         filmeUi.listarFilme();
         
         int codigo = Console.scanInt("");
         
         

         System.out.println("Selecione a sala pelo numero: ");
         
         SalaUI salaUi = new SalaUI();
         salaUi.listarSalas();
         
         int numero = Console.scanInt("");
         
         
         String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
         Date horario;
         Date hoje = new Date();
         
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                
                
                if(horario.compareTo(hoje)==-1) {
                    System.out.println("Impossivel inserir sessao. Data inferior a data atual.");
                } else {
                    
                    if(sessaoDao.seHaSessao(horario,numero)) {

                        System.out.println("Ja ha um filme inserido nesta mesma sessao e neste mesmo horario.");

                    } else {

                        Sessao sessao = new Sessao(horario,salaDao.buscarPorCodigo(numero),filmeDao.buscarPorCodigo(codigo));
                        System.out.println("Cadastrado com sucesso!");
                    }
                }
            } catch (ParseException ex) {
                System.out.println("Data ou hora no formato invalido.");                
            }
        }
    

    private void listarSessoes() {
        List<Sessao> listaSessoes = sessaoDao.listar();
        this.mostrarSessao(listaSessoes);
    }

    private void removerSessao() {
      int codigo = Console.scanInt("Codigo da sessao: ");
      Sessao sessao =  sessaoDao.buscarPorCodigo(codigo);
        
        if (UIUtil.getConfirmacao("Realmente deseja excluir essa sessao?")) {
            sessaoDao.remover(sessao);
            System.out.println("Sessao removida com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }   
    }

    private void buscarSessao() {
       int codigo = Console.scanInt("Codigo da sessao: ");
       sessaoDao.buscarPorCodigo(codigo);
    }
    
     private void listarSessao() {
        List<Sessao> listaSessoes = sessaoDao.listar();
        this.mostrarSessao(listaSessoes);
    }
    
     private void mostrarSessao(Sessao s) {
        System.out.println("-----------------------------");
        System.out.println("Sessao");
        System.out.println("Codigo: "+ s.getCodigo());
        System.out.println("Horario " + DateUtil.dateHourToString(s.getHorario()));
        System.out.println("Filme: " + s.getFilme().getTitulo());
        System.out.println("Sala: " + s.getSala().getNumero());
        System.out.println("-----------------------------");
    }

    private void mostrarSessao(List<Sessao> listaSessoes) {
        if (listaSessoes.isEmpty()) {
            System.out.println("Sessoes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CODIGO") + "\t"
                    + String.format("%-40s", "|HORARIO") + "\t"
                    + String.format("%-10s", "|FILME ") + "\t" 
            + String.format("%-10s", "|SALA"));
          
            for (Sessao sessao : listaSessoes) {
                System.out.println(String.format("%-10s", sessao.getCodigo()) + "\t"
                        + String.format("%-40s", "|" +  DateUtil.dateHourToString(sessao.getHorario()) +"\t"
                        + String.format("%-10s", "|" + sessao.getFilme().getTitulo()) + "\t"
                        + String.format("%-10s", "|" + sessao.getSala().getNumero())));
            }
        }
    }
     
    
    
    
    
    
}
