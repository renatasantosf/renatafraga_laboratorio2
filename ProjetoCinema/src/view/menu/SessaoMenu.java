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
        String texto = "1 - CADASTRAR SESSAO\n"+
                "2 - LISTAR SESSOES\n"+
                "3 - REMOVER SESSAO\n"+
                "4 - BUSCAR SESSAO\n"+
                "5 - SAIR\n";
        return texto;
    }
}
