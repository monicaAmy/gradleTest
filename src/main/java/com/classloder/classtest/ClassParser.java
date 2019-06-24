package com.classloder.classtest;

import com.classloder.classtest.constant.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ClassParser
{

    //常量池集合
    private List<Constant> constants = new ArrayList<Constant>();

    private static int MAGIC = 0xCAFEBABE;

    private DataInputStream stream;

    private String className;

    public static void main(String[] args) throws Exception
    {
        ClassParser cp = new ClassParser();
        cp.parseClass(new File("bin/lesson10/User.class"));
    }

    public void parseClass(File file) throws Exception
    {
        try
        {
            stream = new DataInputStream(new FileInputStream(file));
            parseMagic(); //解析魔数
            parseVersion(); //解析版本
            parseConstantPool(); //解析常量池
            parseAccessFlag(); //解析类访问控制标志
            parseClassName(); //解析类名字
            parseSuperClassName(); //解析父类名
            parseInterfaceNames(); //解析实现接口名
            parseFields(); //解析字段
            parseMethods(); //解析方法
        }
        finally
        {
            if (stream != null)
            {
                stream.close();
            }
        }
    }

    private void parseMagic() throws Exception
    {
        int magic = stream.readInt();
        if (magic != MAGIC)
        {
            throw new RuntimeException("bad class file format.");
        }
    }

    private void parseVersion() throws Exception
    {
        int minorVersion = stream.readShort();
        int majorVersion = stream.readShort();
    }

    private void parseConstantPool() throws Exception
    {
        int count = stream.readShort();//读取常量池数量
        for (int i = 0; i < count - 1; i++)
        {
            int tag = stream.readByte();//读取常量类型
            switch (tag)
            {
                case 1:
                {//解析utf-8常量
                    int length = stream.readShort();//读取utf8长度
                    String value = readString(stream, length);//读取字符串
                    constants.add(new Utf8Constant(value));//生成并加入utf-8常量对象
                    break;
                }
                case 3:
                {//解析整数常量
                    int value = stream.readInt();
                    constants.add(new IntegerConstant(value));
                    break;
                }
                case 4:
                {//解析单精度浮点常量
                    float value = stream.readFloat();
                    constants.add(new FloatConstant(value));
                    break;
                }
                case 5:
                {//解析长整型
                    long value = stream.readLong();
                    constants.add(new LongConstant(value));
                    break;
                }
                case 6:
                {//解析双精度浮点
                    double value = stream.readDouble();
                    constants.add(new DoubleConstant(value));
                    break;
                }
                case 7:
                {//解析class常量
                    int classIndex = stream.readShort();
                    constants.add(new ClassConstant(classIndex));
                    break;
                }
                case 8:
                {//解析字符串常量
                    int stringIndex = stream.readShort();
                    constants.add(new StringConstant(stringIndex));
                    break;
                }
                case 9:
                {//解析FieldRef常量
                    int classIndex = stream.readShort();
                    int nameAndTypeIndex = stream.readShort();
                    constants.add(new FieldRefConstant(classIndex, nameAndTypeIndex));
                    break;
                }
                case 10:
                {//解析MethodRef常量
                    int classIndex = stream.readShort();
                    int nameAndTypeIndex = stream.readShort();
                    constants.add(new MethodRefConstant(classIndex, nameAndTypeIndex));
                    break;
                }
                case 11:
                {//解析InterfaceMethodRef常量
                    int classIndex = stream.readShort();
                    int nameAndTypeIndex = stream.readShort();
                    constants.add(new InterfaceMethodRefConstant(classIndex, nameAndTypeIndex));
                    break;
                }
                case 12:
                {//解析NameAndType常量
                    int nameIndex = stream.readShort();
                    int descriptorIndex = stream.readShort();
                    constants.add(new NameAndTypeConstant(nameIndex, descriptorIndex));
                    break;
                }
            }
        }
    }

    private void parseAccessFlag() throws Exception
    {
        int accessFlag = stream.readShort();
    }

    private void parseClassName() throws Exception
    {
        int classNameIndex = stream.readShort();//读取类常量索引
        ClassConstant cc = (ClassConstant) constants.get(classNameIndex - 1);//获得类名对应的utf8常量对象索引
        Utf8Constant uc = (Utf8Constant) constants.get(cc.getClassIndex() - 1);//获得类名对应的utf8常量对象
        String fullName = uc.getValue();//获取类全名，包括包
        className = getClassSimpleName(fullName);//获取类名
    }

    private void parseSuperClassName() throws Exception
    {
        int classNameIndex = stream.readShort();//读取父类类常量索引
        ClassConstant cc = (ClassConstant) constants.get(classNameIndex - 1);//获得父类类名对应的utf8常量对象索引
        Utf8Constant uc = (Utf8Constant) constants.get(cc.getClassIndex() - 1);//获得父类类名对应的utf8常量对象
        System.out.println("super class name:");
        System.out.println("\t" + uc.getValue());//打印父类类名
    }

    private void parseInterfaceNames() throws Exception
    {
        int count = stream.readShort();
        System.out.println("implemented interface names:");
        for (int i = 0; i < count; i++)
        {
            int classNameIndex = stream.readShort();//读实现接口类常量索引
            ClassConstant cc = (ClassConstant) constants.get(classNameIndex - 1);//获得实现接口类名对应的utf8常量对象索引
            Utf8Constant uc = (Utf8Constant) constants.get(cc.getClassIndex() - 1);//获得实现接口类名对应的utf8常量对象
            System.out.println("\t" + uc.getValue());//打印实现接口类名
        }
    }

    private void parseFields() throws Exception
    {
        int count = stream.readShort();//读取字段数量
        System.out.println("fields:");
        for (int i = 0; i < count; i++)
        {
            int accessFlag = stream.readShort();//读取字段访问控制标志
            int nameIndex = stream.readShort();//读取字段名utf8对象索引
            int descriptorIndex = stream.readShort();//读取字段描述对象索引
            Utf8Constant fieldNameConstant = (Utf8Constant) constants.get(nameIndex - 1);
            Utf8Constant descriptorConstant = (Utf8Constant) constants.get(descriptorIndex - 1);

            String fieldName = fieldNameConstant.getValue();//获取字段名
            String typeAbbrv = descriptorConstant.getValue();//获取返回值类型描述符
            String fieldSignature = getFieldSignature(accessFlag, fieldName, typeAbbrv);//获取方法签名
            System.out.println("\t" + fieldSignature);//输出

            int attributeCount = stream.readShort();//读取字段attribute数量
            for (int j = 0; j < attributeCount; j++)
            {
                int attributeNameIndex = stream.readShort() & 0xFF;//读取字段attribute类型名索引
                int attributeLength = stream.readInt();//读取attribute内容长度
                readBytes(stream, attributeLength);//读取attribute内容
            }
        }
    }

    private void parseMethods() throws Exception
    {
        int count = stream.readShort();//读取方法个数
        System.out.println("methods:");
        for (int i = 0; i < count; i++)
        {
            int accessFlag = stream.readShort();//读取方法访问标志
            int nameIndex = stream.readShort();//读取方法名utf8常量索引
            int descriptorIndex = stream.readShort();//读取方法参数、返回值类型描述符utf8常量索引
            Utf8Constant methodNameConstant = (Utf8Constant) constants.get(nameIndex - 1);
            Utf8Constant descriptorConstant = (Utf8Constant) constants.get(descriptorIndex - 1);

            String methodName = methodNameConstant.getValue();//获取方法名
            String descriptor = descriptorConstant.getValue();//获取返回参数、返回值类型描述字符串

            String methodSignature = getMethodSignature(accessFlag, methodName, descriptor);//获取方法签名
            System.out.println("\t" + methodSignature);//输出

            int attributeCount = stream.readShort();//读取方法attribute个数
            for (int j = 0; j < attributeCount; j++)
            {
                int attributeNameIndex = stream.readShort();//读取方法attribute名对应的索引
                int attributeLength = stream.readInt();//读取方法attribute内容长度
                readBytes(stream, attributeLength);//读取方法attribute内容
            }
        }
    }

    /*
     * 读取指定长度的byte数组
     */
    private byte[] readBytes(DataInputStream stream, int length) throws Exception
    {
        byte[] bytes = new byte[length];
        stream.read(bytes);
        return bytes;
    }

    //获取方法签名
    private String getMethodSignature(int accessFlag, String methodName, String descriptor)
    {
        String accessString = getMethodAccessString(accessFlag);//获得访问描述符字符串
        int begin = descriptor.indexOf('(');
        int end = descriptor.indexOf(')');
        String part1 = descriptor.substring(begin + 1, end);//读取参数列名
        String part2 = descriptor.substring(end + 1);//读取返回值类型
        String returnValueString = getReturnValueString(part2);//读取返回值描述字符串
        String paramStrings = getParamStrings(part1);//读取参数列表描述字符串
        if ("<init>".equals(methodName))
        {//如果是构造函数
            return accessString + " " + className + "(" + paramStrings + ")";
        }
        else
        {//一般函数
            return accessString + " " + returnValueString + " " + methodName + "(" + paramStrings + ")";
        }
    }

    /*
     * 获得返回值类型的描述字符串
     */
    private String getReturnValueString(String returnValueString)
    {
        return geTypeString(returnValueString);
    }

    /*
     * 获得方法参数列表描述字符串
     */
    private String getParamStrings(String params)
    {
        String paramList = "";
        int length = params.length();
        int pos = 0;
        int arrayDimension = 0;//数组维数
        while (pos < length)
        {//如果还未到参数字符串最后
            char ch = params.charAt(pos++);//获得当前字符
            if (ch == '[')
            {//如果数组标志
                arrayDimension++; //维数加1
            }
            else
            {//非数组
                String baseType = "";
                switch (ch)
                {
                    case 'B':
                        baseType = "byte";
                        break;
                    case 'C':
                        baseType = "char";
                        break;
                    case 'D':
                        baseType = "double";
                        break;
                    case 'F':
                        baseType = "float";
                        break;
                    case 'I':
                        baseType = "int";
                        break;
                    case 'J':
                        baseType = "long";
                        break;
                    case 'S':
                        baseType = "short";
                        break;
                    case 'Z':
                        baseType = "boolean";
                        break;
                    case 'V':
                        baseType = "void";
                        break;
                    case 'L':
                        //如果是类型为对象类型，记录下类名开始位置
                        int begin = pos;
                        for (; pos < length; pos++)
                        {
                            if (params.charAt(pos) == ';')
                            {//找到类全名结束位置
                                baseType = getClassSimpleName(params.substring(begin, pos));//获得类名的简单名字
                                pos++;
                                break;//找到类名，跳出循环
                            }
                        }
                        break;
                }
                //拼装类型名
                String typeString = baseType;
                for (int i = 0; i < arrayDimension; i++)
                {
                    typeString += "[]";
                }
                paramList += typeString + ",";
                arrayDimension = 0;
            }
        }

        if (paramList.length() > 0)
        {//如果有参数，删除最后一个,
            return paramList.substring(0, paramList.length() - 1);
        }
        else
        {
            return paramList;
        }
    }

    /*
     * 获取字段签名
     */
    private String getFieldSignature(int accessFlag, String name, String abbrv)
    {
        String accessString = getFieldAccessString(accessFlag);
        String typeString = geTypeString(abbrv);
        return accessString + " " + typeString + " " + name;
    }

    /*
     * 获取类型名，类似getParamStrings
     */
    private String geTypeString(String abbrv)
    {
        String baseType = "";
        int arrayDimension = 0;
        int length = abbrv.length();
        for (int i = 0; i < length; i++)
        {
            char ch = abbrv.charAt(i);
            if (ch == '[')
            {
                arrayDimension++;
            }
            else
            {
                switch (ch)
                {
                    case 'B':
                        baseType = "byte";
                        break;
                    case 'C':
                        baseType = "char";
                        break;
                    case 'D':
                        baseType = "double";
                        break;
                    case 'F':
                        baseType = "float";
                        break;
                    case 'I':
                        baseType = "int";
                        break;
                    case 'J':
                        baseType = "long";
                        break;
                    case 'S':
                        baseType = "short";
                        break;
                    case 'Z':
                        baseType = "boolean";
                        break;
                    case 'V':
                        baseType = "void";
                        break;
                    case 'L':
                        //获取剩下的部份中的类全名，剔除最后一个;
                        baseType = getClassSimpleName(abbrv.substring(i, length - 1));
                        break;
                }
                break;
            }
        }
        //拼装类型字符串
        String typeString = baseType;
        for (int i = 0; i < arrayDimension; i++)
        {
            typeString += "[]";
        }
        return typeString;
    }

    /*
     * 获取方法的描述字符串
     */
    private String getMethodAccessString(int accessFlag)
    {
        String str = "";
        if ((accessFlag & 0x0001) == 0x0001)
        {
            str += "public ";
        }
        if ((accessFlag & 0x0002) == 0x0002)
        {
            str += "private ";
        }
        if ((accessFlag & 0x0004) == 0x0004)
        {
            str += "protected ";
        }
        if ((accessFlag & 0x0008) == 0x0008)
        {
            str += "static ";
        }
        if ((accessFlag & 0x0010) == 0x0010)
        {
            str += "final ";
        }
        if ((accessFlag & 0x0020) == 0x0020)
        {
            str += "synchronized ";
        }
        if ((accessFlag & 0x0100) == 0x0100)
        {
            str += "native ";
        }
        if ((accessFlag & 0x0100) == 0x0400)
        {
            str += "abstract ";
        }
        if ((accessFlag & 0x0100) == 0x0800)
        {
            str += "strictfp ";
        }
        return str.substring(0, str.length() - 1);
    }

    /*
     * 获取字段的访问字符串
     */
    private String getFieldAccessString(int accessFlag)
    {
        String str = "";
        if ((accessFlag & 0x0001) == 0x0001)
        {
            str += "public ";
        }
        if ((accessFlag & 0x0002) == 0x0002)
        {
            str += "private ";
        }
        if ((accessFlag & 0x0004) == 0x0004)
        {
            str += "protected ";
        }
        if ((accessFlag & 0x0008) == 0x0008)
        {
            str += "static ";
        }
        if ((accessFlag & 0x0010) == 0x0010)
        {
            str += "final ";
        }
        if ((accessFlag & 0x0040) == 0x0040)
        {
            str += "volatile ";
        }
        if ((accessFlag & 0x0080) == 0x0080)
        {
            str += "volatile ";
        }
        if (str.length() > 0)
        {
            return str.substring(0, str.length() - 1);
        }
        else
        {
            return str;
        }
    }

    /*
     * 获取类的简单的名字
     */
    private String getClassSimpleName(String fullName)
    {
        int index = fullName.lastIndexOf('/');
        return fullName.substring(index + 1);
    }

    /*
     * 从流中读取指定长度的字符串，并按照utf-8进午编码
     */
    private String readString(DataInputStream stream, int length) throws Exception
    {
        byte[] bytes = new byte[length];
        stream.read(bytes);
        return new String(bytes, "utf-8");
    }

}
