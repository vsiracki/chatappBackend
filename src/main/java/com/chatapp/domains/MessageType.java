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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_MESSAGE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageType.findAll", query = "SELECT m FROM MessageType m")
    , @NamedQuery(name = "MessageType.findById", query = "SELECT m FROM MessageType m WHERE m.id = :id")
    , @NamedQuery(name = "MessageType.findByName", query = "SELECT m FROM MessageType m WHERE m.name = :name")})
public class MessageType implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "T_MESSAGE_TYPE_SEQ", sequenceName = "T_MESSAGE_TYPE_SEQ", allocationSize = 1, initialValue = 1)
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;

    public MessageType() {
    }

    public MessageType(Long id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageType)) {
            return false;
        }
        MessageType other = (MessageType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chatapp.domains.MessageType[ id=" + id + " ]";
    }
    
}
