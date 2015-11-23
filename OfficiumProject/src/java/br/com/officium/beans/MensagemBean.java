/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.MensagemDao;
import br.com.officium.dao.impl.MensagemDaoImpl;
import br.com.officium.dominio.Mensagem;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Usuario;
import java.io.Serializable;
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
@ManagedBean(name = "mensagemBean")
@SessionScoped
public class MensagemBean implements Serializable {

    private MensagemDao mensagemDao;
    private List<Mensagem> listMensagens;

    @PostConstruct
    public void ini() {
        consultar();
    }
    public void consultar(){
        try {
            listMensagens = getMensagemDao().buscarPorUsuarioDestino(getUsuarioLogado().getId());
        } catch (Exception ex) {
            Logger.getLogger(MensagemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuarioLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioBean usuarioBean = context.getApplication().evaluateExpressionGet(context, "#{usuarioBean}", UsuarioBean.class);
        return usuarioBean.getUsuario();

    }

    public MensagemDao getMensagemDao() {
        if (mensagemDao == null) {
            mensagemDao = new MensagemDaoImpl();
        }
        return mensagemDao;
    }

    public List<Mensagem> getListMensagens() {
        return listMensagens;
    }

    public void setListMensagens(List<Mensagem> listMensagens) {
        this.listMensagens = listMensagens;
    }

}
