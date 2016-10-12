
package dominio;

/**
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class Venda {
    

    private int codigo;
    private Sessao sessao;

    public Venda(int codigo, Sessao sessao) {
        this.codigo = codigo;
        this.sessao = sessao;    
    }
    
    public Venda(Sessao sessao) {
        this.sessao = sessao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public Sessao getSessao() {
        return sessao;
    }

       
    public void venderIngresso() {
        if(sessao.getQuantidade()>0) {
            sessao.setQuantidadeSubtracao();
            System.out.println("Venda efetuada com sucesso!");
        } else {
            System.out.println("Ingressos esgostados!");
        }
    }
    
    public void devolverIngresso() {
        if(sessao.getQuantidade()<50) {
            sessao.setQuantidadeSoma();
        } else {
            System.out.println("Esta sessao esta vazia.");
        }
    }
    
   
    
    
    
}
