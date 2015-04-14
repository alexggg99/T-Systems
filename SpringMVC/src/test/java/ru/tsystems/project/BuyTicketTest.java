/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import ru.tsystems.project.controllers.TicketController;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.API.TicketService;

import static org.junit.Assert.*;
import org.junit.Before;
import ru.tsystems.project.domain.entities.Passenger;

@RunWith(MockitoJUnitRunner.class)
public class BuyTicketTest {
    
    @Mock PassengerService passengerService;
    @Mock TicketService ticketService;
    
    @InjectMocks private TicketController ticketController = new TicketController();
    
    MockHttpServletRequest httpRequest;
    List<RouteEntity> list;
    RouteEntity routeEntity;
    Route route;
    Passenger pass;
    
    @Before
    public void setUp() throws ParseException{
        httpRequest = new MockHttpServletRequest("POST","/contexts");
	httpRequest.getSession().setAttribute("cityFrom", "Saint-Peterburg");
        httpRequest.getSession().setAttribute("cityTo", "Moscow");
        httpRequest.setParameter("firstName", "Ivan");
        httpRequest.setParameter("lastName", "Ivanov");
        httpRequest.setParameter("birthday", "1976-03-16");
        httpRequest.setParameter("index", "0");
        
        pass = new Passenger();
        pass.setFirstName("Ivan");
        pass.setLastName("Ivanov");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = format.parse("1976-03-16");
        pass.setBirthday(birthday);
        
        list = new ArrayList<>();
        routeEntity = new RouteEntity();
        routeEntity.setRouteEntity_id(1);
        list.add(routeEntity);
        route = new Route();
        route.setRouteId(1);
        routeEntity.setRoute(route);
        httpRequest.getSession().setAttribute("tickets",list);
    }
    
    @After
    public void tearDown() {
        httpRequest = null;
        list = null;
        routeEntity = null;
        route = null;
    }
    
    
    @Test
    public void isTrainFullTest() throws Exception {  
        Mockito.when(ticketService.isTrainFull(route)).thenReturn(Boolean.TRUE);
        assertEquals("client/cp_client_buyticket", ticketController.buyTicket(httpRequest)); ;
    }
    
    @Test
    public void isPassengerOnTrainTest() throws Exception {  
        Mockito.when(ticketService.isPassengerOnTrain(route, pass)).thenReturn(Boolean.TRUE);
        assertEquals("client/cp_client_buyticket", ticketController.buyTicket(httpRequest)); ;
    }
    
    @Test
    public void isMoreTh10minTest() throws Exception {  
        Mockito.when(ticketService.isMoreTh10min(routeEntity)).thenReturn(Boolean.FALSE);
        assertEquals("client/cp_client_buyticket", ticketController.buyTicket(httpRequest)); 
    }
    
    @Test
    public void buyTicketTest() throws Exception {  
        Mockito.when(ticketService.isTrainFull(route)).thenReturn(Boolean.FALSE);
        Mockito.when(ticketService.isPassengerOnTrain(route, pass)).thenReturn(Boolean.FALSE);
        Mockito.when(ticketService.isMoreTh10min(routeEntity)).thenReturn(Boolean.TRUE);
        assertEquals("client/ticket_bought", ticketController.buyTicket(httpRequest)); ;
    }
    
}
