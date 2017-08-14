package com.itheima.crm.dao;

import com.itheima.crm.pojo.BaseDict;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.Queryvo;

import java.util.List;

public interface CustomerDao {
    List<BaseDict> getBaseDict(String id);

    Integer getTotal(Queryvo queryvo);

    List<Customer> getRows(Queryvo queryvo);

    void removeCustomer(Integer id);

    Customer edit(Integer id);

    void update(Customer customer);
}
