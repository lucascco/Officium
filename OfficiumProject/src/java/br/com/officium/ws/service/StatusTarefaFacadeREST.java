/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dao.GenericDao;
import br.com.officium.dao.StatusTarefaDao;
import br.com.officium.dao.impl.StatusTarefaDaoImpl;
import br.com.officium.dominio.StatusTarefa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lucas Corrêa
 */
@Path("StatusTarefaService")
public class StatusTarefaFacadeREST extends AbstractFacade<StatusTarefa> {
    private StatusTarefaDao statusTarefaDao;

    public StatusTarefaFacadeREST() {
        super(StatusTarefa.class);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(StatusTarefa entity) {
        try {
            super.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(StatusTarefaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, StatusTarefa entity) {
        try {
            super.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(StatusTarefaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            super.remove(super.find(id));
        } catch (Exception ex) {
            Logger.getLogger(StatusTarefaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public StatusTarefa find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<StatusTarefa> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            Logger.getLogger(StatusTarefaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<StatusTarefa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    @Override
    public GenericDao<StatusTarefa> getGenericDao() {
        if(statusTarefaDao == null){
            statusTarefaDao = new StatusTarefaDaoImpl();
        }
        return  statusTarefaDao;
    }
    
}
