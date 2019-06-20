package com.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 8000);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String str = "socket client msg !";
        bufferedWriter.write(str);
        //不关闭流, 客户端在连接到服务器之后就已经被虚拟机回收了会出现connection reset
        //连接到服务器之后,流必须发送字符串之后才能被关闭和回收
        bufferedWriter.close();
    }
}

