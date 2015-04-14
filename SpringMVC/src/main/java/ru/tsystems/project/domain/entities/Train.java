package ru.tsystems.project.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the trains database table.
 * 
 */
@Entity
@Table(name = "trains")
@NamedQueries({
    @NamedQuery(name = Train.FINDALLTRAINS, query = "SELECT t FROM Train t"),
    @NamedQuery(name = Train.FINDTRAIN, query = "SELECT t FROM Train t where t.trainId = :trainId"),
})
public class Train implements Serializable {
	private static final long serialVersionUID = 1L;
        
        public static final String FINDALLTRAINS = "Train.findAll";
        public static final String FINDTRAIN = "Train.find";

	@Id
	@Column(name = "train_id")
	@TableGenerator(name = "TABLE_GEN_TR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TR_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_TR")
	private int trainId;

	private String name;

	private int seats;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "train")
	private List<Ticket> tickets;

        // bi-directional many-to-one association to Route
	@OneToMany(mappedBy = "train")
	private List<Route> routes;

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

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Route addRoute(Route route) {
		getRoutes().add(route);
		route.setTrain(this);
		return route;
	}

	public Route removeRoute(Route route) {
		getRoutes().remove(route);
		route.setTrain(null);

		return route;
	}

	@Override
	public String toString() {
		return "Train [name=" + name + "]";
	}

}