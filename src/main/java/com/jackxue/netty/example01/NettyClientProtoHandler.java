package com.jackxue.netty.example01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientProtoHandler extends SimpleChannelInboundHandler<MessageData.MyMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MessageData.MyMessage message = MessageData.MyMessage.newBuilder()
                .setDataType(MessageData.DataType.PersonType)
                .setPerson(MessageData.Person.newBuilder().setName("jack").setAge(26).build())
                .build();
        ctx.channel().writeAndFlush(message);
        System.out.println("client channelActive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageData.MyMessage myMessage) throws Exception {

    }
}
