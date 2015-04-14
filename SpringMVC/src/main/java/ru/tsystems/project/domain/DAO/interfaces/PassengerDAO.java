package ru.tsystems.project.domain.DAO.interfaces;

import java.util.List;

import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Ticket;

public interface PassengerDAO extends GenericDAO<Passenger> {

	Passenger getPassengerByPassword(String password);

	Passenger getPassengerByLastName(String lastName);

	List<Ticket> getAllPassangersOnTrain(int trainId);
        
        List<Passenger> getPassangers(int routeId);

}
