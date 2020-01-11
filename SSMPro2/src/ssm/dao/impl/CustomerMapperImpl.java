package ssm.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ssm.dao.CustomerMapper;
import ssm.entity.Customer;

public class CustomerMapperImpl extends SqlSessionDaoSupport implements CustomerMapper {

	@Override
	public void insertCustomer(Customer customer) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("insertCustomer", customer);
		// 这里不需要事务提交，因为spring过程会处理
	}

}
