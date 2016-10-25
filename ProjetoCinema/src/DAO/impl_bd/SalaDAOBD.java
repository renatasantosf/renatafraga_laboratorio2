/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.impl_bd;

import DAO.SalaDAO;
import dominio.Genero;
import dominio.Sala;
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
public class SalaDAOBD implements SalaDAO{
    
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
    public void cadastrar(Sala sala) {
        int numero = 0;
        try {
            String sql = "INSERT INTO sala (quantidade) "
                    + "VALUES (?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, sala.getQuantidade());
            comando.executeUpdate();
            
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                numero = resultado.getInt(1);
                sala.setNumero(numero);
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
    public void remover(Sala sala) {
        try {
            String sql = "DELETE FROM sala WHERE numero = ?";

            conectar(sql);
            comando.setInt(1, sala.getNumero());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void alterar(Sala sala) {
        try {
            String sql = "UPDATE sala SET quantidade=?"
                    + "WHERE numero=?";

            conectar(sql);
            comando.setInt(1, sala.getQuantidade());
            comando.setInt(2, sala.getNumero());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao alterar Gênero no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

 

    @Override
    public List<Sala> listar() {
       List<Sala> listaSalas = new ArrayList<>();

        String sql = "SELECT * FROM sala";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int numero = resultado.getInt("Numero");
                int qtd_assent = resultado.getInt("Qtd. Assentos");
                
                

                Sala sala = new Sala(numero,qtd_assent);

                listaSalas.add(sala);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as salas no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaSalas);
    }

    @Override
    public Sala buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM sala WHERE numero = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int quantidade = resultado.getInt("Quantidade");
                
               
               Sala sala = new Sala(codigo, quantidade);

                return sala;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sala pelo numero do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

     
}
