package com.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * NieSu 2018/12/11
 */
public class FileUtil
{

    public static void close(Closeable... closeables)
    {
        for (Closeable closeable : closeables)
        {
            if (closeable != null)
            {
                try
                {
                    closeable.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBrBw()
    {
        try
        {
            //读取文件(字符流)
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("e:\\redis00.conf"), "UTF-8"));
            //BufferedReader in = new BufferedReader(new FileReader("d:\\1.txt")));
            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("e:\\redis01.conf"), "UTF-8"));
            //BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"))；
            //读取数据
            //循环取出数据
            String str = null;
            while ((str = in.readLine()) != null)
            {

                if (str.startsWith("#"))
                {
                    continue;
                }

                System.out.println(str);
                //写入相关文件
                out.write(str);
                out.newLine();
            }

            //清楚缓存
            out.flush();
            //关闭流
            in.close();
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
