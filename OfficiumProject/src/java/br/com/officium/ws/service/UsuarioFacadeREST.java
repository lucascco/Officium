/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dao.UsuarioDao;
import br.com.officium.dao.impl.UsuarioDaoImpl;
import br.com.officium.dominio.Autorizacao;
import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.Usuario;
import br.com.officium.ws.service.utils.JsonGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Lucas Corrêa
 */
@Path("UsuarioService")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    private UsuarioDao usuarioDao;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Path("create")
    @Produces("application/json")
    public String create(@FormParam("jsonUsuario") String strUsuario) {
        String result;
        try {
            if (strUsuario != null && !strUsuario.isEmpty()) {
                Usuario u = (Usuario) JsonGenerator.generateTOfromJson(strUsuario, Usuario.class);
                if (u != null) {
                    AutorizacaoUsuario au = new AutorizacaoUsuario();
                    au.setAutorizacao(new Autorizacao(1l));
                    au.setUsuario(u);
                    getGenericDao().salvar(u, au);
                    result = JsonGenerator.generateSuccessJson("Usuario criado com sucesso!");
                }else{
                    throw new Exception("jsonUsuario inválido");
                }
            } else {
                throw new Exception("jsonUsuario inválido");
            }

        } catch (Exception ex) {
            result = JsonGenerator.generateErrorJson(ex, -1);
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @POST
    @Path("login")
    @Produces("application/json")
    public String login(@FormParam("userString") String user) {
        String result;
        try {
            Usuario u = (Usuario) JsonGenerator.generateTOfromJson(user, Usuario.class);
            Usuario usuarioLogin = getGenericDao().logon(u.getUsername(), u.getPassword());
            if (usuarioLogin == null) {
                throw new Exception("Autenticação inválida");
            }
            result = JsonGenerator.generateSuccessJson("Usuario Existe");
            return result;
        } catch (Exception e) {
            result = JsonGenerator.generateErrorJson(e, -1);
        }
        return result;
    }

    @GET
    @Path("AllUsers")
    @Produces("application/json")
    public String findAllUsers() {
        String result;
        try {
            result = JsonGenerator.generateJson(super.findAll());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            result = JsonGenerator.generateErrorJson(ex, -1);
        }
        return result;
    }

    @Override
    public UsuarioDao getGenericDao() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDaoImpl();
        }
        return usuarioDao;
    }

}
