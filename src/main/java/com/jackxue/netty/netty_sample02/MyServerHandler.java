package com.jackxue.netty.netty_sample02;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server channelRead0...");
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + ", msg:" + msg);

        ctx.writeAndFlush(UUID.randomUUID().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
        System.out.println("server exceptionCaught...");
    }
}
