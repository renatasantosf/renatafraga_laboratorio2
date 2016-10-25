/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dominio.Sala;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface SalaDAO {
    public void cadastrar(Sala sala);
    public void remover(Sala sala);
    public void alterar(Sala sala);
    public List<Sala> listar();
    public Sala buscarPorCodigo(int codigo);

    
    
    
}
