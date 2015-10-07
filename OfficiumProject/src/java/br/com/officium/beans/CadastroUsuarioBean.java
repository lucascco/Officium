/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author marlo
 */
@javax.faces.bean.ManagedBean(name = "cadastroUsuarioBean")
@RequestScoped
public class CadastroUsuarioBean {

    private UsuarioDao usuarioDao;
    private Usuario usuario;

    
    
    public CadastroUsuarioBean() {
        usuario = new Usuario();
    }
    
    public boolean validarCadastro(){
        return false;
    }
    
    public void salvar(){
        if(validarCadastro()){
            try {
                this.getUsuarioDao().salvar(usuario);
            } catch (Exception ex) {
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
        if(usuarioDao == null){
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }
    
    
    
    
    
}
