/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dominio.Venda;
import java.util.List;

/**
 *
 * @author renat
 */
public interface VendaDAO {
    public void cadastrar(Venda venda);
    public void remover(Venda venda);
    public void alterar(Venda venda);
    public List<Venda> listar();
    public Venda buscarPorCodigo(int codigo);
   
    
    
}
