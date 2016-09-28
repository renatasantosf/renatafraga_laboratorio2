package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Venda;
import util.DateUtil;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class RepositorioVenda {
    private List<Venda> vendas;
    
    
    public RepositorioVenda() {
        vendas = new ArrayList<>();
    }
    
    public boolean addVenda(Venda venda) {
        venda.venderIngresso();
       return (vendas.add(venda));
    }
    
    public List<Venda> getVendas() {
        return vendas;
    }
    
    public String buscarVenda(int codigo) {
        for(Venda venda: vendas) {
            if(venda.getCodigoVenda() == codigo) {
                return String.format("%-10s", "CODIGO") + "\t"
                + String.format("%-20s","CODIGO SESSAO")+"\t"+String.format("%-30s","DIA E HORA")  +
                     "\t"+ String.format("%-50s", "NOME DO FILME")
                 + "\n" + String.format("%-10s",vendas.get(codigo).getCodigoVenda())+"\t"+String.format("%-20s",
                         vendas.get(codigo).getSessao().getCodigo())  +
                     "\t"+ String.format("%-30s", 
                     DateUtil.dateHourToString(vendas.get(codigo).getSessao().getHorario())+"\t"+ String.format
                     ("%-50s", vendas.get(codigo).getSessao().getFilme().getTitulo()));
            }
        }
        return null;
    }
    
    
    public boolean existeVenda(int codigo) {
        if(vendas.isEmpty()) {
            return false;
        } else {
            for(Venda venda: vendas) {
                if(venda.getCodigoVenda()==codigo) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void removerVenda(int codigo) {
        for(int i=0;i<vendas.size();i++) {
           if(vendas.get(i).getCodigoVenda()==codigo) {
               vendas.remove(i);
               System.out.println("Venda cancelada com sucesso!");
               break;
           } else {
               System.out.println("Código inválido!");
           }
        }
        
        
    }
}
