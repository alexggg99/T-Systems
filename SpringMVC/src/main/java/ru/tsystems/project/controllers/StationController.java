package ru.tsystems.project.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.implementations.StationServiceImplementation;

@Controller
public class StationController {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(StationController.class);

        @Autowired
        StationService stationService;
        
	@RequestMapping(value = "/addstation", method = RequestMethod.POST)
	public String addStation(@RequestParam("stationName") String name, HttpServletRequest request) {
		
		try {
			Station station = null;
			if (name.length() < 100) {
				// method returns added station
				station = stationService.addStation(name);
				request.setAttribute("isStationInputSucceed", "true");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			request.setAttribute("isStationInputSucceed", "false");
                        request.setAttribute("exception", ex);
			return "employee/error_page";
		}
		return "employee/employee_main";
	}

}
