
package view.menu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class SalaMenu {
    
     public static final int OPCADASTRAR_SALA = 1;
     public static final int OPREMOVER_SALA = 2;
     public static final int OP_LISTARSALAS = 3;
     public static final int OP_VOLTAR = 4;
    

  
    public static String getOpcoes() {
        String texto  = "1 - CADASTRAR SALA" +
                "\n2 - REMOVER SALA "+
                "\n3 - LISTAR SALAS"+
                "\n4 - SAIR";
        return texto;
    }
    
}
