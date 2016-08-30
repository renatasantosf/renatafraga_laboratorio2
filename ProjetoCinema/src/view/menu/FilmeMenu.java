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
    
    public static final int OP_INSERIR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_BUSCARNOME = 3;
    public static final int OP_BUSCARGENERO = 4;
    public static final int OP_ALTERAR = 5;
    public static final int OP_REMOVER = 6;
    public static final int OP_VOLTAR = 7;
    

  
    public static String menuFilme() {
        String texto  = "1 - Inserir Filme" +
                "\n2 - Listar Filmes "+
                "\n3 - Pesquisar Filme por nome"+
                "\n4 - Pesquisar Filme por gênero"+
                "\n5 - Alterar Filme"+
                "\n6 - Remover Filme"+
                "\n7 - Voltar";
        return texto;
    }
}
