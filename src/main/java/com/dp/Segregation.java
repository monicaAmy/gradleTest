package com.dp;

/**
 * 接口隔离
 */
public class Segregation
{
    public static void main(String[] args)
    {
        B b = new B();
        new A().depend1(b);
        new C().depend4(b);
    }
}

interface Interface1
{
    void operation1();

    void operation2();

    void operation3();

    void operation4();

    void operation5();
}

class B implements Interface1
{
    @Override
    public void operation1()
    {
        System.out.println(this.getClass().getName() + " 实现了  " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void operation2()
    {
        System.out.println(this.getClass().getName() + " 实现了  " + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    @Override
    public void operation3()
    {
        System.out.println(this.getClass().getName() + " 实现了  " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void operation4()
    {
        System.out.println(this.getClass().getName() + " 实现了  " + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    @Override
    public void operation5()
    {
        System.out.println(this.getClass().getName() + " 实现了  " + Thread.currentThread().getStackTrace()[1].getMethodName());

    }
}

/**
 * 接口分成实现1 和 2 3 和 4 5的三个接口
 * 实现接口类分别实现 1 2 3 和1 4 5 的两个类
 */
class A
{
    public void depend1(Interface1 interface1)
    {
        interface1.operation1();
    }

    public void depend2(Interface1 interface1)
    {
        interface1.operation2();
    }

    public void depend3(Interface1 interface1)
    {
        interface1.operation3();
    }
}

class C
{
    public void depend1(Interface1 interface1)
    {
        interface1.operation1();
    }

    public void depend4(Interface1 interface1)
    {
        interface1.operation4();
    }

    public void depemd5(Interface1 interface1)
    {
        interface1.operation5();
    }
}