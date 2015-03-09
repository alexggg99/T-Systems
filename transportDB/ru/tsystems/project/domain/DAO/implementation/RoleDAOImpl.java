package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.RoleDAO;
import ru.tsystems.project.domain.entities.Role;
import ru.tsystems.project.exceptions.CustomDAOException;

public class RoleDAOImpl implements RoleDAO {

    private static EntityManager manager;

    public RoleDAOImpl(EntityManager m) {
        RoleDAOImpl.manager = m;
    }

    @Override
    public Role save(Role entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save role: " + ex);
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
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove role: " + ex);
        }
        return entity;
    }

    @Override
    public Role update(Role entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update role: " + ex);
        }
        return entity;
    }

    @Override
    public Role findById(int id) {
        Role result = manager.find(Role.class, id);
        return result;
    }

}
