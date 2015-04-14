package ru.tsystems.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.implementations.RouteEntityServiceImpl;

@Controller
public class RouteEntityController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(RouteEntityController.class);

    @Autowired
    StationService stationService;
    @Autowired
    RouteService routeService;
    @Autowired
    RouteEntityService routeEntityService;

    @RequestMapping(value = "/addRouteEntity")
    public String addRouteEntity(HttpServletRequest request) {
        try {
            String rtId = request.getParameter("route");
            String stId = request.getParameter("station");
            String depatureDate = request.getParameter("depatureDate");
            String arrivalDate = request.getParameter("arrivalDate");
            String seq = request.getParameter("sequence");
            RouteEntity result = null;

            int sequence = Integer.parseInt(seq);
            int routeId = Integer.parseInt(rtId);
            int stationId = Integer.parseInt(stId);
            //method returns added route entity
            result = routeEntityService.addRouteEntity(routeId,
                    stationId, arrivalDate, depatureDate, sequence);
            request.setAttribute("isRouteEntityInputSucceed", "true");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            request.setAttribute("exception", ex);
            return "employee/error_page";
        }
        return "employee/employee_main";
    }

    @RequestMapping(value = "/setRouteEntity")
    public ModelAndView setRouteEntity(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("employee/cp_employee_add_route");
        List<Station> stationList = stationService.getAllStations();
        List<Route> routeList = routeService.getAllRoutes();
        request.getSession().setAttribute("routeList", routeList);
        request.getSession().setAttribute("stationList", stationList);

        return model;
    }

    @RequestMapping(value = "/getRoutesEnteties")
    public String getRoutesEnteties(HttpServletRequest request) {
        try {
            String cityFrom = request.getParameter("cityFrom");
            String cityTo = request.getParameter("cityTo");
            String date = request.getParameter("depatureDate");
            if ((cityFrom.length() > 100)
                    || (cityTo.length() > 100)
                    || (date.length() > 100)) {
                throw new Exception("Wrong input values");
            } else {
                List<RouteEntity> list = routeEntityService.getRoutesEnteties(cityFrom, cityTo, date);

                //find dublicates routeId
                List<Integer> listContainingDublicats = new ArrayList<>();
                for (RouteEntity en : list) {
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
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            request.setAttribute("exception", ex);
            return "client/error_page";
        }
        return "client/cp_client_tickets";
    }
}
