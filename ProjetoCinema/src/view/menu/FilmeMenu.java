/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author renat
 */
public class FilmeMenu {
    
    public static final int OP_INSERIRFILME= 1;
    public static final int OP_INSERIRGENERO = 2;
    public static final int OP_BUSCARNOME = 3;
    public static final int OP_BUSCARGENERO = 4;
    public static final int OP_LISTARFILMES = 5;
    public static final int OP_LISTARGENEROS = 6;
    public static final int OP_REMOVERFILME = 7;
    public static final int OP_REMOVERGENERO = 8;
    public static final int OP_VOLTAR = 9;
    

  
    public static String menuFilme() {
        String texto  = "1 - Inserir Filme" +
                "\n2 - Inserir Gênero "+
                "\n3 - Pesquisar Filme por nome"+
                "\n4 - Pesquisar Filme por gênero"+
                "\n5 - Listar todos os filmes"+
                "\n6 - Listar todos os Gêneros"+
                "\n7 - Remover Filme"+
                "\n8 - Remover Gênero"+
                "\n9 - Voltar";
        return texto;
    }
}
