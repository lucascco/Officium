/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.MensagemDao;
import br.com.officium.dao.UsuarioDao;
import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.Mensagem;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author Lucas Corrêa
 */
public class MensagemDaoImpl extends GenericDaoImpl<Mensagem> implements MensagemDao {

    public MensagemDaoImpl() {
        super(Mensagem.class);
    }

    @Override
    public List<Mensagem> consultar(int start, int maxResult, Mensagem obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long consultarQtd(Mensagem obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mensagem> buscarPorUsuarioDestino(Long idUsuarioDestino) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mensagem> criteriaQuery = criarCriteriaQuery();
        Join<Usuario, Mensagem> usuarioJoin = getObjetoRoot().join("usuarioDestino");
        criteriaQuery.where(builder.equal(usuarioJoin.get("id"), idUsuarioDestino));
        criteriaQuery.orderBy(builder.asc(getObjetoRoot().get("criacao")));
        TypedQuery<Mensagem> query = this.getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    
}
