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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.implementation.PassengerDAOImpl;
import ru.tsystems.project.domain.DAO.implementation.TrainDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Role;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.PassengerService;

@Transactional
public class PassengerServiceImplementation implements PassengerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDAO passengerDAO;

    @Override
    public Passenger getPassangerByLastName(String login) throws CustomDAOException {
        Passenger passenger;
        passenger = passengerDAO.getPassengerByLastName(login);
        return passenger;
    }

    @Override
    public List<Passenger> getAllPassangersOnTrain(int routeId) {
        List<Passenger> result = null;
        result = passengerDAO.getPassangers(routeId);
        return result;
    }

    @Override
    public Passenger createPasseneger(String firstName, String lastName, String birthday) throws CustomDAOException {
        Passenger passeneger = new Passenger();
        passeneger.setFirstName(firstName);
        passeneger.setLastName(lastName);
        passeneger.setRole(entityManager.find(Role.class, 1));
        //convert string to object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(birthday);
        } catch (ParseException ex) {
        }
        passeneger.setBirthday(parsedDate);
        Passenger result;
        result = passengerDAO.save(passeneger);
        return result;
    }



}
