package com.itheima.crm.dao;

import com.itheima.crm.pojo.BaseDict;

import java.util.List;

public interface BaseDictDao {
    List<BaseDict> getBaseDict(String id);
}
