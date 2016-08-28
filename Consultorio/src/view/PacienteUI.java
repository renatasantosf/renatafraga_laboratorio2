package view;

import java.text.ParseException;
import model.Paciente;
import repositorio.RepositorioPacientes;
import util.Console;
import util.DateUtil;
import view.menu.PacienteMenu;

/**
 *
 * @author lhries
 */
public class PacienteUI {

    private RepositorioPacientes lista;

    public PacienteUI(RepositorioPacientes lista) {
        this.lista = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(PacienteMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case PacienteMenu.OP_CADASTRAR:
                    cadastrarPaciente();
                    break;
                case PacienteMenu.OP_LISTAR:
                    mostrarPacientes();
                    break;
                case PacienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != PacienteMenu.OP_VOLTAR);
    }

    private void cadastrarPaciente() {
        String rg = Console.scanString("RG: ");
        if (lista.pacienteExiste(rg)) {
            System.out.println("RG já existente no cadastro");
        } else {
            String nome = Console.scanString("Nome: ");
            String dataString = Console.scanString("Data de Nascimento: ");
            try {
                lista.addPacientes(new Paciente(nome, rg, DateUtil.stringToDate(dataString)));
                System.out.println("Paciente " + nome + " cadastrado com sucesso!");
            
            } catch (ParseException ex) {
                System.out.println("Formato de Data inválido!");
            }
        }
    }


public void mostrarPacientes() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "RG") + "\t"
                + String.format("%-20s", "|NOME") + "\t"
                + String.format("%-20s", "|DATA DE NASCIMENTO"));
        for (Paciente paciente : lista.getListaPacientes()) {
            System.out.println(String.format("%-10s", paciente.getRg()) + "\t"
                    + String.format("%-20s", "|" + paciente.getNome()) + "\t"
                    + String.format("%-20s", "|" + DateUtil.dateToString(paciente.getDataNascimento())));
        }

    }
}
