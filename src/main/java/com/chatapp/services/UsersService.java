/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.services;

import com.chatapp.common.utils.MapperUtil;
import com.chatapp.domains.Friends;
import com.chatapp.domains.Messages;
import com.chatapp.domains.User;
import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.dtos.domains.UserDto;
import com.chatapp.enums.Status;
import com.chatapp.repositories.FriendsRepository;
import com.chatapp.repositories.MessagesRepository;
import com.chatapp.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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
public class UsersService {

    List<String> onlineUsers = new ArrayList<>();
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    FriendsRepository friendsRepository;
    
    @Autowired
    MessagesRepository messageRepository;
    

    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    Mapper mapper;
    
    @Autowired
    MapperUtil mapperUtil;

    public boolean validateUserNameAndPassword(String userName, String password) {
        List<User> users = userRepository.findAllByUserNameAndPassword(userName, password);
        if (users != null && users.size() == 1) {
            return true;
        }

        return false;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findOne(id);
        return mapper.map(user, UserDto.class);
    }

    public UserDto getUserByUserNameAndPassword(String userName, String password) {
        List<User> users = userRepository.findAllByUserNameAndPassword(userName, password);

        if (users != null && users.size() == 1) {
            return mapper.map(users.get(0), UserDto.class);
        }
        return null;
    }
    
    public List<UserDto> getUserFriendsByUserName(String userName) {
        List<User> userEntities = userRepository.findAllByUserName(userName);
        if (userEntities != null && userEntities.size() > 0) {
            List<Friends> friendsEntities = userEntities.get(0).getFriendsList();
            List<User> userEntites = new ArrayList<>();
            for (Friends friend : friendsEntities) {
                userEntites.add(friend.getFriendId());
            }
            
            List<UserDto> usersDto = mapperUtil.getDtoList(userEntites, UserDto.class);
            for(UserDto user: usersDto){
                if (onlineUsers.contains(user.getUserName())) {
                    user.setStatus(Status.ONLINE);
                }
            }
            return usersDto;
            
        }
        return null;
    }

    public List<UserDto> getUserFriends(Long id) {
        User userEntity = userRepository.findOne(id);
//        System.out.println("user data : "+userEntity.getEmail());
        if (userEntity != null) {
            List<Friends> friendsEntities = userEntity.getFriendsList();
//            System.out.println("friends list size : "+friendsEntities.size());
            List<User> userEntites = new ArrayList<>();
            for (Friends friend : friendsEntities) {
                userEntites.add(friend.getFriendId());
            }
            
            List<UserDto> usersDto = mapperUtil.getDtoList(userEntites, UserDto.class);
            for(UserDto user: usersDto){
                if (onlineUsers.contains(user.getUserName())) {
                    user.setStatus(Status.ONLINE);
                }
            }
            return usersDto;
            
        }
        return null;
    }

    public List<String> getOnlineUsers() {
        return onlineUsers;
    }
    
    public List<UserDto> searchPeople(String keyword,Long userId){
        List<User> users = userRepository.searchPeople(keyword,userId);
        return mapperUtil.getDtoList(users, UserDto.class);
    }

    public boolean acceptFriend(MessagesDto message) throws JsonProcessingException {
        User user = mapper.map(message.getConversationId().getConversationsRecepientsList().get(0).getUserId(), User.class);
        User friend = mapper.map(message.getConversationId().getConversationsRecepientsList().get(1).getUserId(),User.class);
        Friends friendship1 = new Friends();
        friendship1.setFriendId(friend);
        friendship1.setUserId(user);
        friendsRepository.save(friendship1);
        
        Friends friendship2 = new Friends();
        friendship2.setFriendId(user);
        friendship2.setUserId(friend);
        friendsRepository.save(friendship2);
        
        User friendToSend;
        UserDto friendDto;
        if(message.getUserSenderId().getId() == user.getId())
            friendToSend = friend;
        else
            friendToSend = user;
        friendDto = mapper.map(friendToSend, UserDto.class);
        if(this.onlineUsers.contains(friendToSend.getUserName())){
            friendDto.setStatus(Status.ONLINE);
        }

        ObjectMapper mapper = new ObjectMapper();
        String friendJsonString = mapper.writeValueAsString(friendDto);
        
        this.template.convertAndSendToUser(message.getUserSenderId().getUserName(), "/topic/newFriendAdded", friendJsonString);
        System.out.println("message "+message.getMessageContent());
        List<Messages> messages = messageRepository.findAllByMessageTypeIdAndConversationIdId(message.getMessageType().getId(), message.getConversationId().getId());
        for(Messages msg:messages){
            messageRepository.delete(msg.getId());
        }
        
        return true;
    }

    public boolean declineFriend(MessagesDto message) {
        List<Messages> messages = messageRepository.findAllByMessageTypeIdAndConversationIdId(message.getMessageType().getId(), message.getConversationId().getId());
        for(Messages msg:messages){
            messageRepository.delete(msg.getId());
        }
        
        return true;
    }

    public boolean isOnline(String username) {
        if(this.onlineUsers.contains(username))
            return true;
        else
            return false;
    }

    public boolean isUserNameExists(String userName) {
        List<User> users = userRepository.findAllByUserName(userName);
        if(users.size() > 0)
            return true;
        return false;
    }

    public boolean isEmailExists(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        if(users.size() > 0)
            return true;
        return false;
    }

    public Long saveUser(User user) {
//         User user = mapper.map(userDto, User.class);
         userRepository.save(user);
         return user.getId();
    }
    
}
