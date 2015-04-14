/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project.services.API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.implementations.PassengerServiceImplementation;

/**
 *
 * @author alexggg99
 */
public class PassengerServiceTest {

    String firstName = "TestName";
    String lastName = "TestLastName";
    String birthday = "2001-01-01";

    public PassengerServiceTest() {
    }


    /**
     * Test of getPassangerByLastName method, of class PassengerService.
     */
    @Test
    public void testGetPassangerByLastName() {
        PassengerService service = new PassengerServiceImplementation();
        Passenger expResult = null;
        service.createPasseneger(firstName, lastName, "2001-01-01");
        Passenger result = service.getPassangerByLastName(lastName);
        service.deletePasseneger(result);
        assertEquals(lastName, result.getLastName());
    }


    /**
     * Test of createPasseneger method, of class PassengerService.
     */
    @Test
    public void testCreatePasseneger() {
        PassengerService service = new PassengerServiceImplementation();
        Passenger expResult = null;
        service.createPasseneger(firstName, lastName, birthday);
        Passenger pas = service.getPassangerByLastName(lastName);
        service.deletePasseneger(pas);
        assertEquals(firstName, pas.getFirstName());
        assertEquals(lastName, pas.getLastName());
    }
}
