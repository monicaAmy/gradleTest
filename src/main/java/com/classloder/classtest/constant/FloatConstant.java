package com.classloder.classtest.constant;

public class FloatConstant extends Constant
{

    float value;

    public FloatConstant(float value)
    {
        this.value = value;
    }

    public float getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "" + this.value;
    }

}
