package com.thread;

/**
 * 对象锁
 */
public class TestSynchronized1
{

  /**
   * 一个方法释放掉锁,另一个才执行
   * @param args
   */
  public static void main(String[] args)
  {
    final TestSynchronized1 myt2 = new TestSynchronized1();
    Thread thread1 = new Thread(new Runnable()
    {
      public void run()
      {
        myt2.test1();
      }
    }, "test1");
    Thread thread2 = new Thread(new Runnable()
    {
      public void run()
      {
        myt2.test2();
      }
    }, "test2");

      thread1.start();
      thread2.start();

  }

  public synchronized void test1()
  {
    int i = 100;
    while (i-- > 0)
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

  public synchronized void test2()
  {
    int i = 100;
    while (i-- > 0)
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