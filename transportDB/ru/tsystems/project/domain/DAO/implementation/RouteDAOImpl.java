package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.RouteDAO;
import ru.tsystems.project.domain.entities.Route;

public class RouteDAOImpl implements RouteDAO {

	private static EntityManager manager = DaoFactory.manager;

	public RouteDAOImpl() {
	}

	@Override
	public Route save(Route entity) {
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
	public List<Route> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("Route.findAll");
		List<Route> result = query.getResultList();
		return result;
	}

	@Override
	public Route delete(Route entity) {
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
	public Route update(Route entity) {
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
	public Route findById(int id) {
		Route result = manager.find(Route.class, id);
		return result;
	}

}
