package com.jvm;

public class TestA
{
    //基本类型属于虚拟机栈
    boolean flag = true;

    public void stop()
    {
        flag = false;
    }

    public static void main(String[] args)
    {
        TestA testA = new TestA();
        testA.stop();
    }
}
