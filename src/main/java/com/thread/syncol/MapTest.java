package com.thread.syncol;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap
 * 对单个段加锁,不是对整个map对象,分为16段,最高支持16个线程并发
 * 代码中使用volatile声明,目的是第一时间获取修改的内容,性能比较好
 * CopyOnWrite
 * 复制副本修改,引用指向新容器,支持并发,不需要加锁,实现了读写分离,读多写少场景
 *
 *ConcurrentHashMap:线程安全的HashMap的实现
 *
 * CopyOnWriteArrayList:线程安全且在读操作时无锁的ArrayList,写操作先复制,在副本上操作,之后将原数据的引用指向新数据
 * 适合读多写少的场景,缺点耗内存,无法保证数据一致性
 * Vector对于读写操作均加锁同步，可以保证读和写的强一致性。
 *
 *
 * CopyOnWriteArraySet:基于CopyOnWriteArrayList 不添加重复元素
 *
 * ArrayBlockingQueue:基于数组 先进先出 线程安全 可实现指定时间的拥塞读写
 *
 * LinkedBlockingQueue:基于链表实现 读写各用一把锁 在高并发读写操作都多的情况下使用 性能优于ArrayBlockingQueue
 *
 *
 *
 * NieSu 2018/4/22
 */
public class MapTest
{

  public static void main(String[] args)
  {
    ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String,Object>(){
      {
        put("V1","vwww");
        putIfAbsent("V1","ddd");
        put("V2","fff");
      }
    };
    map.forEach((k,v) -> System.out.println(k+"="+v));

  }
}
