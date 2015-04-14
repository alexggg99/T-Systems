package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.TicketDTO;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;

@Transactional
public class TicketServiceImplementation implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private StationService stationService;

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
    public Ticket saveTicket(RouteEntity routeEntity, String cityFrom, String cityTo, String departureDate, Passenger passeneger) throws CustomDAOException {
        Ticket ticket = new Ticket();
        Station stationFrom = stationService.getStation(cityFrom);
        Station stationTo = stationService.getStation(cityTo);
        ticket.setStationFrom(stationFrom);
        ticket.setStationTo(stationTo);
        ticket.setPassenger(passeneger);
        ticket.setDepatureTime(routeEntity.getDepatureTime());
        ticket.setRoute(routeEntity.getRoute());
        ticket.setTrain(routeEntity.getRoute().getTrain());
        ticketDAO.save(ticket);
        return ticket;
    }

    @Override
    public boolean isTrainFull(Route route) {
        long tickets = ticketDAO.countTicketsOnTrain(route);
        int ticketsAvalible = route.getTrain().getSeats();
        return (ticketsAvalible <= tickets);
    }

    @Override
    public boolean isPassengerOnTrain(Route route, Passenger passenger) {
        // check if passenger already registed on train
        List<Passenger> passengerList = ticketDAO.passengersOnTrain(route);
        return passengerList.contains(passenger);
    }

    @Override
    public boolean isMoreTh10min(RouteEntity routeEntity) {
        Date departueTime = routeEntity.getDepatureTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 10);
        return cal.getTime().before(departueTime);
    }

    @Override
    public Ticket findById(int id) {
        return ticketDAO.findById(id);
    }

    @Override
    public List<TicketDTO> getTickets(Date dateFrom, Date dateTo) throws CustomDAOException {
        List<TicketDTO> result = new ArrayList<>();
        List<Ticket> list = ticketDAO.getTickets(dateFrom, dateTo);
        for(Ticket t : list){
            TicketDTO ticket = new TicketDTO();
            ticket.setTicketId(t.getTicketId());
            ticket.setDepatureTime(t.getDepatureTime().toString());
            ticket.setPassenger(t.getPassenger().getFirstName() +' '+t.getPassenger().getLastName());
            ticket.setRoute(t.getRoute().getName());
            ticket.setStationFrom(t.getStationFrom().getName());
            ticket.setStationTo(t.getStationTo().getName());
            ticket.setTrain(t.getTrain().getName());
            result.add(ticket);
        }
        return result;
    }

}
