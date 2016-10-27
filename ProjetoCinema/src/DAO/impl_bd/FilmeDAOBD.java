/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.impl_bd;

import DAO.FilmeDAO;
import dominio.Filme;
import dominio.Genero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renat
 */
public class FilmeDAOBD implements FilmeDAO{
    
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
    public void cadastrar(Filme filme) {
       
         int id = 0;
        try {
            String sql = "INSERT INTO filme(titulo,codigo_genero,sinopse) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, filme.getTitulo());
            comando.setInt(2, filme.getGenero().getCodigo());
            comando.setString(3,filme.getSinopse());
            comando.executeUpdate();
            
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                filme.setCodigo(id);
            } else{
                System.err.println("Erro de Sistema - Nao gerou o codigo conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        /* int codigo = 0;
        try {
            String sql = "INSERT INTO filme(titulo,codigo_genero,sinopse) VALUES (?,?,?)";
            conectarObtendoId(sql);
            PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1,filme.getTitulo());
            comando.setInt(2,filme.getGenero().getCodigo());
            comando.setString(3,filme.getSinopse());
            
            comando.execute();
           
            ResultSet resultado = comando.getGeneratedKeys();
            resultado.next();            
           
          
            comando.executeUpdate();
           
            if (resultado.next()) {
                //seta o id para o objeto
                codigo = resultado.getInt(1);
                filme.setCodigo(codigo);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o codigo conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }*/
    }

    @Override
    public void remover(Filme filme) {
        try {
            String sql = "DELETE FROM filme WHERE codigo = ?";

            conectar(sql);
            comando.setInt(1, filme.getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao remover filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void alterar(Filme filme) {
        try {
            String sql = "UPDATE filme SET titulo =?, codigo_genero =?, sinopse=? "
                    + "WHERE codigo=?";

            conectar(sql);
            comando.setString(1, filme.getTitulo());
            comando.setInt(2, filme.getGenero().getCodigo());
            comando.setString(3, filme.getSinopse());
            comando.setInt(4, filme.getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao alterar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Filme> listar() {
          List<Filme> listaFilmes = new ArrayList<>();

        String sql = "SELECT * FROM filme";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String titulo = resultado.getString("titulo");
                int codGenero = resultado.getInt("codigo_genero");
                String sinopse = resultado.getString("sinopse");
                
                GeneroDAOBD generoDao = new GeneroDAOBD(); 
                
                Filme filme = new Filme(codigo,titulo,generoDao.buscarPorCodigo(codGenero),sinopse);

                listaFilmes.add(filme);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os gêneros no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaFilmes);
    }
    

    @Override
    public Filme pesquisarGenero(int codigo_genero) {
        String sql = "SELECT * FROM filme WHERE codigo_genero = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo_genero);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String titulo = resultado.getString("titulo");
                int codigoGenero = resultado.getInt("codigo_genero");
                String sinopse = resultado.getString("sinopse");
               
                GeneroDAOBD generoDAOBD = new GeneroDAOBD();
                        
                Filme film = new Filme(codigo,titulo, generoDAOBD.buscarPorCodigo(codigoGenero), sinopse);


                return film;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar filme pelo genero do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Filme pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM filme WHERE nome = ?";

        try {
            conectar(sql);
            comando.setString(1,nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String titulo = resultado.getString("titulo");
                int codigoGenero = resultado.getInt("codigo_genero");
                String sinopse = resultado.getString("sinopse");
               
                GeneroDAOBD generoDAOBD = new GeneroDAOBD();
                        
                Filme film = new Filme(codigo,titulo, generoDAOBD.buscarPorCodigo(codigoGenero), sinopse);


                return film;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o filme pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Filme buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM filme WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String titulo = resultado.getString("titulo");
                int codigo_gen = resultado.getInt("codigo_genero");
                String sinopse = resultado.getString("sinopse");
               
                GeneroDAOBD generoDAOBD = new GeneroDAOBD();
                
                Filme filme = new Filme(codigo,titulo,generoDAOBD.buscarPorCodigo(codigo_gen),sinopse);

                return filme;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o filme pelo codigo do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
   
}
