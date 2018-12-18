package com.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * NieSu 2018/12/11
 */
public class FileUtil
{

  public static void close(Closeable... closeables)
  {
    for (Closeable closeable : closeables)
    {
      if (closeable != null)
      {
        try
        {
          closeable.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
