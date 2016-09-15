
package model;


/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Sala {
    
    private static int CODIGO_GERADO = 0;
    private int numero;
    private int quantidade;

    
    public Sala() {
        this.numero = gerarCodigo();
        this.quantidade =  50;
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
