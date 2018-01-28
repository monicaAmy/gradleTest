package com.thread;

public class SyncDubbo
{
  private synchronized void method(){
    System.out.println("1");
    method2();
  }
  private synchronized void method2(){
    System.out.println("2");
    method3();
  }
  private synchronized void method3(){
    System.out.println("3");
  }

  public static void main(String[] args)
  {
    Thread thread=new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        new SyncDubbo().method();
      }
    });
    thread.start();
  }


}
