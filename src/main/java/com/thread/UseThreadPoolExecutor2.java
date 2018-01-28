package com.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * 使用无界队列
 * 除非系统资源耗尽,否则无界的任务队列不存在任务入队失败
 * 当有新任务加入,没有空闲线程资源,任务直接进入队列等待
 * 若任务创建和处理速度差异很大,无界队列则会保持快速增长,直到耗尽系统内存
 *
 **/
public class UseThreadPoolExecutor2 implements Runnable {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10, //没有作用
                120L,
                TimeUnit.SECONDS,
                queue);
        for (int i = 0; i < 20; i++) {
            pool.execute(new UseThreadPoolExecutor2());
        }
        try {
            Thread.sleep(1000);
            System.out.println("queue size:" + queue.size());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("任务" + count.incrementAndGet());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
