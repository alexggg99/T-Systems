package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;

@Controller
public class PassengersController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(PassengersController.class);

    @Autowired
    PassengerService passengerService;

    public PassengersController() {
    }

    @RequestMapping(value = "/showPassengers")
    public String doPost(HttpServletRequest request,
            @RequestParam("RouteId") String routeId) 
            throws ServletException, IOException {
        try {
            List<Passenger> list = null;
            // method returns passengers
            list = passengerService.getAllPassangersOnTrain(Integer.parseInt(routeId));
            request.setAttribute("passengersOnTrain", list);
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            request.setAttribute("exception", ex);
            return "employee/error_page";
        }
        return "employee/cp_employee_showPassengers";
    }

}
