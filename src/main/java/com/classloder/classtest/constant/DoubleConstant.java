package com.classloder.classtest.constant;

public class DoubleConstant extends Constant
{

    double value;

    public DoubleConstant(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "" + this.value;
    }
}
