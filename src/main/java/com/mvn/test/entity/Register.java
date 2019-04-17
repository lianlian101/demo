package com.mvn.test.entity;

import java.io.Serializable;

public class Register implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id; 
    /**
     * 邮箱
     */
    private String email; 
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile; 
    /**
     * 公司全名
     */
    private String company_logofull; 
    /**
     * 公司简称
     */
    private String company_logogram; 
    /**
     * 公司联系人
     */
    private String linkman; 
    /**
     * 联系人职位
     */
    private String position; 
    /**
     * 纳税人识别号
     */
    private String company_code;
    /**
     * 公司营业执照照片地址
     */
    private String usiness_license_picture;
    /**
     * 注册步骤，0：未注册过；1：第一步完成；2：第二部完成；3：第三部完成
     */
    private Integer step;
    /**
     * 激活状态，0：已提交；1：通过未激活；2：未通过；3：激活
     */
    private Integer active_status;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getCompany_logofull() {
        return company_logofull;
    }
    public void setCompany_logofull(String company_logofull) {
        this.company_logofull = company_logofull;
    }
    public String getCompany_logogram() {
        return company_logogram;
    }
    public void setCompany_logogram(String company_logogram) {
        this.company_logogram = company_logogram;
    }
    public String getLinkman() {
        return linkman;
    }
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getUsiness_license_picture() {
        return usiness_license_picture;
    }
    public void setUsiness_license_picture(String usiness_license_picture) {
        this.usiness_license_picture = usiness_license_picture;
    }
    public String getCompany_code() {
        return company_code;
    }
    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }
    public Integer getStep() {
        return step;
    }
    public void setStep(Integer step) {
        this.step = step;
    }
    public Integer getActive_status() {
        return active_status;
    }
    public void setActive_status(Integer active_status) {
        this.active_status = active_status;
    }
    @Override
    public String toString() {
        return "Register [id=" + id + ", email=" + email + ", password=" + password + ", mobile=" + mobile + ", company_logofull=" + company_logofull + ", company_logogram=" + company_logogram
                + ", linkman=" + linkman + ", position=" + position + ", company_code=" + company_code + ", usiness_license_picture=" + usiness_license_picture + ", step=" + step + ", active_status="
                + active_status + "]";
    }
    
    
}
