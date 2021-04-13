package com.jackxue.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class GatheringAndScattering {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //监听
        SocketChannel socketChannel = serverSocketChannel.accept();

        //使用ByteBuffer数组进行聚合和分散的测试
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        long messageLength = 8;
        long readBytes = 0;
        while (readBytes < messageLength) {
            //写到ByteBuffer数组中，先写第一个，写满后写道第二个，聚合操作
            long readLen = socketChannel.read(byteBuffers);
            readBytes += readLen;
        }

        Arrays.asList(byteBuffers).stream()
                .map(buffer->{
                    return "position:" + buffer.position() + " limit:" + buffer.limit();
                }).forEach(System.out::println);

        Arrays.asList(byteBuffers).forEach(byteBuffer -> {
            byteBuffer.flip();
        });

        long writeBytes = 0;
        while (writeBytes < messageLength){
            //把Bytebuffer数组中的每个buffer写到Channel中，分散操作
            long writeLen = socketChannel.write(byteBuffers);
            writeBytes += writeLen;
        }
        Arrays.asList(byteBuffers).forEach(byteBuffer -> {
            byteBuffer.clear();
        });

        System.out.println("writeBytes:"+ writeBytes + " readBytes:"+ readBytes + " messageLength:"+ messageLength );

        //释放资源
        socketChannel.close();
        serverSocketChannel.close();
    }
}
