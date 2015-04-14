package ru.tsystems.project.domain.DAO.interfaces;

import java.util.List;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;

public interface StationDAO extends GenericDAO<Station> {
    
    public List<RouteEntity> getTrains(String stationName);

}
