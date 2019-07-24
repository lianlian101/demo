package com.mvn.test.dao;

import java.util.List;

import com.mvn.test.entity.Company;

public interface CompanyDao {

    //获取所有的公司及下的所有部门和部门中的所有人员
    List<Company> listCompany();
    
}
