## Web的学习

### 1、创建一个web项目

### 概述：

#### Web应用

​	请求访问Web程序，基于BS架构，所有程序访问基于浏览器访问

![ ](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\105.png)

### 创建项目：

![ ](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\104.png)

#### 在web.xml中配置

```x&#39;m
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--设置启动页-->
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```

### 2、HTTP协议 

​	浏览器 访问  后端服务程序 ，需通过网络通信，遵循：HTTP协议

#### HTTP概述

如图：

![ ](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\106.png)

------

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\107.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\108.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\109.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\110.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\111.png)

------

####请求协议

cookie：将用户请求信息保存到本地。

淘宝：浏览器将用户信息保存到cookie中

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\112.png)

------

####响应协议

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\114.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\113.png)

####状态行中的状态码

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\115.png)

####请求方式

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\116.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\117.png)

### 3、Servlet

####Servlet简介

a.访问请求需要Servlet容器，如：Tomcat容器

b.访问请求时，通过：请求地址 + 端口号，请求到Tomcat容器，再请求到Servlet,Servlet再去请求其他应用程序。

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\118.png)

------

![ ](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\119.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\120.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\121.png)

#### Servlet初探

##### 创建Servlet

######a.实现Servlet接口，并实现其中的方法
​        init
​        service
​        destroy
​        getServletConfig
​        getServletInfo

 **Servlet写好之后，需告诉Web容器，执行哪个Servlet,如何请求？需再web.xml中进行注册和配置**

######b.在web.xml文件中进行servlet的注册和配置

```xml
 <servlet>
 		<!-- 请求的url地址-->
 		<!--servlet名称 一般和serlvet保持一直-->
        <servlet-name>firstServlet</servlet-name>
        <!--servlet对应的类  需要全类名-->
        <servlet-class>org.lanqiao.web.FirstServlet</servlet-class>
    </servlet>
	<!--创建servlet映射-->
    <servlet-mapping> 
    	<!--servlet名称 需要和上边你的名称保持一致-->
        <servlet-name>firstServlet</servlet-name>
        <!-- 请求的url地址-->
        <url-pattern>/</url-pattern> 
    </servlet-mapping>
```

###### c.执行情况：

​                init  只执行一次  实现对servlet的初始化
​                service 没请求一次 均会执行一次 处理请求的方法
​                destroy  当该servlet从servlet容器中卸载的时候 执行   执行一些扫尾工作
​                getServletConfig
​                getServletInfo

###### d.请求过程:

​        当通过浏览器去访问localhost:8080   tomcat会首先解析web.xml

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\122.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\123.png)

------



####Servlet生命周期

当调用servlet的init方法的时候 就是初始化了一个servlet对象    servlet 对象在整个生命周期中只有一个对象  所以servlet是单例模式
​          什么时候创建servlet的实例对象 与<load-on-startup>1</load-on-startup>
​          消亡：当serlvet从容器中卸载的时候 则宣告servlet生命结束

​	        init  完成servlet的初始化
​                service 处理请求
​                destroy 处理一些扫尾工作               

####Servlet配置

```xml
<!--配置servlet的初始化参数-->
                <init-param>
                    <!--参数名称-->
                    <param-name>hello</param-name>
                    <!--参数值-->
                    <param-value>world</param-value>
                </init-param>
                <!--该配置项 指定了servlet的初始化的时机  如果是负数   那么则在第一次请求的时候 才进行servlet的初始化   如果为正数，则是在servlet容器启动的时候就初始化了  正数越小   初始化的时机越早-->
                <load-on-startup>1</load-on-startup>
```

####Servlet3.0新特性

使用注解开发servlet，就无需进行web.xml的配置
@webServlet
​    name
​    urlpartterns
现在主流的开发模式  约定大于配置
在开发中要么使用xml进行配置  要么使用注解
如果在注解中只有url 则可以直接写 而不需要其他信息 包括url的名称

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\125.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\126.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\127.png)

### 4、url-pattern的配置：

```xml
 <url-pattern>/</url-pattern> /代标项目的根路径
          <url-pattern>/abc/aaa/bbb/servletDemo</url-pattern>多层路径配置 可以很好的区分不同的请求
          前边两种都不常用
          通常情况下  我们采用通配符进行配置  *
           <url-pattern>/</url-pattern> 知道
            <url-pattern>.do</url-pattern>
            <url-pattern>.action</url-pattern>
          注意：在一个url-pattern中只能出现一个通配符（）
           /*/*    *.*
```

### 5、Servlet实现方式:实现Servlet接口

​	思考：如何在实现Servlet接口时只实现service（）方法，其他方法由其内部实现，从而减少代码冗余的问题。

​      Why要实现Servlet接口中的方法，因为Servlet接口中的方法是抽象的。
​      How to do:service()让用户实现，程序员去实现其他四种方法，即让service()方法依然保持抽象本质，而其他四种方法变为具体方法。
​      既包含抽象方法又包含具体方法的类是抽象类。自行定义一个抽象类实现servlet接口，去实现其中的四种方法，而将service()定义为抽象的，让用户去实现。

a.自定义一个抽象类

```java
public abstract class MyServlet implements Servlet {
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse);

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}

```

b.实现类：MyServletImplTest

```java
public class MyServletImplTest extends MyServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
       ServletConfig servletConfig = this.getServletConfig();
        String paramValue = servletConfig.getInitParameter("username");
        System.out.println(paramValue);
        String displayname = servletConfig.getServletName();
        System.out.println(displayname);
    }
}
```

c.配置信息

```xml
 <servlet>
        <display-name>MyServletImplTest</display-name>
        <servlet-name>MyServletImplTest</servlet-name>
        <servlet-class>org.lanqiao.web.MyServletImplTest</servlet-class>
        <init-param>
            <param-name>username</param-name>
            <param-value>宙斯</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServletImplTest</servlet-name>
        <url-pattern>/ss</url-pattern>
    </servlet-mapping>
```

### 6、servletRequest 和servletResponse 封装

ServletRequest servletRequest  封装了请求信息  包括请求头和请求体
ServletResponse servletResponse 封装了响应信息  包括响应头和响应体

请求访问携带参数：在url之后，如：http://localhost:8080/ss?a=1&b=2

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\124.png)

MyServletImplTest类：

```java
  ServletConfig servletConfig = this.getServletConfig();
        String paramValue = servletConfig.getInitParameter("username");
        System.out.println(paramValue);
        String displayname = servletConfig.getServletName();
        System.out.println(displayname);
        // ..............servletRequest.................................................
        String aValue = servletRequest.getParameter("a");
        String bValue = servletRequest.getParameter("b");
        System.out.println("aValue:" + aValue +"  "+"bValue:"+bValue );
        String encoding =servletRequest.getCharacterEncoding();
        String contentType = servletRequest.getContentType();
        System.out.println("encoding:"+ encoding + "  "+ "contentType:"+contentType);
        //..................servletResponse..................................................
        String encoding1 =   servletRequest.getCharacterEncoding();
        System.out.println("encoding="+encoding1 );
        String type=  servletRequest.getContentType();
        System.out.println("type="+type );

        PrintWriter wirter = null;
        try {
            wirter = servletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wirter.write("<html>");
        wirter.write("<head>");
        wirter.write("<title>hello world</title>");
        wirter.write("</head>");
        wirter.write("<body>");
        wirter.write("<title>hello world</title>");
        wirter.write("<h1>hello world</h1>");
        wirter.write("</body>");
        wirter.write("</html>");
```

### 7、Servlet实现方式:继承GenericServlet接口

genericServletTest类

```java
@WebServlet(name = "genericServletTest",urlPatterns ={"/genericServletTest","/demo4"})
public class genericServletTest extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("注解配置");
    }
}
```

**使用注解开发servlet，就无需进行web.xml的配置**
@webServlet
​    name
​    urlpartterns

### 8、继承HttpServlet

**HttpServlet类中只有两个方法：doget() 、dopost()方法**

![ ](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\128.png)

HttpServlet是对Http协议提供了特殊支持的servlet的实现
在http协议中，规定的请求方式有四种 （get  post  put  delete），在现阶段  所有的浏览器只支持两种方式：get  post

**继承HttpServlet将是我们最常用的**,因目前浏览器使用的协议即是HTTP协议

```java
@WebServlet(name = "genericServletTest",urlPatterns ={"/genericServletTest","/demo4"},initParams = {@WebInitParam(name ="username",value = "火女")})
//@WebServlet("/word")
    public class genericServletTest extends GenericServlet {
         @Override
         public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
            System.out.println("注解配置");
            ServletConfig servletConfig = this.getServletConfig();
            String userValue = servletConfig.getInitParameter("username");
            System.out.println(userValue);
        }
}
```

### 9、小知识

getInitParameter(String name):获取初始化参数，通过参数名称获取参数值

getInitParameterNames():获取所有参数名称

### 10、 Servlet API

**总结：**

- 获取  与Servlet配置(.xml文件)相关的信息，使用ServletConfig对象来获取
- 获取  与service进行数据传输，保证数据在各个Servlet中数据共享，使用ServletConfig对象
- 获取  与请求封装的数据，使用request对象
- 获取  与响应封装的数据，使用response对象

#### ServletConfig对象

ServletConfig 该对象表示servlet的配置

```java
//获取ServletConfig对象
this.getServletConfig();
//根据参数名称获取参数值
servletConfig.getInitParameter("username");
//获取参数名称
servletConfig.getInitParameterNames();

```

servletConfigTest类

```java
//@WebServlet( "/servletConfigTest")
//使用注解的方式
public class servletConfigTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //根据参数名称获取参数值
        String userVal = servletConfig.getInitParameter("username");
        String passwordVal = servletConfig.getInitParameter("password");
        System.out.println("userVal=" + userVal);
        System.out.println("passwordVal=" + passwordVal);
        System.out.println(".............................................");
        //................................................................
        //获取参数名称
        Enumeration<String> parameNames = servletConfig.getInitParameterNames();
        while (parameNames.hasMoreElements()){//是否有下一个元素
            String names = parameNames.nextElement();
            String namesVal = servletConfig.getInitParameter(names);
            System.out.println("names = " + names + "------" + "namesVal = " + namesVal);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

```

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
    <servlet>
        <servlet-name>servletConfigTest</servlet-name>
        <servlet-class>org.lanqiao.servletconfigtest.servletConfigTest</servlet-class>
        <init-param>
            <param-name>username</param-name>
            <param-value>wollonefone</param-value>
        </init-param>
        <init-param>
            <param-name>password</param-name>
            <param-value>123456</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>servletConfigTest</servlet-name>
        <url-pattern>/demo6</url-pattern>
    </servlet-mapping>
</web-app>
```

结果图：

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\129.png)

##### 枚举

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\130.png)

Color enum

```java
public enum Color {
    RED,BLUE,YELLOW
}
```

servletConfigTest类中

```java
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        String userVal = servletConfig.getInitParameter("username");
        String passwordVal = servletConfig.getInitParameter("password");
        System.out.println("userVal=" + userVal);
        System.out.println("passwordVal=" + passwordVal);
        System.out.println(".............................................");
        //................................................................
        Enumeration<String> parameNames = servletConfig.getInitParameterNames();
        while (parameNames.hasMoreElements()){//是否有下一个元素
            String names = parameNames.nextElement();
            String namesVal = servletConfig.getInitParameter(names);
            System.out.println("names = " + names + "------" + "namesVal = " + namesVal);
        }
        //Color.RED;
    }
```



####ServletContext对象

表述servlet的上下文，贯穿于整个web应用

随着web容器的启动而创建，随着web容器的关闭而销毁，伴随整个web应用      application域对象

在一个web应用中，所有的Servlet应用共享一个ServletContext对象

作用：

- 实现在不同Servlet之间的数据共享
- 获取web应用程序的初始化参数
- 记录日志
- application域范围的属性
- 访问资源文件
- web应用程序之间的访问

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\131.png)

##### 获取ServletContext对象

```java
ServletConfig.getServletContext();
```

##### 域对象的功能:存取数据

void setAttribute(String name,Object value);

Object getAttribute(String name)

void removeAttribute(String name)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\135.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\133.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\134.png)

##### 获取应用初始化参数

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\136.png)

servletContextTest类

```java
//@WebServlet(name = "servletContextTest")
public class servletContextTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        //获取ServletContext对象
        ServletContext servletContext = servletConfig.getServletContext();
        //获取Web应用的值
        String applicationVal = servletContext.getInitParameter("applicationname");
        System.out.println( "applicationname = " +applicationVal);
        //。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
        //通过该方法返回相对路径的资源的真实物理路径
        String realPath = servletContext.getRealPath("红楼梦.txt");
        System.out.println(realPath);
        //将资源文件作为流对象返回
        InputStream inputStream = servletContext.getResourceAsStream("红楼梦.txt");
        System.out.println(inputStream);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
```

web.xml

```xml
<!--Web应用的初始化-->
    <context-param>
        <param-name>applicationname</param-name>
        <param-value>servletContextTest</param-value>
    </context-param>

 <servlet>
        <servlet-name>servletContextTest</servlet-name>
        <servlet-class>org.lanqiao.servletconfigtest.servletContextTest</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>servletContextTest</servlet-name>
        <url-pattern>/demo7</url-pattern>
    </servlet-mapping>
```

##### 获取资源相关方法

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\138.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\139.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\140.png)

servletContextTest类中

```java
 //通过该方法返回相对路径的资源的真实物理路径
        String realPath = servletContext.getRealPath("红楼梦.txt");
        System.out.println(realPath);
        //将资源文件作为流对象返回
        InputStream inputStream = servletContext.getResourceAsStream("红楼梦.txt");
        System.out.println(inputStream);
```

#####练习：访问量统计

统计当前web应用的访问次数

a.新建一个index.html页面

建立超链接

b.servletContextTest类

```java
//HttpServlet含一个方法，可直接获取ServletContext对象
ServletContext servletContext = this.getServletContext();
        Integer count = (Integer)servletContext.getAttribute("count");
        if (count == null ){
            count = 0;
            servletContext.setAttribute("count",count);
        }else {
            count++;
            //将count保存在ServletContext中
            servletContext.setAttribute("count",count);
        }
        count = (Integer) servletContext.getAttribute("count");
		//设置响应的内容类型及其字符编码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("<h1>当前web应用的访问次数为：" + count +"</h1>");
```

####Request/Response对象

##### request对象

###### request概述

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\141.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\142.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\143.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\144.png)

######request域方法

- 每一个请求就有一个request对象
- request : 请求共享数据
- 域对象：在不同的Servlet中传递数据

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\145.png)

######request获取请求头数据

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\146.png)

servletRequestTest类

```java
@WebServlet( "/demo8")
public class servletRequestTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的方式
       String method =  request.getMethod();
        System.out.println("methon = " + method);
       Enumeration<String> names =  request.getHeaderNames();
       String namesVal = request.getHeader("names");
       while (names.hasMoreElements()){
           String name = names.nextElement();
           System.out.println("name = " + name +"------"+"namesVal = " + namesVal);
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

```

小结：

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\147.png)

######request获取请求相关的其他方法

- request  强转为  HttpServletRequest
- 每当客户端发出一个请求时，就会产生一个request对象产生
- 封装了请求头  、请求体
- a超链接的请求方式为 get请求，不能修改
- 实现 post请求  使用 表单 

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\148.png)

servletRequestTest1类

```java
@WebServlet( "/demo9")
public class servletRequestTest1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //字符编码
        request.setCharacterEncoding("UTF-8");
        //请求内容长度
        int length = request.getContentLength();
        System.out.println(length);
        //请求方式
        String type = request.getContentType();
        System.out.println(type);
        //本地地址(ipv6的)
        String addr = request.getLocalAddr();
        System.out.println(addr);
        //获取请求编码
        String encoding = request.getCharacterEncoding();
        System.out.println(encoding);
        //获取上下文路径
        String path = request.getContextPath();
        System.out.println(path);
        //
        String uri = request.getRequestURI();
        System.out.println(uri);
        String url = request.getRequestURL().toString();
        System.out.println(url);
        //获取用户的IP地址，记录日志
       String remoteAdd = request.getRemoteAddr();
        System.out.println(remoteAdd);

    }
}

```

index.html

如何获取页面中请求数据，在 表单 中，设置Name属性

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>统计当前web应用的访问次数</title>
</head>
<body>
    <!--发出请求可通过：表当和超链接的方式-->
    通过超链接发出请求，每点击一次即发出一次请求<br>
    <a href="/demo6">demo6</a>
    <a href="/demo7">demo7</a>
    <a href="/demo8">demo8</a>
    <!--<a href="/demo9?name=二狗&passworld=123456">demo9</a>-->
    <!--action 表示表单提交的url ,method 表示表单的请求方式-->
    <form action="/demo9" method="post">
        username:<input type="text" name ="username" value=""><br><br>
        password:<input type="password" value="password"><br><br>
        <!--提交表单-->
        <input type="submit" value="提交">
    </form>
</body>
</html>
```

######request获取请求参数( 接收表单数据 )

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\152.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\153.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\154.png)

**获取页面中请求数据**

servletRequestTest1类

```java
@WebServlet( "/demo9")
public class servletRequestTest1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //设置请求的字符编码
       request.setCharacterEncoding("utf-8");
      //..................................................................
      //获取页面的请求数据
      //设置响应的内容类型及字符编码
       response.setContentType("text/html;charset=utf-8");
      //将获取到的数据响应到前端页面
      PrintWriter out = response.getWriter();
      //根据属性名获取属性值
      //用户名及密码
      String usernameVal = request.getParameter("username");
      String passwordVal = request.getParameter("password");
      out.write("username : " + usernameVal +"<br><br>");
      out.write("password : " + passwordVal +"<br><br>");
      //单选
       String sex = request.getParameter("sex");
       out.write("sex : " + sex +"<br><br>");
      //多选：获取到的是一个数组
       String[] loves =  request.getParameterValues("love");
       for (String str:loves){
          out.write("love : " + str +"<br><br>");
       }
      //下拉列表
       String city = request.getParameter("city");
       out.write("city : " + city +"<br><br>");
    }
}
```

**解决编码**

```java
	//设置请求的字符编码
       request.setCharacterEncoding("utf-8");
      //设置响应的内容类型及字符编码
       response.setContentType("text/html;charset=utf-8");
```

index.xml中

```xml
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>统计当前web应用的访问次数</title>
</head>
<body>
    <!--发出请求可通过：表当和超链接的方式-->
    通过超链接发出请求，每点击一次即发出一次请求<br>
    <a href="/demo6">demo6</a>
    <a href="/demo7">demo7</a>
    <a href="/demo8">demo8</a><br><br>


    <!--<a href="/demo9?username=二狗&password=123456">demo9</a><br><br>-->
    
    <!--action 表示表单提交的url ,method 表示表单的请求方式-->
    <form action="/demo9" method="post">
        username:<input type="text" name="username" value=""><br><br>
        password:<input type="password" name="password" value=""><br><br>
        <h2>单选、多选、下拉列表</h2><br><br>
        性  别：<input type="radio" name="sex" value="man">男&nbsp;&nbsp;<input type="radio" name="sex" checked="checked" value="woman">女<br><br>
        兴  趣：<input type="checkbox" name="love" value="basketball">篮球&nbsp;&nbsp;
                <input type="checkbox" name="love" value="tablet ennis">乒乓球&nbsp;&nbsp;
                <input type="checkbox" name="love" value="badminton">羽毛球&nbsp;&nbsp;
                <input type="checkbox" name="love" value="football">足球&nbsp;&nbsp;<br><br>
        归属地：<select name="city">
                    <option selected>北京</option>
                    <option>上海 </option>
                    <option>山西</option>
                    <option>深圳</option>
                    <option>杭州</option>
                </select><br><br>
        <!--提交表单-->
        <input type="submit" value="提交">
    </form>
</body>
</html>
```

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\155.png)

######请求转发和请求包含

- 在之前   对所有的请求都是通过一个servlet来进行处理的   但是   在实际开发中 针对复杂的业务逻辑   一个servlet   往往不能够   完全处理当前请求  因此需要通过多个servlet来进行对当前请求进行处理
- 因一个请求只能请求一个servlet，因此如果需要多个servlet处理一个请求，需要在第一个servlet内部进行响应的处理
- request.setAttribute();          设置/获取属性(自己放进去的)
- request.getParameter();       获取参数( 从html页面或jsp页面传过来的数据)

**a.请求转发**

```java
//请求转发    携带参数：request，response
        request.getRequestDispatcher("/secondServlet").forward(request,response);
```

- 请求转发发送的是一次请求，地址栏不会发生改变,即发送的请求相同(发送的request相同)

步骤：

新建两个Servlet

fristServlet类

```java
@WebServlet( "/fristServlet")
public class fristServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证发送的请求为同一请求(在多个servlet之间request域中的数据是可以共享的)
        request.setAttribute("data","二哈");
        System.out.println("username :" + request.getParameter("username"));
        //请求转发    携带参数：request，response
        request.getRequestDispatcher("/secondServlet").forward(request,response);
    }
}

```

secondServlet类

```java
@WebServlet( "/secondServlet")
public class secondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out =  response.getWriter();
       out.write("secondServlet");
          System.out.println("---------------------");
       //验证发送的请求为同一请求(在多个servlet之间request域中的数据是可以共享的)
        out.write("data");
        System.out.println("secondServlet中username :" + request.getParameter("username"));
    }
}

```

新建一个index.xml

```xml
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>请求转发和请求包含</h1>
    <a href="/fristServlet">fristServlet</a>
</body>
</html>
```

**b.请求包含**

同样是：一个请求

```java
//请求包含
request.getRequestDispatcher("/secondServlet").include(request,response);
```

两者区别：

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\156.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\157.png)

小结：

- 一个请求需多个Servlet处理
- 一个请求只能请求一个servlet
- 在第一个servlet内部进行响应处理
- dispatcher：调度程序

##### response对象

###### response概述

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\159.png)



######response响应正文

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\160.png)

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\161.png)

######设置响应头信息

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\162.png)

######设置状态码及其他方法

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\163.png)

######重定向

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\167.png)

- 客户端发了两次请求，地址栏发生了变换

- 重定向的URL可以是其他应用，不局限于当前应用

- 重定向的响应头为302，且必须有Location响应头

- 重定向不再使用response.getWriter()或response.getOutputStream()输出数据，否则会出现 异常

  ```java
  //重定向
          response.sendRedirect("/fourServlet");
  ```

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\164.png)

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\165.png)

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\166.png)

练习

####路径

####编码