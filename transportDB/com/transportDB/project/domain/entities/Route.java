package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name="routes")
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="route_id")
	private int routeId;

	private String name;

	//bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy="route")
	private List<RouteEntity> routeEntities;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="route")
	private List<Ticket> tickets;

	public Route() {
	}

	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
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
		routeEntity.setRoute(this);

		return routeEntity;
	}

	public RouteEntity removeRouteEntity(RouteEntity routeEntity) {
		getRouteEntities().remove(routeEntity);
		routeEntity.setRoute(null);

		return routeEntity;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setRoute(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setRoute(null);

		return ticket;
	}

}