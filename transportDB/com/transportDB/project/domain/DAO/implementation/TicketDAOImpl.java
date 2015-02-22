package com.transportDB.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.transportDB.project.domain.DAO.interfaces.TicketDAO;
import com.transportDB.project.domain.entities.Ticket;

public class TicketDAOImpl implements TicketDAO {

	private static EntityManager manager = DaoFactory.manager;

	public TicketDAOImpl() {
	}

	@Override
	public Ticket save(Ticket entity) {
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
	public List<Ticket> findAll() {
		// TypedQuery<Train> query;
		Query query = manager.createNamedQuery("Ticket.findAll");
		List<Ticket> result = query.getResultList();
		return result;
	}

	@Override
	public Ticket delete(Ticket entity) {
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
	public Ticket update(Ticket entity) {
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
	public Ticket findById(int id) {
		Ticket result = manager.find(Ticket.class, id);
		return result;
	}

}
