package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ru.tsystems.project.domain.entities.RouteEntity;

import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.implementations.RouteEntityServiceImpl;
import ru.tsystems.project.services.implementations.RouteServiceImpl;
import ru.tsystems.project.services.implementations.StationServiceImplementation;
import ru.tsystems.project.services.implementations.TicketServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/FindTrainsStationServlet")
public class FindTrainsStationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FindTrainsStationServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTrainsStationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            String station = request.getParameter("station");
            if (station.length() > 100 || station == null){
                throw new Exception("Wrong input values");
            } else {
                //input data valid
                try {
                    StationService stationService = new StationServiceImplementation();
                    List<RouteEntity> list = stationService.getTrains(station);

                    request.setAttribute("trains", list);

                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/cp_client_trains.jsp");
                    requestDispatcher.forward(request, response);
                } catch (RuntimeException ex) {
                    logger.error(ex);
                    request.setAttribute("exception", ex);
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/error_page.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } catch (Exception exception) {
            response.sendRedirect("/index.html");
        }
    }
}
