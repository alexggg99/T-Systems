package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Train;

public class TrainDAOImpl implements TrainDAO {

	private static EntityManager manager = DaoFactory.manager;

	public TrainDAOImpl() {
	}

	@Override
	public Train save(Train entity) {
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
	public List<Train> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("Train.findAll");
		List<Train> result = query.getResultList();
		return result;
	}

	@Override
	public Train delete(Train entity) {
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
	public Train update(Train entity) {
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
	public Train findById(int id) {
		Train result = manager.find(Train.class, id);
		return result;
	}

}
