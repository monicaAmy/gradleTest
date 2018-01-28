package com.thread;

/**
 * clientA查询数据为100的数需10分钟,clientB修改为200需5min
 * A会读到100,数据的一致性
 * 修改在oracle中会将数据100存在undo里面,client修改,可能200覆盖100,这时候会报错
 */
public class DirtyRead
{

  private String username = "bjsx";
  private String password = "123";

  /**
   * 主线程和t1同时执行产生脏读
   * set get方法加同一种锁可以解决脏读
   * @param args
   * @throws Exception
   */
  public static void main(String[] args)throws Exception
  {
    final DirtyRead dr = new DirtyRead();
    Thread t1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        dr.setValue("aaaaa","ddddd");
      }
    });
    t1.start();
    Thread.sleep(1000L);
    dr.getValue();

  }

  public synchronized void setValue(String username, String password)
  {
    this.username = username;
    try
    {
      Thread.sleep(2000L);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    this.password = password;
  }

  public  void getValue()
  {
    System.out.println("username=" + username + "\n password=" + password);
  }

}
