package ru.tsystems.project.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.services.API.PassengerService;

import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;
import ru.tsystems.project.services.implementations.StationServiceImplementation;
import ru.tsystems.project.services.implementations.TicketServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(BuyTicketServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyTicketServlet() {
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
            String cityFrom = (String) request.getSession().getAttribute("cityFrom");
            String cityTo = (String) request.getSession().getAttribute("cityTo");
            String firstName = (String) request.getParameter("firstName");
            String lastName = (String) request.getParameter("lastName");
            String birthday = (String) request.getParameter("birthday");
            //route entity id that bought client
            int routeEntityId = Integer.valueOf(request.getParameter("item"));
            List<RouteEntity> list = (List<RouteEntity>) request.getSession().getAttribute("tickets");
            RouteEntity targetRouteEntity = null;
//            if (list == null || routeEntityId == 0) {
//                //if no trains in list
//                response.sendRedirect("/index.html");
//            }
            if (firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty()) {
                request.getSession().setAttribute("isInputValid", "false");
                response.sendRedirect("/cp_client/cp_client_buyticket.jsp?item=" + routeEntityId);
            } else {
                //find route entity
                for (int i = 0; i < list.size(); i++) {
                    RouteEntity e = list.get(i);
                    if (e.getRouteEntity_id() == routeEntityId) {
                        targetRouteEntity = e;
                    }
                }
                TicketService ticketService = new TicketServiceImplementation();
                PassengerService passenegerService = new PassengerServiceImplementation();

                //check if purchase is possible
                Passenger pass = new Passenger();
                pass.setFirstName(firstName);
                pass.setLastName(lastName);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date inputDate = null;
                try {
                    //parse input date of birth
                    inputDate = dateFormat.parse(birthday);
                } catch (ParseException ex) {
                }
                pass.setBirthday(inputDate);
//                boolean ifPurchasePossible = ticketService.ifPurchasePossible(targetRouteEntity
//                        .getRoute(), pass, targetRouteEntity);
                //System.out.println(ifPurchasePossible);
//                if (ifPurchasePossible) {
//                    Passenger passenger = passenegerService.createPasseneger(firstName, firstName, birthday);
//
//                    Ticket result = ticketService.saveTicket(targetRouteEntity, cityFrom, cityTo, birthday, passenger);
//                }

                if (!ticketService.isEnoughTicketsOnTrain(targetRouteEntity.getRoute())) {

                    request.getSession().setAttribute("isEnoughTickets", "false");
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/cp_client_buyticket.jsp");
                    requestDispatcher.forward(request, response);

                }
                if (ticketService.isPassengerOnTrain(targetRouteEntity.getRoute(), pass)) {
                    request.getSession().setAttribute("isPassengerOnTrain", "false");
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/cp_client_buyticket.jsp");
                    requestDispatcher.forward(request, response);
                }
                if (!ticketService.isMoreTh10min(targetRouteEntity)) {
                    request.getSession().setAttribute("isMoreTh10min", "false");
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/cp_client_buyticket.jsp");
                    requestDispatcher.forward(request, response);
                }

                Passenger passenger = passenegerService.createPasseneger(firstName, lastName, birthday);
                Ticket result = ticketService.saveTicket(targetRouteEntity, cityFrom, cityTo, birthday, passenger);

                //ticket successfully bought
                request.getSession().setAttribute("isTicketBought", "true");
                RequestDispatcher requestDispatcher = getServletContext()
                        .getRequestDispatcher("/cp_client/error_page.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            logger.error(ex);
            request.setAttribute("exception", ex);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher("/cp_client/error_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }

}
