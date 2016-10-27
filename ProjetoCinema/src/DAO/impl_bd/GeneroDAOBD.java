/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.impl_bd;

import DAO.GeneroDAO;
import dominio.Genero;
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
public class GeneroDAOBD implements GeneroDAO {
    
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
    public void cadastrar(Genero genero) {
        int id = 0;
        try {
            String sql = "INSERT INTO genero (nome,descricao) "
                    + "VALUES (?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, genero.getNome());
            comando.setString(2, genero.getDescricao());
            comando.executeUpdate();
            
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                genero.setCodigo(id);
            } else{
                System.err.println("Erro de Sistema - Nao gerou o codigo conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar gênero no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    
    @Override
    public void remover(Genero genero) {
        try {
            String sql = "DELETE FROM genero WHERE codigo = ?";

            conectar(sql);
            comando.setInt(1, genero.getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao remover Gênero no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void alterar(Genero genero) {
        try {
            String sql = "UPDATE genero SET nome=?, descricao=?"
                    + "WHERE codigo=?";

            conectar(sql);
            comando.setString(1, genero.getNome());
            comando.setString(2, genero.getDescricao());
            comando.setInt(3, genero.getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao alterar Gênero no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

 

    @Override
    public List<Genero> listar() {
       List<Genero> listaGeneros = new ArrayList<>();

        String sql = "SELECT * FROM genero";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                String descricao = resultado.getString("descricao");
                

                Genero gen = new Genero(codigo,nome,descricao);

                listaGeneros.add(gen);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os gêneros no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaGeneros);
    }

    @Override
    public Genero buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM genero WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("Nome");
                String descricao = resultado.getString("Descricao");
               
                Genero gen = new Genero(codigo, nome, descricao);

                return gen;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o genero pelo codigo do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

     
   
}
