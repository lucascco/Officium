/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dominio;

import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.SerialVersionUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lucas Corrêa
 */
@Entity
@Table(name = "status_tarefas", schema = PojoBase.DB)
public class StatusTarefa implements PojoBase{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, length = 50)
    private String descricao;
    
    
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Object getClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(StatusTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
