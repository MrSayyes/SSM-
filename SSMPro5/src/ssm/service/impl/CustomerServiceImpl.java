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