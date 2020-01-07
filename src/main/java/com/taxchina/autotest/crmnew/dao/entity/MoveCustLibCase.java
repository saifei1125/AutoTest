package com.taxchina.autotest.crmnew.dao.entity;

public class MoveCustLibCase {
    private  int caseId;
    private  int libId;
    private  String libName;
    private  String params;
    private int isMainLkm;
    private String addLinkmanExpect;

    public String getAddLinkmanExpect() {
        return addLinkmanExpect;
    }

    public void setAddLinkmanExpect(String addLinkmanExpect) {
        this.addLinkmanExpect = addLinkmanExpect;
    }

    public int getIsMainLkm() {
        return isMainLkm;
    }

    public void setIsMainLkm(int isMainLkm) {
        this.isMainLkm = isMainLkm;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getLibName() {
        return libName;
    }

    public int getLibId() {
        return libId;
    }

    public void setLibId(int libId) {
        this.libId = libId;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
