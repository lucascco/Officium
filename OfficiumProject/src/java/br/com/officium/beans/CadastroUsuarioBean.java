/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.Autorizacao;
import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.Usuario;
import java.io.Serializable;
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
@ManagedBean(name = "cadastroUsuarioBean")
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private String senha1;
    private String senha2;
    private boolean sucesso_salvar = false;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public boolean validarCadastro() {
        if (senha1 != null && senha2 != null && senha1.equals(senha2)) {
            return true;
        }
        return false;
    }

    public void salvar() {
        if (validarCadastro()) {
            usuario.setPassword(senha1);
            try {
                AutorizacaoUsuario au = new AutorizacaoUsuario();
                au.setAutorizacao(new Autorizacao(1l));
                au.setUsuario(usuario);
                usuario.setEnable(Boolean.TRUE);
                this.getUsuarioDao().salvar(usuario, au);
                init();
                FacesMessage message = new FacesMessage("Usuario salvo com sucesso!");
                FacesContext.getCurrentInstance().addMessage(null, message);
                sucesso_salvar = true;
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage("Ops.. Algum erro aconteceu.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                sucesso_salvar = false;
                Logger.getLogger(CadastroUsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public boolean getSucesso_salvar() {
        return sucesso_salvar;
    }

    public void setSucesso_salvar(boolean sucesso_salvar) {
        this.sucesso_salvar = sucesso_salvar;
    }

}
