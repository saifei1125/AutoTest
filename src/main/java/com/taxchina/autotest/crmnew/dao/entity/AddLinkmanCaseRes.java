package com.taxchina.autotest.crmnew.dao.entity;

public class AddLinkmanCaseRes {
    private int resId;
    private int caseId;
    private int linkmanId;
    private String result;
    private String creatTime;
    private String updateTime;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(int linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
}
