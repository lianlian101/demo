package com.mvn.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvn.test.dao.CompanyDao;
import com.mvn.test.entity.Company;
import com.mvn.test.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    
    @Override
    public List<Company> listCompany() {
        return companyDao.listCompany();
    }

}
