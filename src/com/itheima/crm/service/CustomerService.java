package com.itheima.crm.service;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.pojo.BaseDict;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.Queryvo;
import com.itheima.crm.utils.Page;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    @Resource
    private BaseDictDao baseDictDao;


    public List<BaseDict> getBaseDict(String source) {
      return baseDictDao.getBaseDict(source);
    }

    public Page<Customer> getCustomerPage(Queryvo queryvo, Integer page) {
        Page<Customer> customerPage = new Page<>();
        customerPage.setSize(7);
        if (null==page) {
            page=1;
        }
        queryvo.setCustName(queryvo.getCustName().trim());
        queryvo.setStart((page-1)*customerPage.getSize());
        queryvo.setSize(customerPage.getSize());
        Integer total = customerDao.getTotal(queryvo);
        List<Customer> rows = customerDao.getRows(queryvo);
        customerPage.setRows(rows);
        customerPage.setTotal(total);
        customerPage.setPage(page);
        return customerPage;
    }

    public void remove(Integer id) {
        customerDao.removeCustomer(id);
    }

    public Customer edit(Integer id) {
        return customerDao.edit(id);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
