package com.classloder.classtest.constant;

public class Utf8Constant extends Constant
{

    private String value;

    public Utf8Constant(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "" + this.value;
    }

}
