package com.io.netty.heart;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;

public class HelloHandler extends SimpleChannelHandler
{
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception
    {
        System.out.println(e.getMessage());
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception
    {

        if (e instanceof IdleStateEvent)
        {
            if (((IdleStateEvent) e).getState() == IdleState.ALL_IDLE)
            {
                System.out.println("踢玩家下线");
                //下线之后,客户端断开,客户端再输入数据,(客户端)就会报异常ClosedChannelException
                //客户端可以采取断开重连措施
                ChannelFuture write = ctx.getChannel().write("time out,you will close");
                write.addListener((future) ->
                {
                    ctx.getChannel().close();
                });

            }
        }
        else
        {
            super.handleUpstream(ctx, e);
        }
    }
}
