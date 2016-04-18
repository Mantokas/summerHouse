/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.baraksoft.summersystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Žygimantas
 */
@Entity
@Table(name = "summerhouses")
@NamedQueries({ @NamedQuery(name = "Summerhouse.findAll", query = "SELECT s FROM Summerhouse s"), @NamedQuery(name = "Summerhouse.findById", query = "SELECT s FROM Summerhouse s WHERE s.id = :id"),
		@NamedQuery(name = "Summerhouse.findByTitle", query = "SELECT s FROM Summerhouse s WHERE s.title = :title"), @NamedQuery(name = "Summerhouse.findByAddress", query = "SELECT s FROM Summerhouse s WHERE s.address = :address"),
		@NamedQuery(name = "Summerhouse.findByPrice", query = "SELECT s FROM Summerhouse s WHERE s.price = :price"), @NamedQuery(name = "Summerhouse.findByDescription", query = "SELECT s FROM Summerhouse s WHERE s.description = :description"),
		@NamedQuery(name = "Summerhouse.findByCapacity", query = "SELECT s FROM Summerhouse s WHERE s.capacity = :capacity"), @NamedQuery(name = "Summerhouse.findByDateFrom", query = "SELECT s FROM Summerhouse s WHERE s.dateFrom = :dateFrom"),
		@NamedQuery(name = "Summerhouse.findByDateTo", query = "SELECT s FROM Summerhouse s WHERE s.dateTo = :dateTo"), @NamedQuery(name = "Summerhouse.findByIsArchived", query = "SELECT s FROM Summerhouse s WHERE s.isArchived = :isArchived") })
public class Summerhouse implements IEntity<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Size(min = 5, max = 500)
	@Column(name = "title")
	private String title;
	@Size(min = 5, max = 500)
	@Column(name = "address", unique = true)
	private String address;
	@Digits(integer = 4, fraction = 2)
	@Column(name = "price", precision = 4, scale = 2)
	private BigDecimal price;
	@Size(max = 2000)
	@Column(name = "description")
	private String description;
	@Size(min = 2, max = 500)
	@Column(name = "capacity")
	private Integer capacity;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_from")
	private LocalDateTime dateFrom;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_to")
	private LocalDateTime dateTo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "is_archived")
	private boolean isArchived;
	@ManyToMany(mappedBy = "summerhouseList")
	private List<Service> serviceList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "summerhouseId")
	private List<Reservation> reservationList;

	@Version
	private Integer version;

	public Summerhouse() {
	}

	public Summerhouse(Integer id) {
		this.id = id;
	}

	public Summerhouse(Integer id, LocalDateTime dateFrom, LocalDateTime dateTo, boolean isArchived) {
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.isArchived = isArchived;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
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
		if (!(object instanceof Summerhouse)) {
			return false;
		}
		Summerhouse other = (Summerhouse) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "lt.baraksoft.summersystem.model.Summerhouse[ id=" + id + " ]";
	}

}
