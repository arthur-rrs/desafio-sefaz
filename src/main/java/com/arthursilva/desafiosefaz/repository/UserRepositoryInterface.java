package com.arthursilva.desafiosefaz.repository;

import com.arthursilva.desafiosefaz.model.entity.User;

public interface UserRepositoryInterface extends Crud<User>{
	
	public User findByEmail(String email);
}
