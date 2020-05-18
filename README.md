# gradleTest
gradleTest

com.dp 设计模式代码
里氏替换原则 :  A中method1两数相加,B继承A重写方法method1为相减,改为A,B都继承base ,A中有相加, B中有相减
开闭原则: switch的方法改为继承接口,创建对象,直接调用对象的方法
迪米特法则: 成员变量,方法参数,方法返回值中的类为直接朋友.出现在局部变量中的类不是直接朋友;降低耦合
合成复用原则: 继承转为调用

单例模式有八种写法
java.lang.Runtime 单例
工具类对象,平凡访问数据库或者文件对象

原型模式 创建一摸一样对象,使用clone
spring 多例bean创建. 
org.springframework.beans.factory.support.AbstractBeanFactory#createBean多例创建
浅拷贝在引用类型复制的是地址,两个值指向一个地址:如果修改原本值的其中一个变量,另外一个值的变量也会被修改

工厂模式 根据输入类型,工厂给出合规的产品(创建合适的对象)
spring中BeanFactroy,根据bean名字创建对象

建造者模式
房子的创建交给指挥者,给用户返回房子
StringBuilder append 中建造者 AbstractStringBuilder ensureCapacityInternal实现了扩充
StringBuilder 实现 AbstractStringBuilder是指挥者,也是建造者

适配器模式
spring DispatchServlet HandlerAdapter中针对不同controller有不同的Adapter去处理

桥接模式
jdbc Driver DriverManager getConnection的几种方式,根据入参不同,创建不同的连接
常见的应用场景:
1) -JDBC驱动程序
2) - 银行转账系统
转账分类:网上转账，柜台转账，AMT转账
转账用户类型:普通用户，银卡用户，金卡用户..
3) -消息管理
消息类型:即时消息，延时消息
消息分类:手机短信，邮件消息，QQ消息...

装饰者模式 对象里面 还有对象(洋葱一样)

组合模式 类里面有list ,使用的时候自由往list里面添加对象

外观模式, 选几个类的方法,组合封装起来成统一的方法调用

享元模式 放入Map缓冲池里面,要的时候直接拿,没有就创建
String常量池
String s1 = "Hello";也在常量池里面
通过new操作符创建的字符串对象不指向字符串池中的任何对象，但是可以通过使用字符串的intern()方法来指向其中的某一个。
intern()返回一个保留池字符串，就是一个在全局字符串池中有了一个入口。如果以前没有在全局字符串池中，那么它就会被添加到里面
Integer.valueOf(-128---127)

命令模式
对象实现命令,所有命令放入命令集合中去调用

中介模式
对象注册到map里面,从map中获取对象方法组合使用

备忘录模式
使用list保存对象当前状态




在程序运行的时候有多少类被加载！
可以用verbose:class来监视，在命令行输入java -verbose:class XXX (XXX为程序名)，
也可以直接在JVM启动时添加启动参数-verbose:class，看到加载的类的情况。

ocp原则 对扩展开放,对修改关闭