package com.taxchina.autotest.crmnew.dao.entity;

public class ConfirmInvoiceCase {
    private int caseId;
    private String invoiceNo;
    private int invoiceId;
    private String invoiceAmount;
    private String confirmRemark;
    private String rejectReason;
    private String createTime;
    private String invoiceTaxRate;//开票税率
    private String invoiceTaxAmount;//税额
    private String invoiceNoTaxAmount;//不含税金额
    private int invoiceReceivedPaymentFlag;//是否到款（2到款 1未到款）

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getConfirmRemark() {
        return confirmRemark;
    }

    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInvoiceTaxRate() {
        return invoiceTaxRate;
    }

    public void setInvoiceTaxRate(String invoiceTaxRate) {
        this.invoiceTaxRate = invoiceTaxRate;
    }

    public String getInvoiceTaxAmount() {
        return invoiceTaxAmount;
    }

    public void setInvoiceTaxAmount(String invoiceTaxAmount) {
        this.invoiceTaxAmount = invoiceTaxAmount;
    }

    public String getInvoiceNoTaxAmount() {
        return invoiceNoTaxAmount;
    }

    public void setInvoiceNoTaxAmount(String invoiceNoTaxAmount) {
        this.invoiceNoTaxAmount = invoiceNoTaxAmount;
    }

    public int getInvoiceReceivedPaymentFlag() {
        return invoiceReceivedPaymentFlag;
    }

    public void setInvoiceReceivedPaymentFlag(int invoiceReceivedPaymentFlag) {
        this.invoiceReceivedPaymentFlag = invoiceReceivedPaymentFlag;
    }
}
