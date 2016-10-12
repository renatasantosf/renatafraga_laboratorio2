
package dominio;


/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Sala {
    
    private int numero;
    private int quantidade;

    
    public Sala() {
        this.numero = 50;
      
    }
    
     public Sala(int numero, int quantidade) {
        this.numero = numero;
        this.quantidade = quantidade;
      
    }
    
     public Sala(int numero) {
         this.numero = numero;
    }
    
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public int getNumero() {
        return numero;
    }
    
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    
}
