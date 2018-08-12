package com.ztest;

/**
 * 交叉打印12a34b56c....
 *
 * thread1执行完,没有线程去唤醒thread的等待,thread无法打印
 * NieSu 2018/8/5
 */
public class PrintNumAndChar1
{

  public static void main(String[] args)
  {
    final Object o = new Object();

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        //打印数字
        synchronized (o){
          for (int i = 0; i < 100; i++)
          {
            System.out.println(i);
            o.notify();
            if(i%2==0)
            {
              try
              {
                o.wait();
              }
              catch (InterruptedException e)
              {
                e.printStackTrace();
              }
            }
          }

        }
      }
    });

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        synchronized (o)
        {
          char[] chars = {'a', 'b', 'c','e','f','g'};
          for (int i = 0; i < 6; i++)
          {
            System.out.println(chars[i]);
            o.notify();
            try
            {
              o.wait();
            }
            catch (InterruptedException e)
            {
              e.printStackTrace();
            }
          }

        }
      }
    });

    thread.start();
    thread1.start();

  }
}
