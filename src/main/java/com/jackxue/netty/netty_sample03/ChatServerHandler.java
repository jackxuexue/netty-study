package com.jackxue.netty.netty_sample03;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Iterator;

public class ChatServerHandler extends SimpleChannelInboundHandler<String > {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[服务器] " + ctx.channel().remoteAddress() + "上线了！");
        channelGroup.add(ctx.channel());
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[服务器] " + ctx.channel().remoteAddress() + "下线了！");
    }

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Iterator<Channel> iterator = channelGroup.iterator();
        while (iterator.hasNext()){
            Channel channel = iterator.next();
            if(!channel.equals(ctx.channel())){
                channel.writeAndFlush("[服务器] " + ctx.channel().remoteAddress() + " ：" + msg);
            }else {
                channel.writeAndFlush("自己: " + msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
