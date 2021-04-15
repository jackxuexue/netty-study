package com.jackxue.niochat.dto;

import com.jackxue.niochat.constain.Constant;

public class BoardCastMessage extends BaseMessage{
    public BoardCastMessage(String message){
        this.type = Constant.BOARDCASTMESSAGE;
        this.add("message", message);
    }
}

