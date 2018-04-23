package com.thread.syncol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 并发类容器
 * NieSu 2018/4/22
 */
public class SynColTest
{

  public static void main(String[] args)
  {
    final Vector<String> strs = new Vector<>();
    Map<String, String> map  = Collections.synchronizedMap(new HashMap<String, String>());
    for (int i = 0; i < 1000; i++)
    {
      strs.add("火车票" + 1);
      //strs.add(i);
    }
  }

}
