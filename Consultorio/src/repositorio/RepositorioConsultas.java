/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Consulta;


/**
 *
 * @author lhries
 */
public class RepositorioConsultas {
    private List<Consulta> listaConsultas;

    public RepositorioConsultas() {
        listaConsultas = new ArrayList<Consulta>();
    }


    public boolean addConsulta(Consulta consulta) {
        return (listaConsultas.add(consulta));
    }
     public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public List<Consulta> getListaConsultasFinalizadas() {
        List<Consulta> consultasFinalizadas = new ArrayList<Consulta>();
        for(Consulta c: listaConsultas){
            if(c.estaFinalizada())
                consultasFinalizadas.add(c);
        }
        return(consultasFinalizadas);
    }
     
    public List<Consulta> getListaConsultasPorPaciente(String rg) {
        List<Consulta> consultasPorPaciente = new ArrayList<Consulta>();
        for(Consulta c: listaConsultas){
            if(c.getPaciente().getRg().equals(rg))
                consultasPorPaciente.add(c);
        }
        return(consultasPorPaciente);
    }

    public List<Consulta> getListaConsultasPorMes(int mes, int ano) {
        List<Consulta> consultasPorMes = new ArrayList<Consulta>();
        Calendar calendario = Calendar.getInstance();
        
        for(Consulta c: listaConsultas){
            calendario.setTime(c.getHorario());
            if(calendario.get(Calendar.MONTH)==(mes-1) 
                    && calendario.get(Calendar.YEAR)==ano)
                consultasPorMes.add(c);
        }
        return(consultasPorMes);
    }

    public boolean consultaExiste(int codigo) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getCodigo()==codigo) {
                return true;
            }
        }
        return false;
    }

    public Consulta buscarConsultasPorCodigo(int codigo) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getCodigo()==codigo) {
                return consulta;
            }
        }
        return null;
    }
    
    public List<Consulta> buscarConsultasPorPaciente(String rg) {
        List<Consulta> consultas = new ArrayList<>();
        for (Consulta consulta : listaConsultas) {
            if (consulta.getPaciente().getRg().equals(rg)) {
                consultas.add(consulta);
           }
        }
        return consultas;
    }    

    public List<Consulta> getConsultasFinalizadas(){
        List<Consulta> consultas = new ArrayList<>();
        for (Consulta consulta : listaConsultas) {
            if (consulta.estaFinalizada()) {
                consultas.add(consulta);
           }
        }
        return consultas;
    }
} 