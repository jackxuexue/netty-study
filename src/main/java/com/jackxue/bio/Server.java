package com.jackxue.bio;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("start to accept....");
            final Socket socket = serverSocket.accept();
            System.out.println("accept client:"+ socket.getRemoteSocketAddress().toString());
            new Thread(()->{
                try {
                    byte[] buf = new byte[1024];
                    InputStream is = socket.getInputStream();
                    while (is.read(buf, 0, 1024) > 0){
                        System.out.println("receive "+ socket.getRemoteSocketAddress().toString() +" msg:"+ new String(buf));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
