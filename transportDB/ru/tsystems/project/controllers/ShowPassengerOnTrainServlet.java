package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;


/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/ShowPassengers")
public class ShowPassengerOnTrainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ShowPassengerOnTrainServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPassengerOnTrainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PassengerService passengerService = new PassengerServiceImplementation();
        try {
            String routeId = request.getParameter("RouteId");
            List<Passenger> list = null;
            //method returns passengers
            list = passengerService.getAllPassangersOnTrain(Integer.parseInt(routeId));
            request.setAttribute("passengersOnTrain", list);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_showPassengers.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException ex) {
            logger.error(ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            requestDispatcher.forward(request, response);
        }catch (NumberFormatException ex) {
            logger.error(ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            request.setAttribute("exception", ex);
            requestDispatcher.forward(request, response);
        }

    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("../login.jsp");
    }

}
