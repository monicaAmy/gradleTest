package com.thread.waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * wait/notify 都是object类方法,java所有类对象都提供了这个方法
 * 需要配合syncchronized使用
 * wait释放锁,notify不释放锁
 * NieSu 2018/4/16
 */
public class ListAdd1
{
  private volatile static List list=new ArrayList<>();

  public void add()
  {
      list.add("dfg");
  }

  public int size(){
    return list.size();
  }

  public static void main(String[] args)
  {
    final ListAdd1 wf=new ListAdd1();
    Thread t1=new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          for (int i = 0; i < 10; i++)
          {
            wf.add();
            System.out.println("当前线程: "+Thread.currentThread().getName()+" add "+i);
            Thread.sleep(500);
          }
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    },"t1");

    Thread t2 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        while (true)
        {
          if (list.size() == 5)
          {
            System.out.println("收到线程通知: " + Thread.currentThread().getName() + " 线程停止");
            throw new RuntimeException();
          }
        }
      }
    }, "t2");

    t1.start();
    t2.start();
  }

}
