package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Contract;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;

public class RelevanceInvoice {
    public void testRelevanceInvoice(User user, Invoice invoice, Contract contract){
        String url = TEST_DOMAIN+ "/crm/contract/relevanceInvoice";
        String param ="id="+invoice.getId()+"&invoiceNo="+invoice.getInvoiceNo()+"&contractNo="+contract.getContractNo()+"&amount=10000";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        Log.info(res);
        if(res != null) {
            JSONObject responseBody = JSONObject.parseObject(res);
            Assert.assertEquals(responseBody.getString("code"), "0");
            Log.info("----发票绑定成功-----");
        }
    }
}
