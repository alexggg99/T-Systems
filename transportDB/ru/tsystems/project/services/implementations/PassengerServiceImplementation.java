package ru.tsystems.project.services.implementations;

import java.util.List;

import ru.tsystems.project.domain.DAO.implementation.PassengerDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.PassengerService;

/**
 * An implementation of the API.
 */
public class PassengerServiceImplementation implements PassengerService {

	private final PassengerDAO ticketDAO = new PassengerDAOImpl();

	@Override
	public Passenger getPassangerByLastName(String login) throws CustomDAOException {
		PassengerDAO passengerService = new PassengerDAOImpl();
                Passenger passenger = passengerService
						.getPassengerByLastName(login);
		return passenger;
	}

	@Override
	public List<Passenger> getAllPassangersOnTrain(String cityIn,
			String cityOut, String date) throws CustomDAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
