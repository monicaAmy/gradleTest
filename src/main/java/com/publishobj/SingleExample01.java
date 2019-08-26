package com.publishobj;

import com.annotation.NotRecommend;
import com.annotation.ThreadSafe;

/**
 * 加锁,影响性能不推荐
 */
@ThreadSafe
@NotRecommend
public class SingleExample01
{
    private SingleExample01()
    {

    }

    private static SingleExample01 singleExample = null;

    public static synchronized SingleExample01 getInstance()
    {
        if (singleExample == null)
        {
            singleExample = new SingleExample01();
        }
        return singleExample;
    }
}
