/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.services;

import com.chatapp.common.utils.MapperUtil;
import com.chatapp.domains.ConversationTypes;
import com.chatapp.domains.Conversations;
import com.chatapp.domains.ConversationsRecepients;
import com.chatapp.domains.Messages;
import com.chatapp.domains.UnreadMessages;
import com.chatapp.domains.User;
import com.chatapp.dtos.domains.ConversationsDto;
import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.repositories.ConversationsRecepientsRepository;
import com.chatapp.repositories.ConversationsRepository;
import com.chatapp.repositories.MessagesRepository;
import com.chatapp.repositories.UnreadMessagesRepository;
import com.chatapp.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yousef
 */
@Service
@Transactional
public class ConversationsService {

    @Autowired
    private ConversationsRepository conversationsRepository;

    @Autowired
    private ConversationsRecepientsRepository conversationsRecepientsRepository;

    @Autowired
    private UnreadMessagesRepository unreadMessageRepository;

    @Autowired
    private MessagesRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SimpMessagingTemplate template;

    public List<MessagesDto> getConversationMessages(Long conversationId) {
        List<Messages> messages = messageRepository.findFirst10ByConversationIdIdOrderByIdDesc(conversationId);
        if (messages != null && messages.size() > 0) {
            return mapperUtil.getDtoList(messages, MessagesDto.class);
        }
        return null;
    }

    public List<ConversationsDto> getMyConversations(Long userId) {
        List<Conversations> conversations = conversationsRepository.findMyConversations(userId);
        if (conversations != null && conversations.size() > 0) {
            List<ConversationsDto> conversationDtos = mapperUtil.getDtoList(conversations, ConversationsDto.class);
            List<Object[]> queryResult = unreadMessageRepository.findUnreadMessagesByUserId(userId);
            Map<Long, Long> unreadCounts = new HashMap<>();
            System.out.println("unread counts " + unreadCounts);
            for (Object[] row : queryResult) {
                BigDecimal value1 = BigDecimal.valueOf(((BigInteger)row[0]).intValue());
                BigDecimal value2 = BigDecimal.valueOf(((BigInteger)row[1]).intValue());
                unreadCounts.put(value1.longValueExact(), value2.longValueExact());
            }
            if (unreadCounts.size() > 0) {
                for (ConversationsDto conv : conversationDtos) {
                    conv.setUnreadMessages(unreadCounts.get(conv.getId()));
                }
            }
            return conversationDtos;
        }

        return null;
    }

    public ConversationsDto findConv(Long id, Long id2) {
        List<Conversations> conversations = conversationsRepository.findConversation(id, id2);
        if (conversations != null && conversations.size() > 0) {
            ConversationsDto conversationDtos = mapper.map(conversations.get(0), ConversationsDto.class);
            return conversationDtos;
        }
        return null;
    }

    public void clearUnseenMessages(Long userId, Long conversationId) {
        System.out.println("user id " + userId + " conversaation id " + conversationId);
//        unreadMessageRepository.deleteByUserNameAndConversation(userId, userId);
        List<UnreadMessages> unreads = unreadMessageRepository.findAllByUserIdIdAndConversationIdId(userId, conversationId);
        if (unreads != null && unreads.size() > 0) {
            for (UnreadMessages un : unreads) {
                unreadMessageRepository.delete(un.getId());
            }

        }

    }

    public ConversationsDto startConversation(Long userId, List<Long> recepients) throws JsonProcessingException {
        Conversations conversation = new Conversations();
        ConversationTypes convTypes = new ConversationTypes();
        convTypes.setId(2L);
        convTypes.setName("multi");
        conversation.setConversationTypeId(convTypes);
        conversation.setLastMessageTime(new Date());
        User user = userRepository.findOne(userId);
        String message = user.getFirstName() + " " + user.getLastName() + " created new group";
        conversation.setLastMessageSent(message);
        conversationsRepository.save(conversation);

        List<ConversationsRecepients> recps = new ArrayList<>();
        List<User> friends = new ArrayList<>();
        for (Long currId : recepients) {
            ConversationsRecepients convRecepients = new ConversationsRecepients();
            convRecepients.setConversationId(conversation);
            convRecepients.setOwnerId(user);
            User friendUser = userRepository.findOne(currId);
            friends.add(friendUser);
            convRecepients.setUserId(friendUser);
            conversationsRecepientsRepository.save(convRecepients);
            recps.add(convRecepients);
        }

        ConversationsRecepients convRecepients = new ConversationsRecepients();
        convRecepients.setConversationId(conversation);
        convRecepients.setOwnerId(user);
        convRecepients.setUserId(user);
        conversationsRecepientsRepository.save(convRecepients);
        recps.add(convRecepients);

        conversation.setConversationsRecepientsList(recps);
        ConversationsDto conv = mapper.map(conversation, ConversationsDto.class);
        ObjectMapper Omapper = new ObjectMapper();
        String converstionJsonString = Omapper.writeValueAsString(conv);
        for (User friend : friends) {
            this.template.convertAndSendToUser(friend.getUserName(), "/topic/newFriendRequests", converstionJsonString);
        }

        return conv;

    }

    public ConversationsDto startConversation(Long userId, Long friendId) {

        List<Conversations> convs = conversationsRepository.findConversation(userId, friendId);
        Conversations conversation = null;
        if(convs.size() > 0)
            conversation = convs.get(0);
        if (conversation == null) {
            conversation = new Conversations();
            ConversationTypes convTypes = new ConversationTypes();
            convTypes.setId(1L);
            convTypes.setName("p2p");
            conversation.setConversationTypeId(convTypes);
            conversation.setLastMessageTime(new Date());
            User user = userRepository.findOne(userId);
            User friendUser = userRepository.findOne(friendId);
            String message = user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ") is wanting to add you as a friend !";
            conversation.setLastMessageSent(message);
//            conversation.setId(1l);
            conversationsRepository.save(conversation);

            ConversationsRecepients convRecepients = new ConversationsRecepients();
            convRecepients.setConversationId(conversation);
            convRecepients.setOwnerId(friendUser);
            convRecepients.setUserId(friendUser);
            conversationsRecepientsRepository.save(convRecepients);

            ConversationsRecepients convRecepients1 = new ConversationsRecepients();
            convRecepients1.setConversationId(conversation);
            convRecepients1.setOwnerId(friendUser);
            convRecepients1.setUserId(user);
            conversationsRecepientsRepository.save(convRecepients1);
            System.out.println("conv id : " + conversation.getId());
            List<ConversationsRecepients> recps = new ArrayList<>();
            conversation.setConversationsRecepientsList(recps);
            recps.add(convRecepients);
            recps.add(convRecepients1);
        }
        return mapper.map(conversation, ConversationsDto.class);

    }
}
