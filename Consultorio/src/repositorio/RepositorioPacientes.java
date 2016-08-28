/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Paciente;

/**
 *
 * @author lhries
 */
public class RepositorioPacientes {

    private List<Paciente> pacientes;
    
    public RepositorioPacientes() {
        pacientes = new ArrayList<Paciente>();
    }

    public boolean addPacientes(Paciente paciente) {
        return (pacientes.add(paciente));
    }

    public List<Paciente> getListaPacientes() {
        return pacientes;
    }

    public boolean pacienteExiste(String rg) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRg().equals(rg)) {
                return true;
            }
        }
        return false;
    }

    public Paciente buscarPaciente(String rg) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRg().equals(rg)) {
                return paciente;
           }
        }
        return null;
    }
} 
