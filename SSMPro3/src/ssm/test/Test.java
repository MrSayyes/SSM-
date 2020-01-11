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
