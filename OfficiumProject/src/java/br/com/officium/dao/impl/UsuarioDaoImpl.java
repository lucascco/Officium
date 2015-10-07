/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.StatusTarefaDao;
import br.com.officium.dao.UsuarioDao;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lucas Corrêa
 */
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

    public UsuarioDaoImpl() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> consultar(int start, int maxResult, Usuario obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long consultarQtd(Usuario obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
