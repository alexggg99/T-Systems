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
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;
import ru.tsystems.project.services.API.StationService;

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

    @Override
    public RouteEntity addRouteEntity(int routeId, int stationId, String arrivalDate,
            String departureDate, int sequence) throws CustomDAOException {
        EntityTransaction tr = manager.getTransaction();
        RouteEntity result = null;
        StationService stationService = new StationServiceImplementation();
        RouteService routeService = new RouteServiceImpl();
        Route route;
        Station station;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date arrdate;
        Date depdate;
        try {
            arrdate = format.parse(arrivalDate);
            depdate = format.parse(departureDate);
        } catch (ParseException ex) {
            throw new CustomDAOException("parse date exception");
        }
        try {
            RouteEntity entity = new RouteEntity();
            tr.begin();
            route = routeService.getById(routeId);
            station = stationService.getById(stationId);
            entity.setRoute(route);
            entity.setStation(station);
            entity.setArrivalTime(arrdate);
            entity.setDepatureTime(depdate);
            entity.setSeqNumber(sequence);
            result = routeEntityDAO.save(entity);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

}
