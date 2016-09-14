/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Sessao;
import model.Venda;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVenda;
import util.Console;
import util.DateUtil;
import view.menu.VendaMenu;

/**
 *
 * @author 631510049
 */
public class VendaUI {
    
    private RepositorioVenda listaVendas;
    private RepositorioSessao listaSessoes;
    
    public VendaUI(RepositorioVenda rv, RepositorioSessao rs) {
        listaVendas = rv;
        listaSessoes = rs;
    }
    
     
    
    public void iniciar() {
        int opcao = 0;
        while (opcao!= VendaMenu.SAIR) {
            System.out.println(VendaMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opção que deseja: ");

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
                    System.out.println("Opção inválida!");
            }
            
        }
    }


    private void venderIngresso() {
        if(listaSessoes.getSessoes().isEmpty()) {
            System.out.println("Não há sessões disponíveis.");
        } else { 
            System.out.println("Digite o número da sessão que deseja comprar: ");
            listaSessoes.listaSessoes();
            int numero = Console.scanInt("");
            Sessao sessao = listaSessoes.getSessoes().get(numero);
            if(listaSessoes.sessaoValida(numero)) {
                listaVendas.addVenda(new Venda(sessao));
                listaVendas.getVendas().get(numero).venderIngresso();
            } else {
                System.out.println("Esta sessão não existe.");
            }
        }
    }

    private void buscarSessoes() {
        int numero = Console.scanInt("Digite o número da sessão: ");
        if(listaSessoes.getSessoes().isEmpty()) {
            System.out.println("Não há sessões disponíveis.");
        } else {
            if(listaSessoes.sessaoValida(numero)) {
                listaSessoes.buscarSessao(numero);
            }
        }
       
    }

    private void cancelarVenda() {
        int numero = Console.scanInt("Digite o número da venda que deseja cancelar: ");
        if(listaVendas.getVendas().isEmpty()) {
            System.out.println("Não há vendas disponíveis.");
        } else {
            if(listaVendas.existeVenda(numero)){
                listaVendas.getVendas().get(numero).devolverIngresso();
            }
        
        }
    }
    
    private void listarVendas() {
       if(listaVendas.getVendas().isEmpty()) {
           System.out.println("Não há vendas disponíveis.");
       } else {
           System.out.println(String.format("%-20s", "NÚMERO DA VENDA")+"\t"
                + String.format("%-20s","NÚMERO DA SESSÃO")+  "\t"
                + String.format("%-30s","DIA E HORÁRIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS DISPONÍVEIS"));
            for (Venda venda: listaVendas.getVendas()) {
              System.out.println(String.format("%-20s", venda.getCodigoVenda())+"\t"
                + String.format("%-20s",venda.getSessao().getCodigo())+  "\t"
                + String.format("%-30s",DateUtil.dateHourToString(venda.getSessao().getHorario()))+
                      "\t"+String.format("%-10s",venda.getSessao().getSala().getNumero())
                    +"\t"+String.format("%-30s",venda.getSessao().getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", venda.getQuantidadeAssento()));
            }
       }
    }
    
    
    
    
    
   
}
