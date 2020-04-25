package com.sayyes.test;

import com.github.pagehelper.PageInfo;
import com.sayyes.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author sayyes
 * @date 2020/4/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
    //传入springmvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    //使用注解每次都初始化
    @Before
    public void initMokcMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //测试方法
    @Test
    public void testPage() throws Exception {
        System.out.println("11111111111111111111111111111111111111111");

//
//        //模拟请求拿到返回值
//        // uri=emps,param=pn{1}
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
//        //取请求对象
//        MockHttpServletRequest request = result.getRequest();
//        //取请求对象
//        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
//
//        //分页信息
//        System.out.println("当前页码：" + pi.getPageNum());
//        System.out.println("总页码：" + pi.getPages());
//        System.out.println("总记录数:" + pi.getTotal());
//
//        //员工数据
//        List<Employee> list = pi.getList();
//        for (Employee employee : list) {
//            System.out.println(employee.toString());
//        }

    }

}
