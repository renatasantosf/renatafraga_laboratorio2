/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fraga.renata.DAO;

import br.fraga.renata.dominio.Sessao;
import br.fraga.renata.dominio.Venda;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface VendaDAO {
    public void cadastrar(Venda venda);
    public void remover(Venda venda);
    public void venderIngresso(int codigo, Sessao sessao);
    public List<Venda> listar();
    public Venda buscarPorCodigo(int codigo);
    public List<String> vendasPorFilmes();
    public List<String> vendasPorHorario();
    public List<String> vendasSalas();
    public List<String> vendasPorSessoes();
    public String filmeMaisSessoes();
            
   
    
    
}
