/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Yousef
 */
public class SignUpData {
    private Object file;
    private String userData;

    public SignUpData() {
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
    
}
