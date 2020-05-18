package com.thread;

public class Join
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(() ->
        {
            System.out.println("hhhh....");
            try
            {
                Thread.sleep(6000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("llllllllllllll...");
        });

        thread.start();

        try
        {
            //join主线程等待子线程运行完在运行
            thread.join();

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
