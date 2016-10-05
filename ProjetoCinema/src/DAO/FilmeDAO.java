/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dominio.Filme;
import java.util.List;

/**
 *
 * @author renat
 */
public interface FilmeDAO {
    public void cadastrar(Filme filme);
    public void remover(Filme filme);
    public void alterar(Filme filme);
    public List<Filme> listar();
    public List<Filme> pesquisarGenero(String genero);
    public List<Filme> pesquisarPorNome(String nome);
    
}
