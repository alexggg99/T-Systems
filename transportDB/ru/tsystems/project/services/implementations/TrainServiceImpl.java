package ru.tsystems.project.services.implementations;

import java.util.List;
import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;

import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.TrainDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.API.TrainService;

/**
 * An implementation of the API.
 */
public class TrainServiceImpl implements TrainService {

    private final TrainDAO trainDAO = new TrainDAOImpl();

    @Override
    public Train addTrain(String name) throws CustomDAOException {
        try{
            Train train = new Train();
            train.setName(name);
            Train result = trainDAO.save(train);
            return result;
        }catch(RuntimeException ex){
            throw new CustomDAOException("RuntimeExceptio !");
        }
    }

    @Override
    public List<Train> getAllTrains() throws CustomDAOException {
        try {
            List<Train> list = trainDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomDAOException("Unable to find stations!", ex);
        }
    }

}
