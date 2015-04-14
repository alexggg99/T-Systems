package ru.tsystems.project.domain.DAO.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;

public class StationDAOImpl implements StationDAO {

    private static EntityManager manager;

    public StationDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Station save(Station entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save station: " + ex);
        }
        return entity;
    }

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
            throw ex;
        }
        return result;
    }

}
