
package repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Sessao;
import util.DateUtil;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class RepositorioSessao {

    private List<Sessao> sessoes;

    public RepositorioSessao() {
        sessoes = new ArrayList<>();
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public boolean addSessao(Sessao sessao) {
        return (sessoes.add(sessao));

    }
    
    public void listaSessoes() {
       if(sessoes.isEmpty()) {
            System.out.println("Nao ha sessoes disponiveis.");
        } else {
            System.out.println(String.format("%-20s", "NUMERO DA SESSAO") + "\t"
                + String.format("%-30s","DIA E HORARIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS"));
            for (Sessao sessao : sessoes) {
              System.out.println(String.format("%-20s", sessao.getCodigo()) + "\t"
                + String.format("%-30s",DateUtil.dateHourToString(sessao.getHorario()))+"\t"+String.format("%-10s",sessao.getSala().getNumero())
                    +"\t"+String.format("%-30s",sessao.getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", sessao.getQuantidade()));
            }
        }
    }

    public void buscarSessao(int codigo) {
        for (Sessao sessao : sessoes) {
                if(sessao.getCodigo()==codigo) {
                    System.out.println(String.format("%-20s", "NUMERO DA SESSAO") + "\t"
                + String.format("%-30s","DIA E HORARIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS"));
                    System.out.println(String.format("%-20s", sessao.getCodigo()) + "\t"
                + String.format("%-30s",DateUtil.dateHourToString(sessao.getHorario()))+"\t"+String.format("%-10s",sessao.getSala().getNumero())
                    +"\t"+String.format("%-30s",sessao.getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", sessao.getQuantidade()));
                    break;
                } 
            }

    }

    public boolean existeSessao(Date horario,int numero) {
            for (Sessao sessao : sessoes) {
                if (sessao.getHorario().equals(horario) && sessao.getSala().getNumero()==numero) {
                    return true;
                }
            }
            return false;

    }
    
     public boolean sessaoValida(int codigo) {
            for (Sessao sessao : sessoes) {
                if (sessao.getCodigo()==codigo) {
                    return true;
                }
            }
            return false;

    }
    
}
