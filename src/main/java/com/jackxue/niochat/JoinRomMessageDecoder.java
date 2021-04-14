package com.jackxue.niochat;

import com.jackxue.niochat.dto.JoinRomMessage;

import java.nio.channels.SocketChannel;

public class JoinRomMessageDecoder implements MessageHandler {
    @Override
    public boolean handleMessage(MessageEvent event, SocketChannel channel) {
        try {
            if (event.getType() == 0) {
                JoinRomMessage message = (JoinRomMessage) event.getSource();
                System.out.println(message.getType() + " " + message.getValue("userName"));
                Rom rom = null;
                if(RomCenter.romList.size() <= 0) {
                    rom = new Rom(message.getValue("rom"));
                    RomCenter.joinRom(rom);
                }else {
                    rom = RomCenter.romList.get(0);
                }
                Member member = new Member(message.getValue("userName"), channel);
                rom.addMember(member);
                RomCenter.boardCast(rom.getRomName(), "user:" + member.getUserName() + " join!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}