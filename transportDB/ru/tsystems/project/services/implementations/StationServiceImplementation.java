package ru.tsystems.project.services.implementations;

import java.util.List;
import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;

import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;

/**
 * An implementation of the API.
 */
public class StationServiceImplementation implements StationService {

    private final StationDAO stationDAO = new StationDAOImpl();

    @Override
    public Station addStation(String name) throws CustomDAOException {
        try{
            Station station = new Station();
            station.setName(name);
            Station result = stationDAO.save(station);
            return result;
        }catch(RuntimeException ex){
            throw new CustomDAOException("RuntimeExceptio !");
        }
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

}
