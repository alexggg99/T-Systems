/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tsystems.project.domain.DAO.interfaces.TicketDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.domain.entities.Ticket;
import ru.tsystems.project.domain.entities.TicketDTO;
import ru.tsystems.project.exceptions.BadRequestException;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.API.TicketService;

@Controller
@RequestMapping(value = "/webService")
public class WebServicesController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "{dateF}/{dateT}", method = RequestMethod.GET)
    public @ResponseBody
    List<TicketDTO> getTickets(@PathVariable String dateF, @PathVariable String dateT) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = null, dateTo = null;
        try {
            dateFrom = format.parse(dateF);
            dateTo = format.parse(dateT);
        } catch (ParseException ex) {
            throw new BadRequestException("Invalid parameters");
        }
        List<TicketDTO> result = ticketService.getTickets(dateFrom, dateTo);
        return result;
    }
    
}
