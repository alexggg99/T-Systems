package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import ru.tsystems.project.domain.DAO.implementation.DaoFactory;
import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;

import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;

/**
 * An implementation of the API.
 */
public class StationServiceImplementation implements StationService {

    private final EntityManager entityManager = DaoFactory.getEntityManager();
    private final StationDAO stationDAO = new StationDAOImpl(entityManager);

    @Override
    public Station addStation(String name) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Station result;
        try{
            Station station = new Station();
            station.setName(name);
            tr.begin();
            result = stationDAO.save(station);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public List<Station> getAllStations() throws CustomDAOException {
        try {
            List<Station> list = stationDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomDAOException("Unable to find stations!", ex);
        }
    }

    @Override
    public Station getStation(String name) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Station result;
        try{
            tr.begin();
            Query query = entityManager.createQuery("Select s from Station s where s.name = :name");
            query.setParameter("name", name);
            result = (Station) query.getSingleResult();
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public List<RouteEntity> getTrains(String stationName) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        List<RouteEntity> result = new ArrayList<>();
        try{
            tr.begin();
            result = stationDAO.getTrains(stationName);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public Station removeStation(Station station) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Station result;
        try{
            tr.begin();
            result = stationDAO.delete(station);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

}
