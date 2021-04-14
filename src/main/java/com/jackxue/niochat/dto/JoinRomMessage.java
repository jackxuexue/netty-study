package com.jackxue.niochat.dto;

public class JoinRomMessage extends BaseMessage{
    public JoinRomMessage(String romName, String userName) {
        this.type = 0;
        add("rom", romName);
        add("userName", userName);
    }
}
