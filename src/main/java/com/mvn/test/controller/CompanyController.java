package com.mvn.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.entity.Company;
import com.mvn.test.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    
    @RequestMapping("/listCompany")
    @ResponseBody
    public List<Company> listCompany(){
        return companyService.listCompany();
    }
    
}
