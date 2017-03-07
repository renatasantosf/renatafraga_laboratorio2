
package br.fraga.renata.dominio;


/**
 *
 * @authors Renata Fraga
 */
public class Sala {
    
    private int numero;
    private Cadeira cadeira[];

    
    public Sala() {
        this.cadeira = new Cadeira[50];
      
    }
    
    /* public Sala(int numero, int quantidade) {
    this.numero = numero;
    this.quantidade = quantidade;
    
    }*/
    
     public Sala(int numero) {
         this.numero = numero;
    }
    
    
    public Cadeira[] getCadeira() {
        return this.cadeira;
    }
    
    public int getNumero() {
        return numero;
    }
    
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return ""+numero;
    }
    
    
    
}
