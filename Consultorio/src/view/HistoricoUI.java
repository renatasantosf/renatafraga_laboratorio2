package view;

import java.util.List;
import model.Consulta;
import repositorio.RepositorioConsultas;
import util.Console;
import util.DateUtil;
import view.menu.HistoricoMenu;
import view.menu.PacienteMenu;

public class HistoricoUI {

    private RepositorioConsultas listaConsultas;

    public HistoricoUI(RepositorioConsultas listaConsultas) {
        this.listaConsultas = listaConsultas;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(HistoricoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case HistoricoMenu.OP_LISTARFINALIZADAS:
                    mostrarConsultasFinalizadas();
                    break;
                case HistoricoMenu.OP_LISTARPORPACIENTE:
                    mostrarConsultasPorPaciente();
                    break;
                case HistoricoMenu.OP_LISTARPORMESANO:
                    mostrarConsultasPorMesAno();
                    break;
                case HistoricoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != PacienteMenu.OP_VOLTAR);
    }

    public void mostrarConsultasFinalizadas() {
        for (Consulta consulta : listaConsultas.getListaConsultasFinalizadas()) {
            imprimir(consulta);
        }
    }

    private void mostrarConsultasPorPaciente() {
        String rg = Console.scanString("Digite o RG do Paciente: ");
        for (Consulta consulta : listaConsultas.getListaConsultasPorPaciente(rg)) {
            imprimir(consulta);
        }
        
    }

    private void mostrarConsultasPorMesAno() {
        int mes = Console.scanInt("Mes: ");
        int ano = Console.scanInt("Ano: ");
        for (Consulta consulta : listaConsultas.getListaConsultasPorMes(mes, ano)) {
            imprimir(consulta);
        }
    }

    private void imprimir(Consulta consulta) {
        System.out.println("\nCodigo: " + consulta.getCodigo());
        System.out.println("Data/Hora: " + DateUtil.dateHourToString(consulta.getHorario()));
        System.out.println("Paciente: " + consulta.getPaciente().getNome() + "("
                + consulta.getPaciente().getRg() + ")");
        System.out.println("Prontuario: \n" + consulta.getProntuario());
        System.out.println("Receituário: ");
        new ReceituarioUI(consulta, null).mostrarReceitas();
    }


}
