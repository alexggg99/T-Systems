package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;

@Repository("passengerDao")
public class PassengerDAOImpl implements PassengerDAO {

    @PersistenceContext
    private EntityManager manager;

    public Passenger save(Passenger entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save passenger: " + ex);
        }
        return entity;
    }

    public List<Passenger> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery("Passenger.findAll");
        List<Passenger> result = query.getResultList();
        return result;
    }

    public Passenger delete(Passenger entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove passenger: " + ex);
        }
        return entity;
    }

    public Passenger update(Passenger entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update passenger: " + ex);
        }
        return entity;
    }

    public Passenger findById(int id) {
        Passenger result = manager.find(Passenger.class, id);
        return result;
    }

    public Passenger getPassengerByPassword(String password) {
        Passenger passenger;
        Query query = manager.createQuery("Select p from Passenger p where p.password = :pass");
        query.setParameter("pass", password);
        passenger = (Passenger) query.getSingleResult();
        return passenger;
    }

    public Passenger getPassengerByLastName(String lastName) {
        Passenger passenger;
        Query query = manager.createQuery("Select p from Passenger p where p.lastName = :name");
        query.setParameter("name", lastName);
        passenger = (Passenger) query.getSingleResult();
        return passenger;
    }

    public List<Ticket> getAllPassangersOnTrain(int trainId) {
        Query query = manager.createNamedQuery(Passenger.GETPASSONTRAIN);
        query.setParameter("id", trainId);
        return (List<Ticket>) query.getResultList();
    }

    public List<Passenger> getPassangers(int routeId) {
        Query query = manager.createNamedQuery(Passenger.GETPASSENGERS);
        query.setParameter("routeId", routeId);
        return (List<Passenger>) query.getResultList();
    }

}
