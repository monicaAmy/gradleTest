package com.dp.adapter.objectadapter;

public class VAdapter implements IV5
{

    /**
     * 使用构造和set方法传递参数
     */
    private IV5 iv;

    public VAdapter(IV5 iv)
    {
        this.iv = iv;
    }

    public void setIv(IV5 iv)
    {
        this.iv = iv;
    }

    @Override
    public int output()
    {
        int output = iv.output();
        return output / 44;
    }
}
