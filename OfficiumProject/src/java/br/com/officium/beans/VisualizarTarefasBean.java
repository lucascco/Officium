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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lucas Corrêa
 */
@ManagedBean(name = "visualizarTarefasBean")
@RequestScoped
public class VisualizarTarefasBean implements Serializable {

    private List<Tarefa> listTarefas;
    private Long idStatus;
    private TarefaDao tarefaDao;
    private StatusTarefa statusTarefa;
    private Tarefa tarefa;

    @PostConstruct
    public void ini() {
        iniciarTarefa();
        statusTarefa = new StatusTarefa();
        listTarefas = new ArrayList<>();
    }
    
    private void iniciarTarefa(){
        tarefa = new Tarefa();
        tarefa.setUsuario(new Usuario(getUsuarioLogado().getId()));
    }

    public void carregarTarefasDelegadas() {
        this.tarefa.setUsuario(null);
        this.tarefa.setUsuarioDelegado(new Usuario(getUsuarioLogado().getId()));
        carregarTarefas();
    }

    public void carregarTarefas(Long idstatus) {
        tarefa.setStatusTarefa(new StatusTarefa(idstatus));
        this.carregarTarefas();
    }

    public void carregarTarefas() {
        try {
            listTarefas = getTarefaDao().consultar(0, 0, tarefa);
            iniciarTarefa();
        } catch (Exception ex) {
            Logger.getLogger(VisualizarTarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Usuario getUsuarioLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioBean usuarioBean = context.getApplication().evaluateExpressionGet(context, "#{usuarioBean}", UsuarioBean.class);
        return usuarioBean.getUsuario();

    }

    public List<Tarefa> getListTarefas() {
        return listTarefas;
    }

    public void setListTarefas(List<Tarefa> listTarefas) {
        this.listTarefas = listTarefas;
    }

    public TarefaDao getTarefaDao() {
        if (tarefaDao == null) {
            tarefaDao = new TarefaDaoImpl();
        }
        return tarefaDao;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setStatus(Long idStatus) {
        this.idStatus = idStatus;
    }
}
