package com.classloder.classtest.constant;

public class IntegerConstant extends Constant
{

    int value;

    public IntegerConstant(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "" + this.value;
    }

}
