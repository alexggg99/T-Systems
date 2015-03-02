package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface StationService {
	// public void createTicket(Ticket ticket) throws CustomDAOException;
	// public Contract getcreateTicketById(int id) throws CustomDAOException;
	// public void updateTicket(Ticket ticket) throws CustomDAOException;
	// public void deleteTicket(Ticket ticket) throws CustomDAOException;

	/**
	 * A method to add Station.
	 * 
	 * @param number
	 * @return
	 * @throws CustomDAOException
	 */

	public Station addStation(String name) throws CustomDAOException;

	/**
	 * A method to get all stations in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<Station> getAllStations() throws CustomDAOException;


}
