package com.thread.volatiletest;

/**
 * NieSu 2019/1/18
 */
public class StopExample2
{

  //不用static,可以一直启动,使用static可以启动几次
  //volatile不能保证原子性
  volatile boolean isStart = false;
  //加不加volatile都执行一次
  // boolean isStart = false;


  public static void main(String[] args) throws Exception
  {
    StopExample2 stopExample2 = new StopExample2();

    for (int i = 0; i < 100; i++)
    {
      new Thread(() ->
      {
        //放在里面也是执行多次成功,
        // 放在里面,两个线程执行有各自的内存副本,主内存里面数据也没有引用同一个对象的值,
        //只有两个线程中数据引用主内存中同一个对象的时候,volatile才有作用
        //  StopExample2 stopExample2 = new StopExample2();
        stopExample2.start();
      }).start();

    }
  }

  /**
   * 加synchronized保证原子性, 只用volatile,多个线程同时执行的时候,还是可以成功几次,之后的线程共享内存之后,就无法执行了
   */
  public synchronized void start()
  {
    if (isStart)
    {
      throw new RuntimeException("已经启动过了");
    }
    System.out.println("启动成功");
    isStart = true;
  }

}
