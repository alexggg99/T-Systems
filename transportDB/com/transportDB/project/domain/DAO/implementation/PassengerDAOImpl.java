package com.transportDB.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.transportDB.project.domain.DAO.interfaces.PassengerDAO;
import com.transportDB.project.domain.entities.Passenger;

public class PassengerDAOImpl implements PassengerDAO {

	private static EntityManager manager = DaoFactory.manager;

	public PassengerDAOImpl() {
	}

	@Override
	public Passenger save(Passenger entity) {
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
	public List<Passenger> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("Passenger.findAll");
		List<Passenger> result = query.getResultList();
		return result;
	}

	@Override
	public Passenger delete(Passenger entity) {
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
	public Passenger update(Passenger entity) {
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
	public Passenger findById(int id) {
		Passenger result = manager.find(Passenger.class, id);
		return result;
	}

}
