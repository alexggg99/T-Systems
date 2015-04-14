package ru.tsystems.project.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Ticket;

import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.API.TicketService;
import ru.tsystems.project.services.implementations.TrainServiceImpl;



@Controller
public class TicketController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    PassengerService passengerService;
    @Autowired
    TicketService ticketService;

    public TicketController() {
    }

    @RequestMapping(value = "/buyTicket", method = RequestMethod.POST)
    public String buyTicket(HttpServletRequest request) {
        try {
            
            String cityFrom = (String) request.getSession().getAttribute("cityFrom");
            String cityTo = (String) request.getSession().getAttribute("cityTo");
            String firstName = (String) request.getParameter("firstName");
            String lastName = (String) request.getParameter("lastName");
            String birthday = (String) request.getParameter("birthday");
            //route entity id that bought client
            int index = Integer.valueOf(request.getParameter("index"));
            List<RouteEntity> list = (List<RouteEntity>) request.getSession().getAttribute("tickets");
            RouteEntity targetRouteEntity = list.get(index);
            
            if (firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty()) {
                request.setAttribute("exception", new Exception("String is too long"));
                return "client/error_page";
            } else {

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
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("birthday", birthday);
                if (ticketService.isTrainFull(targetRouteEntity.getRoute())) {
                    //train is full
                    request.getSession().setAttribute("isEnoughTickets", "false");
                    request.getSession().setAttribute("isPassengerOnTrain", "true");
                    request.getSession().setAttribute("isMoreTh10min", "true");
                    return "client/cp_client_buyticket";

                }
                if (ticketService.isPassengerOnTrain(targetRouteEntity.getRoute(), pass)) {
                    request.getSession().setAttribute("isEnoughTickets", "true");
                    request.getSession().setAttribute("isPassengerOnTrain", "false");
                    request.getSession().setAttribute("isMoreTh10min", "true");
                    return "client/cp_client_buyticket";
                }
                if (!ticketService.isMoreTh10min(targetRouteEntity)) {
                    request.getSession().setAttribute("isEnoughTickets", "true");
                    request.getSession().setAttribute("isPassengerOnTrain", "true");
                    request.getSession().setAttribute("isMoreTh10min", "false");
                    return "client/cp_client_buyticket";
                } 
                
                   else {
                    
                    Passenger passenger = passengerService.createPasseneger(firstName, lastName, birthday);
                    Ticket result = ticketService.saveTicket(targetRouteEntity, cityFrom, cityTo, birthday, passenger);
                    //ticket successfully bought
                    request.getSession().setAttribute("isTicketBought", "true");
                    return "client/ticket_bought";
                }
            }
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            request.setAttribute("isRouteEntityInputSucceed", "false");
            request.setAttribute("exception", ex);
            return "client/error_page";
        }
        //return "client/cp_client_buyticket";
    }

    @RequestMapping(value = "/setTicket")
    public String showTrainsOnStation(HttpServletRequest request) {
        return "client/cp_client_buyticket";
    }
}
