package com.jvm;

import com.io.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class MyClassLoader extends ClassLoader
{

  private final String fileType = ".class";
  //类加载器名称
  private String loaderName;
  //加载类的路径
  private String path = "";

  public MyClassLoader(String name)
  {
    super();   //应用类加载器为该类的父类
    this.loaderName = name;
  }

  public MyClassLoader(ClassLoader parent, String name)
  {
    super(parent);
    this.loaderName = name;
  }

  @Override
  public String toString()
  {
    return this.loaderName;
  }

  @Override
  public Class<?> findClass(String name)
  {
    byte[] data = loaderClassData(name);
    return this.defineClass(name, data, 0, data.length);
  }

  //读取.class文件
  private byte[] loaderClassData(String name)
  {
    InputStream is = null;
    byte[] data = null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try
    {
      String classPath = path + name + fileType;
      System.out.println(classPath);
      is = new FileInputStream(new File(classPath));
      int c = 0;
      while (-1 != (c = is.read()))
      {
        baos.write(c);
      }
      data = baos.toByteArray();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      FileUtil.close(is, baos);
    }
    return data;
  }
}