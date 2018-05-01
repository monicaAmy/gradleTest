package com.thread.master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 既然Worker是具体的执行任务，那么Worker一定要实现Runnable接口
 * Worker对象需要能从Master接收任务，它也得有Master ConcurrentLinkedQueue容器的引用
 */
public class Worker implements Runnable
{

  private ConcurrentLinkedQueue<Task> taskQueue;
  private ConcurrentHashMap<String, Object> resultMap;

  public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue)
  {
    this.taskQueue = taskQueue;
  }

  public void setResultMap(ConcurrentHashMap<String, Object> resultMap)
  {
    this.resultMap = resultMap;
  }

  @Override
  public void run()
  {
    while (true)
    {
      Task executeTask = this.taskQueue.poll();
      if (executeTask == null)
      {
        break;
      }
      //真正的任务处理
      Object result = handle(executeTask);
      this.resultMap.put(executeTask.getName(), result);
    }
  }

  //核心处理逻辑，可以抽离出来由具体子类实现
  private Object handle(Task executeTask)
  {
    Object result = null;
    try
    {
      //表示处理任务的耗时....
      Thread.sleep(500);
      result = executeTask.getPrice();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return result;
  }
}