package com.taxchina.autotest.crmnew.service.entity;

public class Invoice {
    private String invoiceNo;
    private int id;
    private String invoiceDate;
    private String amount;
    private String applyAmount;
    private String applyDate;
    private int applyUserId;
    private String applyUserName;
    private int arrivalMoneyFlag;
    private String businessAddress;
    private String businessTel;
    private String rejectReason;
    private int reviewId;
    private String reviewRemark;
    private String reviewer;
    private int status;//发票状态1待开发票 2 已开发票 3 作废 4 已驳回
    private int customerId;
    private String customerName;
    private int custType;
    private int invoiceLabel;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(int applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public int getArrivalMoneyFlag() {
        return arrivalMoneyFlag;
    }

    public void setArrivalMoneyFlag(int arrivalMoneyFlag) {
        this.arrivalMoneyFlag = arrivalMoneyFlag;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessTel() {
        return businessTel;
    }

    public void setBusinessTel(String businessTel) {
        this.businessTel = businessTel;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewRemark() {
        return reviewRemark;
    }

    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getCustType() {
        return custType;
    }

    public void setCustType(int custType) {
        this.custType = custType;
    }

    public int getInvoiceLabel() {
        return invoiceLabel;
    }

    public void setInvoiceLabel(int invoiceLabel) {
        this.invoiceLabel = invoiceLabel;
    }
}
