package ru.tsystems.project.domain.DAO.interfaces;

import ru.tsystems.project.domain.entities.Passenger;

public interface PassengerDAO extends GenericDAO<Passenger> {
	Passenger getPassengerByPassword(String password);

	Passenger getPassengerByLastName(String lastName);
}
