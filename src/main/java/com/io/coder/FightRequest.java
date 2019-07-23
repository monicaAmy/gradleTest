package com.io.coder;

import com.io.proto.Serializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FightRequest extends Serializer
{

    /**
     * 副本id
     */
    private int fubenId;

    /**
     * 次数
     */
    private int count;

    @Override
    protected void read()
    {
        this.fubenId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write()
    {
        writeInt(fubenId);
        writeInt(count);
    }

}
