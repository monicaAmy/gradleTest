package com.thread.synchronize;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 对象锁添加方式
 * synchronized（SyncTest.class）{
 * 代码。。。。。
 * }
 */
public class Test222
{
    /**
     * 不可以同时运行
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ObjectLock122 a = new ObjectLock122();
        ObjectLock122 b = new ObjectLock122();
        Thread t1 = new Thread(() ->
        {
            a.method1();
        });
        Thread t2 = new Thread(() ->
        {
            b.method1();
        });
        t1.start();
        t2.start();
    }

    /**
     * 不同时进行,等待
     * @param args
     */
    /*public static void main(String[] args)
    {
        ObjectLock122 a = new ObjectLock122();
        ObjectLock122 b = new ObjectLock122();
        Thread t1 = new Thread(() ->
        {
            a.method1();
        });
        Thread t2 = new Thread(() ->
        {
            a.method1();
        });
        t1.start();
        t2.start();
    }
*/
    /**
     * 不同时
     * @param args
     */
   /* public static void main(String[] args)
    {
        ObjectLock122 a = new ObjectLock122();
        ObjectLock122 b = new ObjectLock122();
        Thread t1 = new Thread(() ->
        {
            a.method1();
        });
        Thread t2 = new Thread(() ->
        {
            b.method2();
        });
        t1.start();
        t2.start();
    }*/

    /**
     * 不同时
     */
   /* public static void main(String[] args)
    {
        ObjectLock122 a = new ObjectLock122();
        ObjectLock122 b = new ObjectLock122();
        Thread t1 = new Thread(() ->
        {
            a.method1();
        });
        Thread t2 = new Thread(() ->
        {
            a.method2();
        });
        t1.start();
        t2.start();
    }*/
}

class ObjectLock122
{

    /**
     * 对象锁
     */
    public synchronized static void method1()
    {
        try
        {
            System.out.println("do method1" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized static void method2()
    {
        try
        {
            System.out.println("do method2" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}