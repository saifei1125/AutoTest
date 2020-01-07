package com.taxchina.autotest.crmnew.service.entity;
//合同
public class Contract {
    private String contractNo;
    private int customerId;
    private String customerName;
    private String suiteName;
    private String suiteType;
    private String actualAmount;
    private String actualEndTime;
    private int leaderId;
    private String leaderName;
    private int contractStatus;//审核状态   0草稿 1审核中 2 驳回 3 作废 4 审核通过 5 取消，6变更日期审核中，7变更日期审核通过，8日期审核拒绝
    private int serviceStatus;//合同状态，0未生效，1 服务中，2已失效
    private int collectionStatus;//到款状态  0 未到款 1 部分到款 2 款已齐
    private int paymentsFlag;//款项是否已交齐 0 未交齐 1 已交齐
    private int invoiceFlag;//是否已开发票0未开 1 已开

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(String suiteType) {
        this.suiteType = suiteType;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public int getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(int contractStatus) {
        this.contractStatus = contractStatus;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public int getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(int collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public int getPaymentsFlag() {
        return paymentsFlag;
    }

    public void setPaymentsFlag(int paymentsFlag) {
        this.paymentsFlag = paymentsFlag;
    }

    public int getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(int invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }
}
