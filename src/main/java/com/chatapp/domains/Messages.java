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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_MESSAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m")
    , @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id")
    , @NamedQuery(name = "Messages.findByTime", query = "SELECT m FROM Messages m WHERE m.time = :time")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "T_MESSAGES_SEQ", sequenceName = "T_MESSAGES_SEQ", allocationSize = 1, initialValue = 1)
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Lob
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;
    @Size(max = 200)
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Size(max = 200)
    @Column(name = "VIDEO_URL")
    private String videoUrl;
    @JoinColumn(name = "MESSAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private MessageType messageType;
    @OneToMany(mappedBy = "messageId")
    private List<UnreadMessages> unreadMessagesList;
    @JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Conversations conversationId;
    @JoinColumn(name = "USER_SENDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User userSenderId;

    public Messages() {
    }

    public Messages(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @XmlTransient
    public List<UnreadMessages> getUnreadMessagesList() {
        return unreadMessagesList;
    }

    public void setUnreadMessagesList(List<UnreadMessages> unreadMessagesList) {
        this.unreadMessagesList = unreadMessagesList;
    }

    public Conversations getConversationId() {
        return conversationId;
    }

    public void setConversationId(Conversations conversationId) {
        this.conversationId = conversationId;
    }

    public User getUserSenderId() {
        return userSenderId;
    }

    public void setUserSenderId(User userSenderId) {
        this.userSenderId = userSenderId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
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
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chatapp.domains.Messages[ id=" + id + " ]";
    }
    
}
