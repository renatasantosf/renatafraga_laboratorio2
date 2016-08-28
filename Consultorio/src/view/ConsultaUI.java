/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Consulta;
import model.Paciente;
import repositorio.RepositorioConsultas;
import repositorio.RepositorioMedicamentos;
import repositorio.RepositorioPacientes;
import util.Console;
import util.DateUtil;
import view.menu.ConsultaMenu;
import view.menu.PacienteMenu;

/**
 *
 * @author lhries
 */
public class ConsultaUI {

    private RepositorioPacientes listaPacientes;
    private RepositorioMedicamentos listaMedicamentos;
    private RepositorioConsultas listaConsultas;

    public ConsultaUI(RepositorioPacientes listaPacientes,
            RepositorioMedicamentos listaMedicamentos,
            RepositorioConsultas listaConsultas) {
        this.listaPacientes = listaPacientes;
        this.listaMedicamentos = listaMedicamentos;
        this.listaConsultas = listaConsultas;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(ConsultaMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case ConsultaMenu.OP_CADASTRAR:
                    cadastrarConsulta();
                    break;
                case ConsultaMenu.OP_LISTAR:
                    mostrarConsultas();
                    break;
                case ConsultaMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != PacienteMenu.OP_VOLTAR);
    }

    private void cadastrarConsulta() {
        //Relaciona os pacientes:
        System.out.println("Relacione o paciente abaixo para a consulta: ");
        //Mostra todos os pacientes existentes no repositório de pacientes. Notem que o PacienteUI tem uma função exclusiva para mostrar na tela.
        new PacienteUI(listaPacientes).mostrarPacientes();
        String rg = Console.scanString("Escolha o RG do paciente: ");
        Paciente paciente = listaPacientes.buscarPaciente(rg);

        //Relaciona o prontuário:
        String prontuario = Console.scanString("Escreva o prontuario: ");

        //Instancia a consulta (com o horário atual e sem medicamentos)
        Consulta consulta = new Consulta(paciente, prontuario);

        //Chama o menu receituario para cadastrar os medicamentos na consulta.
        new ReceituarioUI(consulta, listaMedicamentos).executar();

        //Tarefa do aluno: caso permita o modo de edição, a finalização não deve ser efetuada nesse método
        consulta.finalizar();

        //Adiciona consulta no repositório
        listaConsultas.addConsulta(consulta);

    }

    public void mostrarConsultas() {
        for (Consulta consulta : listaConsultas.getListaConsultas()) {
            System.out.println("\nCodigo: " + consulta.getCodigo());
            System.out.println("Data/Hora: "+ DateUtil.dateHourToString(consulta.getHorario()));
            System.out.println("Paciente: " + consulta.getPaciente().getNome() + "("
                    + consulta.getPaciente().getRg() + ")");
            System.out.println("Prontuario: \n" + consulta.getProntuario());
            System.out.println("Receituário: ");
            new ReceituarioUI(consulta, null).mostrarReceitas();
            if (consulta.estaFinalizada()) {
                System.out.println("[ENCERRADA]");
            }

        }
    }
}
