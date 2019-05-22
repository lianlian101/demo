package com.mvn.test.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mvn.test.Application;
import com.mvn.test.dao.StuDao;
import com.mvn.test.entity.Stu;
import com.mvn.test.util.DateConvertUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class StuDaoTest {

    @Autowired
    private StuDao stuDao;
    
    @Test
    public void getStu(){
        try {
            Stu stu = stuDao.getStu(1);
            System.out.println(stu);
            System.out.println(DateConvertUtil.dateToStr(stu.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
