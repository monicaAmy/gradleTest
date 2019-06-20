package com.thread.lock;

import java.util.List;
import java.util.Vector;

/**
 * 偏向锁
 * 只叫一个线程获取锁
 */
public class UseBiasedLocking
{
    public static List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args) throws InterruptedException
    {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000)
        {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}

//使用偏向锁,效果更好
//-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0

//-XX:-UseBiasedLocking

