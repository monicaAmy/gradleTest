package com.io;

import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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

    public static void testW(String str, String fileName)
    {
        try
        {
            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            //BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"))；
            //读取数据
            //循环取出数据

            // System.out.println(str);
            //写入相关文件
            out.write(str);
            out.newLine();

            //清楚缓存
            out.flush();
            //关闭流
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 把文本设置到剪贴板（复制）
     */
    public static void setClipboardString(String text)
    {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public static String getClipboardString()
    {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null)
        {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                try
                {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Test
    public void testCV()
    {
        // 把文本设置到剪贴板（复制）
        setClipboardString("Hello System Clipboard!");

        // 从剪贴板中获取文本（粘贴）
        String text = getClipboardString();

        testW(text, "E:\\m.docx");
        System.out.println("text: " + text);

    }

}
