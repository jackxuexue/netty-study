package com.jackxue.niochat.dto;

import com.jackxue.niochat.constain.Constant;

public class JoinRomMessage extends BaseMessage{
    public JoinRomMessage(String romName, String userName) {
        this.type = Constant.JOINMESSAGE;
        add("rom", romName);
        add("userName", userName);
    }
}


