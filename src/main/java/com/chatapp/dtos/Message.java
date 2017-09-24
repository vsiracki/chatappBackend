/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.dtos;

/**
 *
 * @author Yousef
 */
enum MsgType{
    Text,Image,Video
}
public class Message {
    private User from;
    private User[] to;
    private MsgType type;
    private String msgTime;
    private String msgContent;
    
    public Message(){}

    public Message(User from, User[] to, MsgType type, String msgTime, String msgContent) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.msgTime = msgTime;
        this.msgContent = msgContent;
    }
 
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User[] getTo() {
        return to;
    }

    public void setTo(User[] to) {
        this.to = to;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    
    
    
    
}
