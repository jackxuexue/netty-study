package com.jackxue.niochat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class RomCenter {
    public static Map<String,Rom> romMap = new HashMap<>();
    public static Map<SocketChannel, Rom> socketChannelRomMap = new HashMap<>();

    public static void joinRom(String romName, Member member){
        Rom rom = null;
        //如果房间不存在则创建
        if(!romMap.containsKey(romName)){
            rom = new Rom(romName);
            romMap.put(romName, rom);
        }else{
            rom = romMap.get(romName);
        }
        rom.addMember(member);
        socketChannelRomMap.put(member.getChannel(), rom);
    }

    /**
     * 广播消息，不发送消息给自己
     * @param message
     */
    public static void boardCast(SocketChannel sc, String message){
        if(!socketChannelRomMap.containsKey(sc)){
            System.out.println("do not register.....");
            return;
        }
        //获取到用户所在房间
        Rom rom = socketChannelRomMap.get(sc);
        String userName = null;
        //先找到用户的名称
        for (Member member : rom.getMemberList()) {
            if(member.getChannel() == sc){
                userName = member.getUserName();
            }
        }
        for (Member member : rom.getMemberList()) {
            if(member.getChannel() == sc){
                continue;
            }
            try {
                byte[] serverBytes = ("用户["+userName+"] :").getBytes();
                byte[] bytes = message.getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(bytes.length + serverBytes.length);
                buffer.put(serverBytes);
                buffer.put(bytes);
                buffer.flip();
                member.getChannel().write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

