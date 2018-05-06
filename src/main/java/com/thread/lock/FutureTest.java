package com.thread.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * NieSu 2018/5/1
 */
public class FutureTest implements Callable<String>
{

  private String para;

  public FutureTest(String para)
  {
    this.para = para;
  }

  @Override
  public String call() throws Exception
  {
    Thread.sleep(3000);
    String result=para+" OK";
    return result;
  }

  public static void main(String[] args)
  {
    try
    {
      String queryStr="query";
      FutureTask<String> futureTask = new FutureTask<>(new FutureTest(queryStr));
      ExecutorService executorService = Executors.newFixedThreadPool(1);
      Future<?> f = executorService.submit(futureTask);

      //Thread.sleep(1000);
      System.out.println(futureTask.get());
      //任务处理完,get会返回null
      System.out.println(f.get());

      System.out.println("2222222222222");
      executorService.shutdown();
      System.out.println("3333333333333");
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    catch (ExecutionException e)
    {
      e.printStackTrace();
    }
  }
}
