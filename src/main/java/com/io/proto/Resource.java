package com.io.proto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource extends Serializer
{

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
