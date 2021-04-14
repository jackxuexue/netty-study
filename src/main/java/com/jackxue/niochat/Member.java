package com.jackxue.niochat;

import java.nio.channels.SocketChannel;

/**
 * 用户实体类
 */
public class Member {
    private String userName;
    private String romName;
    private SocketChannel channel;

    public Member(){ }
    public Member(String userName, SocketChannel channel) {
        this.userName = userName;
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRomName() {
        return romName;
    }

    public void setRomName(String romName) {
        this.romName = romName;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }
}
