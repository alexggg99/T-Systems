package ru.tsystems.project.domain.DAO.implementation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;

import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;

@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext
    private EntityManager manager;

    public TicketDAOImpl() {
    }

    @Override
    public Ticket save(Ticket entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save ticket: " + ex);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery(Ticket.GETALL);
        List<Ticket> result = query.getResultList();
        return result;
    }

    @Override
    public Ticket delete(Ticket entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove ticket: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket update(Ticket entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update ticket: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket findById(int id) {
        Ticket result = manager.find(Ticket.class, id);
        return result;
    }

    @Override
    public int countTicketsOnTrain(Route route) {
        String query = "Select count(t) from Ticket t where t.route = :route";
        Query q = manager.createQuery(query);
        q.setParameter("route", route);
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    @Override
    public List<Passenger> passengersOnTrain(Route route){
        return manager.createNamedQuery(Passenger.GETPASSONTRAIN).setParameter("route", route).getResultList();
    };

    @Override
    public List<Ticket> getTickets(Date dateFrom, Date dateTo) {
        String query = "Select t from Ticket t where t.depatureTime between :date1 and :date2";
        Query q = manager.createQuery(query);
        q.setParameter("date1", dateFrom, TemporalType.DATE);
        q.setParameter("date2", dateTo, TemporalType.DATE);
        List<Ticket> result = q.getResultList();
        return result;
    }

}
