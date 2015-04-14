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
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteEntityService;

import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.implementations.RouteEntityServiceImpl;
import ru.tsystems.project.services.implementations.StationServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/AddRouteEntityServlet")
public class AddRouteEntityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AddRouteEntityServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRouteEntityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RouteEntityService routeEntityService = new RouteEntityServiceImpl();
        try {
            String rtId = request.getParameter("route");
            String stId = request.getParameter("station");
            String depatureDate = request.getParameter("depatureDate");
            String arrivalDate = request.getParameter("arrivalDate");
            String seq = request.getParameter("sequence");
            RouteEntity result = null;
            
            int sequence = Integer.parseInt(seq);
            int routeId = Integer.parseInt(rtId);
            int stationId = Integer.parseInt(stId);
            //method returns added route entity
            result = routeEntityService.addRouteEntity(routeId, 
                    stationId, arrivalDate, depatureDate, sequence);
            request.setAttribute("isRouteEntityInputSucceed", "true");

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_main.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception ex) {
            logger.error(ex);
            request.setAttribute("exception", ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
