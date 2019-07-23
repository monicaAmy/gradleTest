package com.io.proto;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Test1
{

    @Test
    public void testj()
    {
        System.out.println(Integer.toHexString(13));
        System.out.println(Integer.toBinaryString(13) + "");
    }

    @Test
    public void testO()
    {
        Player player = new Player();
        player.setPlayerId(10001);
        player.setAge(22);
        player.getSkills().add(101);
        player.getResource().setGold(99999);

        byte[] bytes = player.getBytes();

        System.out.println(Arrays.toString(bytes));

        //==============================================

        Player player2 = new Player();
        player2.readFromBytes(bytes);
        System.out.println(player2.getPlayerId() + "   " + player2.getAge() + "     " + Arrays.toString(player2.getSkills().toArray()) + "   " + player2.getResource().getGold());

    }

    /**
     * 动态的,没有String,List,Map方法
     */
    @Test
    public void testDy()
    {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(101);
        buffer.writeDouble(80.1);

        //写了多少,指针就移动多少位置
        byte[] bytes = new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);

        System.out.println(Arrays.toString(bytes));

        "abc".getBytes();

        //================================================
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(bytes);
        System.out.println(wrappedBuffer.readInt());
        System.out.println(wrappedBuffer.readDouble());
    }

    /**
     * 使用API
     */
    @Test
    public void testdouble()
    {
        int id = 101;
        int age = 21;

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(id);
        buffer.putInt(age);
        //long类型会报错,没有自动扩容功能
        //  buffer.putLong(123);
        byte[] array = buffer.array();
        System.out.println(Arrays.toString(buffer.array()));

        //====================================================

        //输出顺序按照插入顺序
        ByteBuffer buffer2 = ByteBuffer.wrap(array);
        System.out.println("id:" + buffer2.getInt());
        System.out.println("age:" + buffer2.getInt());
        //  System.out.println(buffer2.getLong());
    }

    /**
     * 手动位移转
     */
    @Test
    public void ddd()
    {
        try
        {
            int id = 13;
            int age = 2;

            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            arrayOutputStream.write(int2bytes(id));
            arrayOutputStream.write(int2bytes(age));

            byte[] byteArray = arrayOutputStream.toByteArray();

            System.out.println(Arrays.toString(byteArray));

            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
            byte[] idBytes = new byte[4];
            arrayInputStream.read(idBytes);
            System.out.println("id:" + bytes2int(idBytes));

            byte[] ageBytes = new byte[4];
            arrayInputStream.read(ageBytes);
            System.out.println("age:" + bytes2int(ageBytes));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 大端字节序列(先写高位,再写低位)
     * 低字节存在内存的高地址，高字节存在内存的低地址，即高位在前，低位在后
     *
     * @param i
     * @return
     */
    public static byte[] int2bytes(int i)
    {
        byte[] bytes = new byte[4];

        //右移三个字节 0是高位
       /* 1111 1111 1111 1111 1111 1111 1111 1101
        右移一位为1111 1111 1111 1111 1111 1111 1111 1110*/
        bytes[0] = (byte) (i >> 3 * 8);//获取高位字节
        bytes[1] = (byte) (i >> 2 * 8);
        bytes[2] = (byte) (i >> 1 * 8);
        bytes[3] = (byte) (i >> 0 * 8);
        return bytes;
    }

    /**
     * 大端
     *
     * @param bytes
     * @return
     */
    public static int bytes2int(byte[] bytes)
    {
        return (bytes[0] << 3 * 8) |
                (bytes[1] << 2 * 8) |
                (bytes[2] << 1 * 8) |
                (bytes[3] << 0 * 8);
    }
}
