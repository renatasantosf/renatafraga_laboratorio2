
package repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Sessao;

/**
 *
 * @author renat
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

    public void buscarSessao(int codigo) {
        for (Sessao sessao : sessoes) {
                if(sessao.getCodigo()==codigo) {
                    System.out.println(String.format("%-10s", "NÚMERO DA SESSÃO") + "\t"
                + String.format("%-30s","DIA E HORÁRIO")+"\t"+String.format("%-10s","SALA")
                    +"\t"+String.format("%-30s","FILME")  + 
                     "\t"+ String.format("%-30s", "ASSENTOS"));
                    System.out.println(String.format("%-10s", sessao.getCodigo()) + "\t"
                + String.format("%-30s",sessao.getHorario())+"\t"+String.format("%-10s",sessao.getSala().getNumero())
                    +"\t"+String.format("%-30s",sessao.getFilme().getTitulo())  + 
                     "\t"+ String.format("%-30s", sessao.getQuantidade()));
                    break;
                } 
            }

    }

    public boolean existeSessao(Date horario) {
        if (sessoes.isEmpty()) {
            return false;
        } else {
            for (Sessao sessao : sessoes) {
                if (sessao.getHorario().compareTo(horario)==1) {
                    return true;
                }
            }
        }
        return false;
   
    }

    public void removerSessao(int codigo) {
        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getCodigo() == codigo) {
                sessoes.remove(i);
                break;
            } else {
                System.out.println("Código inválido.");
                break;
            }
        }
    }
}
