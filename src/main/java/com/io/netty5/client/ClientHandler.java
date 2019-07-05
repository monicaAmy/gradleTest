package com.io.netty5.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String>
{

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception
    {
        System.out.println("客户端收到消息:" + msg);
    }

}
