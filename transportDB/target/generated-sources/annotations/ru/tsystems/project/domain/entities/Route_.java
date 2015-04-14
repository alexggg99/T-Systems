package ru.tsystems.project.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Route.class)
public abstract class Route_ {

	public static volatile ListAttribute<Route, RouteEntity> routeEntities;
	public static volatile SingularAttribute<Route, String> name;
	public static volatile SingularAttribute<Route, Train> train;
	public static volatile SingularAttribute<Route, Integer> routeId;
	public static volatile ListAttribute<Route, Ticket> tickets;

}

