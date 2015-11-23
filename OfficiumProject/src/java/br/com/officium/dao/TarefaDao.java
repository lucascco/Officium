/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao;

import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import java.util.List;


/**
 *
 * @author Lucas Corrêa
 */
public interface TarefaDao extends GenericDao<Tarefa>{
    public List<Tarefa> consultarVisualizarTarefa(int start, int maxResult, Tarefa obj, String campoOrd, Boolean asc) throws Exception;
}
