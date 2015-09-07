/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.beans;

import br.com.officium.dominio.StatusTarefa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas Corrêa
 */
@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexBean implements Serializable{
    private StatusTarefa statusTarefa;
    
    
    @PostConstruct
    public void ini(){
        statusTarefa = new StatusTarefa();
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }
}
