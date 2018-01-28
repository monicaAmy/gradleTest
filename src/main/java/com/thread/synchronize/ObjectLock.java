package com.thread.synchronize;

/**
 * 锁的几种形式
 *
 */
public class ObjectLock
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
        System.out.println("do method1");
        Thread.sleep(2000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * 类锁
   */
  public void method2()
  {
    synchronized (ObjectLock.class)
    {
      try
      {
        System.out.println("do method1");
        Thread.sleep(2000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * 任何对象锁
   */
  private Object lock=new Object();
  public void method3()
  {
    synchronized (lock)
    {
      try
      {
        System.out.println("do method1");
        Thread.sleep(2000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }



}
