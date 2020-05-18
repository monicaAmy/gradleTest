package com.dp.adapter.classadapter;

public class Phone
{
    public void charging(IVoltage5V iVoltage5V)
    {
        System.out.println(iVoltage5V.output5V());
    }
}
