package com.io.coder.module;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回对象
 *
 * @author -琴兽-
 */
@Getter
@Setter
public class Response
{
    /**
     * 请求模块
     */
    private short module;

    /**
     * 命令号
     */
    private short cmd;

    /**
     * 状态码
     */
    private int stateCode;

    /**
     * 数据部分
     */
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
