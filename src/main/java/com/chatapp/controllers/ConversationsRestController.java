/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.controllers;

import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.services.ConversationsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yousef
 */
@RestController
@RequestMapping("chatmessages")
public class ConversationsRestController {
    
    ConversationsService conversationsService;

    @Autowired
    public void setConversationsService(ConversationsService conversationsService) {
        this.conversationsService = conversationsService;
    }
    
    
    
    @RequestMapping("conversation/{conversationId}")
    public List<MessagesDto> getConversationMessages(@PathVariable(value="conversationId")Long conversationId){
        return conversationsService.getConversationMessages(conversationId);
    }
    
    @RequestMapping("clearunseen/{userId}/{conversationId}")
    public void clearUnseen(@PathVariable("userId") Long userId,@PathVariable("conversationId") Long conversationId){
          conversationsService.clearUnseenMessages(userId,conversationId);
    }
    
}
