package com.taxchina.autotest.crmnew.dao.entity;

import com.taxchina.autotest.crmnew.service.entity.Product;

import java.util.List;

public class SignContractCase {
    private int caseId;
    private int custId;
    private String custName;
    private String signTime;
    private String suiteId;
    private String suiteName;
    private String suiteDesc;
    private String suiteAmount;
    private String suiteType;
    private String templateId;
    private int lkmId;
    private String lkmName;
    private String lkmTel;
    private String reduceAmount;
    private String originalAmount;
    private String actualAmount;
    private String startTime;
    private String endTime;
    private String actualStartTime;
    private String actualEndTime;

    private List<Product> productList;
    private String productId;
    private String productName;
    private String productUnit;
    private String productPrice;
    private String productType;
    private int suiteProdNum;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getSuiteDesc() {
        return suiteDesc;
    }

    public void setSuiteDesc(String suiteDesc) {
        this.suiteDesc = suiteDesc;
    }

    public String getSuiteAmount() {
        return suiteAmount;
    }

    public void setSuiteAmount(String suiteAmount) {
        this.suiteAmount = suiteAmount;
    }

    public String getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(String suiteType) {
        this.suiteType = suiteType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public int getLkmId() {
        return lkmId;
    }

    public void setLkmId(int lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmTel() {
        return lkmTel;
    }

    public void setLkmTel(String lkmTel) {
        this.lkmTel = lkmTel;
    }

    public String getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(String reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSuiteProdNum() {
        return suiteProdNum;
    }

    public void setSuiteProdNum(int suiteProdNum) {
        this.suiteProdNum = suiteProdNum;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
