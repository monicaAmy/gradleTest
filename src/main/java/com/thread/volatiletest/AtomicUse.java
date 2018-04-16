package com.thread.volatiletest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NieSu 2018/4/16
 */
public class AtomicUse extends Thread
{
  private static AtomicInteger atomicInteger=new AtomicInteger(0);

  public int multiAdd()
  {
    //多次执行方法,不能保证原子性
    atomicInteger.addAndGet(1);
    atomicInteger.addAndGet(2);
    atomicInteger.addAndGet(3);
    return  atomicInteger.get();
  }

  public static void main(String[] args)
  {
    final AtomicUse atomicUse = new AtomicUse();
    List<Thread> threads = new ArrayList<Thread>(){
      {
        for (int i = 0; i < 100; i++)
        {
          add( new Thread(() -> System.out.println(atomicUse.multiAdd())) );
        }
      }
    };

    threads.forEach(x->x.start());
  }
}
