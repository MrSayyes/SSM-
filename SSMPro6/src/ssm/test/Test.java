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
