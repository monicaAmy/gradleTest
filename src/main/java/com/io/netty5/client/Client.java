package com.io.netty5.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一个thread + 队列 == 一个单线程线程池   =====> 线程安全的，任务是线性串行执行的
 * <p>
 * <p>
 * 线程安全，不会产生阻塞效应 ，使用对象组(数组,使用后不用返回)
 * <p>
 * 线程不安全，会产生阻塞效应， 使用对象池(队列,使用后归还)
 */
public class Client
{
    public static void main(String[] args)
    {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup worker = new NioEventLoopGroup();
        try
        {
            //设置线程池
            bootstrap.group(worker);

            //设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            //设置管道
            bootstrap.handler(new ChannelInitializer<Channel>()
            {
                @Override
                protected void initChannel(Channel ch) throws Exception
                {
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ClientHandler());

                }
            });

            ChannelFuture connect = bootstrap.connect("127.0.0.1", 10101);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                System.out.println("请输入: ");
                String msg = bufferedReader.readLine();
                // 通道是支持并发

                //writeAndFlush 使用的是单线程线程池,线程安全,  可以使用线程组
                connect.channel().writeAndFlush(msg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            worker.shutdownGracefully();
        }

    }
}
