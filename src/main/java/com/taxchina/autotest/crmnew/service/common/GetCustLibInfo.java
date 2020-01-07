package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.RestAssuredUtil.getRequestWithHeaders;

public class GetCustLibInfo {
    //查询客户库信息
    public List<CustLib> getCustLibs (User user) {
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN+"/crm/lib/libTreeData?askType=requireNum";
        String rs = HttpClientUtil.doGet(url, headers, "utf-8");
        Log.info(rs);
        List<CustLib> custLibs = JSONObject.parseArray(rs,CustLib.class);
        user.setCustLibs(custLibs);
        return custLibs;
    }
    public String getCustLib(User user) {
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN+"/crm/lib/libTreeData?askType=requireNum";
        String rs = HttpClientUtil.doGet(url, headers, "utf-8");
//        Log.info(rs);
        return rs;
    }
    public String getCustLibNew(User user) {
        Map<String,String> headers = user.getHeaders();
        String url = TEST_DOMAIN+"/crm/lib/libTreeData?askType=requireNum";
//        String rs = HttpClientUtil.doGet(url, headers, "utf-8");
//        Log.info(rs);
        Response response = getRequestWithHeaders(url,headers);
        return response.getBody().print();
    }
}
