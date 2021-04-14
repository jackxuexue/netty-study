package com.jackxue.niochat;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class DefaultMessageHandler implements MessageHandler{
    private List<MessageHandler> handlerList = new ArrayList<>();

    @Override
    public boolean handleMessage(MessageEvent event, SocketChannel channel) {
        for (MessageHandler messageHandler : handlerList) {
            if(messageHandler.handleMessage(event, channel) == false) return false;
        }
        return true;
    }

    public void addHandler(MessageHandler handler){
        handlerList.add(handler);
    }
}
