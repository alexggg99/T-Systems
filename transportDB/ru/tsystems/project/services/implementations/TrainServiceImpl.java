package ru.tsystems.project.services.implementations;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import ru.tsystems.project.domain.DAO.implementation.DaoFactory;
import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;

import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.TrainDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.StationDAO;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Passenger;
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

    private final EntityManager manager = DaoFactory.manager;
    private final TrainDAO trainDAO = new TrainDAOImpl(manager);

    @Override
    public Train addTrain(String name, int seats) throws CustomDAOException {
        EntityTransaction tr = manager.getTransaction();
        Train train = new Train();
        train.setName(name);
        train.setSeats(seats);
        try{
            tr.begin();
            Train result = trainDAO.save(train);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return train;   
    }

    @Override
    public List<Train> getAllTrains() throws CustomDAOException {
        try {
            List<Train> list = trainDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomDAOException("Unable to find trains!", ex);
        }
    }

    @Override
    public Train removeTrain(Train train) throws CustomDAOException {
        EntityTransaction tr = manager.getTransaction();
        try{
            tr.begin();
            Train result = trainDAO.delete(train);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return train; 
    }

}
