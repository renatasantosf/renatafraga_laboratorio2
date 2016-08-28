package view.menu;

public class ReceituarioMenu {
    public static final int OP_ADICIONAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_FINALIZAR = 0;
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Adicionar Item de Receituario\n"
                + "2- Listar Itens de Receituario\n"
                + "0- Finalizar Receituario\n" );   
    }
}
