package com.jackxue.niochat;

import com.jackxue.niochat.constain.Constant;
import com.jackxue.niochat.dto.BoardCastMessage;

import java.nio.channels.SocketChannel;

public class BoardCastMessageHandler implements MessageHandler{
    @Override
    public boolean handleMessage(MessageEvent event, SocketChannel channel) {
        System.out.println("BoardCastMessageHandler");
        BoardCastMessage source = (BoardCastMessage) event.getSource();
        if(source.getType() == Constant.BOARDCASTMESSAGE) {
            RomCenter.boardCast(channel, source.getValue("message"));
        }
        return true;
    }
}

