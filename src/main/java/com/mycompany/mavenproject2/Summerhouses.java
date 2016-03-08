/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manta
 */
@Entity
@Table(name = "SUMMERHOUSES")
@NamedQueries({
    @NamedQuery(name = "Summerhouses.findAll", query = "SELECT s FROM Summerhouses s"),
    @NamedQuery(name = "Summerhouses.findById", query = "SELECT s FROM Summerhouses s WHERE s.id = :id"),
    @NamedQuery(name = "Summerhouses.findByTitle", query = "SELECT s FROM Summerhouses s WHERE s.title = :title"),
    @NamedQuery(name = "Summerhouses.findByAddress", query = "SELECT s FROM Summerhouses s WHERE s.address = :address"),
    @NamedQuery(name = "Summerhouses.findByPrice", query = "SELECT s FROM Summerhouses s WHERE s.price = :price"),
    @NamedQuery(name = "Summerhouses.findByDescription", query = "SELECT s FROM Summerhouses s WHERE s.description = :description"),
    @NamedQuery(name = "Summerhouses.findByCapacity", query = "SELECT s FROM Summerhouses s WHERE s.capacity = :capacity"),
    @NamedQuery(name = "Summerhouses.findByDateFrom", query = "SELECT s FROM Summerhouses s WHERE s.dateFrom = :dateFrom"),
    @NamedQuery(name = "Summerhouses.findByDateTo", query = "SELECT s FROM Summerhouses s WHERE s.dateTo = :dateTo"),
    @NamedQuery(name = "Summerhouses.findByIsArchived", query = "SELECT s FROM Summerhouses s WHERE s.isArchived = :isArchived")})
public class Summerhouses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 500)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 500)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PRICE")
    private Integer price;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ARCHIVED")
    private int isArchived;
    @ManyToMany(mappedBy = "summerhousesList")
    private List<Services> servicesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "summerhouseId")
    private List<Reservations> reservationsList;

    public Summerhouses() {
    }

    public Summerhouses(Integer id) {
        this.id = id;
    }

    public Summerhouses(Integer id, Date dateFrom, Date dateTo, int isArchived) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(int isArchived) {
        this.isArchived = isArchived;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Summerhouses)) {
            return false;
        }
        Summerhouses other = (Summerhouses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.Summerhouses[ id=" + id + " ]";
    }
    
}
