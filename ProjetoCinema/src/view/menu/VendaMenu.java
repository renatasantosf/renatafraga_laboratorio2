
package view.menu;

/**
 *
 * @author 631510049
 */
public class VendaMenu {
    public static final int OP_VENDERINGRESSO = 1;
    public static final int OP_BUSCARSESSOES = 2;
    public static final int OP_CANCELARVENDA = 3;
    public static final int SAIR = 4;
    
    public static String getOpcoes() {
        String texto = " 1 - Vender Ingresso "+
                "2 - Buscar Sessões "+
                "3 - Cancelar venda";
        return texto;
    }
   
}
