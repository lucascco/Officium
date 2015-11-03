/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dao.GenericDao;
import java.util.List;

/**
 *
 * @author Lucas Corrêa
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public abstract GenericDao<T> getGenericDao();

    public void create(T entity) throws Exception {
        getGenericDao().salvar(entity);
    }

    public void edit(T entity) throws Exception {
        getGenericDao().salvar(entity);
    }

    public void remove(T entity) throws Exception {
        getGenericDao().excluir(entity);
    }

    public T find(Long id) {
        return getGenericDao().consultarPorId(id);
    }

    public List<T> findAll() throws Exception {
        return getGenericDao().consultarTodos();
    }

//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
//
//    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }

    
}
