package com.transportDB.project.domain.DAO.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

	public static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("transportDB");
	public static final EntityManager manager = factory.createEntityManager();

}
