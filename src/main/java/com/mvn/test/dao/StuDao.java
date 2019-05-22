package com.mvn.test.dao;

import org.apache.ibatis.annotations.Param;

import com.mvn.test.entity.Stu;

public interface StuDao {

    Stu getStu(@Param("id")int id);
    
}
