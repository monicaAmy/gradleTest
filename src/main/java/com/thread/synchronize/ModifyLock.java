package com.thread.synchronize;

/**
 * 类属性的改变不影响类锁
 */
public class ModifyLock
{
  private String name;
  private int age;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public synchronized void changeAttribute(String name,int age)
  {
    while(true)
    {
      System.out.println("thread is"+Thread.currentThread().getName());
      this.name=name;
      this.age=age;
      System.out.println("thread is"+Thread.currentThread().getName());
    }
  }

  public static void main(String[] args)
  {
    final ModifyLock stringLock=new ModifyLock();
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        stringLock.changeAttribute("sss",11);
      }
    },"t1");
    Thread thread2 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        stringLock.changeAttribute("ccc",222);
      }
    },"t2");

    thread.start();
    thread2.start();
  }
}
