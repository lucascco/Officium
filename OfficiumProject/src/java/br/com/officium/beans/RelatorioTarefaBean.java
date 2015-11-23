/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.TarefaDaoImpl;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author marlo
 */
@ManagedBean(name = "relatorioTarefaBean")
@SessionScoped
public class RelatorioTarefaBean implements Serializable {

    private Tarefa tarefa;
    private TarefaDao tarefaDao;
    private UsuarioDao usuarioDao;
    private String dataCriacaoStr;
    private List<Tarefa> listTarefas;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @PostConstruct
    public void init() {
        iniciarObjTarefa();
    }

    private void iniciarObjTarefa() {
        setTarefa(new Tarefa());
        getTarefa().setUsuarioDelegado(new Usuario());
        tarefa.setInicio(new Date());
        dataCriacaoStr = simpleDateFormat.format(tarefa.getInicio());
    }

    public void verficarUsernameDelegado(AjaxBehaviorEvent event) {
        if (tarefa.getUsuarioDelegado().getUsername() != null
                && !tarefa.getUsuarioDelegado().getUsername().isEmpty()) {
            try {
                Usuario usuarioDel = new Usuario();
                usuarioDel.setUsername(tarefa.getUsuarioDelegado().getUsername());
                List<Usuario> usuarios = getUsuarioDao().consultar(0, 0, usuarioDel);
                if (usuarios.isEmpty()) {
                    tarefa.getUsuarioDelegado().setUsername("Usuario não encontrado.");
                } else {
                    tarefa.getUsuarioDelegado().setId(usuarios.get(0).getId());
                }
            } catch (Exception ex) {
                Logger.getLogger(RelatorioTarefaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void consultar(){
        try {
            listTarefas = getTarefaDao().consultar(0, 0, tarefa);
        } catch (Exception ex) {
            Logger.getLogger(RelatorioTarefaBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public UsuarioDao getUsuarioDao() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }

    public String getDataCriacaoStr() {
        return dataCriacaoStr;
    }

    public void setDataCriacaoStr(String dataCriacaoStr) {
        this.dataCriacaoStr = dataCriacaoStr;
    }

    public List<Tarefa> getListTarefas() {
        return listTarefas;
    }

    public void setListTarefas(List<Tarefa> listTarefas) {
        this.listTarefas = listTarefas;
    }

}
