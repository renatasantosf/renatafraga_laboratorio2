/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fraga.renata.DAO;

import br.fraga.renata.dominio.Filme;
import br.fraga.renata.dominio.Genero;
import java.util.List;
import javafx.scene.control.ComboBox;

/**
 *
 * @author renat
 */
public interface FilmeDAO {
    public void cadastrar(Filme filme);
    public void remover(Filme filme);
    public void alterar(Filme filme);
    public List<Filme> listar();
    public Filme pesquisarGenero(int genero);
    public Filme pesquisarPorNome(String nome);
    public Filme buscarPorCodigo(int codigo);
    public void popularCombo(ComboBox<Genero> combo);
}
