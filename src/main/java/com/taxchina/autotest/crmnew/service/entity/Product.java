package com.taxchina.autotest.crmnew.service.entity;

public class Product {
    private int caseId;
    private String suiteId;
    private String prodId;
    private String prodName;
    private String prodUnit;
    private String prodSalePrice;
    private int suitProdNum;
    private String prodType;
    private String suiteName;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdSalePrice() {
        return prodSalePrice;
    }

    public void setProdSalePrice(String prodSalePrice) {
        this.prodSalePrice = prodSalePrice;
    }

    public int getSuitProdNum() {
        return suitProdNum;
    }

    public void setSuitProdNum(int suitProdNum) {
        this.suitProdNum = suitProdNum;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getSuitName() {
        return suiteName;
    }

    public void setSuitName(String suitName) {
        this.suiteName = suitName;
    }
}
