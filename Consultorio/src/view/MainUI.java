package view;

import repositorio.Agenda;
import repositorio.RepositorioConsultas;
import repositorio.RepositorioMedicamentos;
import repositorio.RepositorioPacientes;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author lhries
 */
public class MainUI {
    private RepositorioPacientes listaPacientes;
    private RepositorioMedicamentos listaMedicamentos;
    private RepositorioConsultas listaConsultas;
    private Agenda agenda;

    public MainUI() {
        listaPacientes = new RepositorioPacientes();
        listaMedicamentos = new RepositorioMedicamentos();
        listaConsultas = new RepositorioConsultas();
        agenda = new Agenda();
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case MainMenu.OP_PACIENTES:
                    new PacienteUI(listaPacientes).executar();
                    break;
                case MainMenu.OP_MEDICAMENTOS:
                    new MedicamentoUI(listaMedicamentos).executar();
                    break;
                case MainMenu.OP_CONSULTA:
                    new ConsultaUI(listaPacientes,listaMedicamentos,listaConsultas).executar();
                    break;
                case MainMenu.OP_AGENDA:
                    new AgendaUI(listaPacientes,agenda).executar();
                    break;
                case MainMenu.OP_HISTORICO:
                    new HistoricoUI(listaConsultas).executar();
                    break;
                case MainMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!!!");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MainMenu.OP_SAIR);
    }

}
