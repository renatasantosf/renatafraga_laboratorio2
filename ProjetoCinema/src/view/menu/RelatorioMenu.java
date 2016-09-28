
package view.menu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class RelatorioMenu {
    
     public static final int OPVENDASPORFILME = 1;
     public static final int OPVENDASPORHORARIO = 2;
     public static final int OPVENDASPORSALA = 3;
     public static final int OPFILMEMAISVENDIDO = 4;
     public static final int OPSALAMAISUTILIZADA = 5;
     public static final int SAIR = 6;
    

  
    public static String getOpcoes() {
        String texto  = "1 - VENDAS POR FILME" +
                "\n2 - VENDAS POR HORÁRIO "+
                "\n3 - VENDAS POR SALA"+
                "\n4 - FILME MAIS VENDIDO"+
                "\n5 - SALA MAIS UTILIZADA"+
                "\n6 - SAIR";
        return texto;
    }
    
    
}
