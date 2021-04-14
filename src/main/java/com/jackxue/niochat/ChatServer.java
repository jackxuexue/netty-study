package com.jackxue.niochat;


import com.jackxue.niochat.dto.BaseMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ChatServer extends Thread {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private String ip;
    private int port;
    private volatile boolean isStop = false;
    private MessageHandler messageHandler;


    public ChatServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            //打开选择器
            selector = Selector.open();
            //打开ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //配置为非阻塞
            serverSocketChannel.configureBlocking(false);

            //bind 注册监听事件
            doBind(serverSocketChannel, selector);

            while (!isStop) {
                selector.select(1000);
                //获取有事件的 SelectionKey 集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    keyIterator.remove();
                    //判断是什么事件发生了
                    if (selectionKey.isAcceptable()) {
                        //accept
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //配置为非阻塞
                        socketChannel.configureBlocking(false);
                        //注册读事件
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }
                    //读事件发生
                    if (selectionKey.isReadable()) {
                        //pipeline
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        int read = socketChannel.read(buffer);
                        BaseMessage message = (BaseMessage) SerializeUtil.unSerializeObj(buffer.array());
                        System.out.println("type:" + message.getType());
                        messageHandler.handleMessage(new MessageEvent(message.getType(), message), socketChannel);
                        buffer.clear();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void registerMessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }

    private void doBind(ServerSocketChannel sc, Selector selector) throws IOException {
        //绑定端口
        sc.socket().bind(new InetSocketAddress(port));

        //注册accept 事件
        sc.register(selector, SelectionKey.OP_ACCEPT);
    }


}
