package view.menu;

public class ConsultaMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Nova Consulta\n"
                + "2- Mostrar Consultas\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }      
}
