package com.jackxue.niochat;

import java.nio.channels.SocketChannel;

public interface MessageHandler {
    boolean handleMessage(MessageEvent event, SocketChannel channel);
}
