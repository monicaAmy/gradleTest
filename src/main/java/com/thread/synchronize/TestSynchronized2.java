package com.thread.synchronize;

import com.Util;
import org.junit.Test;

/**
 * 类锁
 */
public class TestSynchronized2
{
    public void test1()
    {
        synchronized (TestSynchronized2.class)
        {
            int i = 100;
            while (i-- > 0)
            {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    public static synchronized void test2()
    {
        int i = 100;
        while (i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }

    public static void main(String[] args)
    {
        // test1之后test2
        final TestSynchronized2 myt2 = new TestSynchronized2();
        Thread test1 = new Thread(new Runnable()
        {
            public void run()
            {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable()
        {
            public void run()
            {
                TestSynchronized2.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
    }

    @Test
    public void testj()
    {
        //test2 之后test1
        final TestSynchronized2 myt2 = new TestSynchronized2();
        Thread test1 = new Thread(new Runnable()
        {
            public void run()
            {
                myt2.test1();
            }
        }, "test1");

        final TestSynchronized2 myt = new TestSynchronized2();

        Thread test2 = new Thread(new Runnable()
        {
            public void run()
            {
                myt.test2();
            }
        }, "test2");
        test1.start();
        test2.start();

        //需要主线程休眠,test方法不会等其他线程执行完就结束了,其他线程就也会结束
        Util.sleep(80_000);
    }
}  