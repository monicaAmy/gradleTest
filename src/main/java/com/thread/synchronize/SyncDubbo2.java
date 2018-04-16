package com.thread.synchronize;

public class SyncDubbo2
{

  static class Sub extends Parent
  {

    public synchronized void submethod()
    {

      parentMethod();

    }
  }

  static class Parent
  {

    public synchronized void parentMethod()
    {
      System.out.println("线程安全的");
    }
  }

  public static void main(String[] args)
  {
    Thread thread=new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        new Sub().submethod();
      }
    });
    thread.start();
  }
}
