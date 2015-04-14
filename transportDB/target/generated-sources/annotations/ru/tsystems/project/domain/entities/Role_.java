package ru.tsystems.project.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile ListAttribute<Role, Passenger> passengers;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, Integer> rolesId;

}

