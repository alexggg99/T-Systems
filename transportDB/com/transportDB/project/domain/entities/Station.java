package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stations database table.
 * 
 */
@Entity
@Table(name="stations")
@NamedQuery(name="Station.findAll", query="SELECT s FROM Station s")
public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="station_id")
	private int stationId;

	private String name;

	//bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy="station")
	private List<RouteEntity> routeEntities;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="station1")
	private List<Ticket> tickets1;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="station2")
	private List<Ticket> tickets2;

	public Station() {
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RouteEntity> getRouteEntities() {
		return this.routeEntities;
	}

	public void setRouteEntities(List<RouteEntity> routeEntities) {
		this.routeEntities = routeEntities;
	}

	public RouteEntity addRouteEntity(RouteEntity routeEntity) {
		getRouteEntities().add(routeEntity);
		routeEntity.setStation(this);

		return routeEntity;
	}

	public RouteEntity removeRouteEntity(RouteEntity routeEntity) {
		getRouteEntities().remove(routeEntity);
		routeEntity.setStation(null);

		return routeEntity;
	}

	public List<Ticket> getTickets1() {
		return this.tickets1;
	}

	public void setTickets1(List<Ticket> tickets1) {
		this.tickets1 = tickets1;
	}

	public Ticket addTickets1(Ticket tickets1) {
		getTickets1().add(tickets1);
		tickets1.setStation1(this);

		return tickets1;
	}

	public Ticket removeTickets1(Ticket tickets1) {
		getTickets1().remove(tickets1);
		tickets1.setStation1(null);

		return tickets1;
	}

	public List<Ticket> getTickets2() {
		return this.tickets2;
	}

	public void setTickets2(List<Ticket> tickets2) {
		this.tickets2 = tickets2;
	}

	public Ticket addTickets2(Ticket tickets2) {
		getTickets2().add(tickets2);
		tickets2.setStation2(this);

		return tickets2;
	}

	public Ticket removeTickets2(Ticket tickets2) {
		getTickets2().remove(tickets2);
		tickets2.setStation2(null);

		return tickets2;
	}

}