/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao;

import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Usuario;

/**
 *
 * @author Lucas Corrêa
 */
public interface UsuarioDao extends GenericDao<Usuario>{
    public Usuario logon(String name, String senha) throws Exception;
    public void salvar(Usuario obj, AutorizacaoUsuario autorizacaoUsuario) throws Exception;
}
