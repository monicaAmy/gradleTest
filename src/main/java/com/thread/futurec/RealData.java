package com.thread.futurec;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
    private String Data;

    public RealData(String Data) {
        this.Data = Data;
    }

    public String call() throws Exception {
        //利用sleep来表示任务处理
        Thread.sleep(2000);

        return "这是处理"+Data+"结果";
    }
}
