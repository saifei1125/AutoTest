package com.taxchina.autotest.crmnew.dao.entity;

public class CheckCustCase {
    private int caseId;
    private String custName;
    private int custType;
    private String creatTime;
    private String checkcustExpect;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getCustType() {
        return custType;
    }

    public void setCustType(int custType) {
        this.custType = custType;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getCheckcustExpect() {
        return checkcustExpect;
    }

    public void setCheckcustExpect(String checkcustExpect) {
        this.checkcustExpect = checkcustExpect;
    }
}
