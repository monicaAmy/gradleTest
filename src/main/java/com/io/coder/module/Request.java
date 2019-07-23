package com.io.coder.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request
{

    private short module;

    private short cmd;

    private byte[] data;

    public int getDataLength()
    {
        if (data == null)
        {
            return 0;
        }
        return data.length;
    }
}
