package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.SignInList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetSignInList {
    public List<SignInList> getSignInList(User user){
        String url = TEST_DOMAIN+"/api/course/signInList";
        Map<String,String> headers = user.getHeaders();
        headers.put("Content-Type","application/json");
        String httpResponse = HttpClientUtil.doPost(url,"",headers,"utf-8");
        JSONObject jsonRes = JSON.parseObject(httpResponse);
        String data = jsonRes.getString("data");
        List<SignInList> list = JSON.parseArray(data,SignInList.class);
        return list;
    }
}
