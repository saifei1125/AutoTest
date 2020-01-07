package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.ConfirmInvoiceCase;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class ConfirmInvoice {
    public void testConfirmBilling(User user, Invoice invoice){
        String url = TEST_DOMAIN + "/crm/invoice/confirmBilling";
        JSONObject jsonObject = new JSONObject();
        List<String> list = new ArrayList<>();
        JSONObject jo = new JSONObject();
        jo.put("invoiceNo",invoice.getInvoiceNo());
        jo.put("invoiceAmount","97");
        jo.put("invoiceTaxRate","0.03");
        jo.put("invoiceTaxAmount","3");
        jo.put("invoiceNoTaxAmount","100");
        jo.put("invoiceReceivedPaymentFlag","2");
        jo.put("remark","测试开票");
        list.add(jo.toJSONString());
        jsonObject.put("crmInvoiceInformationList",list.toString());
        jsonObject.put("invoiceId",invoice.getId());
        EncodeConvert encodeConvert = new EncodeConvert();
        String json = encodeConvert.urlEncoder(jsonObject.toJSONString(),"utf-8");
        String param = "para="+json;
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
        Log.info("审核发票返回结果：" + res);
        JSONObject responseBody = JSONObject.parseObject(res);
        Assert.assertEquals(responseBody.getString("code"), "0");
    }

    //审核通过
    public String confirmInvoiceByCase(User user, ConfirmInvoiceCase confirmInvoiceCase){
        String url = TEST_DOMAIN + "/crm/invoice/confirmBilling";
        JSONObject jsonObject = new JSONObject(true);
        List<String> list = new ArrayList<>();
        JSONObject jo = new JSONObject(true);
        jo.put("invoiceNo",confirmInvoiceCase.getInvoiceNo());
        jo.put("invoiceAmount",confirmInvoiceCase.getInvoiceAmount());
        jo.put("invoiceTaxRate",confirmInvoiceCase.getInvoiceTaxRate());
        jo.put("invoiceTaxAmount",confirmInvoiceCase.getInvoiceTaxAmount());
        jo.put("invoiceNoTaxAmount",confirmInvoiceCase.getInvoiceNoTaxAmount());
        jo.put("invoiceReceivedPaymentFlag",confirmInvoiceCase.getInvoiceReceivedPaymentFlag());
        jo.put("remark",confirmInvoiceCase.getConfirmRemark());
        list.add(jo.toJSONString());
        jsonObject.put("crmInvoiceInformationList",list.toString());
        jsonObject.put("invoiceId",confirmInvoiceCase.getInvoiceId());
        Log.info(jsonObject.toJSONString());
        EncodeConvert encodeConvert = new EncodeConvert();
        String json = encodeConvert.urlEncoder(jsonObject.toJSONString(),"utf-8");
        String param = "para="+json;
        String res =  HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
        return res;
    }
    //审核拒绝
    public String dismissedInvoiceByCase(User user,ConfirmInvoiceCase confirmInvoiceCase){
        String url = TEST_DOMAIN + "/crm/invoice/dismissedBilling";
        String param = "rejectReason="+confirmInvoiceCase.getRejectReason()+"&id="+confirmInvoiceCase.getInvoiceId();
        String res =  HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
        return res;
    }
    //作废
    public String obsoleteInvoiceByCase(User user,ConfirmInvoiceCase confirmInvoiceCase){
        String url = TEST_DOMAIN + "/crm/invoice/obsoleteBilling";
        String param = "ids="+confirmInvoiceCase.getInvoiceId();
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
        return res;
    }

}
