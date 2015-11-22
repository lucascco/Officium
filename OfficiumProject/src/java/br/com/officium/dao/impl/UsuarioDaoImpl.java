/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.UsuarioDao;
import br.com.officium.dominio.AutorizacaoUsuario;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author Lucas Corrêa
 */
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

    public UsuarioDaoImpl() {
        super(Usuario.class);
    }

    @Override
    public void salvar(Usuario obj, AutorizacaoUsuario autorizacaoUsuario) throws Exception {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        try {
            transaction.begin();
            this.getEntityManager().persist(obj);
            this.getEntityManager().persist(autorizacaoUsuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() && transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private List<Predicate> createPredicateFromObject(Usuario obj, CriteriaBuilder builder) {
        Map<String, Object> values = new HashMap<>();
        List<Integer> ops = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        if (obj.getId() != null && obj.getId() != 0) {
            values.put("id", obj.getId());
            ops.add(0);
        } else {
            if (obj.getNome() != null) {
                values.put("nome", obj.getNome());
                ops.add(1);
            }
            if (obj.getUsername() != null && !obj.getUsername().isEmpty()) {
                values.put("username", obj.getUsername());
                ops.add(0);
            }

        }
        predicates.addAll(criarPredicados(values, ops, builder));
        return predicates;
    }

    @Override
    public List<Usuario> consultar(int start, int maxResult, Usuario obj) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criarCriteriaQuery();
        List<Predicate> predicates = createPredicateFromObject(obj, builder);
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Usuario> query = this.getEntityManager().createQuery(criteriaQuery);
        if (maxResult > 0 && start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

    @Override
    public Long consultarQtd(Usuario obj) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(Usuario.class)));
        List<Predicate> predicates = createPredicateFromObject(obj, builder);
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return this.getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Usuario logon(String name, String senha) throws Exception {
        List<Predicate> predicados = new ArrayList<>();
        CriteriaQuery<Usuario> criteriaQuery = criarCriteriaQuery();
        CriteriaBuilder builder = super.getEntityManager().getCriteriaBuilder();
        EntityType<Usuario> type = super.getEntityManager().getMetamodel().entity(Usuario.class);
        predicados.add(builder.and(
                builder.equal(getObjetoRoot().get("username"), name),
                builder.equal(getObjetoRoot().get("password"), senha),
                builder.equal(getObjetoRoot().get("enable"), true)));
        criteriaQuery.where(predicados.toArray(new Predicate[predicados.size()]));
        TypedQuery<Usuario> query = this.getEntityManager().createQuery(criteriaQuery);
        return query.getSingleResult();
    }

}
