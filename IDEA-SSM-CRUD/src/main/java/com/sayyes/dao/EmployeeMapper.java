package com.sayyes.dao;

import com.sayyes.bean.Employee;
import com.sayyes.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    //连接员工和部门
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    //连接员工和部门
    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    //通过注解绑定接口
    @Select("Select * from tbl_emp where emp_id=#{empId}")
    Employee queryNameById(Integer empId);
}