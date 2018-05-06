package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *     Lock lock = new ReentrantLock(boolean isFair);
 *     tryLock(); return boolean尝试获取锁
 *     isFair();是否公平
 *     isLocked();
 *     getHoldCount();查看当前线程保持锁的个数,=调用lock数目  ????
 *     lockInterruptibly();优先响应中断的锁
 *     getQueueLength();返回等待获取锁的线程数
 *     getWaitQueueLength()返回等待与锁定相关给定条件Condition的线程数
 *     hasQueuedThread(Thread thread);查询指定线程是否等待此锁
 *     hasQueuedThreads()查询是否有线程等待此锁
 *     hasWaiters() 查询是否有线程正在等待与此锁相关的Condition条件
 */
public class UseReentrantLock
{
  //读多写少
  private Lock lock = new ReentrantLock();

  public static void main(String[] args)
  {

    final UseReentrantLock ur = new UseReentrantLock();
    Thread t1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        ur.method1();
        ur.method2();
      }
    }, "t1");

    t1.start();
    try
    {
      Thread.sleep(10);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    //System.out.println(ur.lock.getQueueLength());
  }

  public void method1()
  {
    try
    {
      lock.lock();
      System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method1..");
      Thread.sleep(1000);
      System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method1..");
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    finally
    {

      lock.unlock();
    }
  }

  public void method2()
  {
    try
    {
      lock.lock();
      System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
      Thread.sleep(2000);
      System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    finally
    {

      lock.unlock();
    }
  }


}
