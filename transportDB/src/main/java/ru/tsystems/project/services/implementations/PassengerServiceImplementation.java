package ru.tsystems.project.services.implementations;

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
import ru.tsystems.project.domain.DAO.implementation.DaoFactory;

import ru.tsystems.project.domain.DAO.implementation.PassengerDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.TrainDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Role;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.PassengerService;

/**
 * An implementation of the API.
 */
public class PassengerServiceImplementation implements PassengerService {

    private final EntityManager entityManager = DaoFactory.getEntityManager();
    private final PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);

    @Override
    public Passenger getPassangerByLastName(String login) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Passenger passenger;
        try {
            tr.begin();
            passenger = passengerDAO
                    .getPassengerByLastName(login);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return passenger;
    }

    @Override
    public List<Passenger> getAllPassangersOnTrain(int routeId) {
        EntityTransaction tr = entityManager.getTransaction();
        List<Passenger> result = null;
        try {
            tr.begin();
            Query query = entityManager.createQuery("Select DISTINCT tc.passenger from Ticket tc join tc.route r where r.routeId = :routeId");
            query.setParameter("routeId", routeId);
            result = query.getResultList();
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public Passenger createPasseneger(String firstName, String lastName, String birthday) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Passenger passeneger = new Passenger();
        passeneger.setFirstName(firstName);
        passeneger.setLastName(lastName);
        passeneger.setRole(entityManager.find(Role.class,1));
        //convert string to object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(birthday);
        } catch (ParseException ex) {}
        passeneger.setBirthday(parsedDate);
        Passenger result;
        try {
            tr.begin();
            result = passengerDAO.save(passeneger);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return result;
    }

    @Override
    public Passenger deletePasseneger(Passenger passenger) throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Passenger pas = null;
        try {
            tr.begin();
            pas = passengerDAO
                    .getPassengerByLastName(passenger.getLastName());
            entityManager.remove(pas);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return pas;
    }


}
