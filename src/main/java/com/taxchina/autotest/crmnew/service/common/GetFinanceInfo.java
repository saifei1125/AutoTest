package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;
import static com.taxchina.autotest.crmnew.service.utils.RestAssuredUtil.postRequestWithHeadersAndStringParameters;

public class GetFinanceInfo {
    public Finance getFinanceNo(User user){
        Finance finance = new Finance();
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN+"/crm/finance/add";
        String response = HttpClientUtil.doGet(url,headers,"utf-8");
//        Log.info(response);
        Document doc = Jsoup.parse(response);
//        Element span = doc.getElementsByTag("span").first();
//        String collectionNo = span.text();
//        Log.info(collectionNo);
        Elements conNo = doc.select("input[name=collectionNo]");
        String collectionNo = conNo.attr("value");
        finance.setCollectionNo(collectionNo);

        Elements input = doc.select("input[name=businessCompanyName]");
        String companyName = input.attr("value");
//        Log.info(companyName);
        finance.setBusinessCompanyName(companyName);

        Elements input1 = doc.select("input[name=gatheringFinance]");
        String gatheringFinance = input1.attr("value");
//        Log.info(gatheringFinance);
        finance.setGatheringFinance(gatheringFinance);
        return finance;
    }

    public List<Finance> getFinanceInfo(User user){
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN + "/crm/finance/list";
        String param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = doPost(url, param, headers, "utf-8");
        JSONObject res1 = JSONObject.parseObject(res);
        String res2 = res1.getString("rows");
//        Log.info(res2);
        List<Finance> list = JSONObject.parseArray(res2,Finance.class);
        return list;
    }

    public List<Finance> getFinanceInfoNew(User user){
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN + "/crm/finance/list";
        String param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
//        String res = doPost(url, param, headers, "utf-8");
        Response response = postRequestWithHeadersAndStringParameters(url,param,headers);
        JSONObject res1 = JSONObject.parseObject(response.getBody().print());
        String res2 = res1.getString("rows");
//        Log.info(res2);
        List<Finance> list = JSONObject.parseArray(res2,Finance.class);
        return list;
    }
}
