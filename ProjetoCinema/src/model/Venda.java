
package model;

/**
 * @authors Diego Pinto e Renata Fraga
 * 
 */
public class Venda {
    
    private static int CODIGO_GERADO = 0;
    private int codigoVenda;
    private Sessao sessao;

    public Venda(Sessao sessao) {
        this.codigoVenda = gerarCodigo();
        this.sessao = sessao;    
    }

    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    public int getCodigoVenda() {
        return codigoVenda;
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
    
    public int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
    
}
