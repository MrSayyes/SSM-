package com.sayyes.test;

import com.sayyes.bean.Department;
import com.sayyes.bean.Employee;
import com.sayyes.dao.DepartmentMapper;
import com.sayyes.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试dao层
 *
 * @author sayyes
 * @date 2020/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:dispatcherServlet-servlet.xml"})
public class MybatisTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    @Test
    public void test() {
        //传统方式：创建spring的ioc容器->获取mapper
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DepartmentMapper bean = ac.getBean(DepartmentMapper.class);
//        System.out.println(bean);//可以输出对象

        /*
            spring单元测试方式
            1、Maven引入SpringTest模块：添加注解
                @RunWith(SpringJUnit4ClassRunner.class)
                @ContextConfiguration(locations = {"classpath:applicationContext.xml"})
            2、然后就可以Autowired
         */
        //1、部门表测试
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //2、员工表测试
//        employeeMapper.insertSelective(new Employee(null, "张三", "M", "zhangsan@qq.com", 1));
//        employeeMapper.insertSelective(new Employee(null, "李四", "M", "lisi@qq.com", 2));
        //批量插入使用sqlsession
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i=0;i<10;i++){
//            mapper.insertSelective(new Employee(null, "z"+i, "F", "z"+i+"@qq.com", 2));
//        }

        //queryNameById接口注解测试
//        Employee employee = employeeMapper.queryNameById(2);
//        System.out.println(employee.getEmpName()+"|"+employee.getEmail());

        //分页sql
        List<Employee> employee = employeeMapper.selectByExample(null);
        for (Employee employee1 : employee) {
            System.out.println(employee1.toString());
        }

    }
}

