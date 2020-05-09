package com.arthursilva.desafiosefaz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class CrudAbstract<E> implements Crud<E> {
	
private EntityManagerFactory entityManagerFactory;
	
	public CrudAbstract(EntityManagerFactory emFactory) {
		entityManagerFactory = emFactory;
	} 
	
	public E save(E entity) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}
	
	public List<E> findAll(Class<E> cls) {
		String className = cls.getName();
		EntityManager entityManager = this.getEntityManager();
		List<E> entities = entityManager.createQuery("from " + className, cls).getResultList();
		entityManager.close();
		return entities;
	}

	public void remove(Long id, Class<E> cls) {
		EntityManager entityManager = this.getEntityManager();
		E entity = entityManager.find(cls, id);
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public E get(Long id, Class<E> cls) {
		EntityManager entityManager = this.getEntityManager();
		E entity = entityManager.find(cls, id);
		entityManager.close();
		return entity;
	}
	
	public E update(E entity) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}
	
	protected EntityManager getEntityManager() {
		return this.entityManagerFactory.createEntityManager();
	}
}
	
