package com.thread.volatiletest;

/**
 * jvm 堆存放对象,动态分配内存大小,垃圾回收堆内存,所以堆比栈慢 栈,类对象属性,方法入口,参数
 *
 * 不同的线程有不同的线程工作内存, 不同线程工作内存指向同一块主内存 thread1和thread2在各自的工作内存中都保留一个object副本
 * thread1和thread2引用同一个变量,这个变量同步到主内存的时候,两个线程之间的指向的引用数据就一致 引用不同的变量,同步到主存,也是不同的变量,两个线程之间数据不一样
 *
 * ??? 不加volatile就不同步到主存了吗?
 *
 * 电脑一般有多个CPU寄存器(每个CPU都有自己的缓存-高速缓存),内存,磁盘 单核CPU CPU→cache→(bus总线)→主内存 多核,
 * CPU(L1I,L1D),CPU(L1I,L1D→L2 cache(多个)→ L3 cache →(多条bus总线)→主内存 总线上有嗅探机制,保证在主内存数据一致性
 * 但线程的执行是乱序的,不能保证线程执行结果 volatile保证有序性 堆和栈中数据可能保存在缓存中,也可能在主内存中
 *
 * java一次编译,到处执行 Java针对不同的系统有不同的编译器,编译成字节码, javac 之后生成jvm指令,jvm转成汇编指令,在不同的机器上执行
 *
 * TODO 设置工程的vm option生成汇编指令 NieSu 2019/1/18
 */
public class StopExample
{

  //boolean stop = false;
  //加上volatile,把stop放入到内存中,线程之间变量共享
  volatile boolean stop = false;

  /**
   * 运行之后,无法停机,线程之间变量没有共享 两个线程之间的各自持有自己的副本,副本数据没有写到主内存中,线程数据不共享 加上volatile把数据放到主内存中,两个线程都可以读取主内存中的数据
   */
  public static void main(String[] args) throws Exception
  {
    StopExample stopExample = new StopExample();
    new Thread(() ->
    {
      stopExample.doWork();
    }).start();

    //Thread.sleep(2000);
    new Thread(() ->
    {
      stopExample.shutDown();
    }).start();
  }

  public void shutDown()
  {
    stop = true;
  }

  public void doWork()
  {
    while (!stop)
    {

    }
    System.out.println("shut down success");
  }
}
