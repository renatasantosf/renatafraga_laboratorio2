/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.impl_bd;

import DAO.VendaDAO;
import dominio.Sessao;
import dominio.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class VendaDAOBD implements VendaDAO {
    
    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException {
        conexao = BDUtil.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = BDUtil.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }  


    @Override
    public void cadastrar(Venda venda) {
        int codigo = 0;
        
        
        try {
            String sql = "INSERT INTO venda (codigo_sessao) "
                    + "VALUES (?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, venda.getSessao().getCodigo());
            comando.executeUpdate();
            
           
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                codigo = resultado.getInt(1);
                venda.setCodigo(codigo);
            } else{
                System.err.println("Erro de Sistema - Nao gerou o codigo conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    
    @Override
    public void remover(Venda venda) {
        try {
            String sql = "DELETE FROM venda WHERE codigo = ?";
            
            conectar(sql);
            comando.setInt(1, venda.getCodigo());
            comando.executeUpdate();
            
            sql = "UPDATE sessao SET quantidade = ?" +
                    "WHERE codigo=?";
            conectar(sql);
            comando.setInt(1,(venda.getSessao().getQuantidade()+1));
            comando.setInt(2, venda.getSessao().getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

   @Override
    public String vendasPorFilme() {
            String result = "";
               
            String sql = "SELECT FILME.TITULO, COUNT(VENDA.CODIGO) AS INGRESSOS\n" +
            "FROM FILME, VENDA,SESSAO\n" +
            "WHERE FILME.CODIGO = SESSAO.CO"
                    + "DIGO_FILME AND SESSAO.CODIGO = VENDA.CODIGO_SESSAO\n" +
            "GROUP BY FILME.TITULO;";
        try {  
             conectar(sql);

            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                int quantidade = resultado.getInt("quantidade");
                
                
                result = quantidade + "- " +titulo;
                
                return result;
              

                

               

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
        return result;
    }
 

    @Override
    public List<Venda> listar() {
       List<Venda> listaVendas = new ArrayList<>();

        String sql = "SELECT * FROM venda";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int numero = resultado.getInt("codigo");
                int cod_sessao = resultado.getInt("codigo_sessao");
                
                
                SessaoDAOBD sessaoDAOBD = new SessaoDAOBD();
                
                Venda venda = new Venda(numero, sessaoDAOBD.buscarPorCodigo(cod_sessao));

                

                listaVendas.add(venda);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as vendas no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaVendas);
    }

    @Override
    public Venda buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM venda WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int cod_sessao = resultado.getInt("codigo_sessao");
                
               
              
                SessaoDAOBD sessaoDAOBD = new SessaoDAOBD();
                
                Venda venda = new Venda(codigo, sessaoDAOBD.buscarPorCodigo(cod_sessao));


                return venda;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sala pelo numero do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
    
    
    
    @Override
     public void venderIngresso(int codigo,Sessao sessao) {
        try {
            String sql = "UPDATE sessao SET quantidade=?"
                    + "WHERE codigo=?";

            conectar(sql);
            comando.setInt(1, (sessao.getQuantidade()-1));
            comando.setInt(2,codigo);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao vender ingresso");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
/*
    @Override
    public List<String> vendasPorHorario() {
      
    }

    public List<String> vendasPorSalas() {
        
    }

    public List<String> vendasPorSessoes() {
        
    }
*/
}
