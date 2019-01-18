package com.thread.impl;

/**
 * Thread 里面有public enum State包含线程的各种状态 NieSu 2019/1/15
 */
public class Thread01 extends Thread
{

  @Override
  public void run()
  {
    System.out.println("线程执行了...");
  }

  public static void main(String[] args)
  {
    //不能执行线程
    new Thread().run();
    System.out.println("--------------------");
    new Thread01().start();
  }
}
