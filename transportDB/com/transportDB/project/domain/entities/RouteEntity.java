package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the routeEntity database table.
 * 
 */
@Entity
@Table(name="routeEntity")
@NamedQuery(name="RouteEntity.findAll", query="SELECT r FROM RouteEntity r")
public class RouteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int routeEntity_id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="arrival_time")
	private Date arrivalTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="depature_time")
	private Date depatureTime;

	private int seqNumber;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name="station_id")
	private Station station;

	public RouteEntity() {
	}

	public int getRouteEntity_id() {
		return this.routeEntity_id;
	}

	public void setRouteEntity_id(int routeEntity_id) {
		this.routeEntity_id = routeEntity_id;
	}

	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getDepatureTime() {
		return this.depatureTime;
	}

	public void setDepatureTime(Date depatureTime) {
		this.depatureTime = depatureTime;
	}

	public int getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}