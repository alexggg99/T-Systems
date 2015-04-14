package ru.tsystems.project.domain.DAO.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;

import ru.tsystems.project.domain.DAO.interfaces.RouteEntityDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.exceptions.CustomDAOException;

@Repository("routeEntityDAO")
public class RouteEntityDAOImpl implements RouteEntityDAO {

    @PersistenceContext
    private EntityManager manager;

    public RouteEntityDAOImpl() {
    }

    @Override
    public RouteEntity save(RouteEntity entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save routeEntity: " + ex);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RouteEntity> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery(RouteEntity.GETALL);
        List<RouteEntity> result = query.getResultList();
        return result;
    }

    @Override
    public RouteEntity delete(RouteEntity entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove routeEntity: " + ex);
        }
        return entity;
    }

    @Override
    public RouteEntity update(RouteEntity entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update routeEntity: " + ex);
        }
        return entity;
    }

    @Override
    public RouteEntity findById(int id) {
        RouteEntity result = manager.find(RouteEntity.class, id);
        return result;
    }

    @Override
    public List<RouteEntity> getRoutesEnteties(String cityFrom, String cityTo, String date)
            throws CustomDAOException {
        String query = "SELECT r FROM RouteEntity r where " + "r.station.name in (:cityTo, :cityFrom) " + "and r.arrivalTime between :date1 and :date2";
        Query q = manager.createQuery(query);
        q.setParameter("cityFrom", cityFrom);
        q.setParameter("cityTo", cityTo);
        // today date
        q.setParameter("date1", new Date(), TemporalType.DATE);
        // parse input date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2;
        try {
            date2 = dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new CustomDAOException("Wrong input data");
        }
        q.setParameter("date2", date2, TemporalType.DATE);
        return q.getResultList();
    }

}
