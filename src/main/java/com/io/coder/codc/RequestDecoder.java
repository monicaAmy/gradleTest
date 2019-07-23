package com.io.coder.codc;

import com.io.coder.constant.ConstantValue;
import com.io.coder.module.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * ChannelBuffer 读写指针
 */

//1011110100100101
//1023450600700809
public class RequestDecoder extends FrameDecoder
{

    /**
     * 数据包基本长度
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;

    /**
     * @param ctx
     * @param channel
     * @param buffer
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception
    {
        /* *//**
     * 校验
     * 数据包长度,不完整需要等待后面数据包到来
     *
     */

        //可读长度必须大于基本长度
        if (buffer.readableBytes() >= BASE_LENTH)
        {
            //防止socket字节流攻击
            if (buffer.readableBytes() > 2048)
            {
                //清除buffer内容
                buffer.skipBytes(buffer.readableBytes());
            }

            //记录包头开始的index
            int beginReader;

            while (true)
            {
                beginReader = buffer.readerIndex();
                //标记阅读位置
                buffer.markReaderIndex();
                //int 4个字节
                if (buffer.readInt() == ConstantValue.FLAG)
                {
                    break;
                }

                //未读到包头，略过一个字节
                buffer.resetReaderIndex();
                buffer.readByte();

                //长度又变得不满足
                if (buffer.readableBytes() < BASE_LENTH)
                {
                    return null;
                }
            }

            //模块号
            short module = buffer.readShort();
            //命令号
            short cmd = buffer.readShort();
            //长度
            int length = buffer.readInt();

            //判断请求数据包数据是否到齐
            if (buffer.readableBytes() < length)
            {
                //还原读指针
                buffer.readerIndex(beginReader);
                //缓存数据
                return null;
            }

            //读取data数据
            byte[] data = new byte[length];
            buffer.readBytes(data);

            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);

            //继续往下传递
            return request;

        }
        //数据包不完整，需要等待后面的包来
        return null;
    }
}
