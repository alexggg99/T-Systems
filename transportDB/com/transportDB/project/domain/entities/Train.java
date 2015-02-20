package com.transportDB.project.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trains database table.
 * 
 */
@Entity
@Table(name="trains")
@NamedQuery(name="Train.findAll", query="SELECT t FROM Train t")
public class Train implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="train_id")
	private int trainId;

	private String name;

	private int seats;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="train")
	private List<Ticket> tickets;

	public Train() {
	}

	public int getTrainId() {
		return this.trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return this.seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setTrain(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setTrain(null);

		return ticket;
	}

}