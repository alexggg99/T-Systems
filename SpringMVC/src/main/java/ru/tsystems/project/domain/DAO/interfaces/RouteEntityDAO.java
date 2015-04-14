package ru.tsystems.project.domain.DAO.interfaces;

import java.util.Date;
import java.util.List;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.exceptions.CustomDAOException;

public interface RouteEntityDAO extends GenericDAO<RouteEntity> {
        List<RouteEntity> getRoutesEnteties(String cityFrom, String cityTo, String date) throws CustomDAOException;
}
