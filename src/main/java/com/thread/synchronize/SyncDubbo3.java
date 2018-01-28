package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异常的时候释放锁
 */
public class SyncDubbo3
{
  private AtomicInteger i=new AtomicInteger(0);

  public synchronized void operation(){
    while(true)
    {
      try
      {
        int ii = i.addAndGet(1);
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName()+",i="+ ii);
        if(ii==10)
        {
          Integer.parseInt("a");
        }
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
        System.out.println(i.intValue()+"------------");
      }
    }
  }

  public static void main(String[] args)
  {
    final SyncDubbo3 sd=new SyncDubbo3();
    Thread thread=new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        sd.operation();
      }
    });
    thread.start();
    Thread thread2=new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        sd.operation();
      }
    });
    thread2.start();
  }

}
