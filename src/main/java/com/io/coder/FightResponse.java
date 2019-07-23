package com.io.coder;

import com.io.proto.Serializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FightResponse extends Serializer
{

    /**
     * 获取金币
     */
    private int gold;

    @Override
    protected void read()
    {
        this.gold = readInt();
    }

    @Override
    protected void write()
    {
        writeInt(gold);
    }
}
