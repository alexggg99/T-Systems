package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;

public class TicketDAOImpl implements TicketDAO {

    private static EntityManager manager;

    public TicketDAOImpl(EntityManager manager) {
        this.manager = manager;
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
    public List<RouteEntity> getTickets(String cityFrom, String cityTo, String date1, String date2) {
        //Query query = manager.createNamedQuery(Ticket.FINDTICKETS);
        //return query.getResultList();
        return null;
    }

}
