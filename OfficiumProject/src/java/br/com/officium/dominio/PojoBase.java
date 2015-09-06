/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dominio;
import java.io.Serializable;
/**
 *
 * @author Lucas Corrêa
 */
public interface PojoBase extends Serializable, Cloneable {
    public static final String DB = "officium";
    
    public void setId(Long id);
    public Long getId();
    public Object getClone();
}
