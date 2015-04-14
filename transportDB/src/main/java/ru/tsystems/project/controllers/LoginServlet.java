package ru.tsystems.project.controllers;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.tsystems.project.domain.entities.Passenger;

import ru.tsystems.project.services.API.PassengerService;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/controllers/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PassengerService passengerService
                = new PassengerServiceImplementation();
        try {
            String login = request.getParameter("lastName");
            ;
            if (login.length() > 100) {
                throw new Exception("Last name is too long");
            } else {
                Passenger passenger = passengerService
                        .getPassangerByLastName(login);
                if (passenger == null) {
                    throw new Exception("There is no user with the login "
                            + login);
                } else {
                    String password = request.getParameter("password");
                    if (passenger.getPassword().equals(password)) {
                        if (passenger.getRole().getRolesId() == 1) {
                            // if login belongs to passenger
                            request.getSession().setAttribute("currentUserU",
                                    passenger);
                            response.sendRedirect("../index.html");
                        } else if (passenger.getRole().getRolesId() == 2) {
                            // login belongs to employees
                            request.getSession().setAttribute("currentUserU",
                                    passenger);
                            response.sendRedirect("../cp_employee/cp_employee_main.jsp");
                        } else {
                            throw new Exception("The role of user is undefined");
                        }
                    } else {
                        throw new Exception("The users passwords do not match");
                    }
                }
            }
        } catch (Exception exception) {
            logger.error(exception);
            request.getSession().invalidate();
            request.getSession().setAttribute("isInputValid", "false");
            response.sendRedirect("../login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
