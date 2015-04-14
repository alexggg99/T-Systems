package ru.tsystems.project.services.API;

import java.util.List;

import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface PassengerService {
	// public void createTicket(Ticket ticket) throws CustomDAOException;
	// public Contract getcreateTicketById(int id) throws CustomDAOException;
	// public void updateTicket(Ticket ticket) throws CustomDAOException;
	// public void deleteTicket(Ticket ticket) throws CustomDAOException;

	/**
	 * A method to get Passenger by name.
	 * 
	 * @param number
	 * @return
	 * @throws CustomDAOException
	 */

	public Passenger getPassangerByLastName(String login) throws CustomDAOException;

	/**
	 * A method to get pasengers on train, by input parametres(trainId)
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<Passenger> getAllPassangersOnTrain(int trainId) throws CustomDAOException;
        
        public Passenger createPasseneger(String firstName, String lastName, String birthday)
                throws CustomDAOException;
        
        public Passenger deletePasseneger(Passenger passenger)
                throws CustomDAOException; 

}
