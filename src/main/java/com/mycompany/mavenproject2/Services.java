/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manta
 */
@Entity
@Table(name = "SERVICES")
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = :id"),
    @NamedQuery(name = "Services.findByTitle", query = "SELECT s FROM Services s WHERE s.title = :title"),
    @NamedQuery(name = "Services.findByPrice", query = "SELECT s FROM Services s WHERE s.price = :price"),
    @NamedQuery(name = "Services.findByDescription", query = "SELECT s FROM Services s WHERE s.description = :description"),
    @NamedQuery(name = "Services.findByIsArchived", query = "SELECT s FROM Services s WHERE s.isArchived = :isArchived")})
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 500)
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PRICE")
    private Integer price;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ARCHIVED")
    private int isArchived;
    @JoinTable(name = "SUMMERHOUSE_SERVICES", joinColumns = {
        @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SUMMERHOUSE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Summerhouses> summerhousesList;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Services(Integer id, int isArchived) {
        this.id = id;
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

    public int getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(int isArchived) {
        this.isArchived = isArchived;
    }

    public List<Summerhouses> getSummerhousesList() {
        return summerhousesList;
    }

    public void setSummerhousesList(List<Summerhouses> summerhousesList) {
        this.summerhousesList = summerhousesList;
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
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.Services[ id=" + id + " ]";
    }
    
}
