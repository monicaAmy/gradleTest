package com.io.netty5.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多连接客户端
 *
 * @author -琴兽-
 * <p>
 * <p>
 * 测试的时候127地址改为本地ip,断开本地连接(禁用本地连接),会报无可用链接,连上之后会启动重连
 * 如何测试5个链接都满了的情况???
 */
public class MultClient
{

    /**
     * 服务类
     */
    private Bootstrap bootstrap = new Bootstrap();

    /**
     * 会话
     */
    private List<Channel> channels = new ArrayList<>();

    /**
     * 引用计数
     */
    private final AtomicInteger index = new AtomicInteger();

    /**
     * 初始化
     *
     * @param count
     */
    public void init(int count)
    {

        //worker
        EventLoopGroup worker = new NioEventLoopGroup();

        //设置线程池
        bootstrap.group(worker);

        //设置socket工厂、
        bootstrap.channel(NioSocketChannel.class);

        //设置管道
        bootstrap.handler(new ChannelInitializer<Channel>()
        {

            @Override
            protected void initChannel(Channel ch)
            {
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new ClientHandler());
            }
        });

        for (int i = 1; i <= count; i++)
        {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10101);
            channels.add(future.channel());
        }
    }

    /**
     * 获取会话
     *
     * @return
     */
    public Channel nextChannel()
    {
        return getFirstActiveChannel(0);
    }

    private Channel getFirstActiveChannel(int count)
    {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        //是否断开连接
        if (!channel.isActive())
        {
            //重连
            reconnect(channel);
            if (count >= channels.size())
            {
                throw new RuntimeException("no can use channel");
            }
            return getFirstActiveChannel(count + 1);
        }
        return channel;
    }

    /**
     * 重连
     *
     * @param channel
     */
    private void reconnect(Channel channel)
    {
        //锁改成单线程队列,链接断开,扔任务到队列里面,再去执行重连
        //锁会阻塞线程
        synchronized (channel)
        {
            if (channels.indexOf(channel) == -1)
            {
                return;
            }

            Channel newChannel = bootstrap.connect("127.0.0.1", 10101).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }

}
