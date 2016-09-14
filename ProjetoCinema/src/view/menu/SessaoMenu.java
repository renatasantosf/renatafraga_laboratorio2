/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author 631510049
 */
public class SessaoMenu {
    public static final int OP_CADASTRARSESSAO = 1;
    public static final int OP_LISTARSESSOES = 2;
    public static final int OP_REMOVERSESSAO = 3;
    public static final int OP_BUSCARSESSAO = 4;
    public static final int SAIR = 5;
    
    public static String getOpcoes() {
        String texto = "1 - Cadastrar Sessão\n"+
                "2 - Listar sessões\n"+
                "3 - Remover Sessão\n"+
                "4 - Buscar Sessão\n"+
                "5 - SAIR\n";
        return texto;
    }
}
