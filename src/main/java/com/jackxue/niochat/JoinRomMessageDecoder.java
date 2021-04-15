package com.jackxue.niochat;

import com.jackxue.niochat.constain.Constant;
import com.jackxue.niochat.dto.JoinRomMessage;

import java.nio.channels.SocketChannel;

public class JoinRomMessageDecoder implements MessageHandler {
    @Override
    public boolean handleMessage(MessageEvent event, SocketChannel channel) {
        try {
            if (event.getType() == Constant.JOINMESSAGE) {
                JoinRomMessage message = (JoinRomMessage) event.getSource();
                String romName = message.getValue("rom");
                String userName = message.getValue("userName");
                Member member = new Member(userName, channel);
                RomCenter.joinRom(romName, member);
                RomCenter.boardCast(channel, "加入房间");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
