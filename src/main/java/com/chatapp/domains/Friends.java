/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domains;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_FRIENDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f")
    , @NamedQuery(name = "Friends.findById", query = "SELECT f FROM Friends f WHERE f.id = :id")})
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "T_FRIENDS_SEQ", sequenceName = "T_FRIENDS_SEQ", allocationSize = 1, initialValue = 1)
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "FRIEND_ID", referencedColumnName = "ID")
    @ManyToOne
    private User friendId;

    public Friends() {
    }

    public Friends(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
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
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chatapp.domains.Friends[ id=" + id + " ]";
    }
    
}
