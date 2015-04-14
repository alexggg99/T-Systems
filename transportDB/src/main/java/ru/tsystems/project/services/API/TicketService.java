package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;

import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface TicketService {
	// public void createTicket(Ticket ticket) throws CustomDAOException;
	// public Contract getcreateTicketById(int id) throws CustomDAOException;
	// public void updateTicket(Ticket ticket) throws CustomDAOException;
	// public void deleteTicket(Ticket ticket) throws CustomDAOException;

	/**
	 * A method to get Ticket by number.
	 * 
	 * @param number
	 * @return
	 * @throws CustomDAOException
	 */

	public Ticket getTicketByNumber(long number) throws CustomDAOException;

	/**
	 * A method to get all tickets in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<Ticket> getAllTickets() throws CustomDAOException;

	/**
	 * A method to get tickets in the database, by input parametres(cityIn,
	 * cityOut, depatureDate)
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<RouteEntity> getTickets(String cityIn, String cityOut, String date1, String date2)
			throws CustomDAOException;
        
        
        public Ticket saveTicket(RouteEntity routeEntity, String cityFrom, 
                String cityTo, String departureDate, Passenger passeneger)
                        throws CustomDAOException;
        
        public boolean isEnoughTicketsOnTrain(Route route);
        
        public boolean isPassengerOnTrain(Route route, Passenger passenger);
        
        public boolean isMoreTh10min(int routeEntity_id);
}
