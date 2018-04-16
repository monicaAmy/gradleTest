package com.thread.volatiletest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * volatile实现线程变量共享
 * NieSu 2018/4/16
 */
public class Runthread extends Thread
{

  /**
   * 加上,主线程变量改变,子线程也变
   * 不加,主线程改变,不影响子线程,子线程会一直执行 ????
   */
  private   boolean isRunning=true;

  public void setIsRunning(boolean isRunning)
  {
    this.isRunning = isRunning;
  }

  @Override
  public void run()
  {
    System.out.println("进去 run 方法");
    while (isRunning==true)
    {
      // 既然中国在东八区，则要偏移8个小时，这样子获取到的时间才是自己电脑的时间
      System.out.println("true....."+ LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
    System.out.println("线程停止");
  }

  public static void main(String[] args)
  {
    try
    {
      Runthread runthread = new Runthread();
      runthread.start();
      Thread.sleep(3000);
      runthread.setIsRunning(false);
      System.out.println("isRunning set false");
      Thread.sleep(3000);
      System.out.println(runthread.isRunning);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
