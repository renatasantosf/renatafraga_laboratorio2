/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.negocio;

import br.com.fraga.renata.DAO.FilmeDAO;
import br.fraga.renata.DAO.impl_bd.FilmeDAOBD;
import br.fraga.renata.dominio.Filme;
import java.util.List;

/**
 *
 * @author renat
 */
public class FilmeNegocio {
    
    private FilmeDAO filmeDao;
    
    public FilmeNegocio() {
        filmeDao = new FilmeDAOBD();
    }
        
    public void Salvar(Filme f) throws NegocioException {
        this.validarCamposObrigatorios(f);
        this.validarCodigoExistente(f);
        filmeDao.salvar(f);
    }
    
    public List<Filme> listar() {
        return(filmeDao.listar());
    }
    
    public void deletar(Filme filme) throws NegocioException {
        if(filme == null || filme.getCodigo() == null) {
            throw new NegocioException("Filme não existe!");
        }
        filmeDao.deletar(filme);
    }
    
    public void atualizar(Filme filme) throws NegocioException {
        if(filme == null || filme.getCodigo == null) {
            throw new NegocioException("Filme não existe!");
        }
        this.validarCamposObrigatorios(filme);
        filmeDao.atualizar(filme);
    }
    
    public Filme procurarPorCodigo(int codigo) throws NegocioException {
        if(codigo == 0) {
            throw new NegocioException("Campo Código não informado.");
            
            
        }
        Filme filme = filmeDao.buscarPorCodigo(codigo);
        if(filme == null) {
            throw new NegocioException("Filme não encontrado.");
        }
        return (filme);
    }
    
    public List<Filme> procurarPorTitulo(String titulo) throws NegocioException {
        if (titulo == null || titulo.isEmpty()) {
            throw new NegocioException("Campo Título não informado");
        }
        return(filmeDao.pesquisarPorTitulo(titulo));
    }

    public boolean filmeExiste(int codigo) {
        Filme filme = filmeDao.procurarPorCodigo(codigo);
        return (filme != null);
    }

    private void validarCamposObrigatorios(Filme f) throws NegocioException {
        if (f.getCodigo() == 0) {
            throw new NegocioException("Campo Código não informado.");
        }

        if (f.getTitulo() == null || f.getTitulo().isEmpty()) {
            throw new NegocioException("Campo Título não informado.");
        }
    }

    private void validarCodigoExistente(Filme f) throws NegocioException {
        if (filmeExiste(f.getCodigo())) {
            throw new NegocioException("Código já existente.");
        }
    }
    
}
