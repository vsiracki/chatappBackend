/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos;

import com.chatapp.dtos.domains.UserDto;
import com.chatapp.enums.Status;

/**
 *
 * @author Yousef
 */
public class UserStatus {
    private String userName;
    private Status status;

    public UserStatus() {
    }

    public UserStatus(String userName, Status status) {
        this.userName = userName;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
    
}
