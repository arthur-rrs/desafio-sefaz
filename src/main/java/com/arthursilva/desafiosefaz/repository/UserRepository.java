package com.arthursilva.desafiosefaz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import com.arthursilva.desafiosefaz.model.entity.User;

public class UserRepository extends CrudAbstract<User> implements UserRepositoryInterface {

	public UserRepository(EntityManagerFactory emFactory) {
		super(emFactory);
	}

	public List<User> findAll() {
		return this.findAll(User.class);
	}

	public void removeById(Long id) {
		this.remove(id, User.class);
	}

	public User get(Long id) {
		return this.get(id, User.class);
	}

	@Override
	public User findByEmail(String email) {
		String query = "from User u where u.email = :email";
		EntityManager entityManager = this.getEntityManager();
		try {
			User user = (User) entityManager.createQuery(query).setParameter("email", email)
					.getSingleResult();
			entityManager.close();
			return user;
		} catch (NoResultException exc) {
			return null;
		}
	}

}
