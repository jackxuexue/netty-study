package com.jackxue.niochat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class RomCenter {
    public static List<Rom> romList = new ArrayList<>();

    public static void joinRom(Rom rom){
        for (Rom r : romList) {
            if(r.getRomName().equals(rom.getRomName())){
                System.out.println("rom exist!");
                return;
            }
        }
        romList.add(rom);
        System.out.println(romList.size());
    }

    public static void boardCast(String rom, String message){
        for (Rom r : romList) {
            for (Member member : r.getMemberList()) {
                SocketChannel channel = member.getChannel();
                byte[] bytes = message.getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
                buffer.put(bytes);
                buffer.flip();
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("[server]:"+member.getUserName());
            }
        }
    }
}
