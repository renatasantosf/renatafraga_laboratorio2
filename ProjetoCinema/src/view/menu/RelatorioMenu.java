/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author renat
 */
public class RelatorioMenu {
    
     public static final int OPFILMESMAISVENDIDOS = 1;
     public static final int OPSESSAOMAISLOTADA = 2;
     public static final int OPFILMEMENOSVENDIDO = 3;
     public static final int OPSESSAOMENOSLOTADA = 4;
     public static final int SAIR = 5;
    

  
    public static String getOpcoes() {
        String texto  = "1 - FILME MAIS VENDIDO" +
                "\n2 - SESSÃO MAIS LOTADA "+
                "\n3 - FILME MENOS VENDIDO"+
                "\n4 - SESSÃO MENOS LOTADA"+
                "\n5 - SAIR";
        return texto;
    }
    
    
}
