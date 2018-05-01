package com.thread.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * NieSu 2018/5/1
 */
public class DelayTest
{
  public static void main(String[] args) {
    DelayQueue<DelayTask> queue = new DelayQueue<>();
    queue.add(new DelayTask("1", 2L, TimeUnit.SECONDS));
    queue.add(new DelayTask("2", 3L, TimeUnit.SECONDS));
    queue.add(new DelayTask("3", 1L, TimeUnit.SECONDS));

    System.out.println("queue put done");

    while(!queue.isEmpty()) {
      try {
        DelayTask task = queue.take();
        System.out.println(task.name + ":" + System.currentTimeMillis());

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
