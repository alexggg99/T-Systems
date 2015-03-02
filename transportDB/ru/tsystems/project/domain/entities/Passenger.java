package ru.tsystems.project.domain.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the passengers database table.
 * 
 */
@Entity
@Table(name = "passengers")
@NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p")
public class Passenger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "passenger_id")
	@TableGenerator(name = "TABLE_GEN_PS", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PS_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_PS")
	private int passengerId;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String firstName;

	private String lastName;

	private String middleName;

	private String password;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "passenger")
	private List<Ticket> tickets;

	public Passenger() {
	}

	public int getPassengerId() {
		return this.passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setPassenger(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setPassenger(null);

		return ticket;
	}

}