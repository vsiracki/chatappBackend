/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_CONVERSATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversations.findAll", query = "SELECT c FROM Conversations c")
    , @NamedQuery(name = "Conversations.findById", query = "SELECT c FROM Conversations c WHERE c.id = :id")
    , @NamedQuery(name = "Conversations.findByLastMessageTime", query = "SELECT c FROM Conversations c WHERE c.lastMessageTime = :lastMessageTime")})
public class Conversations implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "T_CONVERSATIONS_SEQ", sequenceName = "T_CONVERSATIONS_SEQ", allocationSize = 1, initialValue = 1)
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "LAST_MESSAGE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastMessageTime;
    @Lob
    @Column(name = "LAST_MESSAGE_SENT")
    private String lastMessageSent;
    @OneToMany(mappedBy = "conversationId" ,fetch = FetchType.EAGER)
    private List<ConversationsRecepients> conversationsRecepientsList;
    @JoinColumn(name = "CONVERSATION_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private ConversationTypes conversationTypeId;
    @OneToMany(mappedBy = "conversationId")
    private List<Messages> messagesList;

    public Conversations() {
    }

    public Conversations(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    @XmlTransient
    public List<ConversationsRecepients> getConversationsRecepientsList() {
        return conversationsRecepientsList;
    }

    public void setConversationsRecepientsList(List<ConversationsRecepients> conversationsRecepientsList) {
        this.conversationsRecepientsList = conversationsRecepientsList;
    }

    public ConversationTypes getConversationTypeId() {
        return conversationTypeId;
    }

    public void setConversationTypeId(ConversationTypes conversationTypeId) {
        this.conversationTypeId = conversationTypeId;
    }

    @XmlTransient
    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public void setLastMessageSent(String lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
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
        if (!(object instanceof Conversations)) {
            return false;
        }
        Conversations other = (Conversations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chatapp.domains.Conversations[ id=" + id + " ]";
    }
    
}
