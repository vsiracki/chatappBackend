/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Yousef
 */
public class ConversationsRecepientsDto {
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private ConversationsDto conversationId;
    
    @JsonIgnore
    private UserDto ownerId;

    private UserDto userId;

    public ConversationsRecepientsDto() {
    }

    public ConversationsRecepientsDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversationsDto getConversationId() {
        return conversationId;
    }

    public void setConversationId(ConversationsDto conversationId) {
        this.conversationId = conversationId;
    }

    public UserDto getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserDto ownerId) {
        this.ownerId = ownerId;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }
}
