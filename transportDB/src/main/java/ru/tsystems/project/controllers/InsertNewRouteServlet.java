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
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteService;

import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.implementations.RouteServiceImpl;
import ru.tsystems.project.services.implementations.StationServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/InsertNewRouteServlet")
public class InsertNewRouteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(InsertNewRouteServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNewRouteServlet() {
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
            StationService stationService = new StationServiceImplementation();
            List<Station> stationList = stationService.getAllStations();
            request.getSession().setAttribute("stationList", stationList);
            
            RouteService routeService = new RouteServiceImpl();
            List<Route> routeList = routeService.getAllRoutes();
            request.getSession().setAttribute("routeList", routeList);
            
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_add_route.jsp");
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
