/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Corrêa
 */
public interface GenericDao<T> {
    
    public List<T> consultarTodos();
    public List<T> consultarTodosOrdenados();
    public void salvar(final T obj);
    public void excluir(final T obj);
    public void atualizarCampos(Map<String, String> valores, Long id);
    public void consultar(int start, int maxResult, T obj);
    public Long consultarQtd(T obj);
    
    
    
}
