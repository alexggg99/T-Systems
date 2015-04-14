package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;
import ru.tsystems.project.domain.entities.RouteEntity;

import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.implementations.RouteEntityServiceImpl;
import ru.tsystems.project.services.implementations.RouteServiceImpl;
import ru.tsystems.project.services.implementations.TicketServiceImplementation;

/**
 * Servlet implementation class FindTickets
 */
@WebServlet("/controllers/FindTicketsServlet")
public class FindTicketsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(FindTicketsServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTicketsServlet() {
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
            String cityFrom = request.getParameter("cityFrom");
            String cityTo = request.getParameter("cityTo");
            String date = request.getParameter("depatureDate");
            if ((cityFrom.length() > 100)
                    || (cityTo.length() > 100)
                    || (date.length() > 100)) {
                throw new Exception("Wrong input values");
            } else {
                //input data valid
                try {
                    RouteEntityService routeService = new RouteEntityServiceImpl();
                    List<RouteEntity> list = routeService.getRoutesEnteties(cityFrom, cityTo, date);

                    //find dublicates routeId
                    List<Integer> listContainingDublicats = new ArrayList<>();
                    for(RouteEntity en : list){
                        listContainingDublicats.add(en.getRoute().getRouteId());
                    }
                    Set<Integer> setToReturn = new HashSet<>();
                    Set<Integer> set1 = new HashSet<>();

                    for (Integer yourInt : listContainingDublicats) {
                        if (!set1.add(yourInt)) {
                            setToReturn.add(yourInt);
                        }
                    }
                    
                    //clead result list
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        RouteEntity tmp = (RouteEntity) it.next();
                        if (!setToReturn.contains(tmp.getRoute().getRouteId())) {
                            it.remove();
                        }
                    }
                    
                    Collections.sort(list, new Comparator<RouteEntity>() {

                        @Override
                        public int compare(RouteEntity o1, RouteEntity o2) {
                            return (o1.getRouteEntity_id() - o2.getRouteEntity_id());
                        }
                        
                    });

                    request.getSession().setAttribute("cityFrom", cityFrom);
                    request.getSession().setAttribute("cityTo", cityTo);
                    request.getSession().setAttribute("departureDate", date);
                    request.getSession().setAttribute("tickets", list);
                    request.setAttribute("tickets", list);
                    request.getSession().setAttribute("isEnoughTickets", "true");
                    request.getSession().setAttribute("isPassengerOnTrain", "true");
                    request.getSession().setAttribute("isMoreTh10min", "true");
                    

                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_client/cp_client_tickets.jsp");
                    requestDispatcher.forward(request, response);
                } catch (RuntimeException ex) {
                    logger.error(ex);
                    request.setAttribute("exception", ex);
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/cp_employee/error_page.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } catch (Exception exception) {
            response.sendRedirect("/index.html");
        }
    }
}
