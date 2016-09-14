/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.util.Date;
import model.Filme;
import model.Sala;
import model.Sessao;
import repositorio.RepositorioFilme;
import repositorio.RepositorioGenero;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import util.Console;
import util.DateUtil;
import view.menu.SessaoMenu;

/**
 *
 * @author 631510049
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
            opcao = Console.scanInt("Digite a opção que deseja: ");

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
                    System.out.println("Opção inválida!");
            }
            
        }
    }

    private void cadastrarSessao() {
         System.out.println("Selecione o filme pelo código: ");
         listaFilmes.listarFilmes();
         int codigo = Console.scanInt("");
         Filme filme = listaFilmes.getFilmes().get(codigo);

         System.out.println("Selecione a sala pelo número: ");
         listaSalas.listarSalas();
         int numero = Console.scanInt("");
         Sala sala = listaSalas.getSalas().get(numero);
         
         String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
         Date horario;
         Date hoje = new Date();
         
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                
                
                if(horario.compareTo(hoje)==-1) {
                    System.out.println("Impossível inserir sessão.Data inferior a data atual.");
                } else {
                    
                    if(listaSessoes.existeSessao(horario,numero)) {

                        System.out.println("Já ha um filme inserido nesta mesma sessão e neste mesmo horário.");

                    } else {

                        listaSessoes.addSessao(new Sessao(sala,filme,horario));
                        System.out.println("Sessão inserida com sucesso.");
                    }
                }
            } catch (ParseException ex) {
                System.out.println("Data ou hora no formato inválido.");                
            }
        }
    

    private void listarSessoes() {
       if(listaSessoes.getSessoes().isEmpty()) {
            System.out.println("Não há sessões disponíveis.");
        } else {
            System.out.println(String.format("%-20s", "NÚMERO DA SESSÃO") + "\t"
                + String.format("%-30s","DIA E HORÁRIO")+"\t"+String.format("%-10s","SALA")
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
           System.out.println("Não há sessões disponíveis.");
       } else {
            listarSessoes();
            int codigo = Console.scanInt("Digite o código da sessão: ");
            for(int x=0;x<listaSessoes.getSessoes().size();x++) {
                if(listaSessoes.getSessoes().get(x).getCodigo()==codigo) {
                    listaSessoes.getSessoes().remove(x);
                    System.out.println("Removido com sucesso!");
                } else {
                    System.out.println("Sessão inválida inválida.");
                    break;
                }
            }
        }   
    }

    private void buscarSessao() {
       int codigo = Console.scanInt("Código: ");
       if(listaSessoes.getSessoes().isEmpty()) {
           System.out.println("Não há sessões disponíveis.");
       } else {
           listaSessoes.buscarSessao(codigo);
       }
    }
    
    
    
    
}
