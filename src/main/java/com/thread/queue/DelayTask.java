package com.thread.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * NieSu 2018/5/1
 */
public class DelayTask implements Delayed
{

  public String name;
  public Long delayTime;
  public TimeUnit delayTimeUnit;
  public Long executeTime;//ms

  DelayTask(String name, long delayTime, TimeUnit delayTimeUnit) {
    this.name = name;
    this.delayTime = delayTime;
    this.delayTimeUnit = delayTimeUnit;
    //创建任务时候的当前时间与延时相加
    this.executeTime = System.currentTimeMillis() + delayTimeUnit.toMillis(delayTime);
  }

  @Override
  public long getDelay(TimeUnit unit) {
    //执行时间减去当前时间
    return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o)
  {
    if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
      return 1;
    }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
      return -1;
    }
    return 0;
  }
}
