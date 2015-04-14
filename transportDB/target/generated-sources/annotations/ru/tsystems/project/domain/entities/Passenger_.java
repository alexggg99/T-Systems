package ru.tsystems.project.domain.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passenger.class)
public abstract class Passenger_ {

	public static volatile SingularAttribute<Passenger, String> middleName;
	public static volatile SingularAttribute<Passenger, String> lastName;
	public static volatile SingularAttribute<Passenger, Date> birthday;
	public static volatile SingularAttribute<Passenger, Integer> passengerId;
	public static volatile SingularAttribute<Passenger, Role> role;
	public static volatile SingularAttribute<Passenger, String> firstName;
	public static volatile SingularAttribute<Passenger, String> password;
	public static volatile ListAttribute<Passenger, Ticket> tickets;

}

