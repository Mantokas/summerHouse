/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author manta
 */
@Entity
@Table(name = "RESERVATIONS")
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findById", query = "SELECT r FROM Reservations r WHERE r.id = :id"),
    @NamedQuery(name = "Reservations.findByDateFrom", query = "SELECT r FROM Reservations r WHERE r.dateFrom = :dateFrom"),
    @NamedQuery(name = "Reservations.findByDateTo", query = "SELECT r FROM Reservations r WHERE r.dateTo = :dateTo"),
    @NamedQuery(name = "Reservations.findByIsApproved", query = "SELECT r FROM Reservations r WHERE r.isApproved = :isApproved"),
    @NamedQuery(name = "Reservations.findByIsArchived", query = "SELECT r FROM Reservations r WHERE r.isArchived = :isArchived")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
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
    @Column(name = "IS_APPROVED")
    private int isApproved;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ARCHIVED")
    private int isArchived;
    @JoinColumn(name = "SUMMERHOUSE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Summerhouses summerhouseId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Reservations() {
    }

    public Reservations(Integer id) {
        this.id = id;
    }

    public Reservations(Integer id, Date dateFrom, Date dateTo, int isApproved, int isArchived) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isApproved = isApproved;
        this.isArchived = isArchived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public int getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(int isArchived) {
        this.isArchived = isArchived;
    }

    public Summerhouses getSummerhouseId() {
        return summerhouseId;
    }

    public void setSummerhouseId(Summerhouses summerhouseId) {
        this.summerhouseId = summerhouseId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.Reservations[ id=" + id + " ]";
    }
    
}
