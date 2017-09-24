/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domains;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_AUTHORITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a")
    , @NamedQuery(name = "Authority.findById", query = "SELECT a FROM Authority a WHERE a.id = :id")
    , @NamedQuery(name = "Authority.findByName", query = "SELECT a FROM Authority a WHERE a.name = :name")})
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "T_AUTHORITY_SEQ", sequenceName = "T_AUTHORITY_SEQ", allocationSize = 1, initialValue = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
//    @ManyToMany(mappedBy = "authorityList")
//    private List<User> userList;

    public Authority() {
    }

    public Authority(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @XmlTransient
//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chatapp.domains.Authority[ id=" + id + " ]";
    }
    
}
