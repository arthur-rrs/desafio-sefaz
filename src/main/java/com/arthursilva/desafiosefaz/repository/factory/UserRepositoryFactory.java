package com.arthursilva.desafiosefaz.repository.factory;

import com.arthursilva.desafiosefaz.repository.UserRepository;

public class UserRepositoryFactory {
	
	public UserRepository getUserRepository() {
		return new UserRepository(EntityManagerFactory.getEntityManagerFactory());
	} 
}
