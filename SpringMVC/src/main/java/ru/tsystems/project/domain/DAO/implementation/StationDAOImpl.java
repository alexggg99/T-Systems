package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;

@Repository("stationDao")
public class StationDAOImpl implements StationDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Station save(Station entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save station: " + ex);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Station> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery(Station.GETALL);
        List<Station> result = query.getResultList();
        return result;
    }

    @Override
    public Station delete(Station entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove station: " + ex);
        }
        return entity;
    }

    @Override
    public Station update(Station entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update station: " + ex);
        }
        return entity;
    }

    @Override
    public Station findById(int id) {
        Station result = manager.find(Station.class, id);
        return result;
    }

    @Override
    public List<RouteEntity> getTrains(String stationName) {
        List<RouteEntity> result = null;
        try {
            Query q = manager.createNamedQuery(RouteEntity.GETTRAINS);
            q.setParameter("stationName", stationName);
            result = q.getResultList();
        } catch (PersistenceException ex) {
            throw new CustomDAOException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Station getStation(String name) {
        Station result;
        try {
            Query query = manager.createQuery("Select s from Station s where s.name = :name");
            query.setParameter("name", name);
            result = (Station) query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomDAOException(ex.getMessage());
        }
        return result;
    }

}
