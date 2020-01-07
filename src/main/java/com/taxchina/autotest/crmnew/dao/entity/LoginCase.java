package com.taxchina.autotest.crmnew.dao.entity;

public class LoginCase {
    private int caseId;
    private String loginName;
    private String loginPassword;
    private int loginSource;
    private String phoneNumber;
    private String loginExpect;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public int getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(int loginSource) {
        this.loginSource = loginSource;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLoginExpect() {
        return loginExpect;
    }

    public void setLoginExpect(String loginExpect) {
        this.loginExpect = loginExpect;
    }
}
