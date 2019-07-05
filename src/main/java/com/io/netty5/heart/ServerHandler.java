package com.io.netty5.heart;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends SimpleChannelInboundHandler<String>
{
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception
    {
        System.out.println("reviced " + msg);
        ctx.channel().writeAndFlush("hi");
        ctx.writeAndFlush("hi");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        if (evt instanceof IdleStateEvent)
        {
            if (((IdleStateEvent) evt).state() == IdleState.ALL_IDLE)
            {
                ChannelFuture future = ctx.writeAndFlush("you will close");
                future.addListener(

                        (future1 ->
                        {
                            ctx.channel().close();
                        })
                );
            }
        }
        else
        {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 新客户端接入
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("channelActive");
    }

    /**
     * 客户端断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("channelInactive");
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
    }
}
