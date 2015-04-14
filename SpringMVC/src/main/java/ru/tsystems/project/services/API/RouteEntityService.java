package ru.tsystems.project.services.API;

import java.util.List;
import ru.tsystems.project.domain.entities.Route;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface RouteEntityService {

    /**
     * A method to get all routes entities in the database,
     *
     * @return
     * @throws CustomDAOException
     */
    public List<RouteEntity> getAllRoutes() throws CustomDAOException;

    /**
     * A method to get routes entities in the database,
     *
     * @param cityIn    city  to departure
     * @param cityOut    city to arrive
     * @param date    departure date
     * @return
     * @throws CustomDAOException
     */
    public List<RouteEntity> getRoutesEnteties(String cityIn, String cityOut,
            String date) throws CustomDAOException;

    /**
     * A method to add route entities to the database,
     *
     * @param routeId    route id
     * @param stationId    station id
     * @param arrivalDate    arrival time and date
     * @param departureDate    departure time and date
     * @param sequence    sequence of the point in the route
     * @return
     * @throws Exception
     */
    public RouteEntity addRouteEntity(int routeId, int stationId, String arrivalDate,
            String departureDate, int sequence) throws Exception;

}
