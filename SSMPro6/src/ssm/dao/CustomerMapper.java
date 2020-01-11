package ssm.dao;

import ssm.entity.Customer;

public interface CustomerMapper {

	/**
	 * 添加一个客户
	 */
	public void insertCustomer(Customer customer);
}
