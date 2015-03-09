package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface RouteEntityService {
	// public void createTicket(Ticket ticket) throws CustomDAOException;
	// public Contract getcreateTicketById(int id) throws CustomDAOException;
	// public void updateTicket(Ticket ticket) throws CustomDAOException;
	// public void deleteTicket(Ticket ticket) throws CustomDAOException;

    
        /**
	 * A method to get all routes entities in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
        public List<RouteEntity> getAllRoutes() throws CustomDAOException;
    
	/**
	 * A method to get routes entities in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<RouteEntity> getRoutesEnteties(String cityIn, String cityOut, 
                String date) throws CustomDAOException;


}
