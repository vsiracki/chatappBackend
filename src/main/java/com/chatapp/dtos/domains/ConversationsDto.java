/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yousef
 */
public class ConversationsDto {
    private Long id;

    private Date lastMessageTime;
    
    private String lastMessageSent;

    private List<ConversationsRecepientsDto> conversationsRecepientsList;

    private ConversationTypesDto conversationTypeId;
    
    @JsonIgnore
    private List<MessagesDto> messagesList;
    
    private Long unreadMessages = 0L;

    public ConversationsDto() {
    }

    public ConversationsDto(Long id) {
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

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public void setLastMessageSent(String lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }

    public List<ConversationsRecepientsDto> getConversationsRecepientsList() {
        return conversationsRecepientsList;
    }

    public void setConversationsRecepientsList(List<ConversationsRecepientsDto> conversationsRecepientsList) {
        this.conversationsRecepientsList = conversationsRecepientsList;
    }

    public ConversationTypesDto getConversationTypeId() {
        return conversationTypeId;
    }

    public void setConversationTypeId(ConversationTypesDto conversationTypeId) {
        this.conversationTypeId = conversationTypeId;
    }

    public List<MessagesDto> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<MessagesDto> messagesList) {
        this.messagesList = messagesList;
    }

    public Long getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Long unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    
    
    
}
