package ru.tsystems.transportapp;

import ru.tsystems.project.domain.DAO.implementation.PassengerDAOImpl;
import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.entities.Passenger;

public class Main {

	public static void main(String[] args) {

		PassengerDAO pasDAO = new PassengerDAOImpl();
		Passenger pas;
		// train.setTrainId(17);
		// /train.setName("TEST5555");
		// trainDAO.save(train);

		pas = pasDAO.getPassengerByPassword("Vagin");
		System.out.println(pas.getLastName());

		for (Passenger p : pasDAO.findAll()) {
			System.out.println(p.getLastName());
		}

		System.out.println("TEST!");
	}
}
