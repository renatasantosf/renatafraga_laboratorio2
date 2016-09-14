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
public class SalaMenu {
    
     public static final int OPCADASTRAR_SALA = 1;
     public static final int OPREMOVER_SALA = 2;
     public static final int OP_LISTARSALAS = 3;
     public static final int OP_VOLTAR = 7;
    

  
    public static String getOpcoes() {
        String texto  = "1 - Cadastrar Sala" +
                "\n2 - Remover Sala "+
                "\n3 - Listar Salas"+
                "\n7 - Sair";
        return texto;
    }
    
}
