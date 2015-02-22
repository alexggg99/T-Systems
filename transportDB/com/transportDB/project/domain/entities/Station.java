package com.transportDB.project.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the stations database table.
 * 
 */
@Entity
@Table(name = "stations")
@NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s")
public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "station_id")
	@TableGenerator(name = "TABLE_GEN_ST", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ST_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_ST")
	private int stationId;

	private String name;

	// bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy = "station")
	private List<RouteEntity> routeEntities;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "stationFrom")
	private List<Ticket> ticketsFrom;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "stationTo")
	private List<Ticket> ticketsTo;

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

	public List<Ticket> getTicketsFrom() {
		return this.ticketsFrom;
	}

	public void setTicketsFrom(List<Ticket> tickets) {
		this.ticketsFrom = tickets;
	}

	public Ticket addTicketFrom(Ticket ticket) {
		getTicketsFrom().add(ticket);
		ticket.setStationFrom(this);

		return ticket;
	}

	public Ticket removeTicketFrom(Ticket ticket) {
		getTicketsFrom().remove(ticket);
		ticket.setStationFrom(null);

		return ticket;
	}

	public List<Ticket> getTicketsTo() {
		return this.ticketsTo;
	}

	public void setTicketsTo(List<Ticket> tickets) {
		this.ticketsTo = tickets;
	}

	public Ticket addTicketTo(Ticket ticket) {
		getTicketsTo().add(ticket);
		ticket.setStationTo(this);

		return ticket;
	}

	public Ticket removeTicketTo(Ticket ticket) {
		getTicketsTo().remove(ticket);
		ticket.setStationTo(null);

		return ticket;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + "]";
	}

}