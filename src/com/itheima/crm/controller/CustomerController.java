package com.itheima.crm.controller;

import com.itheima.crm.pojo.BaseDict;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.Queryvo;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Value(value = "${crm.fromType}")
    private String source;

    @Value(value = "${crm.industryType}")
    private String industry;

    @Value(value = "${crm.levelType}")
    private String level;

    @Value("${page.size}")
    private int size;

    @Resource
    private CustomerService service;
//    条件栏查询数据
    @RequestMapping("list")
    public String listCustomer( Queryvo queryvo, Model model,Integer page) {
        List<BaseDict> sources= service.getBaseDict(source);
        List<BaseDict> industrys = service.getBaseDict(industry);
        List<BaseDict> levels = service.getBaseDict(level);
//        取得客户类型下拉框
        model.addAttribute("fromType", sources);
        model.addAttribute("industryType", industrys);
        model.addAttribute("levelType", levels);

//        分页

        Page<Customer> customerPage=service.getCustomerPage(queryvo, page);
        model.addAttribute("page", customerPage);
        model.addAttribute("custName", queryvo.getCustName());
        model.addAttribute("custSource", queryvo.getCustSource());
        model.addAttribute("custIndustry", queryvo.getCustIndustry());
        model.addAttribute("custLevel", queryvo.getCustLevel());
        return "customer" ;
    }
//    删除用户
    @RequestMapping("delete")
    public @ResponseBody String removeCustomer(Integer id) {
        service.remove(id);
        return "ok";
    }

    //    修改用户1:根据id查找用户
    @RequestMapping("edit")
    public @ResponseBody
    Customer edit(Integer id) {
        Customer customer=service.edit(id);
        return customer;
    }
    @RequestMapping("update")
    public @ResponseBody String update (Customer customer) {
        service.update(customer);

        return "ok";
    }

}
