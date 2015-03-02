package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.entities.Passenger;

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

	@Override
	public Passenger getPassengerByPassword(String password) {
		EntityTransaction tr = manager.getTransaction();
		Passenger passenger;
		try {
			Query query = manager
					.createQuery("Select p from Passenger p where p.password = :pass");
			query.setParameter("pass", password);
			passenger = (Passenger) query.getSingleResult();
		} finally {
			if (tr.isActive()) {
				tr.rollback();
				return null;
			}
		}
		return passenger;
	}

	@Override
	public Passenger getPassengerByLastName(String lastName) {
		EntityTransaction tr = manager.getTransaction();
		Passenger passenger;
		try {
			Query query = manager
					.createQuery("Select p from Passenger p where p.lastName = :name");
			query.setParameter("name", lastName);
			passenger = (Passenger) query.getSingleResult();
		} finally {
			if (tr.isActive()) {
				tr.rollback();
				return null;
			}
		}
		return passenger;
	}

}
