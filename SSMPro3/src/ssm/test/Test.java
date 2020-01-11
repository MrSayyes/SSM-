package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// ����spring�����ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ��ȡ����
		CustomerMapper customerMapper = (CustomerMapper) ac.getBean("customerMapper");
		// ���÷���
		Customer customer = new Customer();
		customer.setName("С7");
		customer.setTelephone("555");
		customer.setAddress("�й��ɶ�");
		customerMapper.insertCustomer(customer);
	}
}
