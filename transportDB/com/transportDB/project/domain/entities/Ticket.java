package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticket_id")
	private int ticketId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="depature_time")
	private Date depatureTime;

	//bi-directional many-to-one association to Passenger
	@ManyToOne
	@JoinColumn(name="passenger_id")
	private Passenger passenger;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name="station_from")
	private Station station1;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name="station_to")
	private Station station2;

	//bi-directional many-to-one association to Train
	@ManyToOne
	@JoinColumn(name="train_id")
	private Train train;

	public Ticket() {
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getDepatureTime() {
		return this.depatureTime;
	}

	public void setDepatureTime(Date depatureTime) {
		this.depatureTime = depatureTime;
	}

	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Station getStation1() {
		return this.station1;
	}

	public void setStation1(Station station1) {
		this.station1 = station1;
	}

	public Station getStation2() {
		return this.station2;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

	public Train getTrain() {
		return this.train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

}