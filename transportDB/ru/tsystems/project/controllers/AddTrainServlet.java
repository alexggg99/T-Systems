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
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Train;

import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TrainService;
import ru.tsystems.project.services.implementations.StationServiceImplementation;
import ru.tsystems.project.services.implementations.TrainServiceImpl;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/AddTrainServlet")
public class AddTrainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddTrainServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTrainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        TrainService trainService = new TrainServiceImpl();
        try {
            String trainName = request.getParameter("trainName");
            int trainSeats = Integer.valueOf(request.getParameter("trainSeats"));
            Train train = null;
                //method returns added station
            train = trainService.addTrain(trainName, trainSeats);

            request.setAttribute("isTrainInputSucceed", "true");
            
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_main.jsp");
            
            requestDispatcher.forward(request, response);
        } catch (RuntimeException ex) {
            logger.error(ex);
            request.setAttribute("exception", ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }

}
