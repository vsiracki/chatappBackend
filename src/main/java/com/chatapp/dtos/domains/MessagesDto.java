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
public class MessagesDto {

    private Long id;

    private Date time;

    @JsonIgnore
    private List<UnreadMessagesDto> unreadMessagesList;

    private ConversationsDto conversationId;

    private UserDto userSenderId;
    
    private String messageContent;
 
    private String imageUrl;
    
    private String videoUrl;
    
    private MessageTypeDto messageType;
   

    public MessagesDto() {
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

    public MessageTypeDto getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeDto messageType) {
        this.messageType = messageType;
    }

    public MessagesDto(Long id) {
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

    public List<UnreadMessagesDto> getUnreadMessagesList() {
        return unreadMessagesList;
    }

    public void setUnreadMessagesList(List<UnreadMessagesDto> unreadMessagesList) {
        this.unreadMessagesList = unreadMessagesList;
    }

    public ConversationsDto getConversationId() {
        return conversationId;
    }

    public void setConversationId(ConversationsDto conversationId) {
        this.conversationId = conversationId;
    }

    public UserDto getUserSenderId() {
        return userSenderId;
    }

    public void setUserSenderId(UserDto userSenderId) {
        this.userSenderId = userSenderId;
    }
}
