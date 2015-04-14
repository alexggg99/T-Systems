package ru.tsystems.project.services.implementations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.implementation.RouteEntityDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.RouteEntityDAO;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteEntityService;
import ru.tsystems.project.services.API.RouteService;
import ru.tsystems.project.services.API.StationService;
import ru.tsystems.project.services.API.TicketService;

@Transactional
public class RouteEntityServiceImpl implements RouteEntityService {


    @Autowired private RouteEntityDAO routeEntityDAO;
    @Autowired private StationService stationService;
    @Autowired private RouteService routeService;

    @Override
    public List<RouteEntity> getAllRoutes() throws CustomDAOException {
        List<RouteEntity> list;
        list = routeEntityDAO.findAll();
        return list;
    }

    @Override
    public List<RouteEntity> getRoutesEnteties(String cityFrom, String cityTo, String date) throws CustomDAOException {
        List<RouteEntity> result = null;
        result = routeEntityDAO.getRoutesEnteties(cityFrom, cityTo, date);
        return result;
    }

    @Override
    public RouteEntity addRouteEntity(int routeId, int stationId, String arrivalDate, String departureDate, int sequence) throws Exception {
        RouteEntity result = null;
        Route route;
        Station station;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date arrdate = format.parse(arrivalDate);
        Date depdate = format.parse(departureDate);
        RouteEntity entity = new RouteEntity();
        route = routeService.getById(routeId);
        station = stationService.getById(stationId);
        entity.setRoute(route);
        entity.setStation(station);
        entity.setArrivalTime(arrdate);
        entity.setDepatureTime(depdate);
        entity.setSeqNumber(sequence);
        result = routeEntityDAO.save(entity);
        return result;
    }

}
