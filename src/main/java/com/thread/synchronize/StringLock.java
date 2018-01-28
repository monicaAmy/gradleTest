package com.thread.synchronize;

/**
 * String引起死循环
 * 解说str改变,线程释放锁
 */
public class StringLock
{
  private String str="11";
  private void method()
  {
    synchronized (str)//str 方法中修改str值
    {
      try
      {
        while(true)
        {
          System.out.println("当前线程"+Thread.currentThread().getName()+"start");
          Thread.sleep(1000);
          str="22";
          System.out.println("end thread:"+Thread.currentThread().getName());
        }
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args)
  {
    final StringLock stringLock=new StringLock();
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        stringLock.method();
      }
    },"t1");
    Thread thread2 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        stringLock.method();
      }
    },"t2");

    thread.start();
    thread2.start();

  }
}
