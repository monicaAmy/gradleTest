package com.io.proto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class J2Bytes
{
    public static void main(String[] args)
    {
        try
        {
            byte[] bytes = toBytes();
            toPlayer(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 序列化
     *
     * @return
     * @throws IOException
     */
    public static byte[] toBytes() throws IOException
    {
        Player peter = new Player(101, 20, "peter");
        peter.getSkills().add(1001);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(peter);
        byte[] bytes = bos.toByteArray();
        System.out.println(Arrays.toString(bytes));
        return bytes;

    }

    public static void toPlayer(byte[] bs) throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Player player = (Player) ois.readObject();

        //打印
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name:" + player.getName());
        System.out.println("skills:" + (Arrays.toString(player.getSkills().toArray())));

    }

}
