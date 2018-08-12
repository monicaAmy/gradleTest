package com.ztest;

/**
 * NieSu 2018/8/5
 *
 *  执行顺序T321
 */
public class T123
{

  public static void main(String[] args) throws Exception
  {
    Thread3 thread3 = new Thread3();
    Thread2 thread2 = new Thread2(thread3);
    Thread1 thread1 = new Thread1(thread2);

    /**
     * 使用run的话,会按照固定顺序执行 ,start才是由jvm来调度
     */
    thread3.start();
    thread2.start();
    thread1.start();

  }
}

class Thread1 extends Thread
{

  private Thread2 thread2;

  public Thread1(Thread2 thread2)
  {
    this.thread2 = thread2;
  }

  @Override
  public void run()
  {
    try
    {
      thread2.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    System.out.println("T1");
  }
}

class Thread2 extends Thread
{

  private Thread3 thread3;

  public Thread2(Thread3 thread3)
  {
    this.thread3 = thread3;
  }

  @Override
  public void run()
  {
    try
    {
      thread3.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    System.out.println("T2");
  }
}


class Thread3 extends Thread
{

  @Override
  public void run()
  {
    System.out.println("T3");
  }
}
