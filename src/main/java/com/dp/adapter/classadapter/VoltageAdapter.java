package com.dp.adapter.classadapter;

/**
 * 实现充电,实现5V ---> 获取220转换成5V
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V
{
    @Override
    public int output5V()
    {
        int i = output220V();
        return i / 44;
    }

}
