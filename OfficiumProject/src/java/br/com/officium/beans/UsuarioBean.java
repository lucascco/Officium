/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Lucas Corrêa
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable{
    
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    
    @PostConstruct
    public void ini(){
        usuario = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        
        if(context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if(authentication instanceof Authentication){
                try {
                    usuario = getUsuarioDao().logon(((User)authentication.getPrincipal()).getUsername(),
                            ((User)authentication.getPrincipal()).getPassword());
                } catch (Exception ex) {
                    Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setUsername(((User)authentication.getPrincipal()).getUsername());
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
        if(usuarioDao == null){
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }

}
