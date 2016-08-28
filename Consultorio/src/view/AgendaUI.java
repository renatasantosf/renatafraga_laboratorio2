package view;

import java.text.ParseException;
import java.util.Date;
import model.HorarioAgenda;
import model.Paciente;
import repositorio.Agenda;
import repositorio.RepositorioPacientes;
import util.Console;
import util.DateUtil;
import view.menu.AgendaMenu;

public class AgendaUI {

    private Agenda agenda;
    private RepositorioPacientes listaPacientes;

    public AgendaUI(RepositorioPacientes listaPacientes, Agenda agenda) {
        this.listaPacientes = listaPacientes;
        this.agenda = agenda;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(AgendaMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case AgendaMenu.OP_NOVO:
                    cadastrarHorario();
                    break;
                case AgendaMenu.OP_REMOVER:
                    removerHorario();
                    break;
                case AgendaMenu.OP_EDITAR:
                    editarHorario();
                    break;
                case AgendaMenu.OP_LISTAR:
                    listarHorarios();
                    break;
                case AgendaMenu.OP_CONSULTAR:
                    consultarHorarios();
                    break;                    
                case AgendaMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != AgendaMenu.OP_VOLTAR);
    }

    private void cadastrarHorario() {
        //Relaciona os pacientes:
        System.out.println("Relacione o paciente abaixo para a consulta: ");
        //Mostra todos os pacientes existentes no repositório de pacientes. Notem que o PacienteUI tem uma função exclusiva para mostrar na tela.
        new PacienteUI(listaPacientes).mostrarPacientes();
        String rg = Console.scanString("Escolha o RG do paciente: ");
        
        if(listaPacientes.pacienteExiste(rg)){
            Paciente paciente = listaPacientes.buscarPaciente(rg);
            String dataHora = Console.scanString("Data/Hora (dd/mm/aaaa hh:mm):");
            Date horario;
            try {
                horario = DateUtil.stringToDateHour(dataHora);
                if(agenda.consultaExiste(horario)) 
                    System.out.println("Nesse horario já existe uma consulta");
                else{
                    agenda.addHorario(new HorarioAgenda(horario, paciente));
                    System.out.println("Agendamento realizado com sucesso!");
                }
            } catch (ParseException ex) {
                System.out.println("Data ou hora no formato inválido!");                
            }
        }
        else
            System.out.println("Paciente não encontrado!");
    }

    public void listarHorarios() {
        System.out.println("Horarios:");
        System.out.println("-----------------");
        for(HorarioAgenda horario: agenda.getListaHorarios()){
            System.out.println(DateUtil.dateHourToString(horario.getHorario())
                    +" -> "+horario.getPaciente().getNome());
            System.out.println("-----------------");
        }
    }

    private void removerHorario() {
        //Definir o método
    }

    private void editarHorario() {
        //Definir o método
    }

    private void consultarHorarios() {
        //Definir o método
    }
}
