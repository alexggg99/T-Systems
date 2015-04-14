package ru.tsystems.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;

@Controller
public class LoginController {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class.getName());

        @Autowired
        PassengerService passengerService;
        
	@RequestMapping(value = "/checkUser")
	public String doPost(@ModelAttribute Passenger passenger, HttpServletRequest request) {
		
                String model = "login";
                
		try {
			Passenger pass = passengerService.getPassangerByLastName(passenger.getLastName());
			String p = passenger.getPassword();
			if (pass != null && !p.isEmpty()) {
				if (pass.getPassword().equals(passenger.getPassword())) {
					return "employee/employee_main";
				}
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			request.setAttribute("isLoginInValid", true);
			// return new ModelAndView("login", "passenger", new Passenger());
			return "login";
		}
		request.setAttribute("isLoginInValid", true);
		return model;
	}
}
