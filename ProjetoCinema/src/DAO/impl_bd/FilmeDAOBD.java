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
/*public class FilmeDAOBD implements FilmeDAO {
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
                id = resultado.getInt(1);
                filme.setCodigo(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void remover(Filme filme) {
        try {
            String sql = "DELETE FROM filme WHERE titulo = ?";

            conectar(sql);
            comando.setString(1, filme.getTitulo());
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
            String sql = "UPDATE filme SET titulo =?, genero =?, sinopse=? "
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
                int codigo = resultado.getInt("Codigo");
                String titulo = resultado.getString("Titulo");
               // Genero genero = resultado.getString("Genero");
                String sinopse = resultado.getString("Sinopse");
               

                Filme film = new Filme(codigo,titulo, genero, sinopse);

                listaFilmes.add(film);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaFilmes);
    }
    

    @Override
    public List<Filme> pesquisarGenero(int codigo_genero) {
        String sql = "SELECT * FROM filme WHERE codigo_genero = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo_genero);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String codigo = resultado.getString("Codigo");
                String titulo = resultado.getString("Titulo");
                
                Filme film = new Filme(codigo,titulo);

                return film;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Filme> pesquisarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
*/