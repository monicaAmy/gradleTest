package com.thread.single;

/**
 * NieSu 2018/4/22 单例在多线程中的最佳实践
 */
public class DubbleTest
{

  private static DubbleTest ds;

  public static DubbleTest getDs()
  {
    if (ds == null)
    {
      try
      {
        Thread.sleep(3000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      synchronized (DubbleTest.class)
      {
        if (ds == null)
        {
          ds = new DubbleTest();
        }
      }
    }
    return ds;
  }

  public static void main(String[] args)
  {
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        System.out.println(DubbleTest.getDs());
        System.out.println(DubbleTest.getDs().hashCode());
      }
    });

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        System.out.println(DubbleTest.getDs());
        System.out.println(DubbleTest.getDs().hashCode());
      }
    });

    thread.start();
    thread1.start();
  }
}
