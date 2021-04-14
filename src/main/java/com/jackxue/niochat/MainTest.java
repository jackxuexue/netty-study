package com.jackxue.niochat;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        ChatServer chatServer = new ChatServer("localhost", 8080);
        DefaultMessageHandler messageHandler = new DefaultMessageHandler();
        messageHandler.addHandler(new MessageDecoder());
        chatServer.registerMessageHandler(messageHandler);
        chatServer.setName("chat server");
        chatServer.start();
        chatServer.join();
    }
}
