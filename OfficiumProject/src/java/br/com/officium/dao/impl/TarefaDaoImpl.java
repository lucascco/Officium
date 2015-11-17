/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao.impl;

import br.com.officium.dao.StatusTarefaDao;
import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.UsuarioDao;
import br.com.officium.dominio.AutorizacaoUsuario;
import br.com.officium.dominio.StatusTarefa;
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
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author Lucas Corrêa
 */
public class TarefaDaoImpl extends GenericDaoImpl<Tarefa> implements TarefaDao {

    public TarefaDaoImpl() {
        super(Tarefa.class);
    }
    
    @Override
    public void salvar(Tarefa obj) throws Exception{
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        try {
            transaction.begin();
            this.getEntityManager().persist(obj);            
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive() && transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        }
        
    }

    @Override
    public List<Tarefa> consultar(int start, int maxResult, Tarefa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long consultarQtd(Tarefa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
