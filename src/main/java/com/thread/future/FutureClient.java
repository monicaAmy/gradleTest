package com.thread.future;

public class FutureClient {
    public Data request(final String request) {
        final FutureData futureData = new FutureData();

        new Thread(new Runnable() {

            public void run() {
                RealData realData = new RealData(request);
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
}
