package com.dp.adapter.objectadapter;

/**
 * 实现统一接口,方便参数传递
 */
public class V220 implements IV5
{
    @Override
    public int output()
    {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
        return 220;
    }
}
