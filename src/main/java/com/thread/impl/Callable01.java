package com.thread.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * NieSu 2019/1/15
 */
public class Callable01 implements Callable<String>
{

  @Override
  public String call() throws Exception
  {
    return "线程返回值";
  }

  /**
   * FutureTask --RunnableFuture--Runnable 其实也是一个线程 实现run方法, run里面调用了call方法
   */
  public static void main(String[] args) throws Exception
  {
    System.out.println(new Callable01().call());
    System.out.println("---------------------------------------------");
    FutureTask<String> task = new FutureTask<>(new Callable01());
    new Thread(task).start();
    //取消会报错
    //task.cancel(true);
    System.out.println(task.get());
  }
}
