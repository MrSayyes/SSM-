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
		customer.setName("李四");
		customer.setTelephone("222");
		customer.setAddress("中国北京");
		customerMapper.insertCustomer(customer);
		// 提交事务
		sqlSession.commit();
		// 关闭资源
		sqlSession.close();
	}
}
