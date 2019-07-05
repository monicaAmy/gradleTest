package com.io.proto;

import java.util.Arrays;

public class PB2Bytes
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
     */
    public static byte[] toBytes()
    {
        //获取构造器
        PlayerModule.PBPlayer.Builder builder = PlayerModule.PBPlayer.newBuilder();
        //设置数据
        builder.setPlayerId(101).setAge(20).setName("peter").addSkills(1001);
        //构造对象
        PlayerModule.PBPlayer pbplayer = builder.build();
        //序列化成字节数据
        byte[] bytes = pbplayer.toByteArray();
        System.out.println(Arrays.toString(bytes));

        return bytes;
    }

    /**
     * 反序列化
     *
     * @param bs
     */
    public static void toPlayer(byte[] bs) throws Exception
    {
        PlayerModule.PBPlayer player = PlayerModule.PBPlayer.parseFrom(bs);
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name:" + player.getName());
        System.out.println("skills:" + (Arrays.toString(player.getSkillsList().toArray())));
    }
}
