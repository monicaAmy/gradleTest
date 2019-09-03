package com.ztest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.io.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Pull
{
    @Test
    public void testj()
    {

        try
        {
            String urlInfo = "https://blog.csdn.net/jesonjoke/article/details/79978855";
            //读取目的网页URL地址，获取网页源码
            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            InputStream is = httpUrl.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            //写完之后,使用html文件读取出来

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
    }

    @Test
    public void testhtml() throws Exception
    {
        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        HtmlPage page = webClient.getPage("https://blog.csdn.net/jesonjoke/article/details/79978855");

        HtmlElement mainBoxDiv = page.getHtmlElementById("mainBox");

        DomNodeList<HtmlElement> mainList = mainBoxDiv.getElementsByTagName("main");
        HtmlElement mian = mainList.get(0);
        DomElement firstElementChild = mian.getFirstElementChild();
        String s = firstElementChild.asXml();
        FileUtil.testW(s, "E:/xx.html");
        //   testOpen();
        //不带格式
//        String s1 = firstElementChild.asText();
//        FileUtil.testW(s1,"E:/xx.txt");
//带格式
//        String textContent = firstElementChild.getTextContent();
//        FileUtil.testW(textContent,"E:/xx.docx");
    }

    //https://blog.csdn.net/kenhins/article/details/45486011
    @Test
    public void testOpen()
    {
        try
        {
            String url = "E:/xx.html";
            URI uri = java.net.URI.create(url);

            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();

            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE))
            {
                dp.browse(uri);// 获取系统默认浏览器打开链接
            }

        }
        catch (java.lang.NullPointerException e)
        {
            // 此为uri为空时抛出异常
            e.printStackTrace();
        }
        catch (java.io.IOException e)
        {
            // 此为无法获取系统默认浏览器
            e.printStackTrace();
        }

    }

    @Test
    public void testCopy() throws Exception
    {
        URL url = new URL("file:///E:/xx.html");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inString;
        File outfile = new File("E:/test.docx");
        PrintWriter out = new PrintWriter(new FileWriter(outfile));
        while ((inString = in.readLine()) != null)
        {
            out.println(inString);
        }
        in.close();
        out.close();
    }

    @Test
    public void testCo() throws Exception
    {
        String cmd = "";
        Runtime.getRuntime().exec(cmd).waitFor();

    }
}
