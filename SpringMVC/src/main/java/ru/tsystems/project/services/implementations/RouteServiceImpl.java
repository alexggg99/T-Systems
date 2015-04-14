package ru.tsystems.project.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.implementation.RouteDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.RouteDAO;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.RouteService;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDAO routeDAO;

    @Override
    public List<Route> getAllRoutes() throws CustomDAOException {
        List<Route> result = new ArrayList<Route>();
        result = routeDAO.findAll();
        return result;
    }

    public Route getById(int routeId) throws CustomDAOException {
        return routeDAO.findById(routeId);
    }


}
