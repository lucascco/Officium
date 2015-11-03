/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.GenericDao;
import br.com.officium.utils.JpaUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lucas Corrêa
 * @param <T>
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    protected Class<T> c;
    private EntityManager entityManager;
    private Root<T> objetoRoot;

    public GenericDaoImpl(Class<T> c) {
        this.c = c;
    }

    @Override
    public List<T> consultarTodos() throws Exception {
        CriteriaQuery<T> criteriaQuery = criarCriteriaQuery();
        TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);
        return (query.getResultList());
    }

    @Override
    public List<T> consultarTodosOrdenados(String camposOrdenacao) throws Exception {
        CriteriaQuery<T> criteriaQuery = criarCriteriaQuery();
        criteriaQuery.orderBy(this.getEntityManager().getCriteriaBuilder().asc(criteriaQuery.from(c).get(camposOrdenacao)));
        TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);
        return (query.getResultList());
    }

    @Override
    public void salvar(T obj) throws Exception {
        EntityTransaction tx = this.getEntityManager().getTransaction();
        tx.begin();
        this.getEntityManager().persist(obj);
        tx.commit();
    }

    @Override
    public void salvarVarios(T... objs) throws Exception {
        EntityTransaction tx = this.getEntityManager().getTransaction();
        tx.begin();
        for (T obj : objs) {
            this.getEntityManager().persist(obj);
        }
        tx.commit();
    }

    @Override
    public void excluir(T obj) throws Exception {
        try {
            EntityTransaction tx = this.getEntityManager().getTransaction();
            tx.begin();
            this.getEntityManager().remove(obj);
            tx.commit();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public T consultarPorId(Long id) {
        return this.getEntityManager().find(c, id);
    }

    @Override
    public void atualizarCampos(Map<String, String> valores, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public abstract List<T> consultar(int start, int maxResult, T obj) throws Exception;

    @Override
    public abstract Long consultarQtd(T obj) throws Exception;

    protected EntityManager getEntityManager() {
        if (this.entityManager == null) {
            this.entityManager = JpaUtil.getEntityManager();
        }
        return entityManager;
    }

    protected CriteriaQuery<T> criarCriteriaQuery() {
        CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> objeto = criteriaQuery.from(c);
        setObjetoRoot(objeto);
        criteriaQuery.select(objeto);
        return criteriaQuery;
    }

    protected CriteriaQuery<T> criarPredicadoEqual(CriteriaBuilder builder, Map<String, Object> predicadosMap) {
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> objeto = criteriaQuery.from(c);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entrySet : predicadosMap.entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();

            predicates.add(builder.equal(objeto.get(key), value));

        }
        criteriaQuery.select(objeto);
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery;
    }

    public Root<T> getObjetoRoot() {
        return objetoRoot;
    }

    public void setObjetoRoot(Root<T> objetoRoot) {
        this.objetoRoot = objetoRoot;
    }

}
