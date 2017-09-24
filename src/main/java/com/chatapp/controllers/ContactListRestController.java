/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.controllers;

import com.chatapp.dtos.domains.ConversationsDto;
import com.chatapp.dtos.domains.MessagesDto;
import com.chatapp.dtos.domains.UserDto;
import com.chatapp.services.ConversationsService;
import com.chatapp.services.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.FileTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yousef
 */
@RestController
@RequestMapping("/friends")
public class ContactListRestController {

    @Autowired
    private UsersService userService;
    
    @Autowired
    private ConversationsService conversationsService;
    
    @RequestMapping("/get/{id}")
    public List<UserDto> getMyFriends(@PathVariable Long id){
        System.out.println("inside function");
        return userService.getUserFriends(id);
    }
    
    @RequestMapping("/conversations/{userId}")
    public List<ConversationsDto> getMyConversations(@PathVariable(value = "userId")Long id){
        return conversationsService.getMyConversations(id);
    }
    
    @RequestMapping("/conversation/{id}/{id2}")
    public ConversationsDto getConversation(@PathVariable("id")Long id,@PathVariable("id2")Long id2){
        return conversationsService.findConv(id, id2);
    }
    
    @RequestMapping("/searchpeople/{keyword}/{userId}")
    public List<UserDto> searchPeople(@PathVariable("keyword")String keyword,@PathVariable("userId")Long userId){
        return userService.searchPeople(keyword,userId);
    }
    
    @RequestMapping(value = "initiateNewConversation/{userId}/{friendId}",method = RequestMethod.POST)
    public ConversationsDto startConversation(@PathVariable("userId")Long userId,@PathVariable("friendId")Long friendId){
        return conversationsService.startConversation(userId,friendId);
    }
    
    @RequestMapping(value = "initiateGroupConversation/{userId}",method = RequestMethod.POST)
    public ConversationsDto startConversation(@PathVariable("userId")Long userId ,@RequestBody List<Long> friendsIds) throws JsonProcessingException{
        return conversationsService.startConversation(userId,friendsIds);
    }
    
    @RequestMapping(value = "acceptFriend" ,method=RequestMethod.POST)
    public boolean acceptFriend(@RequestBody MessagesDto message){
        try {
            return userService.acceptFriend(message);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ContactListRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @RequestMapping(value = "declineFriend" ,method=RequestMethod.POST)
    public boolean declineFriend(@RequestBody MessagesDto message){
        return userService.declineFriend(message);
    }
    
    @RequestMapping(value = "isOnline/{username}")
    public boolean isOnline(@PathVariable("username")String username){
        
        return this.userService.isOnline(username);
    }
    
    @RequestMapping(value = "getImage/{userId}/{imageName}.{extension}",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable("userId") Long userId ,@PathVariable("imageName") String imageName ,@PathVariable("extension")String extension) throws IOException {
        System.out.println(imageName+extension);
        File img = new File("src/main/resources/profilePics/"+userId+"/"+imageName+"."+extension);
        if(img.exists())
            return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
        else
            return ResponseEntity.notFound().build();
    }
}
