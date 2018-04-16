package com.io;

import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;


/** 
 *  oracle的insert的时候&符号如何插入（转义） 
 *  insert into table values( 'http://localhost:8080/index.action?username=138&type=1 ');  
 *  insert into table values( 'http://localhost:8080/index.action?username=138 '||chr(38)|| 'type=1 ');  
 * 
 * java.util.Properties --> class Properties extends Hashtable<Object,Object> 
 *  
 * file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/com/qingyuan/proxy/ 
 * file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/ 
 * file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/com/qingyuan/proxy/ 
 * file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/com/qingyuan/proxy/ 
 * sun.misc.Launcher$AppClassLoader@19821f 
 * file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/ 
 *  
 * 工程根目录和ClassPath 不是一回事,这里的getResouce说的是ClassPath 而不是工程根目录,也就是bin 目录; 
 * src --> webRoot/WEB-INF/classes 
 * project -> properites -> javaBuild path -> source 
 * source folder on build path: -> default output folder -> specific here 
 * src directory resouce going to be listed in WEB-INF/classes 
 *  
 * Java中取资源时,经常用到Class.getResource和ClassLoader.getResource,这里来看看他们在取资源文件时候的路径问题 
 * TestMain.class.getResource("/") == t.getClass().getClassLoader().getResource("") 
 */  
public class ResourcePathChecker  
{  
    // path不以’/'开头时，默认是从此类所在的包下取资源；  
    // path  以’/'开头时，则是从ClassPath根下获取；  
    @Test
    public  void testPath()   
    {  
        // file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/com/qingyuan/proxy/  
        System.out.println(ResourcePathChecker.class.getResource(""));  
          
        // Linux environment "/" as the root directory  
        // if your put file in src/, you need use class.getResource("/")  
        // file:/C:/Users/workspace/dynTable/WebRoot/WEB-INF/classes/  
        System.out.println(ResourcePathChecker.class.getResource("/"));  
          
    }  
  
    @SuppressWarnings("unused")  
    private static ClassLoader getClassLoader2()  
    {  
        ResourcePathChecker.class.getClassLoader();  
        ResourcePathChecker.class.getResourceAsStream("");  
        ResourcePathChecker.class.getClassLoader().getResourceAsStream("");  
        // wesConfigFile = getClassLoader().getResourceAsStream("WES_Cfg.properties");  
        return new ResourcePathChecker().getClass().getClassLoader();  
    }  
      
    @SuppressWarnings("unused")  
    @Test
    public void getLogChmodPermission() throws Exception  
    {  
        Properties properties; // Properties + InputStream <输入流对象>  
        InputStream wesConfigFile;  
          
        properties = new Properties();  
        wesConfigFile = ResourcePathChecker.class.getClassLoader().getResourceAsStream("WES_Cfg.properties");  
          
        properties.load(wesConfigFile);  
        String permissions = properties.getProperty("WF_LOG_CHMOD_PERMISSIONS");  
          
        System.out.print(permissions);  
    }  
      
    // t.getClass().getClassLoader().getResource("/"));//null  
    // 记住一点: 如果使用t.getClass().getClassLoader().getResource,就不要用"/"  
    @Test  
    public void  test()   
    {  
        ResourcePathChecker t = new ResourcePathChecker();  
        System.out.println(t.getClass().getResource(""));  
        System.out.println(t.getClass().getClassLoader());  
        System.out.println(t.getClass().getClassLoader().getResource(""));  
        System.out.println(t.getClass().getClassLoader().getResource("/")); //null  
    }  
  
    public static void main(String[] args) {  
          
        // 当前类(class)所在的包目录  
        System.out.println(ResourcePathChecker.class.getResource(""));  
          
        // class path根目录  
        System.out.println(ResourcePathChecker.class.getResource("/"));  
          
        // TestMain.class在<bin>/testpackage包中  
        // 2.properties  在<bin>/testpackage包中  
        System.out.println(ResourcePathChecker.class.getResource("2.properties"));  
          
        // TestMain.class在<bin>/testpackage包中  
        // 3.properties  在<bin>/testpackage.subpackage包中  
        System.out.println(ResourcePathChecker.class.getResource("subpackage/3.properties"));  
          
        // TestMain.class在<bin>/testpackage包中  
        // 1.properties  在bin目录（class根目录）  
        System.out.println(ResourcePathChecker.class.getResource("/1.properties"));  
          
        ResourcePathChecker t = new ResourcePathChecker();  
          
        System.out.println(t.getClass().getClassLoader().getResource(""));       
        System.out.println(t.getClass().getClassLoader().getResource("1.properties"));  
        System.out.println(t.getClass().getClassLoader().getResource("testpackage/2.properties"));  
        System.out.println(t.getClass().getClassLoader().getResource("testpackage/subpackage/3.properties"));  
    }

}  