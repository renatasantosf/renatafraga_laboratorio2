/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.util.Date;
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
    
    public SessaoUI(RepositorioSessao rs) {
       listaSessoes = rs;
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
        
        
        
        String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
        Date horario;
         try {
                horario = DateUtil.stringToDateHour(dataHora);
                if(listaSessoes.existeSessao(horario)) {
                    System.out.println("Nesse horario já existe uma consulta");
                } else {
                    //listaSessoes.addSessao();
                    System.out.println("Agendamento realizado com sucesso!");
                }
            } catch (ParseException ex) {
                System.out.println("Data ou hora no formato inválido.");                
            }
        }
    

    private void listarSessoes() {
       if(listaSessoes.getSessoes().isEmpty()) {
            System.out.println("Não há sessões disponíveis.");
        } else {
            System.out.println(String.format("%-10s", "NÚMERO DA SESSÃO") + "\t"
                + String.format("%-30s","DIA E HORÁRIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS"));
            for (Sessao sessao : listaSessoes.getSessoes()) {
              System.out.println(String.format("%-10s", sessao.getCodigo()) + "\t"
                + String.format("%-30s",sessao.getHorario())+"\t"+String.format("%-10s",sessao.getSala().getNumero())
                    +"\t"+String.format("%-30s",sessao.getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", sessao.getQuantidade()));
            }
        }
    }

    private void removerSessao() {
        listarSessoes();
        int codigo = Console.scanInt("Digite o código da sessão: ");
        for(int x=0;x<listaSessoes.getSessoes().size();x++) {
            if(listaSessoes.getSessoes().get(x).getCodigo()==codigo) {
                listaSessoes.getSessoes().get(x);
                System.out.println("Removido com sucesso!");
            } else {
                System.out.println("Sala inválida.");
                
            }
           
        }
    }

    private void buscarSessao() {
       int codigo = Console.scanInt("Título: ");
       if(listaSessoes.getSessoes().isEmpty()) {
           System.out.println("Não há sessões disponíveis.");
       } else {
           listaSessoes.buscarSessao(codigo);
       }
    }
    
    
    
    
}
