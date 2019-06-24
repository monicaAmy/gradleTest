package com.classloder.classtest.constant;

public class FieldRefConstant extends Constant
{

    int classIndex;
    int nameAndTypeIndex;

    public FieldRefConstant(int classIndex, int nameAndTypeIndex)
    {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex()
    {
        return classIndex;
    }

    public int getNameAndTypeIndex()
    {
        return nameAndTypeIndex;
    }

}
