package com.jackxue.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        


        if(socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080))){
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            System.out.println("register connect.....");
        }

        while (true){
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    System.out.println("need to read!");
                }
                if (selectionKey.isConnectable()) {
                    //若已经建立连接
                    if (socketChannel.finishConnect()) {
                        //向多路复用器注册可读事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        //向管道写数据
                        doWrite(socketChannel);
                    }else {
                        //连接失败 进程退出
                        System.exit(1);
                    }
                }
                iterator.remove();
            }
        }

    }

    private static void doWrite(SocketChannel sc) throws IOException {
        //要写的内容
        byte[] req = "    -    QUERY TIME ORDER     -   ".getBytes();
        //为字节缓冲区分配指定字节大小的容量
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        //将内容写入缓冲区
        writeBuffer.put(req);
        //反转缓冲区
        writeBuffer.flip();
        //输出打印缓冲区的可读大小
        System.out.println(writeBuffer.remaining());
        //将内容写入管道中
        sc.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            //若缓冲区中无可读字节，则说明成功发送给服务器消息
            System.out.println("Send order 2 server succeed.");
        }
    }
}
