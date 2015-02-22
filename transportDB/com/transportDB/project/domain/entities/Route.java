package com.transportDB.project.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name = "routes")
@NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "route_id")
	@TableGenerator(name = "TABLE_GEN_RT", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "RT_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_RT")
	private int routeId;

	private String name;

	// bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy = "route")
	private List<RouteEntity> routeEntities;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "route")
	private List<Ticket> tickets;

	// bi-directional many-to-one association to Train
	@ManyToOne
	@JoinColumn(name = "train_train_id")
	private Train train;

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

	public Train getTrain() {
		return this.train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

}