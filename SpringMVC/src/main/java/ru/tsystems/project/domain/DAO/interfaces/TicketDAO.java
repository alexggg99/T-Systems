package ru.tsystems.project.domain.DAO.interfaces;

import java.util.Date;
import java.util.List;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;

public interface TicketDAO extends GenericDAO<Ticket> {
    public int countTicketsOnTrain(Route route);
    public List<Passenger> passengersOnTrain(Route route);
    public List<Ticket> getTickets(Date dateFrom, Date dateTo);
}
