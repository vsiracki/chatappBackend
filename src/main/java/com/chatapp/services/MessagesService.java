/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.services;

import com.chatapp.domains.Conversations;
import com.chatapp.domains.ConversationsRecepients;
import com.chatapp.domains.MessageType;
import com.chatapp.domains.Messages;
import com.chatapp.domains.UnreadMessages;
import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.repositories.ConversationsRepository;
import com.chatapp.repositories.MessagesRepository;
import com.chatapp.repositories.UnreadMessagesRepository;
import javax.transaction.Transactional;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yousef
 */
@Service
@Transactional
public class MessagesService {

    @Autowired
    private MessagesRepository messageRepository;

    @Autowired
    private UnreadMessagesRepository unreadRepository;
    
    @Autowired
    private ConversationsRepository convRepository;

    @Autowired
    private Mapper mapper;

    public Long saveMessage(MessagesDto messageDto) {

        Messages message = mapper.map(messageDto, Messages.class);
        
        System.out.println("message after mapped " + message.getMessageType().getName());
        
        messageRepository.save(message);
        
        Conversations conversation = convRepository.findOne(message.getConversationId().getId());
        
         conversation.setLastMessageSent(message.getMessageContent());
        conversation.setLastMessageTime(message.getTime());
        
        System.out.println("message sender id "+message.getUserSenderId().getId());
        for (ConversationsRecepients cr : message.getConversationId().getConversationsRecepientsList()) {
            
            System.out.println("cr id "+cr.getUserId().getId());
            if (message.getUserSenderId().getId() != cr.getUserId().getId()) {
                UnreadMessages unreadMessage = new UnreadMessages();
                unreadMessage.setMessageId(message);
                unreadMessage.setConversationId(message.getConversationId());
                unreadMessage.setUserId(cr.getUserId());
                unreadRepository.save(unreadMessage);
            }
        }
        return message.getId();
    }
}
