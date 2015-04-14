/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project.services.API;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import ru.tsystems.project.domain.entities.Train;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.services.API.TrainService;

/**
 *
 * @author alexggg99
 */
public class TrainServiceTest {

    Train train = new Train();
    String name = "Test";
    int seats = 1;

    public TrainServiceTest() {
        train.setName(name);
        train.setSeats(seats);
    }
    
    /**
     * Test of addTrain method, of class TrainServiceImpl.
     */
    @Test
    public void testAddTrain() {
        TrainService trainService = Mockito.mock(TrainService.class);
        Mockito.when(trainService.addTrain(name, seats)).thenReturn(train);
        Train expResult = train;
        Train result = trainService.addTrain(name, seats);
        assertEquals(train, result);
    }

}
