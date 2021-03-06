package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import ru.tsystems.project.domain.DAO.interfaces.RouteDAO;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.exceptions.CustomDAOException;

@Repository("routeDAO")
public class RouteDAOImpl implements RouteDAO {

        @PersistenceContext
	private EntityManager manager;

	public RouteDAOImpl() {
	}

	@Override
	public Route save(Route entity) {
		try {
			manager.persist(entity);
		} catch (PersistenceException ex) {
			throw new CustomDAOException("Can not save route: " + ex);
		}
		return entity;
	}

	@Override
	public List<Route> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery(Route.GETALL);
		List<Route> result = query.getResultList();
		return result;
	}

	@Override
	public Route delete(Route entity) {
		try {
			manager.remove(entity);
		} catch (PersistenceException ex) {
			throw new CustomDAOException("Can not remove route: " + ex);
		}
		return entity;
	}

	@Override
	public Route update(Route entity) {
		try {
			manager.merge(entity);
		} catch (PersistenceException ex) {
			throw new CustomDAOException("Can not update route: " + ex);
		}
		return entity;
	}

	@Override
	public Route findById(int id) {
		Route result = manager.find(Route.class, id);
		return result;
	}

}
