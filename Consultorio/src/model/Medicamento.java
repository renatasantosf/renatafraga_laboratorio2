package model;

/**
 *
 * @author lhries
 */
public class Medicamento {
    private static int CODIGO_GERADO=1;
    private int codigo;
    private String nome;
    private String descricao;

    public Medicamento(String nome, String descricao) {
        this.codigo = generateCodigo();
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
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
    
    
    private int generateCodigo(){
        return(CODIGO_GERADO++);
    }    
}
