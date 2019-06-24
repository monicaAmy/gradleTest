package com.classloder.classtest.constant;

public class LongConstant extends Constant
{

    long value;

    public LongConstant(long value)
    {
        this.value = value;
    }

    public long getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "" + this.value;
    }

}
