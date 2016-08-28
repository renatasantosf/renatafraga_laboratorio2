package view.menu;

public class AgendaMenu {
    public static final int OP_NOVO = 1;
    public static final int OP_REMOVER = 2;
    public static final int OP_EDITAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Agendar Paciente\n"
                + "2- Remover Horario\n"
                + "3- Editar Horario\n"
                + "4- Listar Horários\n"
                + "5- Consultar Horário por Paciente\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }        
}
