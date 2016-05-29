/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.baraksoft.summersystem.model;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Žygimantas
 */
@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
		@NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
		@NamedQuery(name = "User.findByIsApproved", query = "SELECT u FROM User u WHERE u.approved = :isApproved"),
		@NamedQuery(name = "User.findByPoints", query = "SELECT u FROM User u WHERE u.points = :points"),
		@NamedQuery(name = "User.findByGroupNumber", query = "SELECT u FROM User u WHERE u.groupNumber = :groupNumber"),
		@NamedQuery(name = "User.findByIsArchived", query = "SELECT u FROM User u WHERE u.archived = :isArchived") })
public class User implements IEntity<Integer> {
	private static final long serialVersionUID = -887896587150521740L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Size(max = 25)
	@Column(name = "firstname")
	private String firstname;
	@Size(max = 25)
	@Column(name = "lastname")
	private String lastname;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "email")
	private String email;
	@Column(name = "facebook_id")
	private String facebookId;
	@Column(name = "birth_date")
	private LocalDate birthdate;
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
	@Column(name = "valid_to")
	private LocalDate validTo;
	@Column(name = "skype_name")
	private String skypeName;
	@Column(name = "description")
	private String description;
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Payment> paymentList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Reservation> reservationList;


	@Version
	private Integer version;

	@Lob
	@Column(length = 10000000)
	private byte[] image;

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

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
