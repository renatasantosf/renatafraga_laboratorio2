
package view.menu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class FilmeMenu {
    
    public static final int OP_INSERIRFILME= 1;
    public static final int OP_INSERIRGENERO = 2;
    public static final int OP_BUSCARFILME = 3;
    public static final int OP_BUSCARGENERO = 4;
    public static final int OP_LISTARFILMES = 5;
    public static final int OP_LISTARGENEROS = 6;
    public static final int OP_REMOVERFILME = 7;
    public static final int OP_REMOVERGENERO = 8;
    public static final int OP_VOLTAR = 9;
    

  
    public static String getOpcoes() {
        String texto  = "1 - INSERIR FILME " +
                "\n2 - INSERIR GENERO "+
                "\n3 - BUSCAR FILME"+
                "\n4 - BUSCAR GENERO "+
                "\n5 - LISTAR TODOS OS FILMES "+
                "\n6 - LISTAR TODOS OS GENEROS "+
                "\n7 - REMOVER FILME "+
                "\n8 - REMOVER GENERO "+
                "\n9 - VOLTAR";
        return texto;
    }
}
