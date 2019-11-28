package com.demo.easyexcel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

public class ComplexHeadData {

    @ExcelProperty({ "主标题", "字符串标题" })
    private String string;
    @ExcelProperty({ "主标题", "日期标题" })
    private Date date;
    @ExcelProperty({ "主标题", "数字标题" })
    private Double doubleData;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }

}
