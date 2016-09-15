package view.menu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class SessaoMenu {
    public static final int OP_CADASTRARSESSAO = 1;
    public static final int OP_LISTARSESSOES = 2;
    public static final int OP_REMOVERSESSAO = 3;
    public static final int OP_BUSCARSESSAO = 4;
    public static final int SAIR = 5;
    
    public static String getOpcoes() {
        String texto = "1 - CADASTRAR SESSÃO\n"+
                "2 - LISTAR SESSÕES\n"+
                "3 - REMOVER SESSÃO\n"+
                "4 - BUSCAR SESSÃO\n"+
                "5 - SAIR\n";
        return texto;
    }
}
