package com.thread.disruptor.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * 无锁的情况下实现queue并发,异步处理框架,
 *
 * ProducerType.SINGLE 有单个生产者
 *
 * //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
 * WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
 * //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
 * WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
 * //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
 * WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
 *
 * ringbuffer 数组
 * 它是一个环（首尾相接的环），你可以把它用做在不同上下文（线程）间传递数据的buffer。
 * 基本来说，ringbuffer拥有一个序号，这个序号指向数组中下一个可用元素
 * Disruptor说的是生产者和消费者的故事.  有一个数组.生产者往里面扔芝麻.消费者从里面捡芝麻.  但是扔芝麻和捡芝麻也要考虑速度的问题.  1 消费者捡的比扔的快 那么消费者要停下来.生产者扔了新的芝麻,然后消费者继续.  2 数组的长度是有限的,生产者到末尾的时候会再从数组的开始位置继续.这个时候可能会追上消费者,消费者还没从那个地方捡走芝麻,这个时候生产者要等待消费者捡走芝麻,然后继续
 * 找到数组中当前序号指向的元素，以上面的ringbuffer为例（java的mod语法）：12 % ringbuffer槽个数 = 2。很简单吧。
 * 如果槽的个数是2的N次方更有利于基于二进制的计算机进行计算。
 * 不删除数据,覆盖数据

 */
public class LongEventMain
{

  public static void main(String[] args) throws Exception
  {
    //创建缓冲池
    ExecutorService executor = Executors.newCachedThreadPool();
    //创建工厂
    LongEventFactory factory = new LongEventFactory();
    //创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
    int ringBufferSize = 1024 * 1024; //



    //创建disruptor
    Disruptor<LongEvent> disruptor =
        new Disruptor<LongEvent>(factory, ringBufferSize, executor, ProducerType.SINGLE,
            new YieldingWaitStrategy());
    // 连接消费事件方法
    disruptor.handleEventsWith(new LongEventHandler());

    // 启动
    disruptor.start();

    //Disruptor 的事件发布过程是一个两阶段提交的过程：
    //发布事件
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    LongEventProducer producer = new LongEventProducer(ringBuffer);
    //LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
    ByteBuffer byteBuffer = ByteBuffer.allocate(8);//分配8个字节缓冲
    for (long l = 0; l < 100; l++)
    {
      byteBuffer.putLong(0, l);
      System.out.println("调用发布者...");
      producer.onData(byteBuffer);
      //Thread.sleep(1000);
    }

    disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；

  }

}
