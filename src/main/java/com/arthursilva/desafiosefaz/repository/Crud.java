package com.arthursilva.desafiosefaz.repository;

import java.util.List;

public interface Crud<E> {
	
	public E save(E entity);
	
	public List<E> findAll(Class<E> cls);
	
	public void remove(Long id, Class<E> cls);
	
	public E get(Long id, Class<E> cls);
	
	public E update(E entity);
}
