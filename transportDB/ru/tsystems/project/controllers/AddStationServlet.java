package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.tsystems.project.domain.entities.Station;

import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.implementations.StationServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/AddStationServlet")
public class AddStationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        StationService stationService = new StationServiceImplementation();
        try {
            String stationName = request.getParameter("stationName");
            Station station = null;
            if(!stationName.isEmpty()){
                //method returns added station
                station = stationService.addStation(stationName);
            }

            request.setAttribute("station", station);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/cp_employee_main.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException ex) {
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_employee/error_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }

}
