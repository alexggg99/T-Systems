package com.transportDB.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.transportDB.project.domain.DAO.interfaces.RouteEntityDAO;
import com.transportDB.project.domain.entities.RouteEntity;

public class RouteEntityDAOImpl implements RouteEntityDAO {

	private static EntityManager manager = DaoFactory.manager;

	public RouteEntityDAOImpl() {
	}

	@Override
	public RouteEntity save(RouteEntity entity) {
		EntityTransaction tr = manager.getTransaction();
		try {
			tr.begin();
			manager.persist(entity);
			tr.commit();
		} finally {
			if (tr.isActive()) {
				tr.rollback();
			}
		}
		return entity;
	}

	@Override
	public List<RouteEntity> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("RouteEntity.findAll");
		List<RouteEntity> result = query.getResultList();
		return result;
	}

	@Override
	public RouteEntity delete(RouteEntity entity) {
		EntityTransaction tr = manager.getTransaction();
		try {
			tr.begin();
			manager.remove(entity);
			tr.commit();
		} finally {
			if (tr.isActive()) {
				tr.rollback();
				return null;
			}
		}
		return entity;
	}

	@Override
	public RouteEntity update(RouteEntity entity) {
		EntityTransaction tr = manager.getTransaction();
		try {
			tr.begin();
			manager.merge(entity);
			tr.commit();
		} finally {
			if (tr.isActive()) {
				tr.rollback();
				return null;
			}
		}
		return entity;
	}

	@Override
	public RouteEntity findById(int id) {
		RouteEntity result = manager.find(RouteEntity.class, id);
		return result;
	}

}
