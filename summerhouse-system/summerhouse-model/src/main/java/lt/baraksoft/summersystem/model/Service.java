/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.baraksoft.summersystem.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Žygimantas
 */
@Entity
@Table(name = "services")
@NamedQueries({ @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"), @NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id"),
		@NamedQuery(name = "Service.findByTitle", query = "SELECT s FROM Service s WHERE s.title = :title"), @NamedQuery(name = "Service.findByPrice", query = "SELECT s FROM Service s WHERE s.price = :price"),
		@NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description = :description"), @NamedQuery(name = "Service.findByIsArchived", query = "SELECT s FROM Service s WHERE s.archived = :isArchived") })
public class Service implements IEntity<Integer> {
	private static final long serialVersionUID = -3185403197832964682L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Size(max = 500)
	@Column(name = "title")
	private String title;
	@Basic(optional = false)
	@NotNull
	@Column(name = "price")
	private BigDecimal price;
	@Size(max = 2000)
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Column(name = "is_archived")
	private boolean archived;
	@JoinTable(name = "summerhouse_services", joinColumns = { @JoinColumn(name = "service_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "summerhouse_id", referencedColumnName = "id") })
	@ManyToMany
	private List<Summerhouse> summerhouseList;

	@Version
	private Integer version;

	public Service() {
	}

	public Service(Integer id) {
		this.id = id;
	}

	public Service(Integer id, BigDecimal price, boolean isArchived) {
		this.id = id;
		this.price = price;
		this.archived = isArchived;
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

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public List<Summerhouse> getSummerhouseList() {
		return summerhouseList;
	}

	public void setSummerhouseList(List<Summerhouse> summerhouseList) {
		this.summerhouseList = summerhouseList;
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
		if (!(object instanceof Service)) {
			return false;
		}
		Service other = (Service) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "lt.baraksoft.summersystem.model.Service[ id=" + id + " ]";
	}

}
