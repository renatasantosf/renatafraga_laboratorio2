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
    
    private static int CODIGO_GERADO = 1;
    private int codigoVenda;
    private Secao secao;
    private static int quantidadeAssento;

    public Venda(Secao secao) {
        this.codigoVenda = gerarCodigo();
        this.secao = secao;
        quantidadeAssento = 50;
        
    }

    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public Secao getSecao() {
        return secao;
    }

    public static int getQuantidadeAssento() {
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
