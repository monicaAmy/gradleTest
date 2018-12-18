package com.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Java虚拟机工作原理 - best.lei - 博客园.html 讲jvm很清晰
 */
public class Client
{

  @Test
  public void fn()
  {
    //MyClassLoader的父类加载器为系统默认的加载器AppClassLoader
    MyClassLoader myCLoader = new MyClassLoader("MyClassLoader");
    //指定MyClassLoader的父类加载器为ExtClassLoader
    //MyClassLoader myCLoader = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent(),"MyClassLoader");
    //不能使用当前工程的class会报类未定义异常,因为读取的同时还在编译
    myCLoader.setPath("G:\\code\\cookieTest\\common\\target\\classes\\");
    Class<?> clazz;
    try
    {
      clazz = myCLoader.loadClass("Test");
      //获取加载类的属性字段
      Field[] filed = clazz.getFields();
      //获取加载类的方法字段
      Method[] methods = clazz.getMethods();
      System.out.println("该类的类加载器为：" + clazz.getClassLoader());
      //sun.misc.Launcher$AppClassLoader@18b4aac2
      System.out.println("该类的类加载器的父类为:" + clazz.getClassLoader().getParent());
      System.out.println("该类的名称为：" + clazz.getName());
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }


}