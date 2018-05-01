package com.thread.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import org.junit.Test;

/**
 * NieSu 2018/5/1
 * 先take,在add才不会报queue full, add直接给take
 */
public class SqTest
{

  public static void main(String[] args)
  {
    SynchronousQueue synchronousQueue = new SynchronousQueue();
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          System.out.println(synchronousQueue.take());
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }

      }
    }, "t1");

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        synchronousQueue.add("ddddddddddddd");
      }
    }, "t2");

    ExecutorService executorService = Executors.newFixedThreadPool(5);
    //先take后add不会报错, 先add后take报错
    executorService.execute(thread);
    executorService.execute(thread1);
  }

  @Test
  public void fn(){
    try
    {
      SynchronousQueue synchronousQueue = new SynchronousQueue();
      //拿不到元素会一直等待
      System.out.println(synchronousQueue.take());
      synchronousQueue.add("55555");
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
