
package DAO.impl_bd;

import DAO.SessaoDAO;
import dominio.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author renat
 */
public class SessaoDAOBD implements SessaoDAO{
    
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
    public void cadastrar(Sessao sessao) {
       
        int codigo = 0;
        try {
            String sql = "INSERT INTO sessao (horario,numero_sala,codigo_filme,quantidade) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(sessao.getHorario().getTime());
            comando.setDate(1, dataSql);
            comando.setInt(2, sessao.getSala().getNumero());
            comando.setInt(3, sessao.getFilme().getCodigo());
            comando.setInt(4, 50);
            comando.executeUpdate();
            
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                codigo = resultado.getInt(1);
                sessao.setCodigo(codigo);
            } else{
                System.err.println("Erro de Sistema - Nao gerou o codigo conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar sessao no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    
    @Override
    public void remover(Sessao sessao) {
        try {
            String sql = "DELETE FROM sessao WHERE codigo = ?";

            conectar(sql);
            comando.setInt(1, sessao.getCodigo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao remover sessão no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void alterar(Sessao sessao) {
        try {
            String sql = "UPDATE sessao SET horario=?,numero_sala=?, codigo_filme=?,quantidade=?"
                    + "WHERE codigo=?";

            conectar(sql);
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(sessao.getHorario().getTime());
            comando.setDate(1, dataSql);
            comando.setInt(2, sessao.getSala().getNumero());
            comando.setInt(3, sessao.getFilme().getCodigo());
            comando.setInt(4, sessao.getQuantidade());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao alterar sessao no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

 

    @Override
    public List<Sessao> listar() {
       List<Sessao> listaSessoes = new ArrayList<>();

        String sql = "SELECT * FROM sessao";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("Codigo");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("Horario ");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int codigo_sala = resultado.getInt("Numero sala");
                int codigo_filme = resultado.getInt("Filme: ");
                int quantidade = resultado.getInt("Quantidade: ");
                
                SalaDAOBD salaDAOBD = new SalaDAOBD();
                FilmeDAOBD filmeDAOBD = new FilmeDAOBD();
                
                Sessao sessao = new Sessao(codigo, dataUtil, salaDAOBD.buscarPorCodigo(codigo_sala), filmeDAOBD.buscarPorCodigo(codigo_filme));

                listaSessoes.add(sessao);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar sesosoes no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaSessoes);
    }

    @Override
    public Sessao buscarPorCodigo(int codigo) {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT * FROM sessao WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("Horario: ");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int numero_sala = resultado.getInt("Sala: ");
                int codigo_filme = resultado.getInt("Codigo filme: ");
                int quantidade = resultado.getInt("Quantidade: ");
                
                
                
               SalaDAOBD salaDAOBD = new SalaDAOBD();
                FilmeDAOBD filmeDAOBD = new FilmeDAOBD();
                
                Sessao sessao = new Sessao(codigo, dataUtil, salaDAOBD.buscarPorCodigo(numero_sala), filmeDAOBD.buscarPorCodigo(codigo_filme));

                return sessao;
                    

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
    public boolean seHaSessao(java.sql.Date horario, int numero) {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT * FROM sessao WHERE numero_sala = ? and horario = ?";

        try {
            conectar(sql);
            comando.setInt(1, numero);
            comando.setDate(2,horario);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("Horario: ");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int numero_sala = resultado.getInt("Sala: ");
                int codigo_filme = resultado.getInt("Codigo filme: ");
                int quantidade = resultado.getInt("Quantidade: ");
                
                
                
                SalaDAOBD salaDAOBD = new SalaDAOBD();
                FilmeDAOBD filmeDAOBD = new FilmeDAOBD();
                
                Sessao sessao = new Sessao(numero, dataUtil, salaDAOBD.buscarPorCodigo(numero_sala), filmeDAOBD.buscarPorCodigo(codigo_filme));

                return true;
                    

            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as sessao pelo numero do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        
    }
}

     
    
