package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.RouteEntity;
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
	 * @param name
	 * @return
	 * @throws CustomDAOException
	 */

	public Station addStation(String name) throws CustomDAOException;
        
        /**
	 * A method to remove Station.
	 * 
	 * @param name
	 * @return
	 * @throws CustomDAOException
	 */

	public Station removeStation(Station station) throws CustomDAOException;

	/**
	 * A method to get all stations in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<Station> getAllStations() throws CustomDAOException;

        /**
	 * A method to get Station.
	 * 
	 * @param name
	 * @return
	 * @throws CustomDAOException
	 */

	public Station getStation(String name) throws CustomDAOException;
        
        /**
	 * A method to get tickets on station.
	 * 
	 * @param name
	 * @return
	 * @throws CustomDAOException
	 */

	public List<RouteEntity> getTrains(String stationName) throws CustomDAOException;
        
        public Station getById(int stationId) throws CustomDAOException;
        
}
