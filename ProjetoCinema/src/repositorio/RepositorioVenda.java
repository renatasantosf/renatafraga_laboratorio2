package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Venda;

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
    
    public Venda buscarVenda(int codigo) {
        for(Venda venda: vendas) {
            if(venda.getCodigoVenda() == codigo) {
                return venda;
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
