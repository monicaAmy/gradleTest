package com.thread.syncol;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap
 * 对单个段加锁,不是对整个map对象,分为16段,最高支持16个线程并发
 * 代码中使用volatile声明,目的是第一时间获取修改的内容,性能比较好
 * CopyOnWrite
 * 复制副本修改,引用指向新容器,支持并发,不需要加锁,实现了读写分离,读多写少场景
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
