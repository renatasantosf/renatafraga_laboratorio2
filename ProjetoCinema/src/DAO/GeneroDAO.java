/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dominio.Genero;
import java.util.List;

/**
 *
 * @author renat
 */
public interface GeneroDAO {
  public void cadastrar(Genero genero);
    public void remover(Genero genero);
    public void alterar(Genero genero);
    public List<Genero> listar();

    public Genero buscarPorCodigo(int codigo);
    
    
    
    
}
