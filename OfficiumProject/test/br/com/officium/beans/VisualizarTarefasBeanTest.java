/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.impl.TarefaDaoImpl;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Usuario;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Lucas Corrêa
 */
public class VisualizarTarefasBeanTest {

    private TarefaDao tarefaDao;
    
    
    public VisualizarTarefasBeanTest() {
    }
    
    @Test
    public void buscarTarefasPorStatusEUsuario() throws Exception{
        Tarefa tarefa = new Tarefa();
        Usuario usuario = new Usuario();
        StatusTarefa statusTarefa = new StatusTarefa();
        usuario.setId(1l);
        statusTarefa.setId(1l);
        
        tarefa.setUsuario(usuario);
        tarefa.setStatusTarefa(statusTarefa);
        
        List<Tarefa> tarefas = this.getTarefaDao().consultar(0, 0, tarefa);
        
        Assert.assertTrue(tarefas.size() > 1);
        
    }

    public TarefaDao getTarefaDao() {
        if(tarefaDao == null){
            tarefaDao = new TarefaDaoImpl();
        }
        return tarefaDao;
    }
}
