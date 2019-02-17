package com.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * NieSu 2019/2/17
 */
public class LinkedListTest
{

  @Test
  public void fn()
  {
    LinkedList<Object> objects = new LinkedList<Object>();
    objects.add(0, 1);
    objects.add(1, 2);
    objects.add(1, 3);

    // 1 3 2
    System.out.println(objects);

    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(0, 1);
    arrayList.add(1, 2);
    arrayList.add(1, 3);

    // 1 3 2
    System.out.println(arrayList);

    List<Object> list = new ArrayList<>();
    list.add(0, 1);
    list.add(1, 2);
    list.add(1, 3);
    list.add(2, 22);
    list.remove(1);

    // 1  22 2
    System.out.println(list);
  }
}
