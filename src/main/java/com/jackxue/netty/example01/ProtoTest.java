package com.jackxue.netty.example01;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoTest {
    public static void main(String[] args) {
        MessageData.Person jack = MessageData.Person.newBuilder().setName("jack")
                .setAge(26).build();

        byte[] bytes = jack.toByteArray();


        try {
            MessageData.Person person = MessageData.Person.parseFrom(bytes);
            System.out.println(person.getName());
            System.out.println(person.getAge());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


    }
}
