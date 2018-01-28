package com.thread;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;

/**
 * JDK Executor 在java.util.concurrent中
 * newFixedThreadPool()创建固定数量的线程池,当有一个任务提交时,若线程池空闲,就立即执行,没有则缓存在一个任务队列中等待空闲线程去执行
 * newSingleThreadExecutor()创建一个线程的线程池,若空闲则执行,没有则缓存在任务队列
 * newCachedThreadPool()返回一个根据实际情况调整线程个数的线程池,不限制最大线程数量,有空闲线程则执行,没有不创建线程,并且每一个空闲线程会在60秒后自动回收
 * newScheduledThreadPool()返回一个SchededExecutorService对象,可以执行线程池线程数量
 *以上都是通过new ThreadPoolExecutor(int corePoolSize,
 *                                  int maximumPoolSize,
 *                                  long keepAliveTime,//线程池空闲时，线程存活的时间
 *                                  TimeUnit unit,//时间单位
 *                                  BlockingQueue<Runnable> workQueue,
 *                                  ThreadFactory threadFactory,
 *                                  RejectedExecutionHandler handler//线程拒绝策略
 *                                  ){...}
 * 自定义线程池:队列类型比较重要
 * 使用有界队列
 * 有新任务需要执行,线程池实际线程数小于corePoolSize则优先创建
 * 大于corePoolSize,则会将任务加入队列
 * 队列已满,总线程数不大于maximumPoolSize前提下,创建新的线程
 * >maximumPoolSize,则执行拒绝策略,或者其他自定义方式
 * 使用无界队列
 *JDK拒绝策略
 * 1、AbortPolicy策略直接抛出异常，阻止系统工作,默认
 * 2、CallerRunsPolicy策略
 * 只要线程池未关闭，该策略直接在调用者线程中运行当前被丢弃的任务。显然这样不会真的丢弃任务，但是，调用者线程性能可能急剧下降。
 * 3、DiscardOledestPolicy策略
 * 丢弃最老的一个请求任务，也就是丢弃一个即将被执行的任务，并尝试再次提交当前任务。
 * 4、DiscardPolicy策略 默默的丢弃无法处理的任务，不予任何处理。
 */

public class ThreadPoolTest {
    @Test
    public void fn(){
        Executors.newFixedThreadPool(10);
    }


    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1,
                2,
                60,
                 TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)
                //, new LinkedBlockingDeque<Runnable>()
                ,new MyRejected()
                //,new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        MyTask mt1=new MyTask(1,"任务一");
        MyTask mt2=new MyTask(2,"任务二");
        MyTask mt3=new MyTask(3,"任务三");
        MyTask mt4=new MyTask(4,"任务四");
        MyTask mt5=new MyTask(5,"任务五");
        MyTask mt6=new MyTask(6,"任务六");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

    }
}
class MyTask implements Runnable{
    private int id;
    private String name;
    @Override
    public void run(){
        System.out.println(getId());
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MyTask(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MyRejected implements RejectedExecutionHandler{
    public MyRejected() {
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //tips:一般用HttpClinet去发送请求,可以配置请求超时
        System.out.println("记录日志,非高峰期对日志解析并利用定时任务去处理...");
        MyTask myTask=(MyTask)r;
        System.out.println("当前被拒绝的任务为:"+myTask.getId());
    }
}

