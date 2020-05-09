package com.arthursilva.desafiosefaz.repository.factory;

import javax.persistence.Persistence;

public class EntityManagerFactory {
    
    public static javax.persistence.EntityManagerFactory getEntityManagerFactory() {
    	return Persistence
    			.createEntityManagerFactory("desafio-unit");
 
   }
}
