/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.StatusTarefaDao;
import br.com.officium.dominio.StatusTarefa;
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
public class StatusTarefaDaoImpl extends GenericDaoImpl<StatusTarefa> implements StatusTarefaDao {

    public StatusTarefaDaoImpl() {
        super(StatusTarefa.class);
    }

    @Override
    public List<StatusTarefa> consultar(int start, int maxResult, StatusTarefa obj) throws Exception {
        List<Predicate> predicados = new ArrayList<>();
        CriteriaQuery<StatusTarefa> criteriaQuery = criarCriteriaQuery();
        CriteriaBuilder builder = super.getEntityManager().getCriteriaBuilder();

        if (obj.getId() != null && obj.getId() != 0) {
            predicados.add(builder.equal(criteriaQuery.from(c).get("id"), obj.getId()));
        } else if (obj.getDescricao() != null) {
            predicados.add(builder.like(criteriaQuery.from(c).get("descricao"), obj.getDescricao()));
        }
        criteriaQuery.select(criteriaQuery.from(c));
        criteriaQuery.where(predicados.toArray(new Predicate[predicados.size()]));
        TypedQuery<StatusTarefa> query = this.getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Long consultarQtd(StatusTarefa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
