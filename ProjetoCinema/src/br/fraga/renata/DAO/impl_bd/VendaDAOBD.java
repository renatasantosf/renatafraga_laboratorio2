/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.DAO.impl_bd;

import br.com.fraga.renata.DAO.VendaDAO;
import br.fraga.renata.dominio.Sessao;
import br.fraga.renata.dominio.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.fraga.renata.util.DateUtil;

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
    public List<String> vendasPorFilmes() {
            List<String> listaVendasPorFilme = new ArrayList<>();
            
            String sql = "SELECT FILME.TITULO AS TITULO, COUNT(VENDA.CODIGO) AS INGRESSOS\n" +
            "FROM FILME, VENDA,SESSAO\n" +
            "WHERE FILME.CODIGO = SESSAO.CODIGO_FILME AND SESSAO.CODIGO = VENDA.CODIGO_SESSAO\n" +
            "GROUP BY FILME.TITULO;";
        try {  
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
               String titulo = resultado.getString("titulo");
               int qtdIng = resultado.getInt("ingressos");

               listaVendasPorFilme.add("Ingressos vendidos: " + qtdIng +" Filme: "+ titulo);

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
       return listaVendasPorFilme;
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

    @Override
    public List<String> vendasPorHorario() {
        List<String> listaVendasPorHorario = new ArrayList<>();
            
            String sql = "SELECT SESSAO.HORARIO AS HORARIO, COUNT(VENDA.CODIGO) AS INGRESSOS "
                    + "FROM SESSAO,VENDA WHERE VENDA.CODIGO_SESSAO = SESSAO.CODIGO \n" +
                    "GROUP BY SESSAO.CODIGO";
        try {  
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
               String datas = resultado.getString("horario");
               int qtdIng = resultado.getInt("ingressos");

               listaVendasPorHorario.add("Ingressos vendidos: " + qtdIng +" Data:: "+ 
                       DateUtil.stringToDateHour(datas));

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } catch (ParseException ex) {
            
        } finally {
            fecharConexao();
        }
        
       return listaVendasPorHorario;
    }

    @Override
    public List<String> vendasSalas() {
         List<String> listaVendasPorSalas = new ArrayList<>();
            
            String sql = "SELECT SALA.NUMERO AS NUMERO, COUNT(VENDA.CODIGO) AS INGRESSOS \n" +
            "FROM SESSAO, VENDA,SALA WHERE SESSAO.NUMERO_SALA = SALA.NUMERO AND"
                    + " VENDA.CODIGO_SESSAO = SESSAO.CODIGO GROUP BY SALA.NUMERO;";
        try {  
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
              int numero = resultado.getInt("numero");
              int qtdIng = resultado.getInt("ingressos");

               listaVendasPorSalas.add("Sala: " + qtdIng +" Ingressos vendidos: "+ qtdIng);

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
       return listaVendasPorSalas;
    }

    @Override
    public List<String> vendasPorSessoes() {
         List<String> listaVendasPorSessoes = new ArrayList<>();
            
            String sql = "SELECT SESSAO.CODIGO AS CODIGO, SESSAO.HORARIO AS HORARIO, "
                    + "COUNT(VENDA.CODIGO) AS INGRESSOS\n" +
            "FROM SESSAO, VENDA WHERE SESSAO.CODIGO = VENDA.CODIGO_SESSAO \n" +
            "GROUP BY SESSAO.CODIGO; ";
        try {  
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
              int codigo = resultado.getInt("codigo");
              String horario = resultado.getString("horario");
              int qtdIng = resultado.getInt("ingressos");

               listaVendasPorSessoes.add(codigo + " - " + DateUtil.stringToDateHour(horario) + " - "
               + "Ingressos vendidos: "+qtdIng);

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } catch (ParseException ex) {
            
        } finally {
            fecharConexao();
        }
        
       return listaVendasPorSessoes;
    }

    @Override
    public String filmeMaisSessoes() {
                  
            String sql = "SELECT FILME.CODIGO AS CODIGO, FILME.TITULO "
                    + "AS TITULO, GENERO.NOME AS NOME FROM FILME, GENERO, SESSAO \n" +
                "WHERE GENERO.CODIGO = FILME.CODIGO_GENERO AND FILME.CODIGO = SESSAO.CODIGO_FILME ";
        try {  
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
              int codigo = resultado.getInt("codigo");
              String titulo = resultado.getString("titulo");
              String genero = resultado.getString("nome");

               return "Código: "+codigo+" - "+titulo+" - "+genero;

            }
            
            
        } catch( SQLException ex) {
            
            System.err.println("Erro de Sistema - Problema ao remover sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
       return null;
    
    }

}
