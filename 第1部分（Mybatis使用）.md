# Mybatis使用

## 1.0工程目录结构（最终运行成功）

——资源见SSMPro1工程

![image](https://user-images.githubusercontent.com/19297162/72202295-994f3600-3498-11ea-99ee-fa7f064f9fd8.png)

## 1.1导入必须的包

Mybstis下载地址：https://github.com/mybatis/mybatis-3/releases
Mysql下载地址：https://dev.mysql.com/downloads/connector/j/

![image](https://user-images.githubusercontent.com/19297162/72202298-9eac8080-3498-11ea-9241-e088c2df0570.png)

## 1.2建立log4j.properties（日志管理）

```xml
### direct log messages to stdout ###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} 5p %c{1}:%L - %m%n

### direct messages to file mylog.log ###
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=F:\mylog.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} 5p %c{1}:%L - %m%n

### set log levels and outType (console or file) ###
log4j.rootLogger=debug, stdout
```

## 1.3建立数据库和表

建库：create database ssm;

建表：create table customer(

id int(5) primary key auto_increment,

name char(10),

telephone char(11),

address char(100)

);

## 1.4建立实体类（Customer.java）

```java
package ssm.entity;

/**
 * 客户实体类
 * 
 * @author sayyes
 */
public class Customer {
	private int id;
	private String name;
	private String telephone;
	private String address;

	// 无参构造函数
	public Customer() {

	}

	// 有参构造函数
	public Customer(int id, String name, String telephone, String address) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", telephone=" + telephone + ", address=" + address + "]";
	}
}
```

## 1.5建立Mapper接口（CustomerMapper.java）

```java
package ssm.dao;

import ssm.entity.Customer;

public interface CustomerMapper {

	/**
	 * 添加一个客户
	 */
	public void insertCustomer(Customer customer);
}
```

## 1.6建立Sql映射文件（CustomerMapper.xml）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
 "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
 <!-- 该文件编写mybatis中的mapper接口里面的方法提供一个对应的语句 -->
<mapper namespace="ssm.dao.CustomerMapper">
	<!-- 通过namespace.id唯一处理sql -->
	<insert id="insertCustomer" parameterType="ssm.entity.Customer">
		insert into customer
		(
			name,
			telephone,
			address
		)
		values
		(
			#{name},
			#{telephone},
			#{address}
		);
	</insert>
</mapper>
```

## 1.7建立sqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
 PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
 "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<environments default="development">
		<!-- 可以根据environment配置多个数据库环境 -->
		<environment id="development">
			<!-- 事务提交方式：
				JDBC：利用jdbc方式处理事务（commit，rollback，close）
				MANAGED:将事务交由其他组件去托管（spring，jobss），默认关闭连接
			 -->
			<transactionManager type="JDBC" />
			<!-- 数据源类型：
				UNPOOLED:传统的JDBC模式，每次访问数据库均需要打开和关闭数据库，消耗性能
				POOLED:使用数据库连接池（和UNPOOLED相反）
				JNDI:从tomcat中获取内置的数据库连接池
			 -->
			<dataSource type="POOLED">
				<!-- 数据库配置 -->
				<property name="driver" value="com.mysql.cj.jdbc.Driver" />
				<property name="url" value="jdbc:mysql://localhost:3306/ssm?useSSL=false&amp;serverTimezone=UTC" />
				<property name="username" value="root" />
				<property name="password" value="root" />
			</dataSource>
		</environment>
	</environments>
	<mappers>
		<!-- 加载映射文件 -->
		<mapper resource="mapper/CustomerMapper.xml" />
	</mappers>
</configuration>
```

## 1.8编写测试类

```java
package ssm.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

class Test {
	@org.junit.jupiter.api.Test
	void test() throws IOException {
		// 加载配置文件
		Reader reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		// 创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 打开SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		// 获取Mapper接口对象
		CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
		// 操作
		Customer customer = new Customer();
		customer.setName("张三");
		customer.setTelephone("111");
		customer.setAddress("中国广州");
		customerMapper.insertCustomer(customer);
		// 提交事务
		sqlSession.commit();
		// 关闭资源
		sqlSession.close();
	}
}
```

