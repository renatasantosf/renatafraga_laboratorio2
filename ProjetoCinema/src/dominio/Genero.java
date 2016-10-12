
package dominio;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Genero {
    
   
    private int codigo;
    private String nome;
    private String descricao;

    public Genero(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
     public Genero(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }


    public String getNome() {
        return nome;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
   

   public int getCodigo() {
       return codigo;
   }
    
}
