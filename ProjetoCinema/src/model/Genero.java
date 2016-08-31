/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @authors Diego e Renata
 */
public class Genero {
    
    private static int CODIGO_GERADO = 0;
    private int codigo;
    private String nome;
    private String descricao;

    public Genero(String nome, String descricao) {
        this.codigo = gerarCodigo();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    private int gerarCodigo() {
        return (CODIGO_GERADO++);
    }

    @Override
    public String toString() {
        return "Genero{" + "codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + '}';
    }
    
}
