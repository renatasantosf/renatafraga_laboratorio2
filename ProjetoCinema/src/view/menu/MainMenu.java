/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.menu;

/**
 *
 * @author 80105681
 */
public class MainMenu {
    
    public static final int OP_FILME = 1;
    public static final int OP_SALA = 2;
    public static final int OP_SESSAO = 3;
    public static final int OP_VENDA = 4;
    public static final int SAIR = 5;
    
    public static String getOpcoes() {
        String texto = "1 - FILMES "+
                "\n2 - SALAS"+
                "\n3 - SESSÕES"+
                "\n4 - VENDAS"+
                "\n5 - SAIR";
        return texto;
    }
    
}
