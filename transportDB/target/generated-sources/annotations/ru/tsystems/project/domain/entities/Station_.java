package ru.tsystems.project.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Station.class)
public abstract class Station_ {

	public static volatile ListAttribute<Station, RouteEntity> routeEntities;
	public static volatile ListAttribute<Station, Ticket> ticketsTo;
	public static volatile SingularAttribute<Station, String> name;
	public static volatile SingularAttribute<Station, Integer> stationId;
	public static volatile ListAttribute<Station, Ticket> ticketsFrom;

}

