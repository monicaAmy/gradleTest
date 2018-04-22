package com.thread;

/**
 * NieSu 2018/4/22 线程局部变量,是一种多线程间并发访问变量的解决方案,不提供锁 空间换时间,为每个线程的变量提供独立副本,保证线程安全
 * 并发量不高,加锁性能好,并发量高或者竞争激烈,使用ThreadLocal可以减少锁竞争
 */
public class ThreadLocalTest
{

  public static ThreadLocal<String> th = new ThreadLocal<String>();

  public static void main(String[] args) throws Exception
  {
    final ThreadLocalTest ct = new ThreadLocalTest();

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        ct.setTh("张三");
        ct.getTh();
      }
    }, "t1");

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        ct.setTh("hahah");
        ct.getTh();
      }
    }, "t2");

    thread.start();
    thread1.start();
  }

  public void setTh(String value)
  {
    th.set(value);
  }

  public void getTh()
  {
    System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
  }


}
