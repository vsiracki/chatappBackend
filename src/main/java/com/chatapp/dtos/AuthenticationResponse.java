/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos;

import com.chatapp.dtos.domains.UserDto;

/**
 *
 * @author Yousef
 */
public class AuthenticationResponse {
    private boolean result;
    private String message;
    private String token;
    private UserDto user;

    public AuthenticationResponse(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public AuthenticationResponse(boolean result, String message, String token) {
        this.result = result;
        this.message = message;
        this.token = token;
    }

    public AuthenticationResponse(boolean result, String message, String token, UserDto user) {
        this.result = result;
        this.message = message;
        this.token = token;
        this.user = user;
    }
    
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
    
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
