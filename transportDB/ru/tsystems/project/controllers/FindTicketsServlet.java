package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.implementations.TicketServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/FindTicketsServlet")
public class FindTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindTicketsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TicketService ticketService = new TicketServiceImplementation();
		List<Ticket> list = ticketService.getAllTickets();

		request.setAttribute("tickets", list);

		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/cp_client/cp_client_tickets.jsp");
		requestDispatcher.forward(request, response);
	}

}
