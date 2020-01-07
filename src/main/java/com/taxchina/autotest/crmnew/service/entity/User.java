package com.taxchina.autotest.crmnew.service.entity;

import java.util.List;
import java.util.Map;

//用户
public class User {
    private String username;
    private String password;
    private Map<String,String> headers;
    private List<CustLib> custLibs;
    private String custlib;
    private int userId;
    private String loginName;
    private String phonenumber;
    private int deptId;
    private String deptName;
    private int secLevelDeptId;
    private String secLevelDeptName;
    private String creatTime;
    private String updateTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<CustLib> getCustLibs() {
        return custLibs;
    }

    public void setCustLibs(List<CustLib> custLibs) {
        this.custLibs = custLibs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getSecLevelDeptId() {
        return secLevelDeptId;
    }

    public void setSecLevelDeptId(int secLevelDeptId) {
        this.secLevelDeptId = secLevelDeptId;
    }

    public String getSecLevelDeptName() {
        return secLevelDeptName;
    }

    public void setSecLevelDeptName(String secLevelDeptName) {
        this.secLevelDeptName = secLevelDeptName;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustlib() {
        return custlib;
    }

    public void setCustlib(String custlib) {
        this.custlib = custlib;
    }
}
