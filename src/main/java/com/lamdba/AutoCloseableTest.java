package com.lamdba;

public class AutoCloseableTest implements AutoCloseable
{
    @Override
    public void close() throws Exception
    {
        System.out.println("close !");
    }

    public void doSomething()
    {
        System.out.println("do something invoked!");
    }

    public static void main(String[] args) throws Exception
    {
        try (AutoCloseableTest autoCloseableTest = new AutoCloseableTest())
        {
            autoCloseableTest.doSomething();
        }
    }
}
