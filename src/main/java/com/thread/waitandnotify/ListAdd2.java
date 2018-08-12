package com.thread.waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知之后程序执行完,在执行另一个线程
 * NieSu 2018/4/16
 */
public class ListAdd2
{

  private volatile static List list = new ArrayList<>();

  public static void main(String[] args)
  {
    final ListAdd2 wf = new ListAdd2();

    final Object lock = new Object();
    Thread t1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {

        try
        {
          synchronized (lock)
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
                lock.notify();
              }
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
        synchronized (lock)
        {
          try
          {
            System.out.println("wait...");
            //线程等待唤醒才执行,释放锁
            lock.wait();
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

//          throw new RuntimeException();
        }
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
