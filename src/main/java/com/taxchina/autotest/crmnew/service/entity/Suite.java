package com.taxchina.autotest.crmnew.service.entity;

import java.util.List;

//产品套餐
public class Suite {
    private String suiteId;
    private String suiteName;
    private String suiteDesc;
    private String suitePrice;
    private String suiteType;
    private String templateId;
    private List<Product> productList;

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

    public String getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(String suitePrice) {
        this.suitePrice = suitePrice;
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
