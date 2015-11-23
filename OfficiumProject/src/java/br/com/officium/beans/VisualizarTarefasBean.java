/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.MensagemDao;
import br.com.officium.dao.StatusTarefaDao;
import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.impl.MensagemDaoImpl;
import br.com.officium.dao.impl.StatusTarefaDaoImpl;
import br.com.officium.dao.impl.TarefaDaoImpl;
import br.com.officium.dominio.Mensagem;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lucas Corrêa
 */
@ManagedBean(name = "visualizarTarefasBean")
@SessionScoped
public class VisualizarTarefasBean implements Serializable {

    private List<Tarefa> listTarefas;
    private Long idStatus;
    private TarefaDao tarefaDao;
    private StatusTarefaDao statusTarefaDao;
    private StatusTarefa statusTarefa;
    private Tarefa tarefa;
    private String tituloStatus;
    private String labelOrd;
    private Boolean ordAsc;
    private MensagemDao mensagemDao;

    @PostConstruct
    public void ini() {
        iniciarTarefa();
        statusTarefa = new StatusTarefa();
        listTarefas = new ArrayList<>();
        labelOrd = "importancia";
        ordAsc = false;
    }

    private void iniciarTarefa() {
        tarefa = new Tarefa();
        tarefa.setUsuario(new Usuario(getUsuarioLogado().getId()));
    }

    public void carregarTarefasDelegadas() {
        this.tarefa.setUsuario(null);
        this.tarefa.setUsuarioDelegado(new Usuario(getUsuarioLogado().getId()));
        carregarTarefas("Delegadas");
    }

    public boolean renderTerminadaEm(Long idst) {
        return idst == 1;
    }

    public boolean DesabilitaBotaoAFazer(Long idst) {
        return idst == 2 || idst == 1;
    }

    public boolean DesabilitaBotaoJaTerminei(Long idst) {
        return idst == 3 || idst == 1;
    }

    public void salvarMensagem(String conteudo, Long idUsuarioDestino, Long idUsuarioOrigem) {
        Mensagem mensagem = new Mensagem();
        mensagem.setUsuarioOrigem(new Usuario(idUsuarioOrigem));
        mensagem.setUsuarioDestino(new Usuario(idUsuarioDestino));
        mensagem.setConteudo(conteudo);
        mensagem.setCriacao(new Date());
        mensagem.setLida(false);
        try {
            getMensagemDao().salvar(mensagem);
        } catch (Exception ex) {
            Logger.getLogger(VisualizarTarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarStatusTarefa(Tarefa t, Long idSt) {
        if (idSt == 1) {
            t.setDataTermino(new Date());
            if ((t.getUsuarioDelegado() != null && t.getUsuarioDelegado().getId() != null)
                    && t.getUsuarioDelegado().getId().equals(getUsuarioLogado().getId())) {
                salvarMensagem("Olá acabei de concluir uma tarefa que  você delegou para mim.", t.getUsuario().getId(), t.getUsuarioDelegado().getId());
            }
        }
        t.setStatusTarefa(new StatusTarefa(idSt));
        try {
            StatusTarefa st_aux = getStatusTarefaDao().consultarPorId(idSt);
            getTarefaDao().salvar(t);
            t.setStatusTarefa(st_aux);
        } catch (Exception ex) {
            Logger.getLogger(VisualizarTarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recarregarTarefas() {
        carregarTarefas(idStatus, tituloStatus);
    }

    public void carregarTarefas(Long idst, String titulo) {
        idStatus = idst;
        tarefa.setStatusTarefa(new StatusTarefa(idst));
        this.carregarTarefas(titulo);
    }

    public void carregarTarefas(String titulo) {
        tituloStatus = titulo;
        try {
            listTarefas = getTarefaDao().consultarVisualizarTarefa(0, 0, tarefa, labelOrd, ordAsc);
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

    public StatusTarefaDao getStatusTarefaDao() {
        if (statusTarefaDao == null) {
            statusTarefaDao = new StatusTarefaDaoImpl();
        }
        return statusTarefaDao;
    }

    public String getTituloStatus() {
        return tituloStatus;
    }

    public void setTituloStatus(String tituloStatus) {
        this.tituloStatus = tituloStatus;
    }

    public String getLabelOrd() {
        return labelOrd;
    }

    public void setLabelOrd(String labelOrd) {
        this.labelOrd = labelOrd;
    }

    public Boolean getOrdAsc() {
        return ordAsc;
    }

    public void setOrdAsc(Boolean ordAsc) {
        this.ordAsc = ordAsc;
    }

    public MensagemDao getMensagemDao() {
        if (mensagemDao == null) {
            mensagemDao = new MensagemDaoImpl();
        }
        return mensagemDao;
    }
}
