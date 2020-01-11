# 整合Spring

## 2.0拷贝一份第一部分的工程

**<u>——第一部分工程包为SSMPro1</u>**

## 2.1导入必须的包（mybatis-spring）

Mybstis-Spring的jar包下载地址：
https://mvnrepository.com/artifact/org.mybatis/mybatis-spring
Spring自身包下载地址：
https://repo.spring.io/release/org/springframework/spring/

![img](file:///C:\Users\sayyes\AppData\Local\Temp\ksohtml11044\wps1.jpg)

## 2.2MyBatis整合Spring的三种方式

### 2.2.1有Mapper实现类

**<u>——资源见SSMPro2工程</u>**

#### 2.2.1.1接口实现类新增CustomerMapperImpl.java

```java
package ssm.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

public class CustomerMapperImpl extends SqlSessionDaoSupport implements CustomerMapper {

	@Override
	public void insertCustomer(Customer customer) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("insertCustomer", customer);
        // 这里不需要事务提交，因为spring过程会处理
	}
}

```

#### 2.2.1.2编写applicationContext.xml

——引入连接池jar包，用于连接池配置

![img](file:///C:\Users\sayyes\AppData\Local\Temp\ksohtml11044\wps2.jpg)

——删除sqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
   	http://www.springframework.org/schema/context 
   	http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
   	http://www.springframework.org/schema/aop/spring-aop.xsd
   	http://www.springframework.org/schema/tx
   	http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- 创建连接池-数据源DataSource -->
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="url" value="jdbc:mysql://localhost:3306/ssm?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true" />
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<!-- 创建SqlsessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 关联连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 加载sql文件，支持星号通配符 -->
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
	</bean>
	<!-- 创建实现类，注入SqlSessionFactory -->
	<bean id="customerMapper" class="ssm.dao.impl.CustomerMapperImpl">
		<!-- 关联sqlSessionFactory -->
		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
	</bean>
</beans>
```

#### 2.2.1.3编写测试类

```java
package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// 加载spring配置文件
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取对象
		CustomerMapper customerMapper = (CustomerMapper) ac.getBean("customerMapper");
		// 调用方法
		Customer customer = new Customer();
		customer.setName("小刘");
		customer.setTelephone("444");
		customer.setAddress("中国武汉");
		customerMapper.insertCustomer(customer);
	}
}
```

### 2.2.2无Mapper实现类

<u>**——在SSMPro2工程之上处理，资源路径见SSMPro3**</u>

#### 2.2.2.1删除实现类CustomerMapperImpl.java

——基于SSMPro2工程，这个是当时针存在Mapper实现类的处理

#### 2.2.2.2配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
   	http://www.springframework.org/schema/context 
   	http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
   	http://www.springframework.org/schema/aop/spring-aop.xsd
   	http://www.springframework.org/schema/tx
   	http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- 创建连接池-数据源DataSource -->
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="url" value="jdbc:mysql://localhost:3306/ssm?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true" />
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<!-- 创建SqlsessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 关联连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 加载sql文件，支持星号通配符 -->
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
	</bean>
	
	<!-- 配置Mapper接口 -->
	<bean id="customerMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
		<!-- 关联Mapper接口 -->
		<property name="mapperInterface" value="ssm.dao.CustomerMapper"/>
		<!-- 关联sqlSessionFactory -->
		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
	</bean>
</beans>
```

#### 2.2.2.3编写测试类

```java
package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// 加载spring配置文件
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取对象
		CustomerMapper customerMapper = (CustomerMapper) ac.getBean("customerMapper");
		// 调用方法
		Customer customer = new Customer();
		customer.setName("小7");
		customer.setTelephone("555");
		customer.setAddress("中国成都");
		customerMapper.insertCustomer(customer);
	}
}

```

### 2.2.3Mapper接口扫描（推荐）

<u>**——在SSMPro3工程之上处理，资源路径见SSMPro4**</u>

#### 2.2.3.1配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
   	http://www.springframework.org/schema/context 
   	http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
   	http://www.springframework.org/schema/aop/spring-aop.xsd
   	http://www.springframework.org/schema/tx
   	http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- 创建连接池-数据源DataSource -->
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="url" value="jdbc:mysql://localhost:3306/ssm?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true" />
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<!-- 创建SqlsessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 关联连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 加载sql文件，支持星号通配符 -->
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
	</bean>
	
	<!-- Mapper接口扫描 -->
    <!-- 注意：如果使用扫描，那么每个Mapper接口在Spring容器的id为类名：如CustomerMapper->customerMapper -->
 	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 		<!-- 配置Mapper接口所在包路径 -->
 		<property name="basePackage" value="ssm.dao"/>
	</bean>
</beans>
```

#### 2.2.3.2编写测试类

```java
package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// 加载spring配置文件
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取对象
		CustomerMapper customerMapper = (CustomerMapper) ac.getBean("customerMapper");
		// 调用方法
		Customer customer = new Customer();
		customer.setName("小8");
		customer.setTelephone("6666");
		customer.setAddress("中国南京");
		customerMapper.insertCustomer(customer);
	}
}

```

## 2.3整合JDBC事务管理

<u>**——基于SSMPro4项目拷贝，资源见SSMPro5**</u>

### 2.3.1修改applicationContext.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
   	http://www.springframework.org/schema/context 
   	http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
   	http://www.springframework.org/schema/aop/spring-aop.xsd
   	http://www.springframework.org/schema/tx
   	http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- 创建连接池-数据源DataSource -->
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="url" value="jdbc:mysql://localhost:3306/ssm?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true" />
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<!-- 创建SqlsessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 关联连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 加载sql文件，支持星号通配符 -->
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
	</bean>
	
	<!-- Mapper接口扫描 -->
	<!-- 注意：如果使用扫描，那么每个Mapper接口在Spring容器的id为类名：如CustomerMapper->customerMapper -->
 	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 		<!-- 配置Mapper接口所在包路径 -->
 		<property name="basePackage" value="ssm.dao"/>
	</bean>
	
	<!-- 开启Spring的IOC注解扫描 -->
	<context:component-scan base-package="ssm"/>
	
	<!-- 开启Spring的事务 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
	<!-- 启用Spring事务注解 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```

### 2.3.2新增业务接口CustomerService.java

```java
package ssm.service;

import ssm.entity.Customer;

public interface CustomerService {
	public void insertCustomer(Customer customer);
}

```

### 2.3.3新增接口实现类CustomerServiceImpl.java添加注解

```java
package ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;
import ssm.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	// 注入Mapper对象
	@Resource
	private CustomerMapper customerMapper;

	public void insertCustomer(Customer customer) {
        // 该方法可以在两个insert之间添加异常来看事物管理是否正常
		customerMapper.insertCustomer(customer);
	}
}

```

### 2.3.4编写测试类

```java
package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;
import ssm.service.CustomerService;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// 加载spring配置文件
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// bean值取自@service注解类，如果@service不加名字，就是类名，首字母小写
		CustomerService customerService = (CustomerService) ac.getBean("customerService");
		Customer customer = new Customer();
		customer.setName("小9");
		customer.setTelephone("7777");
		customer.setAddress("中国深圳");
		customerService.insertCustomer(customer);
	}
}

```

