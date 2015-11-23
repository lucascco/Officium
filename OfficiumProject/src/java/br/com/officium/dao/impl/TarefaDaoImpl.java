/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.TarefaDao;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Tarefa_;
import br.com.officium.dominio.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author Lucas Corrêa
 */
public class TarefaDaoImpl extends GenericDaoImpl<Tarefa> implements TarefaDao {

    public TarefaDaoImpl() {
        super(Tarefa.class);
    }

    private List<Predicate> createPredicateFromObject(Tarefa obj, CriteriaBuilder builder) {
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
            if (obj.getUsuario() != null && obj.getUsuario().getId() != null) {
                Join<Usuario, Tarefa> usuarioJoin = getObjetoRoot().join("usuario");
                predicates.add(builder.equal(usuarioJoin.get("id"), obj.getUsuario().getId()));
            }

            if (obj.getUsuarioDelegado() != null && obj.getUsuarioDelegado().getId() != null) {
                Join<Usuario, Tarefa> usuarioJoin = getObjetoRoot().join("usuarioDelegado");
                predicates.add(builder.equal(usuarioJoin.get("id"), obj.getUsuarioDelegado().getId()));
            }

            if (obj.getStatusTarefa() != null && obj.getStatusTarefa().getId() != null) {
                Join<StatusTarefa, Tarefa> statusTarefaJoin = getObjetoRoot().join("statusTarefa");
                predicates.add(builder.equal(statusTarefaJoin.get("id"), obj.getStatusTarefa().getId()));
            }

        }
        predicates.addAll(criarPredicados(values, ops, builder));
        return predicates;
    }

    @Override
    public List<Tarefa> consultarVisualizarTarefa(int start, int maxResult, Tarefa obj, String campoOrd, Boolean asc) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tarefa> criteriaQuery = criarCriteriaQuery();
        List<Predicate> predicates = createPredicateFromObject(obj, builder);
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        if(asc){
            criteriaQuery.orderBy(builder.asc(getObjetoRoot().get(campoOrd)));
        }else{
            criteriaQuery.orderBy(builder.desc(getObjetoRoot().get(campoOrd)));
        }
        TypedQuery<Tarefa> query = this.getEntityManager().createQuery(criteriaQuery);
        if (maxResult > 0 && start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

    @Override
    public List<Tarefa> consultar(int start, int maxResult, Tarefa obj) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tarefa> criteriaQuery = criarCriteriaQuery();
        List<Predicate> predicates = createPredicateFromObject(obj, builder);
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.orderBy(builder.desc(getObjetoRoot().get("importancia")));
        TypedQuery<Tarefa> query = this.getEntityManager().createQuery(criteriaQuery);
        if (maxResult > 0 && start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

    @Override
    public Long consultarQtd(Tarefa obj) throws Exception {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(Tarefa.class)));
        List<Predicate> predicates = createPredicateFromObject(obj, builder);
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return this.getEntityManager().createQuery(cq).getSingleResult();
    }

}
