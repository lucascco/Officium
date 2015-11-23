/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.MensagemDao;
import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.MensagemDaoImpl;
import br.com.officium.dao.impl.TarefaDaoImpl;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.Mensagem;
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
@ManagedBean(name = "cadastroTarefaBean")
@SessionScoped
public class CadastroTarefaBean implements Serializable {

    private MensagemDao mensagemDao;
    private Tarefa tarefa;
    private TarefaDao tarefaDao;
    private UsuarioDao usuarioDao;
    private String dataCriacaoStr;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @PostConstruct
    public void init() {
        iniciarObjTarefa();
    }

    private void iniciarObjTarefa() {
        setTarefa(new Tarefa());
        getTarefa().setUsuarioDelegado(new Usuario());
        tarefa.setInicio(new Date());
        tarefa.setImportancia(Short.parseShort("1"));
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
                Logger.getLogger(CadastroTarefaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validarSalvar() {
        return (tarefa.getNome() != null && !tarefa.getNome().isEmpty()
                && tarefa.getDescricao() != null && !tarefa.getDescricao().isEmpty()
                && tarefa.getDuracao() != null
                && tarefa.getImportancia() != null);
    }

    public Mensagem salvarMensagem(String conteudo, Long idUsuarioDestino, Long idUsuarioOrigem) {
        Mensagem mensagem = new Mensagem();
        mensagem.setUsuarioOrigem(new Usuario(idUsuarioOrigem));
        mensagem.setUsuarioDestino(new Usuario(idUsuarioDestino));
        mensagem.setConteudo(conteudo);
        mensagem.setCriacao(new Date());
        mensagem.setLida(false);
        return mensagem;
    }

    public void salvar() {
        if (validarSalvar()) {
            try {
                if (tarefa.getUsuarioDelegado() != null && tarefa.getUsuarioDelegado().getId() == null) {
                    tarefa.setUsuarioDelegado(null);
                } else if (tarefa.getUsuarioDelegado() != null && tarefa.getUsuarioDelegado().getId() != null) {
                    Mensagem mensagem = salvarMensagem("Foi delegado uma tarefa para você, verifique no menu Tarefas - Delegadas.",
                            tarefa.getUsuarioDelegado().getId(),
                            getUsuarioLogado().getId());
                    
                    getMensagemDao().salvar(mensagem);
                }
                tarefa.setUsuario(new Usuario(getUsuarioLogado().getId()));
                tarefa.setStatusTarefa(new StatusTarefa(3l));
                this.getTarefaDao().salvar(tarefa);
                FacesMessage message = new FacesMessage("Tarefa salva com sucesso!");
                FacesContext.getCurrentInstance().addMessage(null, message);
                init();
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage("Erro, tarefa não salva");
                FacesContext.getCurrentInstance().addMessage(null, message);
                Logger.getLogger(CadastroTarefaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage("Erro, preencha os campos obrigatórios!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        init();
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

    public MensagemDao getMensagemDao() {
        if (mensagemDao == null) {
            mensagemDao = new MensagemDaoImpl();
        }
        return mensagemDao;
    }

}
