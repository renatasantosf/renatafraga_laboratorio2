
package dominio;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Filme {
    
   
    private int codigo;
    private String titulo;
    private String sinopse;
    private Genero genero;
    
    public Filme(int codigo, String titulo, Genero genero, String sinopse) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
    }
    
      public Filme(String titulo, Genero genero, String sinopse) {
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
    }

    public Filme(int codigo, String titulo, String sinopse) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.sinopse = sinopse;
    }

    public Filme(String titulo) {
       this.titulo  = titulo;
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
    
     

    @Override
    public String toString() {
        return  titulo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
        
    }
    
    
}
