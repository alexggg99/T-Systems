package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface TrainService {
	// public void createTicket(Ticket ticket) throws CustomDAOException;
	// public Contract getcreateTicketById(int id) throws CustomDAOException;
	// public void updateTicket(Ticket ticket) throws CustomDAOException;
	// public void deleteTicket(Ticket ticket) throws CustomDAOException;

	/**
	 * A method to add a Train.
	 * 
	 * @param name
         * @param seats
	 * @return
	 * @throws CustomDAOException
	 */
	public Train addTrain(String name, int seats) throws CustomDAOException;

	/**
	 * A method to get all trains in the database,
	 * 
	 * @return
	 * @throws CustomDAOException
	 */
	public List<Train> getAllTrains() throws CustomDAOException;
        
        /**
	 * A method to remove a Train.
	 * 
	 * @param train
	 * @return
	 * @throws CustomDAOException
	 */
        public Train removeTrain(Train train) throws CustomDAOException;


}
