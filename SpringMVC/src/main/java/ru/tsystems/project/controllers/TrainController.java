package ru.tsystems.project.controllers;

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
import ru.tsystems.project.domain.entities.RouteEntity;

import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TrainService;
import ru.tsystems.project.services.implementations.TrainServiceImpl;

@Controller
public class TrainController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    TrainService trainService;
    @Autowired
    StationService stationService;

    
    public TrainController() {
    }

    @RequestMapping(value = "/addTrain", method = RequestMethod.POST)
    public String doPost(@RequestParam("trainName") String trainName,
            @RequestParam("trainSeats") String trainSeats,
            HttpServletRequest request) {
        try {
            int seats = Integer.valueOf(trainSeats);
            if (trainName.length() < 100) {
                Train train = null;
                // method returns added station
                train = trainService.addTrain(trainName, seats);
                request.setAttribute("isTrainInputSucceed", "true");
            }
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            request.setAttribute("exception", ex);
            return "employee/error_page";
        }
        return "employee/employee_main";
    }
    
    @RequestMapping(value = "/showAllTrains", method = RequestMethod.POST)
    public String showAllTrains(HttpServletRequest requeste){
        try {
            List<Train> list = null;
            //method returns passengers
            list = trainService.getAllTrains();
            requeste.setAttribute("allTrains", list);
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            requeste.setAttribute("exception", ex);
            return "employee/error_page";
        }
        return "employee/cp_employee_showAllTrains";

    }
    
    @RequestMapping(value = "/showTrainsOnStation")
    public String showTrainsOnStation(HttpServletRequest request){
        try {
            String station = request.getParameter("station");
            if (station.length() > 100 || station == null){
                throw new RuntimeException("Wrong input values");
            } else {
                List<RouteEntity> list = stationService.getTrains(station);
                request.setAttribute("trains", list);
            }
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            request.setAttribute("exception", ex);
            return "client/error_page";
        }
        return "client/cp_client_trains";
    }
}
