package com.jackxue.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8080);
        OutputStream ops = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ops.write("hello".getBytes());
        while (true) {
            String s = br.readLine();
            if (s == null) break;
            if(s.equals("q")) break;
            ops.write(s.getBytes());
        }
        socket.close();
    }
}