/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project.services.API;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import ru.tsystems.project.domain.entities.RouteEntity;
import ru.tsystems.project.domain.entities.Station;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.implementations.StationServiceImplementation;

/**
 *
 * @author alexggg99
 */
public class StationServiceTest {
    
    Station station = new Station();
    String name = "Test";

    public StationServiceTest() {
        station.setName(name);
    }
    
    /**
     * Test of addStation method, of class StationService.
     */
    @Test
    public void testAddStation() {
        StationService stationService = new StationServiceImplementation();
        Station expResult = station;
        Station result = stationService.addStation(name);
        Station st = stationService.getStation(name);
        stationService.removeStation(st);
        assertEquals(st, result);
    }

    /**
     * Test of getStation method, of class StationService.
     */
    @Test
    public void testGetStation() {
        StationService service = new StationServiceImplementation();
        service.addStation(name);
        Station result = service.getStation(name);
        service.removeStation(result);
        assertEquals(station.getName(), result.getName());
    }

    /**
     * Test of getTrains method, of class StationService.
     */
    @Test
    public void testGetTrains() {
        List<RouteEntity> list = new ArrayList<>();
        StationService service = Mockito.mock(StationService.class);
        Mockito.when(service.getTrains(name)).thenReturn(list);
        List<RouteEntity> result = service.getTrains(name);
        assertEquals(list, result);
    }
    
}
