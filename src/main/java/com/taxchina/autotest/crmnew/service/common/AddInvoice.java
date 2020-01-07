package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCase;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class AddInvoice {
    public CloseableHttpResponse addInvoiceByCase(User user, AddInvoiceCase addInvoiceCase){
        Map<String, String> headers = user.getHeaders();
        Iterator<Map.Entry<String,String>> it = headers.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> item = it.next();
            String s = item.getKey();
            if(s.equals("Content-Type")){
                it.remove();
            }
        }
        String url = TEST_DOMAIN + "/crm/invoice/add";
        Map<String,String> entity = new LinkedHashMap<>();
        entity.put("invoiceNo",addInvoiceCase.getInvoiceNo());
        entity.put("contractNo","");
        entity.put("customerId",String.valueOf(addInvoiceCase.getCustomerId()));
        entity.put("customerNo","");
        entity.put("applyDate",addInvoiceCase.getApplyDate());
        entity.put("customerName",addInvoiceCase.getCustomerName());
        entity.put("applyUserName",addInvoiceCase.getApplyUserame());
        entity.put("invoiceType",String.valueOf(addInvoiceCase.getInvoiceType()));
        entity.put("invoiceProject",String.valueOf(addInvoiceCase.getInvoiceProject()));
        entity.put("custType",String.valueOf(addInvoiceCase.getCustType()));
        entity.put("invoiceLabel",String.valueOf(addInvoiceCase.getInvoiceLabel()));
        entity.put("invoiceTitle",addInvoiceCase.getInvoiceTitle());
        entity.put("tin",addInvoiceCase.getTin());
        entity.put("applyAmount",addInvoiceCase.getApplyAmount());
        entity.put("openingBank",addInvoiceCase.getOpeningBank());
        entity.put("openingBankAccount",addInvoiceCase.getOpeningBankAccount());
        entity.put("businessAddress",addInvoiceCase.getBusinessAddress());
        entity.put("businessTel",addInvoiceCase.getBusinessTel());
        entity.put("siuteType",addInvoiceCase.getSiuteType());
        entity.put("arrivalMoneyFlag",String.valueOf(addInvoiceCase.getArrivalMoneyFlag()));
        entity.put("filePath\"; filename=\"","");
        entity.put("fileName","");
        entity.put("file","");
        entity.put("params[coreServices]","{}");
        CloseableHttpResponse httpResponse =  HttpClientUtil.doPost1(url, headers, entity,"utf-8");
        headers.put("Content-Type","application/x-www-form-urlencoded");
        return httpResponse;
    }

    public void testAddInvoice(User user, Customer customer, Invoice invoice){
        Map<String, String> headers = user.getHeaders();
        Iterator<Map.Entry<String,String>> it = headers.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> item = it.next();
            String s = item.getKey();
            if(s.equals("Content-Type")){
                it.remove();
            }
        }
        String url = TEST_DOMAIN + "/crm/invoice/add";
        Map<String,String> entity = new LinkedHashMap<>();
        entity.put("invoiceNo",invoice.getInvoiceNo());
        entity.put("contractNo","");
        entity.put("customerId",String.valueOf(customer.getCustId()));
        entity.put("customerNo","");
        entity.put("applyDate","2019-09-01");
        entity.put("customerName",customer.getCustName());
        entity.put("applyUserName",user.getUsername());
        entity.put("invoiceType","1");
        entity.put("invoiceProject","0");
        entity.put("custType","0");
        entity.put("invoiceLabel","0");
        entity.put("invoiceTitle","测试发票抬头");
        entity.put("tin","");
        entity.put("applyAmount",invoice.getAmount());
        entity.put("openingBank","");
        entity.put("openingBankAccount","");
        entity.put("businessAddress","");
        entity.put("businessTel","");
        entity.put("siuteType","BJK");
        entity.put("arrivalMoneyFlag","1");
        entity.put("filePath\"; filename=\"","");
        entity.put("fileName","");
        entity.put("file","");
        entity.put("params[coreServices]","{}");

        String res = HttpClientUtil.doPost(url, headers, entity,"utf-8");
        Log.info("添加发票请求返回结果："+ res);
        JSONObject responseBody = JSONObject.parseObject(res);
        Assert.assertEquals(responseBody.getString("code"), "0");
        headers.put("Content-Type","application/x-www-form-urlencoded");

    }
}
