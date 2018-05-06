package com.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * NieSu 2018/5/6
 */
public class UseSemaphore
{

  public static void main(String[] args)
  {
    ExecutorService executorService = Executors.newCachedThreadPool();
    //每次只接受五个,超过五个任务需要等到释放的时候在执行
    final Semaphore semaphore = new Semaphore(5);

    for (int i = 0; i < 20; i++)
    {
      final int NO = i;
      Runnable runnable = new Runnable()
      {
        @Override
        public void run()
        {
          try
          {
            semaphore.acquire();
            System.out.println("accessing " + NO);
            Thread.sleep((long) Math.random() * 1000);
            semaphore.release();
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }
        }
      };

      executorService.execute(runnable);
    }

    executorService.shutdown();
  }

}
