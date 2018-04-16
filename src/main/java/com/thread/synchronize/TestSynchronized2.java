package com.thread.synchronize;

/**
 * 类锁
 */
public class TestSynchronized2
{
  public void test1()
  {
    synchronized(TestSynchronized2.class)
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

  public static synchronized void test2()
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
    final TestSynchronized2 myt2 = new TestSynchronized2();
    Thread test1 = new Thread(  new Runnable() {  public void run() {  myt2.test1();  }  }, "test1"  );
    Thread test2 = new Thread(  new Runnable() {  public void run() { TestSynchronized2.test2();   }  }, "test2"  );
    test1.start();
    test2.start();
  }

}  