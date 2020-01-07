package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCase;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import com.taxchina.autotest.crmnew.service.utils.RestAssuredUtil;
import io.restassured.response.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

//添加回款
public class AddFinance {
    public CloseableHttpResponse addFinanceByCase(User accountant, AddFinanceCase addFinanceCase){
        //由于方法有默认头信息，所以需要去掉头信息中的Content-Type
        Map<String, String> headers = accountant.getHeaders();
//        Iterator<Map.Entry<String,String>> it = headers.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry<String,String> item = it.next();
//            if(item.getKey().equals("Content-Type")){
//                it.remove();
//            }
//        }
        headers.remove("Content-Type");

        String url = TEST_DOMAIN+"/crm/finance/add";
        Map<String,String> entity = new LinkedHashMap<>();
        entity.put("collectionNo",addFinanceCase.getCollectionNo());
        entity.put("invoiceDate",addFinanceCase.getInvoiceDate());
        entity.put("applyUserId",addFinanceCase.getBusinessUserId());
        entity.put("userName",addFinanceCase.getBusinessUserName());
        entity.put("businessUserId",addFinanceCase.getBusinessUserId());
        entity.put("custType",String.valueOf(addFinanceCase.getCustType()));
        entity.put("paymentName",addFinanceCase.getPaymentName());
        entity.put("amount",addFinanceCase.getAmount());
        entity.put("gatheringType",String.valueOf(addFinanceCase.getGatheringType()));
        entity.put("collectionDate",addFinanceCase.getCollectionDate());
        entity.put("businessUserName",addFinanceCase.getBusinessUserName());
        entity.put("noticeFlag",addFinanceCase.getNoticeFlag());
        entity.put("businessCompanyName",addFinanceCase.getBusinessCompanyName());
        entity.put("gatheringFinance",addFinanceCase.getGatheringFinance());
        entity.put("invoiceFlag",String.valueOf(addFinanceCase.getInvoiceFlag()));
        entity.put("suitetType",addFinanceCase.getSuitetType());
        entity.put("contractLabel",String.valueOf(addFinanceCase.getContractLabel()));
        entity.put("remark",addFinanceCase.getRemark());
        entity.put("invoiceNo",addFinanceCase.getInvoiceNo());
        entity.put("params[coreServices]","{}");
        CloseableHttpResponse response = HttpClientUtil.doPost1(url, headers, entity,"utf-8");
        return response;
    }

    public Response addFinanceByCaseNew(User accountant, AddFinanceCase addFinanceCase){
        Map<String, String> headers = accountant.getHeaders();
        String url = TEST_DOMAIN+"/crm/finance/add";
        Map<String,String> entity = new LinkedHashMap<>();
        entity.put("collectionNo",addFinanceCase.getCollectionNo());
        entity.put("invoiceDate",addFinanceCase.getInvoiceDate());
        entity.put("applyUserId",addFinanceCase.getBusinessUserId());
        entity.put("userName",addFinanceCase.getBusinessUserName());
        entity.put("businessUserId",addFinanceCase.getBusinessUserId());
        entity.put("custType",String.valueOf(addFinanceCase.getCustType()));
        entity.put("paymentName",addFinanceCase.getPaymentName());
        entity.put("amount",addFinanceCase.getAmount());
        entity.put("gatheringType",String.valueOf(addFinanceCase.getGatheringType()));
        entity.put("collectionDate",addFinanceCase.getCollectionDate());
        entity.put("businessUserName",addFinanceCase.getBusinessUserName());
        entity.put("noticeFlag",addFinanceCase.getNoticeFlag());
        entity.put("businessCompanyName",addFinanceCase.getBusinessCompanyName());
        entity.put("gatheringFinance",addFinanceCase.getGatheringFinance());
        entity.put("invoiceFlag",String.valueOf(addFinanceCase.getInvoiceFlag()));
        entity.put("suitetType",addFinanceCase.getSuitetType());
        entity.put("contractLabel",String.valueOf(addFinanceCase.getContractLabel()));
        entity.put("remark",addFinanceCase.getRemark());
        entity.put("invoiceNo",addFinanceCase.getInvoiceNo());
        entity.put("params[coreServices]","{}");
//        CloseableHttpResponse response = HttpClientUtil.doPost1(url, headers, entity,"utf-8");
        Response response = RestAssuredUtil.postRequestWithHeadersAndMapParameters(url,headers,entity);
        return response;
    }


    public Finance testAddFinance(User accountant, Finance finance, User sell, String amount) {
        //由于方法有默认头信息，所以需要去掉头信息中的Content-Type
        Map<String, String> headers = accountant.getHeaders();
//        Iterator<Map.Entry<String,String>> it = headers.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry<String,String> item = it.next();
//            String s = item.getKey();
//            if(s.equals("Content-Type")){
//                it.remove();
//            }
//        }
        headers.remove("Content-Type");

        if(amount!= null) {
            finance.setAmount(amount);
        }else{
            finance.setAmount("10000");
        }

        String url = TEST_DOMAIN+"/crm/finance/add";
        Map<String,String> entity = new LinkedHashMap<>();
        entity.put("collectionNo",finance.getCollectionNo());
        entity.put("invoiceDate","");
        entity.put("applyUserId",String.valueOf(sell.getUserId()));
        entity.put("userName","");
        entity.put("businessUserId",String.valueOf(sell.getUserId()));
        entity.put("custType","0");
        entity.put("paymentName","测试回款信息");
        entity.put("amount",finance.getAmount());
        entity.put("gatheringType","0");
        entity.put("collectionDate","2019-11-20");
        entity.put("businessUserName",sell.getUsername());
        entity.put("noticeFlag","2");
        entity.put("businessCompanyName",finance.getBusinessCompanyName());
        entity.put("gatheringFinance",finance.getGatheringFinance());
        entity.put("invoiceFlag","0");
        entity.put("suitetType","1");
        entity.put("contractLabel","0");
        entity.put("remark","测试回款备注");
        entity.put("invoiceNo","");
        entity.put("params[coreServices]","{}");
        String res =HttpClientUtil.doPost(url, headers, entity,"utf-8");
        Log.info("添加回款请求返回结果："+ res);
        if(res != null) {
            JSONObject responseBody = JSONObject.parseObject(res);
            Assert.assertEquals(responseBody.getString("code"), "0");
            headers.put("Content-Type","application/x-www-form-urlencoded");
        }
        return finance;
    }
}
