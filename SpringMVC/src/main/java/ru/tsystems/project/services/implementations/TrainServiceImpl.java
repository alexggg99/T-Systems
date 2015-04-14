package ru.tsystems.project.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;
import ru.tsystems.project.services.API.TrainService;

@Transactional
public class TrainServiceImpl implements TrainService {
        
        @Autowired
	private TrainDAO trainDAO;

	@Override
	public Train addTrain(String name, int seats) throws CustomDAOException {
		Train train = new Train();
		train.setName(name);
		train.setSeats(seats);
		Train result = trainDAO.save(train);
		return train;
	}

	@Override
	public List<Train> getAllTrains() throws CustomDAOException {
		try {
			List<Train> list = trainDAO.findAll();
			return list;
		} catch (RuntimeException ex) {
			throw new CustomDAOException("Unable to find trains!", ex);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Train removeTrain(Train train) throws CustomDAOException {
                Train result = trainDAO.delete(train);
		return train;
	}

}
