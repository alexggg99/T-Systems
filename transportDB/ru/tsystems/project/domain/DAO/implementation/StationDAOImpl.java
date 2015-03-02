package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.entities.Station;

public class StationDAOImpl implements StationDAO {

	private static EntityManager manager = DaoFactory.manager;

	public StationDAOImpl() {
	}

	@Override
	public Station save(Station entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
		return entity;
	}

	@Override
	public List<Station> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("Station.findAll");
		List<Station> result = query.getResultList();
		return result;
	}

	@Override
	public Station delete(Station entity) {
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
	public Station update(Station entity) {
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
	public Station findById(int id) {
		Station result = manager.find(Station.class, id);
		return result;
	}

}
