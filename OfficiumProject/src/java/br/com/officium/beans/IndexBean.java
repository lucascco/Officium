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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lucas Corrêa
 */
@ManagedBean(name = "indexBean")
@SessionScoped
public class IndexBean implements Serializable{
    private StatusTarefa statusTarefa;
    private String label;
    
    @PostConstruct
    public void ini(){
        statusTarefa = new StatusTarefa();
        this.label = "Clique no botão";
    }
    
    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }
    
    public void retornarDados(){
        this.label = "Descrição: "+this.statusTarefa.getDescricao()+" ID: "+this.getStatusTarefa().getId();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
