package com.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NieSu 2018/5/1
 */
public class CountDownLatchTest
{

  public static void main(String[] args)
  {
    //countDown两次之后才能唤醒await方法
    final CountDownLatch countDownLatch = new CountDownLatch(2);
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          System.out.println("t1 start init...");
          countDownLatch.await();
          System.out.println("t1 init ok...");
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }, "t1");

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          System.out.println("t2 start init...");
          Thread.sleep(3000);
          countDownLatch.countDown();
          System.out.println(countDownLatch.getCount());
          System.out.println("t2 init ok...");
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }, "t2");

    Thread thread2 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          System.out.println("t3 start init...");
          Thread.sleep(3000);
          //注释掉这一行t1无法执行
          countDownLatch.countDown();
          System.out.println(countDownLatch.getCount());
          System.out.println("t3 init OK...");
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }, "t3");

    ExecutorService service = Executors.newFixedThreadPool(3);
    service.execute(thread);
    service.execute(thread1);
    service.execute(thread2);
  }

}
