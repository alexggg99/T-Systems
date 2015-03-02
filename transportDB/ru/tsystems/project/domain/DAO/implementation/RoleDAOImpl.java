package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.RoleDAO;
import ru.tsystems.project.domain.entities.Role;

public class RoleDAOImpl implements RoleDAO {

	private static EntityManager manager = DaoFactory.manager;

	public RoleDAOImpl() {
	}

	@Override
	public Role save(Role entity) {
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
	public List<Role> findAll() {
		Query query = manager.createNamedQuery("Role.findAll");
		List<Role> result = query.getResultList();
		return result;
	}

	@Override
	public Role delete(Role entity) {
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
	public Role update(Role entity) {
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
	public Role findById(int id) {
		Role result = manager.find(Role.class, id);
		return result;
	}

}
