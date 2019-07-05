package com.io.netty5.heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class Server
{
    public static void main(String[] args)
    {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        try
        {
            bootstrap.group(boss, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<Channel>()
            {
                @Override
                protected void initChannel(Channel ch) throws Exception
                {
                    ch.pipeline().addLast(new IdleStateHandler(5, 5, 10));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ServerHandler());

                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            ChannelFuture future = bootstrap.bind(10101);

            System.out.println("start");

            future.channel().closeFuture().sync();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
