/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author Lucas Corrêa
 * @param <T> Objeto gerenciado pelo Dao
 */
public interface GenericDao<T> {
    
    public List<T> consultarTodos() throws Exception;
    public List<T> consultarTodosOrdenados(String camposOrdenacao) throws Exception;
    public void salvar(final T obj) throws Exception;
    public void salvarVarios(final T... objs) throws Exception;
    public void excluir(final T obj) throws Exception;
    public void atualizarCampos(Map<String, String> valores, Long id) throws Exception;
    public abstract List<T> consultar(int start, int maxResult, T obj) throws Exception;
    public abstract Long consultarQtd(T obj) throws Exception;
    
    
}
