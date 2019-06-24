package com.classloder.classtest.constant;

public class MethodRefConstant extends Constant
{

    int classIndex;
    int nameAndTypeIndex;

    public MethodRefConstant(int classIndex, int nameAndTypeIndex)
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
