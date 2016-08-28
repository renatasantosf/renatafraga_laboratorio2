/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Medicamento;

/**
 *
 * @author lhries
 */
public class RepositorioMedicamentos {
    private List<Medicamento> listaMedicamentos;

    public RepositorioMedicamentos() {
        listaMedicamentos = new ArrayList<Medicamento>();
    }

    public boolean addMedicamentos(Medicamento medicamento) {
        return (listaMedicamentos.add(medicamento));
    }

    public List<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public boolean medicamentoExiste(int codigo) {
        for (Medicamento medicamento : listaMedicamentos) {
            if (medicamento.getCodigo()==codigo) {
                return true;
            }
        }
        return false;
    }

    public Medicamento buscarMedicamento(int codigo) {
        for (Medicamento medicamento : listaMedicamentos) {
            if (medicamento.getCodigo()==codigo) {
                return medicamento;
           }
        }
        return null;
    }    
    public Medicamento buscarMedicamentoPorNome(String nome) {
        for (Medicamento medicamento : listaMedicamentos) {
            if (medicamento.getNome().equals(nome)) {
                return medicamento;
           }
        }
        return null;
    }    
}
