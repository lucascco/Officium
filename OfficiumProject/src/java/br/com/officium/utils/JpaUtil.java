/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas Corrêa
 */
public class JpaUtil {
    public static String NAME_PERSISTENCE_UNIT = "OfficiumProjectPU"; // Nome da persistencia configurada em persistence.xml
    private static EntityManagerFactory factory;
    
    static{
        factory = Persistence.createEntityManagerFactory(NAME_PERSISTENCE_UNIT);
    }
     public static EntityManager getEntityManager(){
         return factory.createEntityManager();
     }
     
     public static  void close(){
         factory.close();
     }
    
}
