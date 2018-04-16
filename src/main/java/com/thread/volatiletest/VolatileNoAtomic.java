package com.thread.volatiletest;

/**
 * volatile 线程之间可见,但是不具备原子性,netty底层大量使用volatile
 * 实现原子性要使用atomic类对象
 * atomic类对象只保证本身方法的原子性,不保证多次操作的原子性,例子:atomicUse
 * NieSu 2018/4/16
 */
public class VolatileNoAtomic extends Thread
{

  /**
   * volatile 不具备原子性,三个线程同时执行,都加一,同步之后结果还是1,可能最后一次结果不是10000
   * 加不加结果都一样
   */
  private static volatile int count;

  /**
   * 具备原子性,最后一次打印都是10000
   */
  //private static AtomicInteger count=new AtomicInteger(0);

  private void addCount()
  {
    for (int i = 0; i < 10; i++)
    {
      count++;
      System.out.println(getNameStr()+": count now is "+count);
      //count.incrementAndGet();
    }
    System.out.println(count);
  }

  private  String nameStr;

  public String getNameStr()
  {
    return nameStr;
  }

  public void setNameStr(String nameStr)
  {
    this.nameStr = nameStr;
  }

  @Override
  public void run()
  {
    addCount();
  }

  public static void main(String[] args)
  {
    try
    {
      VolatileNoAtomic[] volatileNoAtomics = new VolatileNoAtomic[10];
      for (int i = 0; i < 3; i++)
      {
        volatileNoAtomics[i]=new VolatileNoAtomic();
        volatileNoAtomics[i].setNameStr(i+"");
      }
      for (int i = 0; i < 3; i++)
      {
        volatileNoAtomics[i].start();
      }
      Thread.sleep(3000);
      System.out.println("main:"+count);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
