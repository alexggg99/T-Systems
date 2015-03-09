package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.implementation.DaoFactory;
import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;

/**
 * An implementation of the API.
 */
public class TicketServiceImplementation implements TicketService {

    private final EntityManager entityManager = DaoFactory.getEntityManager();
    private final TicketDAO ticketDAO = new TicketDAOImpl(entityManager);

    @Override
    public Ticket getTicketByNumber(long number) throws CustomDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() throws CustomDAOException {
        try {
            List<Ticket> list = ticketDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomDAOException("Unable to find tickets!", ex);
        }
    }

    @Override
    public List<RouteEntity> getTickets(String cityFrom, String cityTo,
            String date1, String date2) throws CustomDAOException {
        return null;
    }

    @Override
    public Ticket saveTicket(RouteEntity routeEntity, String cityFrom,
            String cityTo, String departureDate, Passenger passeneger)
            throws CustomDAOException {
        EntityTransaction tr = entityManager.getTransaction();
        Ticket ticket = new Ticket();
        StationService stationService = new StationServiceImplementation();
        Station stationFrom = stationService.getStation(cityFrom);
        Station stationTo = stationService.getStation(cityTo);
        ticket.setStationFrom(stationFrom);
        ticket.setStationTo(stationTo);
        ticket.setPassenger(passeneger);
        ticket.setDepatureTime(routeEntity.getDepatureTime());
        ticket.setRoute(routeEntity.getRoute());
        ticket.setTrain(routeEntity.getRoute().getTrain());
        try {
            tr.begin();
            ticketDAO.save(ticket);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return ticket;
    }
    
    @Override
    public boolean isEnoughTicketsOnTrain(Route route){
        String query = "Select count(t) from Ticket t where t.route = :route";
        Query q = entityManager.createQuery(query);
        q.setParameter("route", route);
        long ticketsBought =  (long) q.getSingleResult();
        int ticketsAvalible = route.getTrain().getSeats();
        return (ticketsAvalible > ticketsBought);
    }
    
    @Override
    public boolean isPassengerOnTrain(Route route, Passenger passenger){
        //check if passenger already registed on train
        List<Passenger> passengerList = entityManager.createNamedQuery(Passenger.GETPASSONTRAIN)
                .setParameter("route", route).getResultList();
        return passengerList.contains(passenger);
    }

    @Override
    public boolean isMoreTh10min(RouteEntity routeEntity) {
        //check its more than 10 min till departure
        Date departueTime = routeEntity.getDepatureTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 10);
        return cal.getTime().before(departueTime);
    }


}
