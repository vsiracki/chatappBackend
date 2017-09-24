/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.controllers;

import com.chatapp.dtos.TypingStatus;
import com.chatapp.dtos.domains.ConversationsDto;
import com.chatapp.dtos.domains.ConversationsRecepientsDto;
import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.services.MessagesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yousef
 */
@RestController
public class WebSocketController {

    @Autowired
    MessagesService messageService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/messages")
    public void greeting(String message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MessagesDto messageDto = mapper.readValue(message, MessagesDto.class);
        System.out.println("message sent " + messageDto.getMessageContent() + " id " + messageDto.getId());
        messageDto.setId(messageService.saveMessage(messageDto));
        if (headerAccessor.getUser().getName().equals(messageDto.getUserSenderId().getUserName())) {
            String currentUserName = headerAccessor.getUser().getName();
            String friendUserName = "";
            if (messageDto.getMessageType().getId() == 4) {
                if (messageDto.getConversationId().
                        getConversationsRecepientsList()
                        .get(0).getUserId().getUserName()
                        .equals(currentUserName)) {
                    friendUserName = messageDto.getConversationId().
                            getConversationsRecepientsList()
                            .get(1).getUserId().getUserName();
                } else {
                    friendUserName = messageDto.getConversationId().
                            getConversationsRecepientsList()
                            .get(0).getUserId().getUserName();
                }
                ConversationsDto conversationDto = messageDto.getConversationId();
                String converstionJsonString = mapper.writeValueAsString(conversationDto);
                this.template.convertAndSendToUser(friendUserName, "/topic/newFriendRequests", converstionJsonString);

            }
            String messageJsonString = mapper.writeValueAsString(messageDto);
            for (ConversationsRecepientsDto recepient : messageDto.getConversationId().getConversationsRecepientsList()) {
                this.template.convertAndSendToUser(recepient.getUserId().getUserName(), "/topic/messages", messageJsonString);
            }
        }
    }

    @MessageMapping("/typings")
    public void isTypingOrNot(String message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        System.out.println("typing");
        ObjectMapper mapper = new ObjectMapper();
        TypingStatus status = mapper.readValue(message, TypingStatus.class);
        for (ConversationsRecepientsDto recepient : status.getRecepients()) {
            if (!headerAccessor.getUser().getName().equals(recepient.getUserId().getUserName())) {
                this.template.convertAndSendToUser(recepient.getUserId().getUserName(), "/queue/typings", message);
            }
        }
    }

}
