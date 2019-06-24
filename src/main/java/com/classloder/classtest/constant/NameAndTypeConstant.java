package com.classloder.classtest.constant;

public class NameAndTypeConstant extends Constant
{

    int nameIndex;
    int descriptorIndex;

    public NameAndTypeConstant(int nameIndex, int descriptorIndex)
    {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getNameIndex()
    {
        return nameIndex;
    }

    public int getDescriptorIndex()
    {
        return descriptorIndex;
    }

}
