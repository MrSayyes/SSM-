package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;
import ssm.service.CustomerService;

class Test {
	@org.junit.jupiter.api.Test
	void test() {
		// ����spring�����ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// beanֵȡ��@serviceע���࣬���@service�������֣���������������ĸСд
		CustomerService customerService = (CustomerService) ac.getBean("customerService");
		Customer customer = new Customer();
		customer.setName("С9");
		customer.setTelephone("7777");
		customer.setAddress("�й�����");
		customerService.insertCustomer(customer);
	}
}
