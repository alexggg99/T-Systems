package ru.tsystems.project.domain.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Passenger> passenger;
	public static volatile SingularAttribute<Ticket, Integer> ticketId;
	public static volatile SingularAttribute<Ticket, Station> stationTo;
	public static volatile SingularAttribute<Ticket, Route> route;
	public static volatile SingularAttribute<Ticket, Date> depatureTime;
	public static volatile SingularAttribute<Ticket, Train> train;
	public static volatile SingularAttribute<Ticket, Station> stationFrom;

}

