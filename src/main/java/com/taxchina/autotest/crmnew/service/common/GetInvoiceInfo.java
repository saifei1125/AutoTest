package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetInvoiceInfo {
    public Invoice getInvoiceInfo(User user, Customer customer){
        Invoice invoice = new Invoice();
        String url = TEST_DOMAIN + "/crm/invoice/add?id="+customer.getCustId()+"&contractNo=";
        String response = HttpClientUtil.doGet(url,user.getHeaders(),"utf-8");
        Document doc = Jsoup.parse(response);
        Elements input = doc.select("input[name=invoiceNo]");
        String invoiceNo = input.attr("value");
//        Log.info(invoiceNo);
        invoice.setInvoiceNo(invoiceNo);
        return invoice;
    }
    public List<Invoice> getInvoiceId(User user){
        String url = TEST_DOMAIN + "/crm/invoice/list";
        String param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
//        Log.info(res);
        JSONObject jo = JSON.parseObject(res);
        String res1 = jo.getString("rows");
        List<Invoice> invoiceList = JSONObject.parseArray(res1,Invoice.class);
        return invoiceList;
    }
}
