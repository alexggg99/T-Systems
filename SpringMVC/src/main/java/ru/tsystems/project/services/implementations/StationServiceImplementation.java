package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;

@Transactional
public class StationServiceImplementation implements StationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StationDAO stationDAO;

    @Override
    public Station addStation(String name) throws CustomDAOException {
        Station result;
        Station station = new Station();
        station.setName(name);
        result = stationDAO.save(station);
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
        return stationDAO.getStation(name);
    }

    @Override
    public List<RouteEntity> getTrains(String stationName) throws CustomDAOException {
        List<RouteEntity> result = new ArrayList<RouteEntity>();
        result = stationDAO.getTrains(stationName);
        return result;
    }

    @Override
    public Station removeStation(Station station) throws CustomDAOException {
        return stationDAO.delete(station);
    }

    @Override
    public Station getById(int stationId) throws CustomDAOException {
        Station station = entityManager.find(Station.class, stationId);
        return station;
    }

}
