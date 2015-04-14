package ru.tsystems.project.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
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
@NamedQueries({
    @NamedQuery(name = Passenger.GETALL, query = "SELECT p FROM Passenger p"),
    @NamedQuery(name = Passenger.GETPASSONTRAIN, query = "select tr.passenger from Ticket tr where tr.route = :route"),
    @NamedQuery(name = Passenger.FINDPASSONTRAIN, query = "select tk.passenger from Ticket tk join tk.passenger p "
            + " where p.firstName = :firstName and p.lastName = :lastName")
})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GETALL = "Passenger.findAll";
    public static final String GETPASSONTRAIN = "Passenger.getPassOnTain";
    public static final String FINDPASSONTRAIN = "Passenger.findPasonTrain";

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.birthday);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Passenger other = (Passenger) obj;
        if (this.birthday.equals(other.birthday) && this.firstName.equalsIgnoreCase(other.firstName)
                && this.lastName.equalsIgnoreCase(other.lastName)) {
            return true;
        }
        return false;
    }
    
    
}
