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
	// ע��Mapper����
	@Resource
	private CustomerMapper customerMapper;

	public void insertCustomer(Customer customer) {
		// �÷�������������insert֮������쳣������������Ƿ�����
		customerMapper.insertCustomer(customer);
	}
}
