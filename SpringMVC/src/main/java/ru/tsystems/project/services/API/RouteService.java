package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface RouteService {

    /**
     * A method to get all routes in the database,
     *
     * @return
     * @throws CustomDAOException
     */
    public List<Route> getAllRoutes() throws CustomDAOException;

    /**
     * A method to get route from the database,
     *
     * @param routeId    route id
     * @return
     * @throws CustomDAOException
     */
    public Route getById(int routeId) throws CustomDAOException;
}
