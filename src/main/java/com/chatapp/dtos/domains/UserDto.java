/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos.domains;

import com.chatapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 *
 * @author Yousef
 */
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;
    @JsonIgnore
    private String password;

    private String email;

    private Status status = Status.OFFLINE;

    private String imageName;

    @JsonIgnore
    private List<AuthorityDto> authorityList;
    @JsonIgnore
    private List<FriendsDto> friendsList;
    @JsonIgnore
    private List<UnreadMessagesDto> unreadMessagesList;
    @JsonIgnore
    private List<MessagesDto> messagesList;

    public UserDto() {
    }

    public UserDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<AuthorityDto> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<AuthorityDto> authorityList) {
        this.authorityList = authorityList;
    }

    public List<FriendsDto> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<FriendsDto> friendsList) {
        this.friendsList = friendsList;
    }

    public List<UnreadMessagesDto> getUnreadMessagesList() {
        return unreadMessagesList;
    }

    public void setUnreadMessagesList(List<UnreadMessagesDto> unreadMessagesList) {
        this.unreadMessagesList = unreadMessagesList;
    }

    public List<MessagesDto> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<MessagesDto> messagesList) {
        this.messagesList = messagesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
