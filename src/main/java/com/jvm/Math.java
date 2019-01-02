package com.jvm;

/**
 * 反编译class文件 javap -c Math.class > math.class
 *
 * 本地方法栈 存放native方法, 比如调用C语言,python等 new的对象放在堆里面 局部变量放在栈里面
 *
 * NieSu 2019/1/2
 */
public class Math
{

  public static final Integer CONSTANT_1 = 666;
  public static Object object = new Object();

  public static void main(String[] args)
  {
    Math math = new Math();
    System.out.println(math.math());
  }

  public int math()
  {
    int a = 1;
    int b = 2;
    int c = (a + b) * 10;
    return c;
  }
}
