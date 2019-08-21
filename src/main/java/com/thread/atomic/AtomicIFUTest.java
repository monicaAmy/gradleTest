package com.thread.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
public class AtomicIFUTest
{
    private static AtomicIntegerFieldUpdater<AtomicIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIFUTest.class, "count");

    // volatile 保证线程可见,不保证原子性(多个线程执行加1,结果可能还是1)
    @Getter
    public volatile int count = 100;

    public static void main(String[] args)
    {
        AtomicIFUTest atomicIFUTest = new AtomicIFUTest();

        if (updater.compareAndSet(atomicIFUTest, 100, 200))
        {
            log.info("update success 1, {}", atomicIFUTest.getCount());

        }

        if (updater.compareAndSet(atomicIFUTest, 100, 120))
        {
            log.info("update success 2, {}", atomicIFUTest.getCount());
        }
        else
        {
            log.info("update failed, {}", atomicIFUTest.getCount());
        }

    }
}
