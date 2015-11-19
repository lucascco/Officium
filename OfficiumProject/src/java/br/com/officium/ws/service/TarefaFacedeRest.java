/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dao.GenericDao;
import br.com.officium.dao.TarefaDao;
import br.com.officium.dao.impl.TarefaDaoImpl;
import br.com.officium.dominio.StatusTarefa;
import br.com.officium.dominio.Tarefa;
import br.com.officium.dominio.Usuario;
import br.com.officium.ws.service.utils.JsonGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Lucas Corrêa
 */
@Path("TarefaService")
public class TarefaFacedeRest extends AbstractFacade<Tarefa> {

    TarefaDao tarefaDao;

    public TarefaFacedeRest() {
        super(Tarefa.class);
    }

    @GET
    @Path("getTarefas")
    @Produces("application/json")
    public String getTarefasGeral(@QueryParam("idUsuario") String idUser,
            @QueryParam("idStatus") String idStatus,
            @QueryParam("idUsuarioDelegado") String idUserDelegate) {
        String result = "";
        try {
            Tarefa tarefa = new Tarefa();
            if (idUser != null && !idUser.isEmpty()) {
                Long idUsuario = Long.parseLong(idUser);
                tarefa.setUsuario(new Usuario(idUsuario));
            }

            if (idStatus != null && !idStatus.isEmpty()) {
                Long idStatusL = Long.parseLong(idStatus);
                tarefa.setStatusTarefa(new StatusTarefa(idStatusL));
            }

            if (idUserDelegate != null && !idUserDelegate.isEmpty()) {
                Long idUsuarioDelegado = Long.parseLong(idUserDelegate);
                tarefa.setUsuarioDelegado(new Usuario(idUsuarioDelegado));
            }

            result = JsonGenerator.generateJson(getGenericDao().consultar(0, 0, tarefa));
        } catch (NumberFormatException ex) {
            result = JsonGenerator.generateErrorJson(ex, -1);
        } catch (Exception ex) {
            result = JsonGenerator.generateErrorJson(ex, -1);
        }
        return result;
    }

    @Override
    public TarefaDao getGenericDao() {
        if (tarefaDao == null) {
            tarefaDao = new TarefaDaoImpl();
        }
        return tarefaDao;
    }

}
