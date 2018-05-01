package com.thread.lock;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

/**
 * NieSu 2018/5/1
 */
public class CyclicBarrierTest
{

  @Test
  public void fn()
  {
    try
    {
      SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
      int i = sha1PRNG.nextInt(10);
      System.out.println(i);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
  }

  static class Runner implements Runnable
  {

    private CyclicBarrier barrier;
    private String name;

    public Runner(CyclicBarrier barrier, String name)
    {
      this.barrier = barrier;
      this.name = name;
    }

    @Override
    public void run()
    {

      try
      {
        Thread.sleep(1000 * (new Random()).nextInt(5));
        barrier.await();
        System.out.println(name + " OK");
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }catch (BrokenBarrierException e)
      {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void main(String[] args)
  {
    //有4个 线程同时等待才会4个同时执行,有3个线程等待,3个都不会执行
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
    //submit可以传入实现callable接口的实例对象,有返回值
    //execute无返回值
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.submit(new Thread(new Runner(cyclicBarrier,"t1")));
    executorService.submit(new Thread(new Runner(cyclicBarrier,"t2")));
    executorService.submit(new Thread(new Runner(cyclicBarrier,"t3")));

  }
}
