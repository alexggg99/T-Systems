package ru.tsystems.project.services.API;

import java.util.List;

import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface PassengerService {

    /**
     * A method to get Passenger by name.
     *
     * @param login    login to app
     * @return
     * @throws CustomDAOException
     */
    public Passenger getPassangerByLastName(String login) throws CustomDAOException;

    /**
     * A method to get passengers on train, input parameters(trainId)
     *
     * @param trainId  train id where you want to see all passengers
     * @return
     * @throws CustomDAOException
     */
    public List<Passenger> getAllPassangersOnTrain(int trainId) throws CustomDAOException;

    
    /**
     * A method to create passenger in DB, input parameters(trainId)
     *
     * @param firstName   passenger first name
     * @param lastName  passenger last name
     * @param birthday  passenger birthday
     * @return
     * @throws CustomDAOException
     */
    public Passenger createPasseneger(String firstName, String lastName, String birthday)
            throws CustomDAOException;

//    public Passenger deletePasseneger(Passenger passenger)
//            throws CustomDAOException;

}
