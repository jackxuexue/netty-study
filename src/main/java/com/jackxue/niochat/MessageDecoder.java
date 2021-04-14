package com.jackxue.niochat;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class MessageDecoder implements MessageHandler {
    @Override
    public boolean handleMessage(MessageEvent event) {
        try {
            if (event.getType() == 0) {
                SelectionKey selectionKey = (SelectionKey) event.getSource();
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                int read = socketChannel.read(buffer);
                System.out.println("read len:" + read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}