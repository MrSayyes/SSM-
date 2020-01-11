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
		// ���������ļ�
		Reader reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		// ����SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// ��SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		// ��ȡMapper�ӿڶ���
		CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
		// ����
		Customer customer = new Customer();
		customer.setName("����");
		customer.setTelephone("222");
		customer.setAddress("�й�����");
		customerMapper.insertCustomer(customer);
		// �ύ����
		sqlSession.commit();
		// �ر���Դ
		sqlSession.close();
	}
}
