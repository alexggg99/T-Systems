package ru.tsystems.project.services.implementations;

import java.util.List;

import ru.tsystems.project.domain.DAO.implementation.TicketDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.TicketService;

/**
 * An implementation of the API.
 */
public class TicketServiceImplementation implements TicketService {
	private final TicketDAO ticketDAO = new TicketDAOImpl();

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
	public List<Ticket> getTickets(String cityIn, String cityOut, String date)
			throws CustomDAOException {
		try {
			List<Ticket> list = ticketDAO.findAll();
			return list;
		} catch (RuntimeException ex) {
			throw new CustomDAOException("Unable to find tickets!", ex);
		}
	}

}
