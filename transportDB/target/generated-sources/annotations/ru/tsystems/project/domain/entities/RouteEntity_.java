package ru.tsystems.project.domain.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RouteEntity.class)
public abstract class RouteEntity_ {

	public static volatile SingularAttribute<RouteEntity, Date> arrivalTime;
	public static volatile SingularAttribute<RouteEntity, Station> station;
	public static volatile SingularAttribute<RouteEntity, Integer> routeEntity_id;
	public static volatile SingularAttribute<RouteEntity, Route> route;
	public static volatile SingularAttribute<RouteEntity, Date> depatureTime;
	public static volatile SingularAttribute<RouteEntity, Integer> seqNumber;

}

