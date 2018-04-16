package com.thread.waitandnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实时通知执行另一线程
 * NieSu 2018/4/16
 */
public class ListAdd3
{

  private volatile static List list = new ArrayList<>();

  public static void main(String[] args)
  {
    final ListAdd3 wf = new ListAdd3();
    //1 有一次通知
    //2  必须有两次通知才执行
    final CountDownLatch countDownLatch = new CountDownLatch(1);
    Thread t1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          for (int i = 0; i < 10; i++)
          {
            wf.add();
            System.out.println("当前线程: " + Thread.currentThread().getName() + " add " + i);
            Thread.sleep(500);
            if (wf.size() == 5)
            {
              System.out.println("t1 发出通知");
              //唤醒之后不释放锁,t2需要等待t1执行完,才能获取锁
              countDownLatch.countDown();
            }
          }
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }, "t1");

    Thread t2 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          System.out.println("wait...");
          //线程等待唤醒才执行,释放锁
          countDownLatch.await();
          System.out.println("ddddd " + list.size());
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }

        System.out.println("收到线程通知: " + Thread.currentThread().getName() + " 线程停止 " + list.size());

        try
        {
          Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }

        throw new RuntimeException();
      }

    }, "t2");

    t2.start();
    t1.start();
  }

  public void add()
  {
    list.add("dfg");
  }

  public int size()
  {
    return list.size();
  }
}
