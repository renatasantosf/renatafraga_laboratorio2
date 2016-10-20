/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dominio.Sessao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author renat
 */
public interface SessaoDAO {
    public void cadastrar(Sessao sessao);
    public void remover(Sessao sessao);
    public void alterar(Sessao sessao);
    public List<Sessao> listar();
    public Sessao buscarPorCodigo(int codigo);

    public boolean seHaSessao(java.sql.Date horario, int numero);
    
}
