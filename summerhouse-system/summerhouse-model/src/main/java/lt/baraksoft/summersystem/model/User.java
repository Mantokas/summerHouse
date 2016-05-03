/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.baraksoft.summersystem.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 *
 * @author Žygimantas
 */
@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"), @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"), @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"), @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
		@NamedQuery(name = "User.findByIsApproved", query = "SELECT u FROM User u WHERE u.approved = :isApproved"), @NamedQuery(name = "User.findByPoints", query = "SELECT u FROM User u WHERE u.points = :points"),
		@NamedQuery(name = "User.findByGroupNumber", query = "SELECT u FROM User u WHERE u.groupNumber = :groupNumber"), @NamedQuery(name = "User.findByIsArchived", query = "SELECT u FROM User u WHERE u.archived = :isArchived") })
public class User implements IEntity<Integer> {
	private static final long serialVersionUID = -887896587150521740L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	@Size(min = 3, max = 25)
	@Column(name = "firstname")
	private String firstname;
	@Size(min = 4, max = 25)
	@Column(name = "lastname")
	private String lastname;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Invalid email")//if the field contains email address consider
	// using this annotation to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@NotNull
	@Size(min = 5, max = 128)
	@Column(name = "password")
	private String password;
	@Basic(optional = false)
	@NotNull
	@Column(name = "is_approved")
	private boolean approved;
	@Basic(optional = false)
	@NotNull
	@Column(name = "points", columnDefinition = "int default 0")
	private int points;
	@Column(name = "group_number", columnDefinition = "int default 0")
	private int groupNumber;
	// Same here
	@Basic(optional = false)
	@NotNull
	@Column(name = "is_archived")
	private boolean archived;
    @Column(name = "valid_to", nullable = true)
    private LocalDate validTo;

	@JoinTable(name = "user_payments", joinColumns = { @JoinColumn(name = "user_id",
			referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "payment_id",
			referencedColumnName = "id") })
	@ManyToMany
	private List<Payment> paymentList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Reservation> reservationList;

	@Version
	private Integer version;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String email, String password, boolean approved, int points, boolean archived) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.approved = approved;
		this.points = points;
		this.archived = archived;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "lt.baraksoft.summersystem.model.User[ id=" + id + " ]";
	}

}
