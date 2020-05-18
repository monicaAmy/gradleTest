package com.dp.adapter.objectadapter;

public class V240 implements IV5
{
    @Override
    public int output()
    {
        System.out.println(Thread.currentThread().getStackTrace()[1].getFileName());
        return 240;
    }
}
