package com.taxchina.autotest.crmnew.service.entity;
//回款
public class Finance {
    private String collectionNo;
    private String invoiceDate;
    private String applyUserId;
    private String userName;
    private String businessUserId;
    private int custType;
    private String paymentName;
    private String amount;
    private String gatheringType;//收款方式 1 转账 2 现金 3 支票
    private String collectionDate;
    private String businessUserName;
    private String noticeFlag;//是否通知业务员 1 是 2  否
    private String businessCompanyName;
    private String gatheringFinance;//收款财务
    private int invoiceFlag;//是否已开票 0 否 1 是
    private String suitetType;
    private String contractLabel;
    private String remark;
    private String invoiceNo;
    private String params;
    private String id;
    private String createUserId;
    private String createBy;
    private String gatheringFinaceId;
    private String businessDeptId;//业务员所在部门
    private String businessCompanyId;
    private int status;//回款状态
    private String deptId;//申请人所在部门ID
    private int contractFlag;//回款是否关联合同：0未关联 1 已关联
    private int rejectStatus;//0 正常，1 表示业绩拒绝了,2自己撤销


    public String getCollectionNo() {
        return collectionNo;
    }

    public void setCollectionNo(String collectionNo) {
        this.collectionNo = collectionNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(String businessUserId) {
        this.businessUserId = businessUserId;
    }

    public int getCustType() {
        return custType;
    }

    public void setCustType(int custType) {
        this.custType = custType;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getGatheringType() {
        return gatheringType;
    }

    public void setGatheringType(String gatheringType) {
        this.gatheringType = gatheringType;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getBusinessUserName() {
        return businessUserName;
    }

    public void setBusinessUserName(String businessUserName) {
        this.businessUserName = businessUserName;
    }

    public String getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(String noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getBusinessCompanyName() {
        return businessCompanyName;
    }

    public void setBusinessCompanyName(String businessCompanyName) {
        this.businessCompanyName = businessCompanyName;
    }

    public String getGatheringFinance() {
        return gatheringFinance;
    }

    public void setGatheringFinance(String gatheringFinance) {
        this.gatheringFinance = gatheringFinance;
    }

    public int getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(int invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public String getSuitetType() {
        return suitetType;
    }

    public void setSuitetType(String suitetType) {
        this.suitetType = suitetType;
    }

    public String getContractLabel() {
        return contractLabel;
    }

    public void setContractLabel(String contractLabel) {
        this.contractLabel = contractLabel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getGatheringFinaceId() {
        return gatheringFinaceId;
    }

    public void setGatheringFinaceId(String gatheringFinaceId) {
        this.gatheringFinaceId = gatheringFinaceId;
    }

    public String getBusinessDeptId() {
        return businessDeptId;
    }

    public void setBusinessDeptId(String businessDeptId) {
        this.businessDeptId = businessDeptId;
    }

    public String getBusinessCompanyId() {
        return businessCompanyId;
    }

    public void setBusinessCompanyId(String businessCompanyId) {
        this.businessCompanyId = businessCompanyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public int getContractFlag() {
        return contractFlag;
    }

    public void setContractFlag(int contractFlag) {
        this.contractFlag = contractFlag;
    }

    public int getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(int rejectStatus) {
        this.rejectStatus = rejectStatus;
    }
}
