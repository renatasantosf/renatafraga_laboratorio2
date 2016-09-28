package view;

import java.text.ParseException;
import java.util.Date;
import model.Filme;
import model.Sala;
import model.Sessao;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import util.Console;
import util.DateUtil;
import view.menu.SessaoMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class SessaoUI {
     
    private RepositorioSessao listaSessoes;
    private RepositorioFilme listaFilmes;
    private RepositorioSala listaSalas;
    
    public SessaoUI(RepositorioSessao rs,RepositorioFilme rf, RepositorioSala rps) {
       listaSessoes = rs;
       listaFilmes = rf;
       listaSalas = rps;
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
         listaFilmes.listaFilmes();
         int codigo = Console.scanInt("");
         Filme filme = listaFilmes.getFilmes().get(codigo);

         System.out.println("Selecione a sala pelo numero: ");
         listaSalas.listaSalas();
         int numero = Console.scanInt("");
         Sala sala = listaSalas.getSalas().get(numero);
         
         String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
         Date horario;
         Date hoje = new Date();
         
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                
                
                if(horario.compareTo(hoje)==-1) {
                    System.out.println("Impossivel inserir sessao. Data inferior a data atual.");
                } else {
                    
                    if(listaSessoes.existeSessao(horario,numero)) {

                        System.out.println("Ja ha um filme inserido nesta mesma sessao e neste mesmo horario.");

                    } else {

                        listaSessoes.addSessao(new Sessao(sala,filme,horario));
                        System.out.println("Sessao inserida com sucesso.");
                    }
                }
            } catch (ParseException ex) {
                System.out.println("Data ou hora no formato invalido.");                
            }
        }
    

    private void listarSessoes() {
       if(listaSessoes.getSessoes().isEmpty()) {
            System.out.println("Nao ha sessoes disponiveis.");
        } else {
            System.out.println(String.format("%-20s", "NUMERO DA SESSAO") + "\t"
                + String.format("%-30s","DIA E HORARIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS"));
            for (Sessao sessao : listaSessoes.getSessoes()) {
              System.out.println(String.format("%-20s", sessao.getCodigo()) + "\t"
                + String.format("%-30s",DateUtil.dateHourToString(sessao.getHorario()))+"\t"+String.format("%-10s",sessao.getSala().getNumero())
                    +"\t"+String.format("%-30s",sessao.getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", sessao.getQuantidade()));
            }
        }
    }

    private void removerSessao() {
       if(listaSessoes.getSessoes().isEmpty()) {
           System.out.println("Nao ha sessoes disponiveis.");
       } else {
            listarSessoes();
            int codigo = Console.scanInt("Digite o codigo da sessao: ");
            for(int x=0;x<listaSessoes.getSessoes().size();x++) {
                if(listaSessoes.getSessoes().get(x).getCodigo()==codigo) {
                    listaSessoes.getSessoes().remove(x);
                    System.out.println("Removido com sucesso!");
                } else {
                    System.out.println("Sessao invalida.");
                    break;
                }
            }
        }   
    }

    private void buscarSessao() {
       int codigo = Console.scanInt("Codigo: ");
       if(listaSessoes.getSessoes().isEmpty()) {
           System.out.println("Nao ha sessões disponiveis.");
       } else {
           listaSessoes.buscarSessao(codigo);
       }
    }
    
    
    
    
}
