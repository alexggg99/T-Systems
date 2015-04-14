package ru.tsystems.project.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Train.class)
public abstract class Train_ {

	public static volatile ListAttribute<Train, Route> routes;
	public static volatile SingularAttribute<Train, String> name;
	public static volatile SingularAttribute<Train, Integer> seats;
	public static volatile SingularAttribute<Train, Integer> trainId;
	public static volatile ListAttribute<Train, Ticket> tickets;

}

