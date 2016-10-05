
package dominio;


/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Sala {
    
    private static int CODIGO_GERADO = 0;
    private int numero;
    private static int quantidade = 50;

    
    public Sala() {
        this.numero = gerarCodigo();
      
    }
    
    
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public int getNumero() {
        return numero;
    }
    
    
    public int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
    
    
}
