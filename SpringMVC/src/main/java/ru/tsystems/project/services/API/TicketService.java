package ru.tsystems.project.services.API;

import java.util.Date;
import java.util.List;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;

import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.TicketDTO;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface TicketService {

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
     * A method to save ticket to database
     *
     * @param routeEntity  route entity
     * @param cityFrom  city from
     * @param cityTo  city to
     * @param departureDate  departure date
     * @param passeneger  passenger who registered on the train
     * @return
     * @throws CustomDAOException
     */
    public Ticket saveTicket(RouteEntity routeEntity, String cityFrom,
            String cityTo, String departureDate, Passenger passeneger)
            throws CustomDAOException;

    /**
     * A method to check if train is full
     *
     * @param route  route to check
     * @return
     * @throws CustomDAOException
     */
    public boolean isTrainFull(Route route);

    /**
     * A method to check if passenger on the train
     *
     * @param route  
     * @param passenger  
     * @return
     * @throws CustomDAOException
     */
    public boolean isPassengerOnTrain(Route route, Passenger passenger);

    /**
     * A method to check if more than 10 minutes to departure
     *
     * @param routeEntity  route entity to check
     * @return
     * @throws CustomDAOException
     */
    public boolean isMoreTh10min(RouteEntity routeEntity);

    /**
     * A method to get ticket by id
     *
     * @return
     * @throws CustomDAOException
     */
    public Ticket findById(int id);

    /**
     * A method to get tickets for web service.
     *
     * @param dateFrom  departure date from which start to look for
     * @param dateTo  departure date from which stop to look for
     * @return
     * @throws CustomDAOException
     */
    public List<TicketDTO> getTickets(Date dateFrom, Date dateTo) throws CustomDAOException;
}
