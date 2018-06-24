package com.thread.disruptor.base;

/**
 * 定义事件数据，本质是个普通JavaBean
 */
public class LongEvent {
    private long value;

    public LongEvent() {
        super();
    }

    public LongEvent(long value) {
        super();
        this.value = value;
    }

    public long getValue() { 
        return value; 
    } 
 
    public void setValue(long value) { 
        this.value = value; 
    } 
} 