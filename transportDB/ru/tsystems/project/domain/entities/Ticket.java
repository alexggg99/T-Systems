package ru.tsystems.project.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name = "tickets")
@NamedQuery(name = Ticket.GETALL, query = "SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
        
        public static final String GETALL = "Tickets.findAll";

	@Id
	@Column(name = "ticket_id")
	@TableGenerator(name = "TABLE_GEN_TK", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TK_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_TK")
	private int ticketId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "depature_time")
	private Date depatureTime;

	// bi-directional many-to-one association to Passenger
	@ManyToOne
	@JoinColumn(name = "passenger_id")
	private Passenger passenger;

	// bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	// bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name = "station_from")
	private Station stationFrom;

	// bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name = "station_to")
	private Station stationTo;

	// bi-directional many-to-one association to Train
	@ManyToOne
	@JoinColumn(name = "train_id")
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

	public Station getStationFrom() {
		return this.stationFrom;
	}

	public void setStationFrom(Station station) {
		this.stationFrom = station;
	}

	public Station getStationTo() {
		return this.stationTo;
	}

	public void setStationTo(Station station) {
		this.stationTo = station;
	}

	public Train getTrain() {
		return this.train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

}