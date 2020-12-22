package com.jackxue.netty.example01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerProtoHandler extends SimpleChannelInboundHandler<MessageData.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageData.MyMessage myMessage) throws Exception {
        System.out.println("server channelRead0");
        MessageData.DataType dataType = myMessage.getDataType();
        switch (dataType){
            case PersonType:
                System.out.println("personType");
                MessageData.Person person = myMessage.getPerson();
                System.out.println(person.getName());
                System.out.println(person.getAge());
                break;
            case CatType:
                System.out.println("catType");
                break;
            case DogType:
                System.out.println("dogType");
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
