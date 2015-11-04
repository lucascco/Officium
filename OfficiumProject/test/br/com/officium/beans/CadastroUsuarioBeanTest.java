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
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author marlo
 */
public class CadastroUsuarioBeanTest {

    private UsuarioDao usuarioDao;

    public CadastroUsuarioBeanTest() {
    }

    @Test
    public void cadastrarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marlon");
        usuario.setUsername("marlon_user");
        usuario.setPassword("123456");
        usuario.setEnable(Boolean.TRUE);

        AutorizacaoUsuario autorizacaoUsuario = new AutorizacaoUsuario();
        autorizacaoUsuario.setAutorizacao(new Autorizacao(1l));
        autorizacaoUsuario.setUsuario(usuario);

        getUsuarioDao().salvar(usuario, autorizacaoUsuario);
        
        Assert.assertTrue(getUsuarioDao().consultarPorId(usuario.getId()) != null);

    }

    public UsuarioDao getUsuarioDao() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

}
