package ru.tsystems.project.domain.DAO.interfaces;

import java.util.List;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;

public interface TicketDAO extends GenericDAO<Ticket> {
    public List<RouteEntity> getTickets(String cityFrom, String cityTo, String date1, String date2);
}
