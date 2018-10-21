package com.ztest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * NieSu 2018/8/12
 *
 * 用synchronized 不会读到写了一半的数据
 */
public class ReadAndWrite
{

  public static final String FILE_FORMAT = "UTF-8";



  public static void main(String[] args)
  {
    for (int i = 0; i < 100; i++)
    {
      Thread thread = new Thread(new Write());
      Thread thread1 = new Thread(new Read());
      thread.start();
      thread1.start();

    }

  }


  public static void read()
  {
    try
    {
      System.out.println("start syn read......" + Thread.currentThread().getName());

      synchronized (ReadAndWrite.class)
      {
        String pathname = "E:\\text.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
            new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        File writename = new File(".\\output" + Thread.currentThread().getName()+Fun.getRandom()
            + ".txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));

        String line = "";
        while ((line=br.readLine()) != null)
        {
          System.out.println(line);
          out.write(line+"\r\n"); // \r\n即为换行
        }
        out.flush();
        out.close();
        System.out.println("读入完成");
      }
      System.out.println("end syn read......");

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void write()
  {
    try
    {
      System.out.println("start syn write......");

      synchronized (ReadAndWrite.class)
      {
        System.out.println("write start......" + Thread.currentThread().getName());

        File writename = new File("E:\\text.txt");
        writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
        out.append("\r\n我会写入文件啦\r\n" + Thread.currentThread().getName()+Fun.getRandom());
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
        System.out.println("write end......");
      }
      System.out.println("write syn ......");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}

class Write implements Runnable
{
  @Override
  public void run()
  {
    ReadAndWrite.write();
  }
}

class Read implements Runnable
{
  @Override
  public void run()
  {
    ReadAndWrite.read();
  }
}