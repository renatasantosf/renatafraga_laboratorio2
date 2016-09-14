/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author renat
 */
public class Venda {
    
    private static int CODIGO_GERADO = 0;
    private int codigoVenda;
    private Sessao sessao;
    private static int quantidadeAssento = 50;

    public Venda(Sessao sessao) {
        this.codigoVenda = gerarCodigo();
        this.sessao = sessao;    
    }

    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public int getQuantidadeAssento() {
        return quantidadeAssento;
    }
    
    public void venderIngresso() {
        if(quantidadeAssento>0) {
            quantidadeAssento--;
        } else {
            System.out.println("Ingressos esgostados!");
        }
    }
    
    public void devolverIngresso() {
        if(quantidadeAssento<50) {
            quantidadeAssento++;
        } else {
            System.out.println("Está sessão está livre.");
        }
    }
    
    public int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
    
}
