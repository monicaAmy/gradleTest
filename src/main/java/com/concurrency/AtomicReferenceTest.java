package com.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceTest
{
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args)
    {
        //初始化0,期望是1 ,不相等,不更新为2
        count.compareAndSet(1, 2);
        //相等初始化为3
        count.compareAndSet(0, 3);
        count.compareAndSet(3, 4);
        log.info(count.get() + "==count");
    }
}
