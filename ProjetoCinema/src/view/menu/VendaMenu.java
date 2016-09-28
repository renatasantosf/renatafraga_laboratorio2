package view.menu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class VendaMenu {
    public static final int OP_VENDERINGRESSO = 1;
    public static final int OP_BUSCARSESSOES = 2;
    public static final int OP_LISTARVENDAS = 3;
    public static final int OP_CANCELARVENDA = 4;
    public static final int SAIR = 5;
    
    public static String getOpcoes() {
        String texto = "1 - VENDER INGRESSO \n"+
                "2 - BUSCAR SESSOES \n"+
                "3 - LISTAR VENDAS \n"+
                "4 - CANCELAR VENDA \n"+
                "5 - SAIR";
        return texto;
    }
   
}
