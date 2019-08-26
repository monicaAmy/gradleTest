package com.publishobj;

import com.annotation.ThreadSafe;

/**
 * 单例模式 , 饿汉
 */
@ThreadSafe
public class SingleExample03
{
    private static SingleExample03 singleExample = new SingleExample03();

    private SingleExample03()
    {

    }

    public static SingleExample03 getInstance()
    {
        return singleExample;
    }

}
