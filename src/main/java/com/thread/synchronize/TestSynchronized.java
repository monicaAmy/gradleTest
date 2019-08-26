package com.thread.synchronize;

import org.junit.Test;

/**
 * 对象锁,与调用者无关,与方法本身是类锁还是对象锁有关
 * 被同一种锁修饰的方法不能同时执行
 */
public class TestSynchronized
{    
    public void test1()   
    {    
         synchronized(this)   
         {    
              int i = 100;
              while( i-- > 0)   
              {    
                   System.out.println(Thread.currentThread().getName() + " : " + i);    
                   try   
                   {    
                        Thread.sleep(500);    
                   }   
                   catch (InterruptedException ie)   
                   {    
                   }    
              }    
         }    
    }    
      
    public synchronized void test2()   
    {    
         int i = 100;
         while( i-- > 0)   
         {    
              System.out.println(Thread.currentThread().getName() + " : " + i);    
              try   
              {    
                   Thread.sleep(500);    
              }   
              catch (InterruptedException ie)   
              {    
              }    
         }    
    }    
      
    public static void main(String[] args)
    {
        //先执行test1,在执行test2
         final TestSynchronized myt2 = new TestSynchronized();
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
                myt2.test2();
            }
        }, "test2");
         test1.start();;    
         test2.start();
    }

    @Test
    public void testj()
    {
        //交叉执行
        final TestSynchronized myt2 = new TestSynchronized();
        final TestSynchronized myt = new TestSynchronized();
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
                myt.test2();
            }
        }, "test2");
        test1.start();
        ;
        test2.start();
    }
}  