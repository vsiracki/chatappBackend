/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos.domains;

/**
 *
 * @author Yousef
 */
public class FriendsDto {
    private Long id;

    private UserDto userId;

    private UserDto friendId;

    public FriendsDto() {
    }

    public FriendsDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public UserDto getFriendId() {
        return friendId;
    }

    public void setFriendId(UserDto friendId) {
        this.friendId = friendId;
    }
}
