/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos;

import com.chatapp.dtos.domains.ConversationsRecepientsDto;
import java.util.List;

/**
 *
 * @author Yousef
 */
public class TypingStatus {
    private boolean isTyping;
    private List<ConversationsRecepientsDto> recepients;
    private Long conversationId;
    private String message;

    public TypingStatus() {
    }

    public boolean isIsTyping() {
        return isTyping;
    }

    public void setIsTyping(boolean isTyping) {
        this.isTyping = isTyping;
    }

    public List<ConversationsRecepientsDto> getRecepients() {
        return recepients;
    }

    public void setRecepients(List<ConversationsRecepientsDto> recepients) {
        this.recepients = recepients;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
