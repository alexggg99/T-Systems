package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="roles_id")
	private int rolesId;

	private String name;

	//bi-directional many-to-one association to Passenger
	@OneToMany(mappedBy="role")
	private List<Passenger> passengers;

	public Role() {
	}

	public int getRolesId() {
		return this.rolesId;
	}

	public void setRolesId(int rolesId) {
		this.rolesId = rolesId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Passenger> getPassengers() {
		return this.passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Passenger addPassenger(Passenger passenger) {
		getPassengers().add(passenger);
		passenger.setRole(this);

		return passenger;
	}

	public Passenger removePassenger(Passenger passenger) {
		getPassengers().remove(passenger);
		passenger.setRole(null);

		return passenger;
	}

}