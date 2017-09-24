/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos.domains;

import com.chatapp.domains.Conversations;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Yousef
 */
public class UnreadMessagesDto {

    private Long id;

    private MessagesDto messageId;

    @JsonIgnore
    private UserDto userId;
    
    private Conversations conversationId;

    public UnreadMessagesDto() {
    }

    public UnreadMessagesDto(Long id) {
        this.id = id;
    }

    public Conversations getConversationId() {
        return conversationId;
    }

    public void setConversationId(Conversations conversationId) {
        this.conversationId = conversationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessagesDto getMessageId() {
        return messageId;
    }

    public void setMessageId(MessagesDto messageId) {
        this.messageId = messageId;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }
}
