# 整合SpringMVC

## 3.0拷贝一份第二部分的整合jdbc事务的工程

<u>**——基于工程SSMPro5拷贝，见SSMPro6**</u>

## 3.1导入必须的包（spring-mvc）

Spring自身包下载地址：
https://repo.spring.io/release/org/springframework/spring/

![image](https://user-images.githubusercontent.com/19297162/72202314-d1ef0f80-3498-11ea-9e68-4bceb8c9dfd1.png)

## 3.2配置web.xml

——启动Spring，加载applicationContext.xml

——启动SpringMVC，加载spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<web-app version="3.0" xmlns="http://java.sun.com/xml/ns/javaee"  
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
	<display-name>SSMPro6</display-name>
	
	<!-- 启动SpringMVC -->
	<servlet>
		<!-- servlet-name可以自定义 -->
		<servlet-name>SpringMVC</servlet-name>
		<!--servlet-class中的值是spring-webmvc包提供的类，即前端控制器，用于控制所有请求 -->
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 进行初始化，配置SPringMVC环境位置，为classpath目录下名字为spring-mvc.xml的文件 -->
  		<init-param>
  			<param-name>contextConfigLocation</param-name>
  			<param-value>classpath:spring-mvc.xml</param-value>
  		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>SpringMVC</servlet-name>
		<!--url-pattern（重点）中有3个值，分别为/、 /*、 *.action  -->
		<url-pattern>*.action</url-pattern>
	</servlet-mapping>
	
	<!-- 启动spring -->
	<listener>
		<!-- 默认加载WEB-INF下面配置 -->
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<!-- 修改加载路径 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>
	
  	<welcome-file-list>
    	<welcome-file>index.html</welcome-file>
    	<welcome-file>index.htm</welcome-file>
    	<welcome-file>index.jsp</welcome-file>
    	<welcome-file>default.html</welcome-file>
   		<welcome-file>default.htm</welcome-file>
   		<welcome-file>default.jsp</welcome-file>
  	</welcome-file-list>
</web-app>

```

## 3.3配置spring-mvc.xml

——格式和spring的基本一致

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/mvc
   	http://www.springframework.org/schema/mvc/spring-mvc.xsd
   	http://www.springframework.org/schema/context 
   	http://www.springframework.org/schema/context/spring-context.xsd">

	<!-- 扫描Controller的所有包 -->
	<context:component-scan base-package="ssm.controller"/>
	
	<!-- 注解驱动 -->
	<mvc:annotation-driven></mvc:annotation-driven>

	<!-- 试图解析器：简化在Controller类编写的试图路径 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 前缀 -->
		<property name="prefix" value="/WEB-INF/jsp/"/>
		<!-- 后缀 -->
		<property name="suffix" value=".jsp"/>
	</bean>
</beans>
```

## 3.4编写Controller

——控制界面

```java
package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}

```

## 3.5编写页面test.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	测试SpringMVC是否可用
</body>
</html>
```

