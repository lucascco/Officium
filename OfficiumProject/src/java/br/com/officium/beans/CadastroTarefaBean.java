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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marlo
 */
@ManagedBean(name = "cadastroTarefaBean")
@RequestScoped
public class CadastroTarefaBean implements Serializable {

    private Tarefa tarefa;
    private TarefaDao tarefaDao;

    @PostConstruct
    public void init() {
        setTarefa(new Tarefa());
        tarefa.setInicio(new Date());
    }

    public void salvar() {
        try {
            tarefa.setUsuario(new Usuario(getUsuarioLogado().getId()));
            tarefa.setStatusTarefa(new StatusTarefa(3l));
            this.getTarefaDao().salvar(tarefa);
            init();
            FacesMessage message = new FacesMessage("Tarefa salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage("msg_cadastro_tarefa", message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erro, tarefa não salva");
            FacesContext.getCurrentInstance().addMessage(null, message);            
            Logger.getLogger(CadastroTarefaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public Usuario getUsuarioLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioBean usuarioBean = context.getApplication().evaluateExpressionGet(context, "#{usuarioBean}", UsuarioBean.class);
        return usuarioBean.getUsuario();

    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public TarefaDao getTarefaDao() {
        if (tarefaDao == null) {
            tarefaDao = new TarefaDaoImpl();
        }
        return tarefaDao;
    }

}
