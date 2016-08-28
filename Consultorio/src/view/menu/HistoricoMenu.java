package view.menu;

public class HistoricoMenu {
    public static final int OP_LISTARFINALIZADAS = 1;
    public static final int OP_LISTARPORPACIENTE = 2;
    public static final int OP_LISTARPORMESANO = 3;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Visualizar Consultas Finalizadas\n"
                + "2- Visualizar Consultas Por Paciente\n"
                + "3- Visualizar Consultas do Mes\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }      
    
}
