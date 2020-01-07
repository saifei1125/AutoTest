package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.SignContractCase;
import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class SignContract {
    public String signContractByCase(User user, SignContractCase signContractCase) {
        String signUrl = TEST_DOMAIN + "/crm/contract/signIndex";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        Log.info(df.format(new Date()));// new Date()为获取当前系统时间

        List<JSONObject> productsList = new ArrayList<>();
        List<Product> list = signContractCase.getProductList();
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            JSONObject products = new JSONObject(new LinkedHashMap());
            products.put("productId", p.getProdId());
            products.put("productName", p.getProdName());
            products.put("classService", 1);
            products.put("unit", p.getProdUnit());
            products.put("salePrice", p.getProdSalePrice());
            products.put("num", p.getSuitProdNum());
            products.put("realNum", p.getSuitProdNum());
            products.put("productType", p.getProdType());
            products.put("customerId", signContractCase.getCustId());
            productsList.add(i, products);
        }

        JSONObject contract = new JSONObject(new LinkedHashMap());
        contract.put("signTime", df.format(new Date()));
        contract.put("customerId", signContractCase.getCustId());
        contract.put("customerName", signContractCase.getCustName());
        contract.put("mergeFlg", 1);
        contract.put("suiteId", signContractCase.getSuiteId());
        contract.put("contractLabel", 0);
        contract.put("suiteName", signContractCase.getSuiteName());
        contract.put("suiteDesc", signContractCase.getSuiteDesc());
        contract.put("suiteAmount", signContractCase.getSuiteAmount());
        contract.put("originalAmount", signContractCase.getOriginalAmount());
        contract.put("actualAmount", signContractCase.getActualAmount());
        contract.put("suiteMin", 0);
        contract.put("suiteMax", 999999);
        contract.put("suiteType", signContractCase.getSuiteType());
        contract.put("reduceAmount", "");
        contract.put("templateId", signContractCase.getTemplateId());
        contract.put("linkmanId", signContractCase.getLkmId());
        contract.put("linkmanName", signContractCase.getLkmName());
        contract.put("linkmanTel", signContractCase.getLkmTel());
        contract.put("linkmanEmail","");
        contract.put("linkmanAddress", "");
        contract.put("contractType", 0);
        contract.put("adjustFlag", 1);
        contract.put("adjustReason", "");
        contract.put("incrAmount", 100);
        contract.put("clauseFlag", 0);
        contract.put("startTime", signContractCase.getStartTime());
        contract.put("endTime", signContractCase.getEndTime());
        contract.put("actualStartTime", signContractCase.getActualStartTime());
        contract.put("actualEndTime", signContractCase.getActualEndTime());
        contract.put("contractFileName", "");
        contract.put("contractFileSuffix", "");
        contract.put("contractFileDate", "");
        contract.put("contractFileOperator", "");

        JSONObject jsonObject = new JSONObject(new LinkedHashMap());
        jsonObject.put("products", productsList);
        jsonObject.put("contract", contract);

        EncodeConvert<String> encodeConvert = new EncodeConvert<>();
        String param = "para=" + encodeConvert.urlEncoder(jsonObject.toString(), "utf-8");
        Log.info("请求合同的参数：------" + jsonObject.toString());

        String response = HttpClientUtil.doPost(signUrl, param, user.getHeaders(), "utf-8");

        return response;
    }



    public Contract testSignContract(User user, Customer customer, Suite suite) {
        String signUrl = TEST_DOMAIN + "/crm/contract/signIndex";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        Log.info(df.format(new Date()));// new Date()为获取当前系统时间

        List<JSONObject> productsList = new ArrayList<>();
        List<Product> list = suite.getProductList();
        for(int i=0;i<list.size();i++ ){
            Product p = list.get(i);
            JSONObject products =new JSONObject(new LinkedHashMap());
            products.put("productId",p.getProdId());
            products.put("productName",p.getProdName());
            products.put("classService",1);
            products.put("unit",p.getProdUnit());
            products.put("salePrice",p.getProdSalePrice());
            products.put("num",p.getSuitProdNum());
            products.put("realNum",p.getSuitProdNum());
            products.put("productType",p.getProdType());
            products.put("customerId",customer.getCustId());
            productsList.add(i,products);
        }

        JSONObject contract = new JSONObject(new LinkedHashMap());
        contract.put("signTime",df.format(new Date()));
        contract.put("customerId",customer.getCustId());
        contract.put("customerName",customer.getCustName());
        contract.put("mergeFlg",1);
        contract.put("suiteId",suite.getSuiteId());
        contract.put("contractLabel",0);
        contract.put("suiteName",suite.getProductList().get(0).getSuitName());
        contract.put("suiteDesc",suite.getSuiteDesc());
        contract.put("suiteAmount",suite.getSuitePrice());
        contract.put("originalAmount",suite.getSuitePrice());
        contract.put("actualAmount",suite.getSuitePrice());
        contract.put("suiteMin",0);
        contract.put("suiteMax",999999);
        contract.put("suiteType",suite.getSuiteType());
        contract.put("reduceAmount","");
        contract.put("templateId",suite.getTemplateId());
        contract.put("linkmanId",customer.getLkms().get(0).getLkmId());
        contract.put("linkmanName",customer.getLkms().get(0).getLkmName());
        contract.put("linkmanTel",customer.getLkms().get(0).getLkmMobile());
        contract.put("linkmanEmail","");
        contract.put("linkmanAddress","");
        contract.put("contractType",0);
        contract.put("adjustFlag",1);
        contract.put("adjustReason","");
        contract.put("incrAmount",100);
        contract.put("clauseFlag",0);
        contract.put("startTime",df.format(new Date()));
        contract.put("endTime","2020-08-26");
        contract.put("actualStartTime",df.format(new Date()));
        contract.put("actualEndTime","2020-08-26");
        contract.put("contractFileName","");
        contract.put("contractFileSuffix","");
        contract.put("contractFileDate","");
        contract.put("contractFileOperator","");

        JSONObject jsonObject =  new JSONObject(new LinkedHashMap());
        jsonObject.put("products",productsList);
        jsonObject.put("contract",contract);

        EncodeConvert<String> encodeConvert = new EncodeConvert<>();
        String param = "para="+ encodeConvert.urlEncoder(jsonObject.toString(), "utf-8");
        Log.info("请求合同的参数：------"+jsonObject.toString());

        String res = HttpClientUtil.doPost(signUrl,param, user.getHeaders(), "utf-8");
        Log.info("签合同请求返回结果：" + res);
        Contract cont = new Contract();
        if(res != null) {
            JSONObject responseBody = JSONObject.parseObject(res);
            Assert.assertEquals(responseBody.getString("code"), "0");
            JSONObject jo = JSONObject.parseObject(responseBody.getString("data"));
            String contractNo = jo.getString("contractNo");
            cont.setContractNo(contractNo);
        }
        return cont;
    }
}
