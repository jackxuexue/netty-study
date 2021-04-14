package com.jackxue.niochat.dto;

public class BoardCastMessage extends BaseMessage{
    public BoardCastMessage(String message){
        this.type = 1;
        this.add("message", message);
    }
}
