package ru.tsystems.project.services.implementations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import ru.tsystems.project.domain.DAO.implementation.DaoFactory;
import ru.tsystems.project.domain.DAO.implementation.RouteDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.RouteEntityDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.RouteDAO;
import ru.tsystems.project.domain.DAO.interfaces.RouteEntityDAO;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;

/**
 * An implementation of the API.
 */
public class RouteEntityServiceImpl implements RouteEntityService {

    private final EntityManager manager = DaoFactory.manager;
    private final RouteEntityDAO routeEntityDAO = new RouteEntityDAOImpl(manager);

    @Override
    public List<RouteEntity> getAllRoutes() throws CustomDAOException {
        EntityTransaction tr = manager.getTransaction();
        List<RouteEntity> result = new ArrayList<>();
        List<RouteEntity> list;
        try {
            tr.begin();
            list = routeEntityDAO.findAll();
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return list;
    }

    @Override
    public List<RouteEntity> getRoutesEnteties(String cityFrom, String cityTo,
            String date) throws CustomDAOException {
        List<RouteEntity> result = null;
        try {
            String query = "SELECT r FROM RouteEntity r where "
                    + "r.station.name in (:cityTo, :cityFrom) "
                    + "and r.arrivalTime between :date1 and :date2";
            Query q = manager.createQuery(query);
            q.setParameter("cityFrom", cityFrom);
            q.setParameter("cityTo", cityTo);
            //today date
            q.setParameter("date1", new Date(), TemporalType.DATE);
            //parse input date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat.parse(date);
            q.setParameter("date2", date2, TemporalType.DATE);

            //keep only entires where route_id the same
            return q.getResultList();
        } catch (ParseException ex) {
        }
        return result;
    }

}
