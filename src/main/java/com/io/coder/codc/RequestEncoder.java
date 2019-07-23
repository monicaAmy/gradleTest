package com.io.coder.codc;

import com.io.coder.constant.ConstantValue;
import com.io.coder.module.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * 请求解码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头          | 模块号        | 命令号      |  长度        |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 *
 * @uthor -琴兽-
 */
public class RequestEncoder extends OneToOneEncoder
{
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception
    {
        Request request = (Request) msg;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(ConstantValue.FLAG);
        channelBuffer.writeShort(request.getModule());
        channelBuffer.writeShort(request.getCmd());
        channelBuffer.writeInt(request.getDataLength());
        if (request.getData() != null)
        {
            channelBuffer.writeBytes(request.getData());
        }
        return channelBuffer;
    }
}
