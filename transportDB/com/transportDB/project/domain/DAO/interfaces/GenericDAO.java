package com.transportDB.project.domain.DAO.interfaces;

import java.io.Serializable;
import java.util.List;

interface GenericDAO<T extends Serializable> {

	T save(T entity);

	List<T> findAll();

	T delete(T entity);

	T update(T entity);

	T findById(int id);

}
