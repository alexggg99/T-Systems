package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import ru.tsystems.project.domain.DAO.implementation.DaoFactory;
import ru.tsystems.project.domain.DAO.implementation.RouteDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.StationDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.TrainDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.RouteDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteService;

/**
 * An implementation of the API.
 */
public class RouteServiceImpl implements RouteService {

    private final EntityManager manager = DaoFactory.manager;
    private final RouteDAO routeDAO = new RouteDAOImpl(manager);

    @Override
    public List<Route> getAllRoutes() throws CustomDAOException {
        EntityTransaction tr = manager.getTransaction();
        List<Route> result = new ArrayList<>();
        try {
            tr.begin();
            result = routeDAO.findAll();
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public Route getById(int routeId) throws CustomDAOException {
        Route result = manager.find(Route.class, routeId);
        return result;
    }


}
