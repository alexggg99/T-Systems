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
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.services.API.TrainService;
import ru.tsystems.project.services.implementations.TrainServiceImpl;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/ShowAllTrains")
public class ShowAllTrainsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ShowAllTrainsServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllTrainsServlet() {
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
            List<Train> list = null;
            //method returns passengers
            list = trainService.getAllTrains();
            request.setAttribute("allTrains", list);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_showAllTrains.jsp");
            requestDispatcher.forward(request, response);
        } catch (RuntimeException ex) {
            logger.error(ex);
            request.setAttribute("exception", ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("../login.jsp");
    }
    
    

}
