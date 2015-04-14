package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.RouteEntityDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.exceptions.CustomDAOException;

public class RouteEntityDAOImpl implements RouteEntityDAO {

    private static EntityManager manager;

    public RouteEntityDAOImpl(EntityManager manager) {
        RouteEntityDAOImpl.manager = manager;
    }

    @Override
    public RouteEntity save(RouteEntity entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save routeEntity: " + ex);
        }
        return entity;
    }

    @Override
    public List<RouteEntity> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery(RouteEntity.GETALL);
        List<RouteEntity> result = query.getResultList();
        return result;
    }

    @Override
    public RouteEntity delete(RouteEntity entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove routeEntity: " + ex);
        }
        return entity;
    }

    @Override
    public RouteEntity update(RouteEntity entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update routeEntity: " + ex);
        }
        return entity;
    }

    @Override
    public RouteEntity findById(int id) {
        RouteEntity result = manager.find(RouteEntity.class, id);
        return result;
    }

}
