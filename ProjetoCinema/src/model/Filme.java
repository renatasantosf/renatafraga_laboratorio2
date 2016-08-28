
package model;

/**
 *
 * @author renat
 */
public class Filme {
    
    private static int CODIGO_GERADO = 1;
    private int codigo;
    private String titulo;
    private Genero genero;
    private String sinopse;

    public Filme(String titulo, Genero genero, String sinopse) {
        this.codigo = gerarCodigo();
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }
    
    public int getCodigo() {
        return this.codigo;
    }
    

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    
     private int gerarCodigo() {
        return (CODIGO_GERADO++);
    }
    
}
