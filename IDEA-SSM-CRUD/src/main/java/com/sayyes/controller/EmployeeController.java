package com.sayyes.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sayyes.bean.Employee;
import com.sayyes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 处理员工的crud请求
 *
 * @author sayyes
 * @date 2020/4/25
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 查询员工数据（分页查询）
     *
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //处理分页
        //引入PageHelper分页插件
        //在查询之前只需要调用,这里每页查询5个
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟的查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //PageInfo包装查询后的内容,将pageInfo给界面就可以了呢（查询数据、分页信息）
        //PageInfo第二个传连续显示5页
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);//通过Model返回给界面
        return "list";//list页
    }
}
