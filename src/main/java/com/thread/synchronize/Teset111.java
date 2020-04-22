package com.thread.synchronize;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 对象锁	类锁
 * a1	b1		×
 * a1	a1	×	×
 * a1	b2		×
 * a1	a2	×	×
 * <p>
 * ×代表不能同时执行,需要等待锁
 * 对象锁
 */
public class Teset111
{

    /**
     * 可以同时运行
     *
     * @param args
     */
    /*public static void main(String[] args)
    {
        ObjectLock12 a = new ObjectLock12();
        ObjectLock12 b = new ObjectLock12();
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
*/

    /**
     * 不同时进行,等待
     * @param args
     */
   /* public static void main(String[] args)
    {
        ObjectLock12 a = new ObjectLock12();
        ObjectLock12 b = new ObjectLock12();
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
    }*/

    /**
     * 同时
     * @param args
     */
   /* public static void main(String[] args)
    {
        ObjectLock12 a = new ObjectLock12();
        ObjectLock12 b = new ObjectLock12();
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
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ObjectLock12 a = new ObjectLock12();
        ObjectLock12 b = new ObjectLock12();
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
    }
}

class ObjectLock12
{

    /**
     * 对象锁
     */
    public void method1()
    {
        synchronized (this)
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
    }

    public void method2()
    {
        synchronized (this)
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
}